# LIMS 前端 Token 管理系统

本文档介绍 LIMS 前端项目中 accessToken 和 refreshToken 的使用方法。

## 概述

本系统实现了基于 JWT 的双 token 认证机制：
- **accessToken**: 用于 API 请求认证，有效期较短（通常 15-30 分钟）
- **refreshToken**: 用于刷新 accessToken，有效期较长（通常 7-30 天）

## 核心组件

### 1. TokenManager 工具类 (`src/utils/token.ts`)

Token 管理的核心工具类，提供以下功能：

```typescript
import TokenManager from '@/utils/token'

// 保存用户认证信息
TokenManager.saveAuthInfo(authInfo)

// 更新访问令牌
TokenManager.updateAccessToken(tokenInfo)

// 获取访问令牌
const accessToken = TokenManager.getAccessToken()

// 获取刷新令牌
const refreshToken = TokenManager.getRefreshToken()

// 检查令牌是否即将过期
const isExpiring = TokenManager.isTokenExpiringSoon()

// 检查令牌是否已过期
const isExpired = TokenManager.isTokenExpired()

// 检查是否有有效的认证信息
const hasValidAuth = TokenManager.hasValidAuth()

// 清除所有认证信息
TokenManager.clearAuthInfo()

// 获取完整的认证头
const authHeader = TokenManager.getAuthHeader()
```

### 2. 用户状态管理 (`src/store/user.ts`)

基于 Pinia 的用户状态管理：

```typescript
import { useUserStore } from '@/store/user'

const userStore = useUserStore()

// 设置用户认证信息
userStore.setAuthInfo(authInfo)

// 更新访问令牌
userStore.updateAccessToken(accessToken, expiresIn)

// 清除用户认证信息
userStore.clearAuthInfo()

// 检查令牌是否有效
const isValid = userStore.isTokenValid()

// 获取用户信息
const userInfo = userStore.userInfo

// 获取认证头
const authHeader = userStore.authHeader
```

### 3. 认证 API (`src/api/user.ts`)

提供认证相关的 API 接口：

```typescript
import { login, refreshToken, logout, getCaptcha } from '@/api/user'

// 用户登录
const loginResponse = await login({
  username: 'admin',
  password: '123456',
  captcha: '1234',
  captchaId: 'captcha-id'
})

// 刷新访问令牌
const refreshResponse = await refreshToken({
  refreshToken: 'refresh-token-value'
})

// 用户登出
await logout({
  refreshToken: 'refresh-token-value'
})

// 获取验证码
const captchaResponse = await getCaptcha()
```

### 4. 请求拦截器 (`src/utils/request.ts`)

自动处理 token 刷新和认证：

- 在请求前检查 token 是否即将过期
- 自动刷新即将过期的 token
- 处理 401 错误，自动尝试刷新 token
- 刷新失败时跳转到登录页面

## 使用流程

### 1. 用户登录

```typescript
import { login } from '@/api/user'
import { useUserStore } from '@/store/user'

const userStore = useUserStore()

const handleLogin = async () => {
  try {
    const response = await login({
      username: 'admin',
      password: '123456'
    })
    
    if (response.code === 200 && response.data) {
      // 保存认证信息到状态管理和本地存储
      userStore.setAuthInfo(response.data)
      
      // 跳转到目标页面
      router.push('/dashboard')
    }
  } catch (error) {
    console.error('登录失败:', error)
  }
}
```

### 2. 自动 Token 刷新

系统会自动处理 token 刷新，无需手动干预：

1. **请求前检查**: 每次 API 请求前检查 token 是否即将过期
2. **自动刷新**: 如果即将过期，自动调用刷新接口
3. **更新存储**: 刷新成功后更新本地存储和状态管理
4. **重试请求**: 使用新的 token 重新发送原请求

### 3. 用户登出

```typescript
import { logout } from '@/api/user'
import { useUserStore } from '@/store/user'

const userStore = useUserStore()

const handleLogout = async () => {
  try {
    const refreshToken = userStore.getRefreshToken()
    if (refreshToken) {
      await logout({ refreshToken })
    }
  } catch (error) {
    console.error('登出失败:', error)
  } finally {
    // 清除本地认证信息
    userStore.clearAuthInfo()
    
    // 跳转到登录页面
    router.push('/login')
  }
}
```

### 4. 路由守卫

在路由守卫中检查用户认证状态：

```typescript
import { useUserStore } from '@/store/user'
import TokenManager from '@/utils/token'

router.beforeEach((to, from, next) => {
  const userStore = useUserStore()
  
  // 检查是否有有效的认证信息
  if (TokenManager.hasValidAuth()) {
    if (to.path === '/login') {
      // 已登录用户访问登录页面，重定向到首页
      next('/')
    } else {
      // 正常访问
      next()
    }
  } else {
    if (to.path === '/login') {
      // 未登录用户访问登录页面，允许访问
      next()
    } else {
      // 未登录用户访问其他页面，重定向到登录页面
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      })
    }
  }
})
```

## 配置说明

### 常量配置 (`src/utils/constants.ts`)

```typescript
// Token 相关常量
export const TOKEN_EXPIRES_BUFFER = 5 * 60 * 1000 // 5分钟缓冲时间

// 业务状态码
export const BUSINESS_CODE = {
  SUCCESS: 200,
  TOKEN_EXPIRED: 410000,
  UNAUTHORIZED: 401,
  FORBIDDEN: 403
}
```

### 类型定义 (`src/api/types/common.ts`)

```typescript
// 登录请求参数
export interface ILoginDTO {
  username: string
  password: string
  captcha?: string
  captchaId?: string
}

// 刷新令牌请求参数
export interface IRefreshTokenDTO {
  refreshToken: string
}

// 访问令牌响应
export interface IAccessTokenVO {
  accessToken: string
  tokenType: string
  expiresIn: number
}

// 登录响应
export interface ILoginResponseVO {
  accessToken: string
  refreshToken: string
  tokenType: string
  expiresIn: number
  userId: number
  username: string
  nickName: string
  email: string
  avatar: string
}

// 用户认证信息
export interface IUserAuthInfo {
  accessToken: string
  refreshToken: string
  expiresIn: number
  userId: number
  username: string
  nickName: string
  email: string
  avatar: string
}
```

## 安全特性

1. **自动刷新**: 无需用户干预，系统自动处理 token 刷新
2. **并发控制**: 防止多个请求同时刷新 token
3. **过期处理**: 自动检测和处理 token 过期情况
4. **安全存储**: token 存储在 localStorage 中，支持加密（可选）
5. **错误处理**: 完善的错误处理和用户提示

## 注意事项

1. **Token 存储**: 当前使用 localStorage 存储，生产环境建议考虑安全性
2. **刷新频率**: 避免过于频繁的 token 刷新，设置合理的缓冲时间
3. **错误处理**: 确保所有认证相关的错误都有适当的处理
4. **用户体验**: 在 token 刷新过程中提供适当的加载状态
5. **安全性**: 定期清理过期的 token，避免存储过多无用数据

## 故障排除

### 常见问题

1. **Token 刷新失败**
   - 检查 refreshToken 是否有效
   - 确认后端刷新接口是否正常
   - 检查网络连接

2. **重复刷新请求**
   - 确认并发控制机制是否正常工作
   - 检查 refreshPromise 变量是否正确管理

3. **认证状态不一致**
   - 检查 localStorage 中的数据是否完整
   - 确认状态管理是否正确同步

### 调试方法

```typescript
// 检查当前认证状态
console.log('Access Token:', TokenManager.getAccessToken())
console.log('Refresh Token:', TokenManager.getRefreshToken())
console.log('Token Expires:', TokenManager.getTokenExpires())
console.log('Is Valid:', TokenManager.hasValidAuth())
console.log('Is Expiring Soon:', TokenManager.isTokenExpiringSoon())
``` 