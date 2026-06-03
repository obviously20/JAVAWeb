<script setup>
import { reactive, ref ,onMounted} from 'vue'
import axios from 'axios'

const searchEmp = ref({
  name: '',
  gender: '',
  job: '',
})

const search = async () => {
  const response = await axios.get(`https://web-server.itheima.net/emps/list?name=${searchEmp.value.name}&gender=${searchEmp.value.gender}&job=${searchEmp.value.job}`)
  tableData.value = response.data.data
}

const clear = () => {
  searchEmp.value = {
    name: '',
    gender: '',
    job: '',
  };
  search();
}

const tableData = ref([])

// 钩子
onMounted(() => {
  search();
})

</script>


<template>
  <div id="emp-list">
    <!-- 搜索栏 -->
    <el-form :inline="true" :model="searchEmp" class="demo-form-inline">
    <!-- 姓名 -->
      <el-form-item label="姓名" prop="name" width="260px">
        <el-input v-model="searchEmp.name" placeholder="请输入姓名" />
      </el-form-item>

    <!-- 性别 -->
      <el-form-item label="性别" prop="gender" width="260px">
        <el-select v-model="searchEmp.gender" placeholder="请选择性别">
          <el-option label="男" value="1" />
          <el-option label="女" value="2" />
        </el-select>
      </el-form-item>

    <!-- 职位 -->
      <el-form-item label="职位" prop="job" width="260px">
        <el-select v-model="searchEmp.job" placeholder="请选择职位">
          <el-option label="班主任" value="1" />
          <el-option label="讲师" value="2" />
          <el-option label="咨询师" value="3" />
          <el-option label="学工主管" value="4" />
          <el-option label="教研主管" value="5" />
        </el-select>
      </el-form-item>

      <!-- 按键 -->
    <el-form-item>
      <el-button type="primary" @click="search">查询</el-button>
      <el-button @click="clear">清空</el-button>
    </el-form-item>
  </el-form>

  <!-- 表格 -->
   <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="id" label="ID" width="80px" align="center" />
    <el-table-column prop="name" label="姓名" width="100px" align="center" />
    <el-table-column prop="image" label="头像" width="120px" align="center" >
      <template #default="scope">
        <img :src="scope.row.image" height="40px">
      </template>
    </el-table-column>
    <el-table-column prop="gender" label="性别" width="80px" align="center" >
      <template #default="scope">
          {{ scope.row.gender == 1 ? '男' : '女' }}
        </template>
    </el-table-column>
    <el-table-column prop="job" label="职位" width="80px" align="center" >
      <template #default="scope">
          <span v-if="scope.row.job == 1">班主任</span>
          <span v-if="scope.row.job == 2">讲师</span>
          <span v-if="scope.row.job == 3">咨询师</span>
          <span v-if="scope.row.job == 4">学工主管</span>
          <span v-if="scope.row.job == 5">教研主管</span>
        </template>
    </el-table-column>
    <el-table-column prop="entrydate" label="入职日期" width="260px" align="center" />
    <el-table-column prop="updatetime" label="更新时间" width="260px" align="center" />
  </el-table>





    
  

  </div>




</template>



<style scoped>
#emp-list {
  width: 70%;
  margin-left: 15%;
  margin-right: 15%;
}
</style>