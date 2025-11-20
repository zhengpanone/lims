/**
 * 公共基础接口封装
 */
import request from '@/utils/request'
import { IResponseData, ILoginInfo, ILoginRresponse, ILoginData, ICaptchaDTO } from './types/common'

export const getLoginInfo = () => {
  return request<IResponseData<ILoginInfo>>({
    method: 'GET',
    url: '/api/user/login/info',
  })
}
/**
 * 获取图片验证码
 * @returns 返回图片验证码
 */
// export const getCaptcha = (data: ICaptchaDTO) => {
//   return request<Blob>({
//     method: 'POST',
//     url: '/sys/captcha/get',
//     data,
//     responseType: 'blob',
//   })
// }

/**
 * 获取验证码
 */
export const getCaptcha = (data: ICaptchaDTO) => {
  return request<IResponseData<{ captchaId: string; captchaImage: string }>>({
    url: '/sys/captcha/get',
    method: 'POST',
    data
  })
}





export const login = (data: ILoginData) => {
  return request<IResponseData<ILoginRresponse>>({
    method: 'POST',
    url: '/api/user/login',
    data,
  })
}
