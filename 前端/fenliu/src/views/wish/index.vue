<template>
  <div class="app-container">
    <el-form ref="form" label-width="80px" :rules="rules" :model="checkform">
      <el-form-item label="姓名">
        <el-input disabled :value="name"/>
      </el-form-item>
      <el-form-item label="学号">
        <el-input disabled :value="userId"/>
      </el-form-item>
      <el-form-item label="系别">
        <el-input disabled :value="department.departmentName"/>
      </el-form-item>
      <el-form-item label="志愿1" prop="aspiration1">
        <el-select
          v-model="checkform.aspiration1"
          placeholder="请选择第一志愿"
          style="width:100% !important"
        >
          <el-option
            v-for="(item,index) in department.majors"
            :key="index"
            :label="item.majorName"
            :value="item.objectId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="志愿2" prop="aspiration2">
        <el-select
          v-model="checkform.aspiration2"
          placeholder="请选择第二志愿"
          style="width:100% !important"
        >
          <el-option
            v-for="(item,index) in department.majors"
            :key="index"
            :label="item.majorName"
            :value="item.objectId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="志愿3" prop="aspiration3">
        <el-select
          v-model="checkform.aspiration3"
          placeholder="请选择第三志愿"
          style="width:100% !important"
        >
          <el-option
            v-for="(item,index) in department.majors"
            :key="index"
            :label="item.majorName"
            :value="item.objectId"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handlePost">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { Message } from "element-ui";
import { getInfo, reviseAspiration } from "@/api/user";
export default {
  data() {
    return {
      name: "",
      userId: "",
      department: {},
      majors: "",
      aspirations: [
        {
          level: "1",
          major: {
            objectId: "",
            majorId: "",
            majorName: ""
          }
        },
        {
          level: "2",
          major: {
            objectId: "",
            majorId: "",
            majorName: ""
          }
        },
        {
          level: "3",
          major: {
            objectId: "",
            majorId: "",
            majorName: ""
          }
        }
      ],
      checkform: {
        aspiration1: "",
        aspiration2: "",
        aspiration3: ""
      },
      // 表单验证
      rules: {
        aspiration1: [
          { required: true, message: "请选择第一个志愿", trigger: "change" }
        ],
        aspiration2: [
          { required: true, message: "请选择第二个志愿", trigger: "change" }
        ],
        aspiration3: [
          { required: true, message: "请选择第三个志愿", trigger: "change" }
        ]
      }
    };
  },
  computed: {
    a1() {
      return this.checkform.aspiration1;
    },
    a2() {
      return this.checkform.aspiration2;
    },
    a3() {
      return this.checkform.aspiration3;
    }
  },
  watch: {
    a1(newval, oldval) {
      if (oldval !== newval && newval !== "") {
        this.aspirations[0].major.objectId = newval;
        this.aspirations[0].major.majorId = this.majors.get(newval).majorId;
        this.aspirations[0].major.majorName = this.majors.get(newval).majorName;
      }
    },
    a2(newval, oldval) {
      if (oldval !== newval && newval !== "") {
        this.aspirations[1].major.objectId = newval;
        this.aspirations[1].major.majorId = this.majors.get(newval).majorId;
        this.aspirations[1].major.majorName = this.majors.get(newval).majorName;
      }
    },
    a3(newval, oldval) {
      if (oldval !== newval && newval !== "") {
        this.aspirations[2].major.objectId = newval;
        this.aspirations[2].major.majorId = this.majors.get(newval).majorId;
        this.aspirations[2].major.majorName = this.majors.get(newval).majorName;
      }
    }
  },
  created() {
    getInfo().then(res => {
      const data = res.data;
      this.department = data.department;
      this.userId = data.userId;
      this.name = data.name;
      const map = new Map();
      data.department.majors.forEach(element => {
        map.set(element.objectId, element);
      });
      this.majors = map;
      if (JSON.stringify(data.aspirations) !== "[]") {
        this.checkform.aspiration1 = data.aspirations[0].major.objectId;
        this.checkform.aspiration2 = data.aspirations[1].major.objectId;
        this.checkform.aspiration3 = data.aspirations[2].major.objectId;
      }
    }).catch(e => {
      
    })
  },
  methods: {
    handlePost() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          reviseAspiration({ aspirations: this.aspirations })
            .then(res => {
              if (res.data == null) {
                Message({
                  message: "志愿专业重复",
                  type: "warning",
                  duration: 5 * 1000
                });
              }
              Message({
                message: `${res.data.name}的志愿提交成功`,
                type: "success",
                duration: 5 * 1000
              });
            })
            .catch(error => {
              console.log(error);
              Message({
                message: error,
                type: "error",
                duration: 5 * 1000
              });
              this.loading = false;
            });
        }
      });
    }
  }
};
</script>
