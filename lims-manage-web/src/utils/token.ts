import { getItem, setItem, removeItem } from './storage'
import { ACCESS_TOKEN, REFRESH_TOKEN, TOKEN_EXPIRES, USER_INFO } from './constants'
import type { IUserAuthInfo, IAccessTokenVO } from '@/api/types/common'

/**
 * Token 管理工具类
 */
export class TokenManager {
  /**
   * 保存用户认证信息
   */
  static saveAuthInfo(authInfo: IUserAuthInfo): void {
    const expiresTime = Date.now() + authInfo.expiresIn * 1000
    
    setItem(ACCESS_TOKEN, authInfo.accessToken)
    setItem(REFRESH_TOKEN, authInfo.refreshToken)
    setItem(TOKEN_EXPIRES, expiresTime.toString())
    setItem(USER_INFO, {
      userId: authInfo.userId,
      username: authInfo.username,
      nickName: authInfo.nickName,
      email: authInfo.email,
      avatar: authInfo.avatar
    })
  }

  /**
   * 更新访问令牌
   */
  static updateAccessToken(tokenInfo: IAccessTokenVO): void {
    const expiresTime = Date.now() + tokenInfo.expiresIn * 1000
    
    setItem(ACCESS_TOKEN, tokenInfo.accessToken)
    setItem(TOKEN_EXPIRES, expiresTime.toString())
  }

  /**
   * 获取访问令牌
   */
  static getAccessToken(): string | null {
    return getItem<string>(ACCESS_TOKEN)
  }

  /**
   * 获取刷新令牌
   */
  static getRefreshToken(): string | null {
    return getItem<string>(REFRESH_TOKEN)
  }

  /**
   * 获取令牌过期时间
   */
  static getTokenExpires(): number | null {
    const expiresStr = getItem<string>(TOKEN_EXPIRES)
    return expiresStr ? parseInt(expiresStr, 10) : null
  }

  /**
   * 获取用户信息
   */
  static getUserInfo(): any {
    return getItem(USER_INFO)
  }

  /**
   * 检查令牌是否即将过期
   */
  static isTokenExpiringSoon(bufferTime: number = 5 * 60 * 1000): boolean {
    const expiresTime = this.getTokenExpires()
    if (!expiresTime) return true
    
    return Date.now() + bufferTime >= expiresTime
  }

  /**
   * 检查令牌是否已过期
   */
  static isTokenExpired(): boolean {
    const expiresTime = this.getTokenExpires()
    if (!expiresTime) return true
    
    return Date.now() >= expiresTime
  }

  /**
   * 检查是否有有效的认证信息
   */
  static hasValidAuth(): boolean {
    const accessToken = this.getAccessToken()
    const refreshToken = this.getRefreshToken()
    
    return !!(accessToken && refreshToken && !this.isTokenExpired())
  }

  /**
   * 清除所有认证信息
   */
  static clearAuthInfo(): void {
    removeItem(ACCESS_TOKEN)
    removeItem(REFRESH_TOKEN)
    removeItem(TOKEN_EXPIRES)
    removeItem(USER_INFO)
  }

  /**
   * 获取完整的认证头
   */
  static getAuthHeader(): string | null {
    const token = this.getAccessToken()
    return token ? `Bearer ${token}` : null
  }
}

export default TokenManager 