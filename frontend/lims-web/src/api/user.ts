/**
 * 用户相关请求模块
 */
import request from '@/utils/request'
import type {
  IResponseData
} from './types/common'

import { User, IListPageParams, UserPostData } from './types/user'



/**
 * 创建用户
 * @param data
 * @returns
 */
export const createUser = (data: UserPostData) => {
  return request<IResponseData<null>>({
    method: 'POST',
    url: '/sys/user/add',
    data: data,
  })
}

/**
 * 修改用户
 * @param id 用户ID
 * @param data 要更新的数据
 * @returns
 */
export const updateUser = (id: string, data: UserPostData) => {
  return request<IResponseData<null>>({
    method: 'PUT',
    url: `/sys/user/update`,
    data: { id: id, ...data },
  })
}

export const getUser = (id: string) => {
  return request<IResponseData<User>>({
    method: 'GET',
    url: `/sys/user/:${id}`,
  })
}

/**
 * 删除用户
 * @param id 用户ID
 * @returns 
 */
export const deleteUser = (id: string) => {
  return request<IResponseData<null>>({
    method: 'DELETE',
    url: `/sys/user/delete/${id}`,
  })
}

/**
 * 获取用户列表
 * @param params 
 * @returns 
 */
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
