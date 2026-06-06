<script setup>
import { ref,watch,onMounted } from 'vue'
import { queryPageApi } from '@/api/emp.js'

// 定义搜索员工的表单数据
const SearchEmp = ref({
  name: '',
  gender: '',
  date: [],
  begin:'',
  end:''
})

// 定义查询方法
const search = async () => {
  const result = await queryPageApi(SearchEmp.value.name,SearchEmp.value.gender,
                                    SearchEmp.value.begin,SearchEmp.value.end,
                                    currentPage.value,pageSize.value);
  if(result.code){
    empList.value = result.data.rows;
    total.value = result.data.total;
  }
}

// 定义清空方法
const clear = () => {
  SearchEmp.value = {name: '',gender: '',date: [],begin:'',end:''};
  search()
}

// 为了对应请求参数，begin 和 end 我们要利用vue中的watch监听date 属性，在date属性变化时，将date[0]和date[1]分别赋给begin和end
watch(()=>SearchEmp.value.date,(newVal,oldVal)=>{
  if(newVal.length==2){
    SearchEmp.value.begin = newVal[0];
    SearchEmp.value.end = newVal[1];
  }else{
    SearchEmp.value.begin = '';
    SearchEmp.value.end = '';
  }
})

// 获取员工列表
const empList = ref([])

// 分页条所需属性
const total = ref(0)
const currentPage = ref(1)//当前页数
const pageSize = ref(10)//每页条数
const background = ref(false)

const handleSizeChange = (val) => {//每页条数改变时触发
  search();
}
const handleCurrentChange = (val) => {//当前页改变时触发
  search();
}

// 钩子
onMounted(()=>{
  search()
})




</script>

<template>
  <!-- 标题 -->
  <h3>员工管理</h3>

  <!-- 搜索栏 -->
  <div class="container">
    <!-- {{ SearchEmp }} -->
    <el-form :inline="true" :model="SearchEmp" class="demo-form-inline">
    <el-form-item label="姓名">
      <el-input v-model="SearchEmp.name" placeholder="请输入员工姓名" clearable />
    </el-form-item>
    <el-form-item label="性别">
      <el-select
        v-model="SearchEmp.gender" placeholder="请选择" clearable>
        <el-option label="男" value="1" />
        <el-option label="女" value="2" />
      </el-select>
    </el-form-item>
    <el-form-item label="入职时间">
      <el-date-picker
        v-model="SearchEmp.date"
        type="datetimerange"
        range-separator="到"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        value-format="YYYY-MM-DD"
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="search">查询</el-button>
      <el-button type="info" @click="clear">清空</el-button>
    </el-form-item>
  </el-form>
  </div>

  <!-- 表格上面的两个按钮 -->
   <div class="container">
    <el-button type="primary" @click="">+ 新增员工</el-button>
    <el-button type="danger" @click="">- 批量删除</el-button>
  </div>

  <!-- 表格 -->
   <div class="container">
    <el-table :data="empList" border style="width: 100%">
      <el-table-column prop="name" label="姓名" width="120" align="center" />
      <el-table-column label="性别" width="90" align="center" >
         <template #default="scope" >
        {{ scope.row.gender == 1 ? '男' : '女' }}
      </template>
      </el-table-column>
      <el-table-column label="头像" width="120" align="center" >
        <template #default="scope" >
          <img :src="scope.row.image" width="50" />
        </template>
      </el-table-column>
      <el-table-column prop="deptName" label="所属部门" width="120" align="center" />
      <el-table-column label="职位" width="120" align="center" >
        <template #default="scope">
          <span v-if="scope.row.job == 1">班主任</span>
          <span v-else-if="scope.row.job == 2">讲师</span>
          <span v-else-if="scope.row.job == 3">学工主管</span>
          <span v-else-if="scope.row.job == 4">教研主管</span>
          <span v-else-if="scope.row.job == 5">咨询师</span>
          <span v-else>其他</span>
        </template>
      </el-table-column>
      <el-table-column prop="entryDate" label="入职日期" width="180" align="center" />
      <el-table-column prop="updateTime" label="最后操作时间" width="180" align="center" />
      <el-table-column  label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="mini" @click="">编辑</el-button>
          <el-button type="danger" size="mini" @click="">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
   </div>

   <!-- 分页条 -->
  <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[5,10, 20, 30, 40,75,100,200]"
      :background="background"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />


</template>

<style scoped>
.container {
  margin-top: 15px;
  margin-bottom: 12px;
}
</style>