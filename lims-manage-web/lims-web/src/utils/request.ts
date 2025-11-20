import axios from 'axios'
import type { AxiosInstance, AxiosRequestConfig, AxiosError, AxiosResponse, InternalAxiosRequestConfig } from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus'

import { useUserStore, useAccessStore } from '@/store/auth'

import router from '@/router/'

import { refreshToken } from '@/api/auth'
import { BUSINESS_CODE, TOKEN_EXPIRES_BUFFER } from './constants'
// import eventEmiter from './eventEmiter'
// 创建实例
const request: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_API_BASEURL,
})

// 用于存储正在进行的刷新请求
let refreshPromise: Promise<any> | null = null

// 请求拦截器
request.interceptors.request.use(
  async (config: AxiosRequestConfig & InternalAxiosRequestConfig) => {
    try {
      const accessStore = useAccessStore();
      const access = accessStore.$state.accessToken
      if (access && config.headers) {
        config.headers.Authorization = `Bearer ${access}`
      }
    } catch (error) {
      // 刷新失败，清除认证信息并跳转到登录页
      router.push('/login')
      return Promise.reject(error)
    }

    return config
  },
  (error: AxiosError) => {
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  (response: AxiosResponse) => {
    // 统一处理接口响应错误,如token过期、服务端异常
    const status = response.data.code

    console.log(response.data)
    if (!status || status === BUSINESS_CODE.SUCCESS) {
      // 正确情况
      return response
    }

    // 登录过期
    if (status === BUSINESS_CODE.TOKEN_EXPIRED) {
      return handleTokenExpired(response)
    }

    ElMessage.error(response.data.msg || '请求失败,请联系管理员')
    // 手动返回promise异常
    return Promise.reject(response)
  },
  (error: AxiosError) => {
    // 处理 HTTP 401 错误
    if (error.response?.status === BUSINESS_CODE.UNAUTHORIZED) {
      return handleTokenExpired(error.response)
    }
    return Promise.reject(error)
  }
)

/**
 * 处理 token 过期
 */
async function handleTokenExpired(response: any) {
  try {
    // 尝试刷新 token

    // 刷新成功，重新发送原请求
    const originalRequest = response.config
    if (originalRequest) {

      return request(originalRequest)
    }
  } catch (error) {
    // 刷新失败，清除认证信息并跳转到登录页
    ElMessageBox.confirm(
      '你的登录已经过期,您可以取消停留在此页面,或确认重新登录',
      '登录过期',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
      }
    )
      .then(() => {
        // 跳转到登录页面
        router.push({
          name: 'login',
          query: {
            redirect: router.currentRoute.value.fullPath,
          },
        })
      })
      .catch(() => {
        // 用户取消，不做任何操作
      })
  }
  return Promise.reject(response)
}


export default <T = any>(config: AxiosRequestConfig) => {
  return request(config).then((res) => {
    console.log("-----" + res)
    return res.data as T
  })
}
