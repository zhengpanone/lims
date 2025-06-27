<template>
  <div class="login-container">
    <div class="login-box">
      <div class="login-header">
        <h2>LIMS 系统登录</h2>
      </div>

      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginRules"
        class="login-form"
        @submit.prevent="handleLogin"
      >
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            placeholder="请输入用户名"
            prefix-icon="User"
            size="large"
          />
        </el-form-item>

        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            prefix-icon="Lock"
            size="large"
            show-password
          />
        </el-form-item>

        <el-form-item prop="captcha" v-if="captchaConfig.showCaptcha">
          <div class="captcha-container">
            <el-input
              v-model="loginForm.captcha"
              placeholder="请输入验证码"
              size="large"
              style="flex: 1; margin-right: 10px"
            />
            <img
              :src="captchaConfig.captchaImage"
              alt="验证码"
              class="captcha-image"
              @click="refreshCaptcha"
            />
          </div>
        </el-form-item>

        <el-form-item>
          <el-button
            type="primary"
            size="large"
            :loading="loading"
            style="width: 100%"
            @click="handleLogin"
          >
            {{ loading ? "登录中..." : "登录" }}
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import { ElMessage } from "element-plus";
import { login } from "@/api/auth";
import { getCaptcha } from "@/api/common";
import { useUserStore, useAccessStore } from "@/store/auth";
// import {useIndexStore as indexStore} from '@/store'
import TokenManager from "@/utils/token";
import { BUSINESS_CODE } from "@/utils/constants";

const router = useRouter();
const route = useRoute();
const userStore = useUserStore();
const accessStore = useAccessStore();

// 表单引用
const loginFormRef = ref();

// 加载状态
const loading = ref(false);

// 登录表单数据
const loginForm = reactive({
  username: "",
  password: "",
  captcha: "",
  captchaId: "",
});

// 验证码配置
const captchaConfig = reactive({
  showCaptcha: false,
  captchaImage: "",
  captchaId: "",
});

// 表单验证规则
const loginRules = {
  username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
  password: [
    { required: true, message: "请输入密码", trigger: "blur" },
    { min: 6, message: "密码长度不能少于6位", trigger: "blur" },
  ],
  captcha: [{ required: true, message: "请输入验证码", trigger: "blur" }],
};

// 获取验证码
const refreshCaptcha = async () => {
  try {
    const response = await getCaptcha({ captchaType: "blockPuzzle" });
    if (response.code === 200 && response.data) {
      captchaConfig.captchaImage = response.data.captchaImage;
      captchaConfig.captchaId = response.data.captchaId;
      loginForm.captchaId = response.data.captchaId;
      captchaConfig.showCaptcha = true;
    }
  } catch (error) {
    console.error("获取验证码失败:", error);
  }
};

// 处理登录
const handleLogin = async () => {
  try {
    // 表单验证
    await loginFormRef.value.validate();

    loading.value = true;

    // 构建登录参数
    const loginData = {
      username: loginForm.username,
      password: loginForm.password,
    };

    // 如果需要验证码，添加验证码信息
    if (captchaConfig.showCaptcha) {
      // loginData.captcha = loginForm.captcha
      // loginData.captchaId = loginForm.captchaId
    }

    // 调用登录 API
    const response = await login(loginData);
    let data = response.data;
    if (response.code === BUSINESS_CODE.SUCCESS && data) {
      userStore.setUserInfo({ username: data.username });
      accessStore.setAccessToken(data.accessToken);

      // 更新indexStore中的user状态，以便路由守卫能正确识别用户已登录
      // const indexStoreInstance = indexStore()
      // indexStoreInstance.setUser({
      //   token: data.accessToken,
      //   username: data.username
      // })

      ElMessage.success("登录成功");

      // 跳转到目标页面或首页
      const redirect =
        typeof route.query.redirect === "string" ? route.query.redirect : "/";
      await router.push(redirect);
    } else {
      ElMessage.error(response.msg || "登录失败");

      // 如果登录失败，刷新验证码
      if (captchaConfig.showCaptcha) {
        refreshCaptcha();
      }
    }
  } catch (error) {
    console.error("登录失败:", error);
    ElMessage.error("登录失败，请检查用户名和密码");

    // 如果登录失败，刷新验证码
    if (captchaConfig.showCaptcha) {
      refreshCaptcha();
    }
  } finally {
    loading.value = false;
  }
};

// 组件挂载时初始化
onMounted(() => {
  // 检查是否已经登录
  if (TokenManager.hasValidAuth()) {
    const redirect =
      typeof route.query.redirect === "string" ? route.query.redirect : "/";
    router.push(redirect);
    return;
  }

  // 获取验证码
  refreshCaptcha();
});
</script>

<style scoped lang="scss">
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.login-box {
  width: 400px;
  padding: 40px;
  background: white;
  border-radius: 10px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
}

.login-header {
  text-align: center;
  margin-bottom: 30px;
}

.login-header h2 {
  color: #333;
  margin: 0;
  font-size: 24px;
}

.login-form {
  margin-top: 20px;
}

.captcha-container {
  display: flex;
  align-items: center;
}

.captcha-image {
  width: 120px;
  height: 40px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  cursor: pointer;
}
</style>
