import type { Pinia } from 'pinia';
import { defineStore, createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import type { App } from 'vue';
import { IUserInfo } from '@/api/types/common'
import { setItem, getItem } from '@/utils/storage'
import { USER } from '@/utils/constants'

let piniaStore: Pinia;

export interface InitStoreOptions {
  /**
   * @zh_CN 应用名,后续可能有多个app，为了防止多个app缓存冲突，可在这里配置应用名,应用名将被用于持久化的前缀
   */
  namespace: string;
}

export async function initStores(app: App, options?: InitStoreOptions) {
  // 创建pinia
  piniaStore = createPinia();
  piniaStore.use(piniaPluginPersistedstate);
  app.use(piniaStore);
  return piniaStore
}


const state = {
  count: 1,
  isCollapse: false,
  // user: getItem<({ token: string } & IUserInfo) | null>(USER),
}


export const useIndexStore = defineStore('index', {
  state: () => {
    return state
  },
  actions: {
    setIsCollapse(payload: boolean) {
      this.isCollapse = payload
    },
    // setUser(user: ({ token: string } & IUserInfo) | null) {
    //   this.user = user
    //   setItem(USER, JSON.stringify(user))
    // },
  },

  getters: {},
  persist: {
    strategies: [
      {
        key: 'index-store',
        storage: localStorage,
        paths: ['isCollapse']
      }
    ]
  }

})
