<template>
    <el-icon @click="indexStore.setIsCollapse(!indexStore.isCollapse)">
        <Expand v-if="indexStore.isCollapse" />
        <Fold v-else="indexStore.isCollapse" />
    </el-icon>
</template>
<script lang="ts" setup>
import {
    Fold, Expand
} from '@element-plus/icons-vue'
import { useIndexStore } from '@/store/index'
const indexStore = useIndexStore()

</script>
<style scoped>
.el-icon {
    cursor: pointer;
}
</style>