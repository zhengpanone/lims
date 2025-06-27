/**
 * 用户相关请求模块
 */
import request from '@/utils/request'
import type { IResponseData } from './types/common'
import { Role, IListPageParams, RolePostData, RoleUpdateData } from './types/role'


/**
 * 创建角色
 * @param data
 * @returns
 */
export const createRole = (data: RolePostData) => {
  return request<IResponseData<null>>({
    method: 'POST',
    url: '/sys/role/add',
    data: data,
  })
}
/**
 * 修改角色
 * @param id 管理员ID
 * @param data 要更新的数据
 * @returns
 */
export const updateRole = (id: string, data: RoleUpdateData) => {
  return request<IResponseData<null>>({
    method: 'POST',
    url: `/sys/role/update`,
    data: { id: id, ...data },
  })
}

export const getRole = (id: string) => {
  return request<IResponseData<Role>>({
    method: 'GET',
    url: `/sys/role/${id}`,
  })
}

export const getRoleList = (params: IListPageParams) => {
  return request<
    IResponseData<{
      total: number
      list: Role[]
    }>
  >({
    method: 'GET',
    url: '/sys/role/page',
    params: params,
  })
}

export const getAllRoleList = () => {
  return request<
    IResponseData<{
      total: number
      list: Role[]
    }>
  >({
    method: 'GET',
    url: '/sys/role/list',
  })
}

/**
 * 更新用户状态
 * @param id 用户ID
 * @param status 用户状态 0:禁用 1:启用
 * @returns
 */
export const updateRoleStatus = (id: string, status: number) => {
  return request<IResponseData<null>>({
    method: 'POST',
    url: `/sys/role/updateState`,
    data: {
      id,
      status
    }
  })
}

/**
 * 删除角色
 * @param id 角色ID
 * @returns
 */
export const deleteRole = (id: string) => {
  return request<IResponseData<null>>({
    method: 'DELETE',
    url: `/sys/role/delete/${id}`,
  })
}
