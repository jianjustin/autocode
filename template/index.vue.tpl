<template>
    <div>
        <div class="gva-search-box">
            <!-- 搜索表单 -->
            <el-form ref="searchForm" :inline="true" :model="searchInfo">
              <el-form-item>
                {{range $label, $model, $type := .SearchField}}
                <el-form-item label="{{$label}}">
                    <el-input v-model="searchInfo.{{$model}}" placeholder="请输入" />
                </el-form-item>
                {{end}}
                <el-button size="small" type="primary" icon="search" @click="onSubmit">查询</el-button>
                <el-button size="small" icon="refresh" @click="onReset">重置</el-button>
                <el-button size="small" type="success" icon="download" @click="download">导出</el-button>
              </el-form-item>
            </el-form>
        </div>


    </div>
  </template>

<script>
export default {
  name: '{{.PageName}}',
}
</script>