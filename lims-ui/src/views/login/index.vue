<template>
    <div class="login-container">
        <el-form class="login-form" ref="form" :rules="rules" :model="user" size="default"
            @submit.prevent="handleSubmit">
            <div class="login-form__header">
                <img class="login-logo" src="@/assets/login_logo.png" alt="拉勾心选">
            </div>
            <el-form-item prop="account">
                <el-input v-model="user.account" placeholder="请输入用户名">
                    <template #prefix>
                        <el-icon>
                            <User />
                        </el-icon>
                    </template>
                </el-input>
            </el-form-item>
            <el-form-item prop="pwd">
                <el-input v-model="user.pwd" type="password" placeholder="请输入密码">
                    <template #prefix>
                        <el-icon>
                            <Lock />
                        </el-icon>
                    </template>
                </el-input>
            </el-form-item>
            <el-form-item prop="imgCode">
                <div class="imgcode-wrap">
                    <el-input v-model="user.imgCode" placeholder="请输入验证码">
                        <template #prefix>
                            <el-icon>
                                <Lock />
                            </el-icon>
                        </template>
                    </el-input>
                    <img class="imgcode" alt="验证码" :src="captchaSrc" @click="loadCaptcha">
                </div>
            </el-form-item>
            <el-form-item>
                <el-button class="submit-button" type="primary" native-type="submit" :loading="loading">
                    登录
                </el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script lang="ts" setup>
import { getCaptcha, login } from '@/api/common'
import { onMounted, reactive, ref } from 'vue'
import { User, Lock } from '@element-plus/icons-vue';
import { ElForm } from 'element-plus';
import { useRouter, useRoute } from 'vue-router';
import type { IElForm, IFormRule } from '@/types/element-plus'
import { indexStore } from '@/store/index'

const router = useRouter()
const route = useRoute()

const captchaSrc = ref('') // 验证码图片src
const form = ref<IElForm | null>(null) // 登录表单

const user = reactive({
    account: 'admin', pwd: '123456', imgCode: ''
})
const loading = ref(false) // 是否加载

const rules = reactive<IFormRule>({
    account: [{ required: true, message: '请输入账号', trigger: 'change' }],
    pwd: [{ required: true, message: '请输入密码', trigger: 'change' }],
    imgCode: [{ required: true, message: '请输入验证码', trigger: 'change' }],

})



onMounted(() => {
    loadCaptcha()
})

const loadCaptcha = async () => {
    const data = await getCaptcha()
    captchaSrc.value = URL.createObjectURL(data)

}

const handleSubmit = async () => {
    const valid = await form.value?.validate()
    if (!valid) {
        return
    }
    loading.value = true
    const loginData = await login(user).finally(() => { loading.value = false })
    const store = indexStore()
    store.setUser({ ...loginData.data.userInfo, token: loginData.data.token })
    let redirect = route.query.redirect || '/'
    if (typeof redirect !== 'string') {
        redirect = '/'
    }
    router.replace(redirect)

}
</script>
<style scoped lang="scss">
.login-container {
    min-width: 400px;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #2d3a4b;
}

.login-form {
    padding: 30px;
    border-radius: 6px;
    background: #fff;
    min-width: 350px;

    .login-form__header {
        display: flex;
        justify-content: center;
        align-items: center;
        padding-bottom: 30px;
    }

    .el-form-item:last-child {
        margin-bottom: 0;
    }

    .login__form-title {
        display: flex;
        justify-content: center;
        color: #fff;
    }

    .submit-button {
        width: 100%;
    }

    .login-logo {
        width: 271px;
        height: 74px;
    }

    .imgcode-wrap {
        display: flex;
        align-items: center;

        .imgcode {
            height: 37px;
        }
    }
}
</style>