<template>
  <h1>首页</h1>
  <el-button>测试按钮</el-button>
  <div>
    {{ userStore.name }}的年龄：<!--{{ userStore.age }}--><br />
    <button @click="addAge">点击给{{ userStore.name }}的年龄增加一岁</button>
  </div>
</template>
<script lang="ts" setup>
import { useUserStore } from '@/store/auth'
const userStore = useUserStore()
const addAge = () => {
  // userStore.updateAge(++userStore.age)
}
</script>
<style scoped lang="scss">
@use '@/styles/variables' as vars; // 使用@use导入并设置命名空间

h1 {
  color: vars.$color;
}
</style>
