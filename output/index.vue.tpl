<template>
    <div>
        <div class="gva-search-box">
            <!-- 搜索表单 -->
            <el-form ref="searchForm" :inline="true" :model="searchInfo">
              <el-form-item>
                <el-form-item label="商品ID">
                    <el-input v-model="searchInfo.goodId" placeholder="请输入" />
                </el-form-item>
                <el-form-item label="商品名称">
                    <el-input v-model="searchInfo.goodName" placeholder="请输入" />
                </el-form-item>
                <el-button size="small" type="primary" icon="search" @click="onSubmit">查询</el-button>
                <el-button size="small" icon="refresh" @click="onReset">重置</el-button>
                <el-button size="small" type="success" icon="download" @click="download">导出</el-button>
              </el-form-item>
            </el-form>
        </div>

        <div class="gva-table-box">
          <div class="gva-btn-list">
            <el-button size="small" type="primary" icon="plus" @click="openDialog('add')">新增</el-button>
          </div>
          <el-table
              :data="tableData"
              row-key="ID"
          >
            <el-table-column align="left" label="名称" min-width="200" prop="goodsName" />
            <el-table-column align="left" label="描述" min-width="110" prop="goodsDesc" />
            <el-table-column align="left" label="平台类型" min-width="80" prop="platform" />
            <el-table-column align="left" label="上架状态" min-width="100" prop="isOnline" />
            <el-table-column label="操作" min-width="100" fixed="right">
              <template #default="scope">
                <el-button type="text" icon="price-tag" size="small" @click="editGoodFunc(scope.row)">编辑</el-button>
                <el-button type="text" icon="price-tag" size="small" @click="deleteGoodFunc(scope.row)">删除</el-button>
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
            title="商品"
            :show-close="false"
            :close-on-press-escape="false"
            :close-on-click-modal="false"
        >
          <div style="height:60vh;overflow:auto;padding:0 12px;">
            <el-form ref="goodForm" :rules="rules" :model="goodInfo" label-width="80px">
              <el-form-item label="商品名称" prop="goodsName">
                <el-input v-model="goodInfo.goodsName" />
              </el-form-item>
              <el-form-item label="权益类型" prop="proId">
                <el-input v-model="goodInfo.proId" />
              </el-form-item>
              <el-form-item label="订阅类型" prop="subscriptionId">
                <el-input v-model="goodInfo.subscriptionId" />
              </el-form-item>
              <el-form-item label="产品类型" prop="productId">
                <el-input v-model="goodInfo.productId" />
              </el-form-item>
              <el-form-item label="产品版本" prop="ver">
                <el-input v-model="goodInfo.ver" />
              </el-form-item>
              <el-form-item label="币种" prop="currencySymbol">
                <el-input v-model="goodInfo.currencySymbol" />
              </el-form-item>
              <el-form-item label="原价" prop="orgPrice">
                <el-input v-model="goodInfo.orgPrice" />
              </el-form-item>
              <el-form-item label="售价" prop="goodsPrice">
                <el-input v-model="goodInfo.goodsPrice" />
              </el-form-item>
              <el-form-item label="是否推荐" prop="is_recommend">
                <el-input v-model="goodInfo.is_recommend" />
              </el-form-item>
              <el-form-item label="上线状态" prop="isOnline">
                <el-input v-model="goodInfo.isOnline" />
              </el-form-item>
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
  name: 'good',
}
</script>

<script setup>
import {
    getGoodList
} from '@/api/updf/good'

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
    const table = await getGoodList({ page: page.value, pageSize: pageSize.value, ...searchInfo.value })
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


const editGoodFunc = async(row) => {
  const res = await getGoodById({ id: row.ID })
  form.value = res.data
  openDialog('edit')
}

const enterDialog = async() => {
  apiForm.value.validate(async valid => {
    if (valid) {
      switch (type.value) {
        case 'add':
          {
            const res = await createGood(form.value)
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
            const res = await updateGood(form.value)
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

const deleteGoodFunc = async(row) => {
  ElMessageBox.confirm('此操作将永久删除, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(async() => {
      const res = await deleteGood(row)
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