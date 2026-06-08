<script setup>
import { ref, watch,onMounted } from 'vue'
import { queryPageApi,addClazzApi,queryClazzByIdApi,updateClazzApi,deleteClazzApi } from '@/api/clazz'
import { queryAllApi as queryEmpPageApi } from '@/api/emp'
import {ElMessage,ElMessageBox} from 'element-plus'

// 定义班级搜索的参数
const searchClazz = ref({
  name: '',
  date: [],
  begin: '',
  end: '',
})

// watch监听searchClazz.date变化
watch(()=>searchClazz.value.date, (newVal, oldVal) => {
  if(newVal.length && newVal.length==2){
    searchClazz.value.begin = newVal[0];
    searchClazz.value.end = newVal[1];
  }else{
    searchClazz.value.begin = '';
    searchClazz.value.end = '';
  }
})

// 接受查询到的班级列表
const clazzs = ref([])
// 搜索方法
const search = async () => {
  const result = await queryPageApi(searchClazz.value.name,
                                    searchClazz.value.begin,searchClazz.value.end,
                                    currentPage.value,pageSize.value);
  if(result.code){
    clazzs.value = result.data.rows;
    total.value = result.data.total;
  }
}

// 定义清空方法
const clear = () => {
  searchClazz.value = {name: '',date: [],begin: '',end: ''};
  search();
}


// 分页组件
const total = ref(0)
const currentPage = ref(1)
const pageSize = ref(10)
const background = ref(false)
const handleSizeChange = (val) => {//每页条数改变时触发
  search();
}
const handleCurrentChange = (val) => {//当前页改变时触发
  search();
}

// 钩子
onMounted(() => {
  search();
  getMasters();
})


// 新增班级弹窗---------------
const dialogFormVisible = ref(false)//新增班级弹窗是否显示
const formLabelWidth = '140px'

const form = ref({
  id: '',
  name: '',
  room: '',
  beginDate: '',
  endDate: '',
  masterId: '',
  subject: ''
})

// 获取所有班主任（获取所有员工信息）
const masters = ref([])
const getMasters = async () => {
   let result = await queryEmpPageApi();
  if(result.code){
    masters.value = result.data;
  }
}

// 学科列表
const subjects = ref([{ name: 'Java', value: 1 },{ name: '前端', value: 2 },{ name: '大数据', value: 3 },{ name: 'Python', value: 4 },{ name: 'Go', value: 5 },{ name: '嵌入式', value: 6 }])

let formTitle = ref('')
// 新增班级方法
const addClazz = () => {
  dialogFormVisible.value = true;
  formTitle.value = '新增班级';
  form.value = {name: '',room: '',beginDate: '',endDate: '',masterId: '',subject: ''};
}

// 新增/修改班级方法
const save = async () => { 
  await clazzFormRef.value.validate(async valid => {
  if(valid){
    let result;

    if(form.value.id){//修改班级
      result = await updateClazzApi(form.value);
    }else{//新增班级
      result = await addClazzApi(form.value);
    }


    if(result.code){
      ElMessage.success('新增/修改班级成功');
      dialogFormVisible.value = false;
      search();
    }else{
      ElMessage.error(result.msg);
    }
  }else {//校验失败
    ElMessage.error('请检查表单数据');
    return;
  }
})
  
  
  
  
}

// 表单校验规则
const clazzFormRef = ref()
const rules = ref({
  name: [
    { required: true, message: '班级名称为必填项', trigger: 'blur' },
    { min: 4, max: 30, message: '用户名长度为4-30个字', trigger: 'blur' }
  ],
  room: [
    { min: 1, max: 20, message: '班级教室长度为1-20个字', trigger: 'blur' }
  ],
  beginDate: [{ required: true, message: '开课时间为必填项', trigger: 'change' }],
  endDate: [{ required: true, message: '结课时间为必填项', trigger: 'change' }],
  subject: [{ required: true, message: '学科为必填项', trigger: 'change' }]
})

//修改班级方法
const editClazz = async (id) => { 
  const result = await queryClazzByIdApi(id);
  if(result.code){
    dialogFormVisible.value = true
    formTitle.value = '修改班级';
    form.value = result.data;

  }else {
    ElMessage.error(result.msg)
  }
}


// 删除班级方法
const deleteClazz = async (id) => { 
  ElMessageBox.confirm(
    '你确定删除该班级吗？','提示',
    {confirmButtonText: '确定',cancelButtonText: '取消',type: 'warning',}
  ).then(async() => {//确定按钮的点击事件
      //调用删除接口
      const result = await deleteClazzApi(id);
      if(result.code){
        //提示信息
        ElMessage.success('删除成功')
        //刷新列表
        search(); 
      }else{//删除失败
        //提示信息
        ElMessage.error(result.msg)
      }
  }).catch(() => {//取消按钮的点击事件
      ElMessage.info('已取消删除');
    })
}


 
</script>

<template>
  <!-- 标题 -->
  <h3>班级管理</h3>

  <!-- 搜索栏 -->
  <div class="container">
    <!-- {{ searchClazz }} -->
     <el-form :inline="true" :model="searchClazz" class="demo-form-inline" >
    <!-- 班级名称搜索 -->
      <el-form-item label="班级名称" width="200px">
      <el-input v-model="searchClazz.name" placeholder="请输入班级名称" clearable />
    </el-form-item>

    <!-- 结课时间搜索 -->
    <el-form-item label="结课时间" width="220px">
      <el-date-picker
        v-model="searchClazz.date"
        type="datetimerange"
        range-separator="到"
        start-placeholder="开始时间"
        end-placeholder="结束时间"
        value-format="YYYY-MM-DD"
      />
    </el-form-item>
    
    <!-- 查询/重置 按钮 -->
    <el-form-item>
      <el-button type="primary" @click="search">查询</el-button>
      <el-button type="info" @click="clear">清空</el-button>
    </el-form-item>
  </el-form>
  </div>

  <!-- 新增班级按键 -->
  <div class="container">
    <el-button type="primary" @click="addClazz" width="180px" >+ 新增班级</el-button>
  </div>

  <!-- 查询到的班级列表 -->
  <div class="container">
    <el-table :data="clazzs" border style="width: 100%">
      <el-table-column type="index" label="序号" width="60"  align="center" />
    <el-table-column prop="name" label="班级名称" width="150" align="center" />
    <el-table-column prop="room" label="班级教室" width="100" align="center" />
    <el-table-column prop="masterName" label="班主任" width="110" align="center" />
    <el-table-column prop="beginDate" label="开课时间" width="130" align="center" />
    <el-table-column prop="endDate" label="结课时间" width="130" align="center" />
    <el-table-column prop="status" label="状态" width="100" align="center" />
    <el-table-column prop="updateTime" label="最后操作时间" width="160" align="center" />
    <el-table-column label="操作" align="center" >
      <template #default="scope">
        <el-button type="primary" size="small" @click="editClazz(scope.row.id)">修改</el-button>
        <el-button type="danger" size="small" @click="deleteClazz(scope.row.id)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  </div>

  <div class="container">
    <!-- 分页组件-->
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[5, 10, 20, 30, 40, 50,75,100,200]"
      :background="background"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>


  <!-- 新增班级弹窗 -->
    <el-dialog v-model="dialogFormVisible" :title="formTitle" width="500">
    <el-form :model="form" ref="clazzFormRef" :rules="rules">
      <!-- {{ form }} -->
      
      <el-form-item label="班级名称" :label-width="formLabelWidth" prop="name">
        <el-input v-model="form.name" autocomplete="off" placeholder="请输入班级名称" />
      </el-form-item>

      <el-form-item label="班级教室" :label-width="formLabelWidth" prop="room">
        <el-input v-model="form.room" autocomplete="off" placeholder="请输入班级教室" />
      </el-form-item>

      <el-form-item label="开课时间" :label-width="formLabelWidth" prop="beginDate">
        <el-date-picker v-model="form.beginDate" type="date" style="width: 100%;" placeholder="请选择开课时间" format="YYYY-MM-DD" value-format="YYYY-MM-DD"></el-date-picker>
      </el-form-item>

      <el-form-item label="结课时间" :label-width="formLabelWidth" prop="endDate">
        <el-date-picker v-model="form.endDate" type="date" style="width: 100%;" placeholder="请选择结课时间" format="YYYY-MM-DD" value-format="YYYY-MM-DD"></el-date-picker>
      </el-form-item>

      <el-form-item label="班主任" :label-width="formLabelWidth" prop="masterId">
        <el-select v-model="form.masterId" placeholder="请选择">
          <el-option v-for="item in masters" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>

      <el-form-item label="学科" :label-width="formLabelWidth" prop="subject">
        <el-select v-model="form.subject" placeholder="请选择">
          <el-option v-for="item in subjects" :label="item.name" :value="item.value" />
        </el-select>
      </el-form-item>
    </el-form>

    <!-- 保存/取消 按钮 -->
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" @click="save">保存</el-button>
        <el-button type="info" @click="dialogFormVisible = false">取消</el-button>
      </div>
    </template>
  </el-dialog>

  <!-- 修改 -->
  <!-- 根据班级ID查询班级详情 -->
   <!-- 修改完的数据用form保存 -->

   <!-- 删除班级 -->
  













</template>

<style scoped>
.demo-form-inline .el-input {
  --el-input-width: 220px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}

.container {
  margin-top: 20px;
}


</style>