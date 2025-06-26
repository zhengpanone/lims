import { defineStore } from 'pinia'

type AccessToken = null | string;

interface BasicUserInfo{
  [key:string]:any;
  /**
   * 用户名
   */
  username:string;
  /**
   * 用户角色
   */
  roles?:string[];
  /**
   * 头像
   */
  avatar?:string;
}

interface AccessState{
    /**
   * 登录 accessToken
   */
    accessToken: AccessToken;
}

interface UserState{
  /**
   * 用户信息
   */
  userInfo: BasicUserInfo| null;
  /**
   * 用户角色
   */
  userRoles: string[];
}

/**
 * 用户信息相关
 */
export const useUserStore = defineStore('user', {
  state: (): UserState => ({
    // 用户基本信息
    userInfo: null,
    userRoles: [],
  }),
  actions: {
    // 设置用户认证信息
    setUserInfo(userInfo: BasicUserInfo | null) {
      // 设置用户信息
      this.userInfo = userInfo;
      const roles = userInfo?.roles || [];
      this.setUserRoles(roles);
    },
    setUserRoles(roles: string[]){
      this.userRoles=roles;
    },
  },
});

export const useAccessStore = defineStore('access',{
  actions:{
    setAccessToken(token: AccessToken){
      this.accessToken = token;
    }
  },
  state:():AccessState=>({
    accessToken: null,
  }),

});
