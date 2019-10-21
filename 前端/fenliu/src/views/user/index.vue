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
      <el-table-column label="用户编号">
        <template slot-scope="scope">{{ scope.row.userId }}</template>
      </el-table-column>
      <el-table-column label="用户名称">
        <template slot-scope="scope">{{ scope.row.userName }}</template>
      </el-table-column>
      <el-table-column label="电子邮箱">
        <template slot-scope="scope">{{ scope.row.email }}</template>
      </el-table-column>
      <el-table-column label="用户类型">
        <template slot-scope="scope">{{ scope.row.roles[0].roleName }}</template>
      </el-table-column>
      <el-table-column label="管理的省份">
        <template
          slot-scope="scope"
        >{{ scope.row.province==null?"":scope.row.province.provinceName }}</template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="300">
        <template slot-scope="scope">
          <delete-popover :id="scope.row.userId" size="small" @handleDelete="handleDelete" />
          <el-button type="primary" icon="el-icon-refresh" size="small" @click="handleReset">重置密码</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="title" center :visible.sync="dialogFormVisible">
      <el-form :model="info">
        <el-form-item label="用户名">
          <el-input v-model="info.userName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="info.email" autocomplete="off" />
        </el-form-item>
        <el-form-item label="用户类型">
          <el-select v-model="info.roles[0].roleId" style="width:100%" placeholder="请选择用户类型">
            <el-option label="普通用户" value="1" />
            <el-option label="管理员" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="省份">
          <el-select v-model="info.province.provinceId" style="width:100%" placeholder="请选择省份">
            <el-option
              v-for="province in provinces"
              :key="province.provinceId"
              :label="province.provinceName"
              :value="province.provinceId"
            />
          </el-select>
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
import { getList, delByid, update, insert, deleteByList, resetPassword } from '@/api/user'
import { getProvinceList } from '@/api/province'
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
        userId: '',
        userName: '',
        email: '',
        province: {
          provinceId: '',
          provinceName: ''
        },
        roles: [{
          roleId: '',
          roleName: ''
        }]
      },
      provinces: [
        {
          provinceId: '',
          provinceName: ''
        }
      ]
    }
  },
  watch: {
    keyword(val) {
      this.fetchData()
    },
    info(val) {
      if (val.province == null) {
        val.province = {
          provinceId: '',
          provinceName: ''
        }
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData(page = 0) {
      this.listLoading = true
      getList({ page, size: this.size, keyword: this.keyword }).then(
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
      this.size = val
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
          message: `更新ID为${this.info.userId}的数据成功！`,
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
      getProvinceList().then(res => {
        this.func = this.update
        this.title = '修改'
        this.info = row
        this.provinces = res.data
        this.dialogFormVisible = true
      })
    },
    handleInsert() {
      getProvinceList().then(res => {
        this.func = this.insert
        this.title = '添加'
        this.info = {
          city: '',
          street: '',
          zip: '',
          province: {
            provinceId: '',
            provinceName: ''
          },
          roles: [{
            roleId: '',
            roleName: ''
          }]
        }
        this.provinces = res.data
        this.dialogFormVisible = true
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val.length
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
    handleReset() {
      resetPassword().then(res => {
        this.$notify({
          title: '成功',
          message: `重置ID为${this.info.userId}的密码为123456！`,
          type: 'success'
        })
      })
    },
    action() {
      this.func()
    }
  }
}
</script>
