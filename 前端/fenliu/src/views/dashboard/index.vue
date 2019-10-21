<template>
  <div v-cloak class="dashboard-container">
    <div  v-if="checkPermission(['ADMIN'])"> 
      <DepartmentDashboard></DepartmentDashboard>
    </div>
    <el-divider content-position="center">详细信息</el-divider>
    <div>
      <StudentDashboard  v-if="checkPermission(['STUDENT'])"/>
    </div>
  </div>
</template>

<script>
import checkPermission from '@/utils/permission'
import { mapGetters } from 'vuex'
import StudentDashboard from '@/components/StudentDashboard'
import DepartmentDashboard from '@/components/DepartmentDashboard'
export default {
  name: 'Dashboard',
  methods:{
    checkPermission
  },
  computed: {
    ...mapGetters(['name', 'roles'])
  },components: {
    StudentDashboard,
    DepartmentDashboard
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  [v-cloak] {
    display: none;
  }
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
