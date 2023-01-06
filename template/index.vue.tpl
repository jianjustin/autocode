<template>
    <div>
        <div class="gva-search-box">
            <!-- 搜索表单 -->
            <el-form ref="searchForm" :inline="true" :model="searchInfo">
              <el-form-item>
                {{- range $i,$v := .SearchField}}
                <el-form-item label="{{$v.label}}">
                    <el-input v-model="searchInfo.{{$v.model}}" placeholder="请输入" />
                </el-form-item>
                {{- end}}
                <el-button size="small" type="primary" icon="search" @click="onSubmit">查询</el-button>
                <el-button size="small" icon="refresh" @click="onReset">重置</el-button>
                <el-button size="small" type="success" icon="download" @click="download">导出</el-button>
              </el-form-item>
            </el-form>
        </div>

        <div class="gva-table-box">
          <div class="gva-btn-list">
            <el-button size="small" type="primary" icon="plus" @click="{{.AddFunc}}">新增</el-button>
          </div>
          <el-table
              :data="tableData"
              row-key="ID"
          >
            {{- range $i,$v := .TableField}}
            <el-table-column align="left" label="{{$v.label}}" min-width="{{$v.width}}" prop="{{$v.prop}}" />
            {{- end}}
            <el-table-column label="操作" min-width="100" fixed="right">
              <template #default="scope">
                <el-button type="text" icon="price-tag" size="small" @click="{{.EditFunc}}">编辑</el-button>
                <el-button type="text" icon="price-tag" size="small" @click="{{.DelFunc}}">删除</el-button>
              </template>
            </el-table-column>

          </el-table>
          <!--分页组件-->
          <div class="gva-pagination">
            <el-pagination
                :current-page="page"
                :page-size="pageSize"
                :page-sizes="[10, 30, 50, 100]"
                :total="total"
                layout="total, sizes, prev, pager, next, jumper"
                @current-change="handleCurrentChange"
                @size-change="handleSizeChange"
            />
          </div>
        </div>

        <el-dialog
            v-model="dialogFormVisible"
            custom-class="user-dialog"
            title="{{.ServiceName}}"
            :show-close="false"
            :close-on-press-escape="false"
            :close-on-click-modal="false"
        >
          <div style="height:60vh;overflow:auto;padding:0 12px;">
            <el-form ref="{{.Service}}Form" :rules="rules" :model="{{.Service}}Info" label-width="80px">
              {{- range $i,$v := .FormField}}
              <el-form-item label="{{$v.label}}" prop="{{$v.prop}}">
                <el-input v-model="{{$.Service}}Info.{{$v.prop}}" />
              </el-form-item>
              {{- end}}
            </el-form>
          </div>

          <template #footer>
            <div class="dialog-footer">
              <el-button size="small" @click="closeDialog">取 消</el-button>
              <el-button size="small" type="primary" @click="enterDialog">确 定</el-button>
            </div>
          </template>
        </el-dialog>

    </div>
  </template>

<script>
export default {
  name: '{{.PageName}}',
}
</script>

<script setup>
import {
    {{- range $i,$v := .Apis}}
    {{$v.api}}
    {{- end}}
} from '@/api/updf/{{.PageName}}'

import { nextTick, ref, watch } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const path = ref(import.meta.env.VITE_BASE_API)
const page = ref(1)
const total = ref(0)
const pageSize = ref(10)
const tableData = ref([])
const searchInfo = ref({})

  const onReset = () => {
    searchInfo.value = {};
  }

  // 搜索
  const onSubmit = () => {
    page.value = 1
    pageSize.value = 10
    getTableData()
  }

  // 分页
  const handleSizeChange = (val) => {
    pageSize.value = val
    getTableData()
  }


  const handleCurrentChange = (val) => {
    page.value = val
    getTableData()
  }

  // 查询
  const getTableData = async() => {
    const table = await get{{.PageName1}}List({ page: page.value, pageSize: pageSize.value, ...searchInfo.value })
    if (table.code === 0) {
      tableData.value = table.data.list
      total.value = table.data.total
      page.value = table.data.page
      pageSize.value = table.data.pageSize
    }
  }

  const initPage = async() => {
    onReset();
    getTableData();
  }

  initPage()

const dialogTitle = ref('新增')
const dialogFormVisible = ref(false)
const type = ref('')
const openDialog = (key) => {
  switch (key) {
    case 'add':
      dialogTitle.value = '新增'
      break
    case 'edit':
      dialogTitle.value = '编辑'
      break
    default:
      break
  }
  type.value = key
  dialogFormVisible.value = true
}
const closeDialog = () => {
  initForm()
  dialogFormVisible.value = false
}


const edit{{.PageName1}}Func = async(row) => {
  const res = await get{{.PageName1}}ById({ id: row.ID })
  form.value = res.data
  openDialog('edit')
}

const enterDialog = async() => {
  apiForm.value.validate(async valid => {
    if (valid) {
      switch (type.value) {
        case 'add':
          {
            const res = await create{{.PageName1}}(form.value)
            if (res.code === 0) {
              ElMessage({
                type: 'success',
                message: '添加成功',
                showClose: true
              })
            }
            getTableData()
            closeDialog()
          }

          break
        case 'edit':
          {
            const res = await update{{.PageName1}}(form.value)
            if (res.code === 0) {
              ElMessage({
                type: 'success',
                message: '编辑成功',
                showClose: true
              })
            }
            getTableData()
            closeDialog()
          }
          break
        default:
          {
            ElMessage({
              type: 'error',
              message: '未知操作',
              showClose: true
            })
          }
          break
      }
    }
  })
}

const delete{{.PageName1}}Func = async(row) => {
  ElMessageBox.confirm('此操作将永久删除, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(async() => {
      const res = await delete{{.PageName1}}(row)
      if (res.code === 0) {
        ElMessage({
          type: 'success',
          message: '删除成功!'
        })
        if (tableData.value.length === 1 && page.value > 1) {
          page.value--
        }
        getTableData()
      }
    })
}

</script>

<style lang="scss">
  .user-dialog {
  .header-img-box {
    width: 200px;
    height: 200px;
    border: 1px dashed #ccc;
    border-radius: 20px;
    text-align: center;
    line-height: 200px;
    cursor: pointer;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409eff;
  }
  .avatar-uploader-icon {
    border: 1px dashed #d9d9d9 !important;
    border-radius: 6px;
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }

  .nickName{
    display: flex;
    justify-content: flex-start;
    align-items: center;
  }
  .pointer{
    cursor: pointer;
    font-size: 16px;
    margin-left: 2px;
  }
  .total-order{
    display: flex;
    justify-content: flex-start;
    padding: 10px 0;
    margin-right: 16px;
    font-weight: 400;
    color: var(--el-text-color-regular);
  }
  }
</style>