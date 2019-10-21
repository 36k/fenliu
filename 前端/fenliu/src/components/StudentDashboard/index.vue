<template>
  <el-form ref="form" :rules="rules" :model="form" label-width="150px" @submit.native.prevent>
    <el-form-item label="分配结果">
      <el-input disabled :value="major"/>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="form.email"/>
    </el-form-item>
    <el-form-item label="有结果通知我">
      <el-switch v-model="form.subscribe"/>
    </el-form-item>
  </el-form>
</template>
<script>
import { getInfo, subscribe } from '@/api/user'
import { Message } from 'element-ui'
export default {
  name: 'StudentDashboard',
  data() {
    var checkEmail = (rule, value, callback) => {
      const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
      if (!value) {
        return callback(new Error('邮箱不能为空'))
      }
      setTimeout(() => {
        if (mailReg.test(value)) {
          callback()
        } else {
          callback(new Error('请输入正确的邮箱格式'))
        }
      }, 100)
    }
    return {
      major: '',
      form: {
        email: '',
        subscribe: ''
      },
      rules: {
        email: [{ validator: checkEmail, trigger: 'blur' }]
      }
    }
  }, computed: {
    deliver() {
      return this.form.subscribe
    }
  },
  watch: {
    deliver(newVal, oldVal) {
      if (oldVal !== '') {
        this.$refs['form'].validate(valid => {
          if (valid) {
            subscribe(this.form).then(res => {
              Message({
                type: 'success',
                message: this.form.subscribe
                  ? `已开启订阅，分流结束后，将发送邮件至${this.form.email}`
                  : `订阅已关闭`
              })
            })
          } else {
            this.form.subscribe = false
          }
        })
      }
    }
  }, created() {
    getInfo().then(res => {
      if (res.data.major === null) {
        this.major = '专业分配还未结束'
      } else {
        this.major = res.data.major.majorName
      }
      this.form.email = res.data.email
      this.form.subscribe = res.data.subscribe
    }).catch(error=>{
      Message({
          message: error,
          type: "warning",
          duration: 5 * 1000
        });
    })
  }
}
</script>
<style>
</style>
