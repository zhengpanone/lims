export interface IListPageParams {
  page: number
  limit: number
  name: string
  roles: string
  status: 0 | 1 | ''
}

export interface Role {
  id: string
  name: string
  status: number
  is_del: number
  _add_time: string
  _last_time: string
  statusLoading?: boolean
}

export interface RolePostData {
  name: string
  status: 0 | 1

}

export interface RoleUpdateData {
  name: string
  status: 0 | 1

}
