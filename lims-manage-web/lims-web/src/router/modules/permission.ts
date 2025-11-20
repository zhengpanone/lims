import { RouteRecordRaw, RouterView } from 'vue-router'

const routes: RouteRecordRaw = {
  path: '/permission',
  name: 'permission',
  component: RouterView,
  meta: {
    title: '权限管理',
  },
  children: [
    {
      path: 'user',
      name: 'user',
      component: () => import('@/views/permission/user/index.vue'),
      meta: {
        // 自定义元数据
        title: '用户管理',
      },
    },
    {
      path: 'role',
      name: 'role',
      component: () => import('@/views/permission/role/index.vue'),
      meta: {
        // 自定义元数据
        title: '角色管理',
      },
    },
  ],
}

export default routes
