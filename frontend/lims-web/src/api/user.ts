/**
 * 用户相关请求模块
 */
import request from '@/utils/request'
import type { 
  IResponseData, 
  ILoginDTO, 
  IRefreshTokenDTO, 
  IAccessTokenVO, 
  ILoginResponseVO
} from './types/common'

/**
 * 用户登录
 */
export const login = (data: ILoginDTO) => {
  return request<IResponseData<ILoginResponseVO>>({
    url: '/sys/auth/login',
    method: 'POST',
    data
  })
}

/**
 * 刷新访问令牌
 */
export const refreshToken = (data: IRefreshTokenDTO) => {
  return request<IResponseData<IAccessTokenVO>>({
    url: '/auth/refresh',
    method: 'POST',
    data
  })
}

/**
 * 用户登出
 */
export const logout = (data: IRefreshTokenDTO) => {
  return request<IResponseData<null>>({
    url: '/auth/logout',
    method: 'POST',
    data
  })
}

/**
 * 注销所有设备
 */
export const logoutAll = () => {
  return request<IResponseData<null>>({
    url: '/auth/logout-all',
    method: 'POST'
  })
}


