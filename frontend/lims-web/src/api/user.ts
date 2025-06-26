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
