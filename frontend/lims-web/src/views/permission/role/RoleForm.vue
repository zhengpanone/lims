<template>
  <app-dialog-form
    :title="props.roleId ? '编辑角色' : '添加角色'"
    :confirm="handleSubmit"
    @closed="handleDialogClose"
    @open="handleDialogOpen"
  >
    <template #default="scope">
      <el-form
        ref="form"
        label-position="right"
        label-width="120px"
        :model="formData"
        :rules="formRules"
        v-loading="formLoading"
      >
        <el-form-item label="角色名称" prop="name">
          <el-input v-model="formData.name" placeholder="请输入角色名称" />
        </el-form-item>
        <el-form-item label="角色code" prop="code">
          <el-input v-model="formData.code" placeholder="请输入角色code" />
        </el-form-item>
        <el-form-item label="角色描述" prop="description">
          <el-input
            v-model="formData.description"
            placeholder="请输入角色描述"
          />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="formData.status">
            <el-radio :label="1">开启</el-radio>
            <el-radio :label="0">关闭</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
    </template>
  </app-dialog-form>
</template>
<script lang="ts" setup>
import { IDict } from "@/api/types/common";
import { RolePostData } from "@/api/types/role";
import { IElForm, IFormRule } from "@/types/element-plus";
import { PropType, ref, reactive } from "vue";
import { createRole, updateRole, getRole } from "@/api/role";
import { ElMessage } from "element-plus";

const form = ref<IElForm | null>(null);
const formLoading = ref(false);
const roles = ref<[IDict] | []>([]);
const formData = ref<RolePostData>({
  name: "",
  code: "",
  description: "",
  status: 0 as 0 | 1,
});

const formRules = reactive<IFormRule>({
  name: [
    { required: true, message: "请输入角色名称", trigger: "blur" },
    { min: 3, max: 60, message: "角色名称长度在3-60之间", trigger: "blur" },
  ],
  code: [{ required: true, message: "请输入角色code", trigger: "blur" }],
});
const props = defineProps({
  roleId: {
    type: String as PropType<string | null>,
    default: null,
  },
});
interface EmitsType {
  (e: "update:role-id", value: string | null): void;
  (e: "success"): void;
}
const emit = defineEmits<EmitsType>();

const loadRole = async () => {
  if (!props.roleId) {
    return;
  }
  const data = await getRole(props.roleId);
  if (!data.data) {
    ElMessage.error("获取角色信息失败");
    return;
  }
  formData.value = {
    name: data.data.name,
    code: data.data.code,
    description: data.data.description || "",
    status: data.data.status,
  } as RolePostData;
};

const handleDialogOpen = () => {
  formLoading.value = true;
  Promise.all([loadRole()]).finally(() => {
    formLoading.value = false;
  });
};

const handleDialogClose = () => {
  emit("update:role-id", null);
  form.value?.clearValidate(); // 清除验证结果
  form.value?.resetFields(); // 清除表单数据
};

const handleSubmit = async () => {
  const valid = await form.value?.validate();
  if (!valid) {
    return;
  }
  if (props.roleId) {
    await updateRole(props.roleId, formData.value);
  } else {
    await createRole(formData.value);
  }
  emit("success");
  ElMessage.success("保存成功");
};
</script>
<style scoped></style>
