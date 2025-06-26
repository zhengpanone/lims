// 存储键名常量
export const USER = 'user'
export const ACCESS_TOKEN = 'access_token'
export const REFRESH_TOKEN = 'refresh_token'
export const TOKEN_EXPIRES = 'token_expires'
export const USER_INFO = 'user_info'

// Token 相关常量
export const TOKEN_PREFIX = 'Bearer '
export const TOKEN_EXPIRES_BUFFER = 5 * 60 * 1000 // 5分钟缓冲时间

// HTTP 状态码
export const HTTP_STATUS = {
  UNAUTHORIZED: 401,
  FORBIDDEN: 403,
  NOT_FOUND: 404,
  INTERNAL_SERVER_ERROR: 500
}

// 业务状态码
export const BUSINESS_CODE = {
  SUCCESS: 0,
  TOKEN_EXPIRED: 410000,
  UNAUTHORIZED: 401,
  FORBIDDEN: 403
}
