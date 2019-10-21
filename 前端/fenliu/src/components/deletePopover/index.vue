<template>
  <el-popover v-model="visible" placement="top" width="160" trigger="manual">
    <p>您确定要删除吗？</p>
    <div style="text-align: right; margin: 0">
      <el-button size="mini" type="text" @click="visible = false">取消</el-button>
      <el-button type="primary" size="mini" @click="handleOk">确定</el-button>
    </div>
    <el-button
      slot="reference"
      icon="el-icon-delete"
      :size="size"
      type="danger"
      @click="handleShow"
    >删除</el-button>
  </el-popover>
</template>
<script>
import { isNull } from 'util'
export default {
  name: 'DeletePopover',
  props: ['id', 'size'],
  data() {
    return {
      visible: false
    }
  },
  methods: {
    handleOk() {
      this.visible = false
      this.$emit('handleDelete', this.id)
    },
    handleShow() {
      if (!isNull(this.id)) {
        this.visible = true
      } else {
        this.$message({
          message: '请至少选择一条数据！',
          type: 'warning'
        })
      }
    }
  }
}
</script>

