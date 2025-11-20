<template>
  <el-col>
    <el-card class="search-toolbar">
      <template #header>
        <div class="card-header">
          <span>数据筛选</span>
        </div>
      </template>
      <el-form
        :inline="true"
        ref="form"
        :model="listParams"
        :disabled="listLoading"
      >
        <el-form-item label="状态" clearable>
          <el-select placeholder="请选择状态" v-model="listParams.status">
            <el-option label="全部" value="" />
            <el-option label="启用" :value="1" />
            <el-option label="禁用" :value="0" />
          </el-select>
        </el-form-item>
        <el-form-item label="管理员名称">
          <el-input placeholder="请输入管理员名称" v-model="listParams.name" />
        </el-form-item>
        <el-form-item>
          <el-button native-type="submit" @click="handleQuery">查询</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card>
      <template #header>
        <div class="card-header">
          <el-button type="primary" @click="formVisible = true"
            >添加角色</el-button
          >
        </div>
      </template>
      <el-table style="width: 100%" :data="list" v-loading="listLoading">
        <el-table-column prop="id" label="ID" width="180" />
        <el-table-column prop="code" label="角色code" width="180" />
        <el-table-column prop="name" label="角色名称" />
        <el-table-column label="状态" width="180">
          <template #default="scope">
            <el-switch
              v-model="scope.row.status"
              class="ml-2"
              active-color="#13ce66"
              inactive-color="#ff4949"
              :active-value="1"
              :inactive-value="0"
              :loading="scope.row.statusLoading"
              @change="handleStatusChange(scope.row)"
            />
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          fixed="right"
          label="操作"
          min-width="100"
        >
          <template #default="scope">
            <el-button type="primary" link @click="handleUpdate(scope.row.id)"
              >编辑</el-button
            >
            <el-popconfirm
              title="Are you sure to delete this?"
              @confirm="handleDelete(scope.row.id)"
            >
              <template #reference>
                <el-button type="primary" link>删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <AppPagination
        v-model:page="listParams.page"
        v-model:limit="listParams.limit"
        :list-count="listCount"
        :load-list="loadList"
        :disabled="listLoading"
      />
    </el-card>
  </el-col>
  <RoleForm
    v-model="formVisible"
    v-model:role-id="roleId"
    @success="handleSuccess"
  />
</template>
<script lang="ts" setup>
import { ref, reactive, onMounted } from "vue";
import { getRoleList, updateRoleStatus, deleteRole } from "@/api/role";
import type { IListPageParams, Role } from "@/api/types/role";
import RoleForm from "./RoleForm.vue";
import { ElMessage } from "element-plus";
const list = ref<Role[]>([]); // 列表数据
const listCount = ref(0);
const listLoading = ref(true);
const listParams = reactive({
  page: 1,
  limit: 10,
  name: "",
  roles: "",
  status: "" as IListPageParams["status"],
});
const formVisible = ref(false);
const roleId = ref<string | null>(null);

const loadList = async () => {
  listLoading.value = true;
  const data = await getRoleList(listParams).finally(() => {
    listLoading.value = false;
  });
  data.data.list.forEach((item) => {
    item.statusLoading = false;
  });
  list.value = data.data.list;
  listCount.value = data.data.total;
};

const handleQuery = async () => {
  await loadList();
};

const handleStatusChange = async (item: Role) => {
  item.statusLoading = true;
  await updateRoleStatus(item.id, item.status).finally(() => {
    item.statusLoading = false;
  });
  ElMessage.success(`${item.status === 1 ? "启用" : "禁用"}成功`);
};

const handleDelete = async (id: string) => {
  await deleteRole(id);
  ElMessage.success(`删除成功`);
  loadList();
};

const handleUpdate = (id: string) => {
  roleId.value = id;
  formVisible.value = true;
};

const handleSuccess = () => {
  formVisible.value = false;
  loadList();
};

onMounted(() => {
  loadList();
});
</script>
<style scoped>
.search-toolbar {
  margin-bottom: 20px;
}
</style>
