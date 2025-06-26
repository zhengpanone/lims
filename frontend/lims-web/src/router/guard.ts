import { Router } from 'vue-router'
import TokenManager from '@/utils/token'
import nprogress from 'nprogress'
import 'nprogress/nprogress.css'
import { useAccessStore,useUserStore } from '@/store/auth'

/**
 * 通用守卫配置
 */
export function setupCommonGuard(router: Router) {
  // 记录已经加载的页面
  const loadedPaths = new Set<String>();

  router.beforeEach((to) => {
    to.meta.loaded = loadedPaths.has(to.path);

    if (!to.meta.loaded) {
      nprogress.start();
    }
    return true;

  });

  router.afterEach((to) => {
    loadedPaths.add(to.path);
    // 关闭页面加载进度条
    nprogress.done()

  });

}

function setupAccessGuard(router: Router) {
  // 全局前置守卫
  router.beforeEach((to) => {
    const userStore = useUserStore()
    if (to.meta.requireAuth && !userStore.$state.userInfo) {
      return {
        path: '/login',
        query: { redirect: to.fullPath },
      }
    }

  })
}

/**
 * 检查用户权限
 */
export function checkUserPermission(requiredPermissions: string[] = []): boolean {
  const userStore = useUserStore()
  const userInfo = userStore.userInfo

  if (!userInfo) {
    return false
  }

  // 这里可以根据实际需求实现权限检查逻辑
  // 例如检查用户角色、权限列表等

  return true
}

/**
 * 强制登出
 */
export function forceLogout(router: Router, reason: string = '认证失败') {
  // 清除认证信息
  TokenManager.clearAuthInfo()

  // 跳转到登录页面
  router.push({
    path: '/login',
    query: {
      redirect: router.currentRoute.value.fullPath,
      reason: reason
    }
  })
}



function createRouterGuard(router: Router) {
  /** 通用 */
  setupCommonGuard(router);
   /** 权限访问 */
   setupAccessGuard(router);
}

export { createRouterGuard };

