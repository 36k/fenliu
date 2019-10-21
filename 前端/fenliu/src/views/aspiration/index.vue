<template>
  <div class="app-container">
    <el-table
      :data="tdata"
      :default-sort="{prop: 'aspirations.length', order: ''}"
      style="width: 100%"
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-table
            :default-sort="{prop: 'level', order: ''}"
            :data="props.row.aspirations"
            border
            style="width: 100%"
          >
            <el-table-column prop="level" label="志愿等级"/>
            <el-table-column prop="major.majorName" label="所报专业"/>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column label="学号" prop="userId"/>
      <el-table-column label="姓名" prop="name"/>
      <el-table-column label="成绩" prop="score"/>
      <el-table-column label="是否填报志愿" prop="aspirations.length">
        <template slot-scope="scope">{{ scope.row.aspirations.length==0?'未填报':'已填报' }}</template>
      </el-table-column>
      <el-table-column label="分配的专业">
        <template slot-scope="scope">{{ scope.row.major==null?'未分配专业':scope.row.major.majorName }}</template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { studentInfo } from "@/api/admin";
import {Message} from "element-ui"
export default {
  name: "Aspiration",
  data() {
    return {
      tdata: []
    };
  },
  watch: {},
  created() {
    studentInfo()
      .then(res => {
        console.log(res);
        this.tdata = res.data;
      })
  },
  methods: {}
};
</script>

<style>
</style>
