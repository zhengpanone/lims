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
        <el-form-item label="角色名称" prop="rolename">
          <el-input v-model="formData.name" placeholder="请输入角色名称" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="formData.status">
            <el-radio label="1">开启</el-radio>
            <el-radio label="0">关闭</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
    </template>
  </app-dialog-form>
</template>
<script lang="ts" setup>
import { IDict } from "@/api/types/common";
import { Role, RolePostData } from "@/api/types/role";
import { IElForm, IFormRule } from "@/types/element-plus";
import { PropType, ref, reactive } from "vue";
import { createRole, updateRole, getRole } from "@/api/role";
import { ElMessage } from "element-plus";

const form = ref<IElForm | null>(null);
const formLoading = ref(false);
const roles = ref<[IDict] | []>([]);
const formData = ref<RolePostData>({
  name: "",
  status: 0 as 0 | 1,
});

const formRules = reactive<IFormRule>({
  account: [
    { required: true, message: "请输入管理员账号", trigger: "blur" },
    { min: 3, max: 60, message: "管理员账号长度在3-60之间", trigger: "blur" },
  ],
  pwd: [{ required: true, message: "请输入管理员密码", trigger: "blur" }],
  confPwd: [{ required: true, message: "请输入确认密码", trigger: "blur" }],
  realName: [{ required: true, message: "请输入管理员姓名", trigger: "blur" }],
  roles: [{ required: true, message: "请选择管理员身份", trigger: "blur" }],
});
const props = defineProps({
  roleId: {
    type: String as PropType<string | null>,
    default: null,
  },
});
interface EmitsType {
  (e: "update:admin-id", value: string | null): void;
  (e: "success"): void;
}
const emit = defineEmits<EmitsType>();

const loadRole = async () => {
  if (!props.roleId) {
    return;
  }
  const data = await getRole(props.roleId);
  formData.value = data.data;
};
const handleDialogOpen = () => {
  formLoading.value = true;
  Promise.all([loadRole()]).finally(() => {
    formLoading.value = false;
  });
};
const handleDialogClose = () => {
  emit("update:admin-id", null);
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
