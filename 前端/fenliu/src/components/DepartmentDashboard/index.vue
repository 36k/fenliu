<template>
  <div>
    <el-row type="flex" justify="space-between" style="padding-bottom:40px">
      <el-col :span="12">
        <div class="dashboard-text">{{name}}</div>
      </el-col>
      <el-col :span="2">
        <el-button type="primary" @click="dialogVisible = true">一键分流</el-button>
      </el-col>
    </el-row>
    <el-dialog title="调整各专业人数" :visible.sync="dialogVisible" :center="true" width="30%">
      <div class="block">
        <span>{{ majors[0].majorName }}</span>
        <el-slider v-model="majors[0].majornum" :max="max" show-stops/>
      </div>
      <div class="block">
        <span>{{ majors[1].majorName }}</span>
        <el-slider v-model="majors[1].majornum" :max="max" show-stops/>
      </div>
      <div class="block">
        <span>{{ majors[2].majorName }}</span>
        <el-slider v-model="majors[2].majornum" :max="max" show-stops/>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handlefenliu">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { MapTOJson } from "@/utils";
import { fenliu, count } from "@/api/admin";
import { getInfo } from "@/api/user";
import { Message } from "element-ui";
export default {
  name: "DepartmentDashboard",
  data() {
    return {
      max: 0,
      name: "",
      dialogVisible: false,
      majors: [
        {
          majorName: ""
        },
        {},
        {}
      ]
    };
  },
  created() {
    count().then(res => {
      this.max = res.data;
    }).catch(e=>{

    })
    getInfo()
      .then(res => {
        this.majors = res.data.department.majors;
        this.name = res.data.name;
        console.log(this.majors);
      })
      .catch(error => {
      });
  },
  methods: {
    handlefenliu() {
      console.log(this.majors);
      const d = new Map();
      this.majors.forEach(item => {
        d.set(item.majorId, item.majornum);
      });
      fenliu(JSON.parse(MapTOJson(d))).then(res => {
        Message({
          type: "success",
          message: "分流成功"
        });
        this.dialogVisible = false;
      });
    }
  }
};
</script>
<style>
</style>
