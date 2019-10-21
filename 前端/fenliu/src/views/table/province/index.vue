<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="18">
        <el-input v-model="keyword" placeholder="请输入内容" prefix-icon="el-icon-search" />
      </el-col>
      <el-col :span="6">
        <el-button-group>
          <el-button type="primary" icon="el-icon-plus" @click="handleInsert">添加</el-button>
          <delete-popover :id="multipleSelection" @handleDelete="handleDeleteByList" />
        </el-button-group>
      </el-col>
    </el-row>
    <br>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column align="center" label="行号" width="95">
        <template slot-scope="scope">{{ scope.$index+1 }}</template>
      </el-table-column>
      <el-table-column label="省份编号">
        <template slot-scope="scope">{{ scope.row.provinceId }}</template>
      </el-table-column>
      <el-table-column label="省份名称">
        <template slot-scope="scope">{{ scope.row.provinceName }}</template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="300">
        <template slot-scope="scope">
          <delete-popover :id="scope.row.provinceId" size="small" @handleDelete="handleDelete" />
          <el-button type="primary" icon="el-icon-edit-outline" size="small" @click="handleEdit(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="title" center :visible.sync="dialogFormVisible">
      <el-form :model="info">
        <el-form-item label="省份名称">
          <el-input v-model="info.provinceName" autocomplete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="success" @click="action">确 定</el-button>
      </div>
    </el-dialog>
    <el-row type="flex" justify="center">
      <div>
        <el-pagination
          type="flex"
          justify="space-between"
          background
          layout="sizes, total ,prev, pager, next ,jumper"
          :total="totalElements"
          :page-count="totalPages"
          :page-sizes="[10,15,20,25]"
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
        />
      </div>
    </el-row>
  </div>
</template>

<script>
import { getProvinces, delByid, update, insert, deleteByList } from '@/api/province'
import deletePopover from '@/components/deletePopover'
export default {

  filters: {},
  components: {
    'delete-popover': deletePopover
  },
  data() {
    return {
      title: '', // form 标题
      list: null, // table数据
      listLoading: true, // 是否加载
      totalElements: null, // 总条数
      totalPages: null, // 总页数
      dialogFormVisible: false, // dialog是否可见
      size: 10, // 每一页显示的条数
      keyword: '', // 搜索的关键字
      func: null, // dialog 执行的函数
      multipleSelection: null, // 选择的内容
      info: {
        provinceId: '',
        provinceName: ''
      }
    }
  },
  watch: {
    keyword(val) {
      this.fetchData()
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData(page = 0) {
      this.listLoading = true
      getProvinces({ page, size: this.size, keyword: this.keyword }).then(
        response => {
          this.list = response.data.items
          this.totalElements = response.data.totalElements
          this.totalPages = response.data.totalPages
          this.listLoading = false
        }
      )
    },
    handleCurrentChange(val) {
      this.fetchData(val - 1)
    },
    handleSizeChange(val) {
      this.fetchData()
    },
    handleDelete(id) {
      delByid(id).then(res => {
        this.$notify({
          title: '成功',
          message: `删除ID为${id}的数据成功！`,
          type: 'success'
        })
        this.fetchData()
      })
    },
    update() {
      update(this.info).then(res => {
        this.dialogFormVisible = false
        this.$notify({
          title: '成功',
          message: `更新ID为${this.info.provinceId}的数据成功！`,
          type: 'success'
        })
        this.fetchData()
      })
    },
    insert() {
      insert(this.info).then(res => {
        this.dialogFormVisible = false
        this.$notify({
          title: '成功',
          message: `插入数据成功！`,
          type: 'success'
        })
        this.fetchData()
      })
    },
    handleEdit(row) {
      this.info = row
      this.dialogFormVisible = true
      this.func = this.update
    },
    handleInsert() {
      this.func = this.insert
      this.title = '添加'
      this.info = {
        provinceName: ''
      }
      this.dialogFormVisible = true
    },
    handleSelectionChange(val) {
      this.multipleSelection = val.length == 0 ? null : val
    },
    handleDeleteByList() {
      deleteByList(this.multipleSelection).then(res => {
        this.$notify({
          title: '成功',
          message: `批量删除${this.multipleSelection.length}条数据成功！`,
          type: 'success'
        })
        this.fetchData()
      })
    },
    action() {
      this.func()
    }
  }
}
</script>
