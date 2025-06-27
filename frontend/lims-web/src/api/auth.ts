import { ILoginDTO, ILoginResponseVO, IAccessTokenVO, IRefreshTokenDTO, IResponseData } from './types/common'
import request from '@/utils/request'


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
    url: '/sys/auth/logout',
    method: 'POST',
    headers: {
      Authorization: 'Bearer ' + data.refreshToken,
    },
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
