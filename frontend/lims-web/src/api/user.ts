/**
 * 用户相关请求模块
 */
import request from '@/utils/request'
import type {
  IResponseData
} from './types/common'

import { User, IListPageParams } from './types/user'


export const getUserList = (params: IListPageParams) => {
  return request<
    IResponseData<{
      total: number
      list: User[]
    }>
  >({
    method: 'GET',
    url: '/sys/user/page',
    params: params,
  })
}

/**
 * 更新用户状态
 * @param id 用户ID
 * @param status 用户状态 0:禁用 1:启用
 * @returns
 */
export const updateUserStatus = (id: string, status: number) => {
  return request<IResponseData<null>>({
    method: 'PUT',
    url: `/sys/user/updateState`,
    data: {
      id,
      status
    }
  })
}
