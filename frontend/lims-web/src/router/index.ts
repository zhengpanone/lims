import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import ProductRoutes from './modules/product'
import PermissionRoutes from './modules/permission'
import AppLayout from '@/layout/AppLayout.vue'

import { createRouterGuard } from './guard'
// import eventEmiter from '@/utils/eventEmiter'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: AppLayout,
    meta: {
      requireAuth: true,
    },
    children: [
      {
        path: '', // 默认子路由
        name: 'home',
        component: () => import('@/views/home/index.vue'),
        meta: {
          title: '首页',
        },
      },
      ProductRoutes,
      PermissionRoutes,
    ],
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login/index.vue'),
    meta: {
      title: '登录'
    }
  },
] // 路由规则

const router = createRouter({
  history: createWebHashHistory(), // 路由模式
  routes,
})

createRouterGuard(router);
// eventEmiter.on('API:UN_AUTH',()=>{
//   router.push('/')
// })

export default router;
