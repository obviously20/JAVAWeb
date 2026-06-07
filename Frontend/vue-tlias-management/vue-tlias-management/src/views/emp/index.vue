<script setup>
import { ref,watch,onMounted } from 'vue'
import { queryPageApi,addEmpApi,queryEmpByIdApi,updateEmpApi,deleteEmpApi } from '@/api/emp.js'
import { getDeptList as queryAllDeptApi } from '@/api/dept.js'
import { ElMessage } from 'element-plus'
import { ElMessageBox } from 'element-plus'

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
  search();//查询员工列表
  getDeptList();//查询部门列表
  // 从localStorage中获取token
  getToken();
})


//新增/修改表单
const employee = ref({
  username: '',
  name: '',
  gender: '',
  phone: '',
  job: '',
  salary: '',
  deptId: '',
  entryDate: '',
  image: '',
  exprList: []
})

// 控制弹窗
const dialogVisible = ref(false)
const dialogTitle = ref('新增员工')

//文件上传
// 图片上传成功后触发
const handleAvatarSuccess = (response) => {
  employee.value.image = response.data
}
// 文件上传之前触发
const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {
    ElMessage.error('只支持上传图片')
    return false
  } else if (rawFile.size / 1024 / 1024 > 10) {
    ElMessage.error('只能上传10M以内图片')
    return false
  }
  return true
}

//新增员工
const addEmp = () => {
  dialogTitle.value = '新增员工';
  dialogVisible.value = true;
  employee.value = {
    username: '',
    name: '',
    gender: '',
    phone: '',
    job: '',
    salary: '',
    deptId: '',
    entryDate: '',
    image: '',
    exprList: []
  }
  // 重置表单
  if(employeeFormRef.value){
      employeeFormRef.value.resetFields()
  }
}

//职位列表数据
const jobs = ref([{ name: '班主任', value: 1 },{ name: '讲师', value: 2 },{ name: '学工主管', value: 3 },{ name: '教研主管', value: 4 },{ name: '咨询师', value: 5 },{ name: '其他', value: 6 }])
//性别列表数据
const genders = ref([{ name: '男', value: 1 }, { name: '女', value: 2 }])
//部门列表数据
const depts = ref([])
//获取部门列表
const getDeptList = async () =>{
  const result = await queryAllDeptApi();
  if(result.code){
    depts.value = result.data
  }
}


//工作经历
//动态添加工作经历 .
const addExprItem = () => {
  employee.value.exprList.push({exprDate: [], begin: '', end: '', company: '', job: ''})
}

//动态删除工作经历 .
const delExprItem = (index) => {
  employee.value.exprList.splice(index, 1)
}

// 监听
watch(()=>employee.value.exprList,(newVal,oldVal)=>{
  if(employee.value.exprList.length>0 && employee.value.exprList){
    employee.value.exprList.forEach(expr => {
      expr.begin = expr.exprDate[0];
      expr.end = expr.exprDate[1];
    })
  }
},{deep:true})

// 保存员工
const save = async () =>{
  await employeeFormRef.value.validate(async valid => {
    if(valid){ // 校验通过
      
      let result;

      if(employee.value.id){
        result = await updateEmpApi(employee.value);
      }else{
        result = await addEmpApi(employee.value);
      }
    
      if(result.code){
        ElMessage.success('新增员工成功')
        dialogVisible.value = false
        search()
      }else {
        ElMessage.error(result.msg)
      }
    }else{// 校验失败
      ElMessage.error('请检查表单数据')
      return
    }
  })
}

//表单校验规则
// 验证规则
const rules = ref({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 20, message: '用户名长度应在2到20个字符之间', trigger: 'blur' }
  ],
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' },
    { min: 2, max: 10, message: '姓名长度应在2到10个字符之间', trigger: 'blur' }
  ],
  gender: [
    { required: true, message: '请选择性别', trigger: 'change' }
  ],
  phone: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
    { pattern: /^1[3-9]\d{9}$/g, message: '请输入有效的手机号', trigger: 'blur' }
  ]
});

// 将表单校验的规则绑定并应用到表单中
const employeeFormRef = ref(null)


//修改员工--根据id查询员工
const editEmp = async (id) => { 
  const result = await queryEmpByIdApi(id);
  if(result.code){
    dialogTitle.value = '修改员工';
    dialogVisible.value = true
    employee.value = result.data

    //对工作经历进行处理
    let exprList = employee.value.exprList;
    if(exprList && exprList.length > 0){
      exprList.forEach((expr) => {
        expr.exprDate = [expr.begin, expr.end];
      })
    }
  }else {
    ElMessage.error(result.msg)
  }
}


//删除员工--单个
const deleteEmp = async (ids) => { 
  //点击删除按钮
  ElMessageBox.confirm(
    '你确定删除该员工吗？','提示',
    {confirmButtonText: '确定',cancelButtonText: '取消',type: 'warning',}
  ).then(async() => {//确定按钮的点击事件
      //调用删除接口
      const result = await deleteEmpApi(ids);
      if(result.code){
        //提示信息
        ElMessage.success('删除成功')
        //重新获取员工列表数据
        search()
      }else{//删除失败
        //提示信息
        ElMessage.error(result.msg)
      }
    }).catch(() => {//取消按钮的点击事件
      ElMessage.info('已取消删除');
    })
}

//记录勾选的员工的id
const selectedIds = ref([]);
//复选框勾选发生变化时触发 - selection: 当前选中的记录 (数组)
const handleSelectionChange = (selection) => {
  selectedIds.value = selection.map( item => item.id);
}
//批量删除员工
const deleteByIds = async () =>{
  //点击删除按钮
  ElMessageBox.confirm(
    '你确定删除该员工吗？','提示',
    {confirmButtonText: '确定',cancelButtonText: '取消',type: 'warning',}
  ).then(async() => {//确定按钮的点击事件
      //调用删除接口
      const result = await deleteEmpApi(selectedIds.value);
      if(result.code){
        //提示信息
        ElMessage.success('删除成功')
        //重新获取员工列表数据
        search()
      }else{//删除失败
        //提示信息
        ElMessage.error(result.msg)
      }
    }).catch(() => {//取消按钮的点击事件
      ElMessage.info('已取消删除');
    })
}


let token = ref('');
// 从localStorage中获取token
const getToken = () =>{
  const loginUser = JSON.parse(localStorage.getItem('loginUser'));
  if(loginUser && loginUser.token){
    token.value = loginUser.token;
  }
}




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
    <el-button type="primary" @click="addEmp">+ 新增员工</el-button>
    <el-button type="danger" @click="deleteByIds">- 批量删除</el-button>
  </div>

  <!-- 表格 -->
   <div class="container" >
    <!-- {{ selectedIds }} -->
    <el-table :data="empList" border style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
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
          <el-button type="primary" size="mini" @click="editEmp(scope.row.id)">编辑</el-button>
          <el-button type="danger" size="mini" @click="deleteEmp(scope.row.id)">删除</el-button>
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

    <!-- 新增员工/修改员工 对话框 -->
     <el-dialog v-model="dialogVisible" :title="dialogTitle">
      <!-- {{ employee }} -->
      <el-form :model="employee" label-width="80px" :rules="rules" ref="employeeFormRef">
        <!-- {/* 基本信息 */} -->
        <!-- {/* 第一行 */} -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="employee.username" placeholder="请输入员工用户名，2-20个字"></el-input>
            </el-form-item>
          </el-col>
          
          <el-col :span="12">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="employee.name" placeholder="请输入员工姓名，2-10个字"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第二行 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="性别" prop="gender">
              <el-select v-model="employee.gender" placeholder="请选择性别" style="width: 100%;">
                <el-option v-for="gender in genders" :key="gender.name" :label="gender.name" :value="gender.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="employee.phone" placeholder="请输入员工手机号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第三行 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="职位">
              <el-select v-model="employee.job" placeholder="请选择职位" style="width: 100%;">
                <el-option v-for="job in jobs" :key="job.name" :label="job.name" :value="job.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="薪资">
              <el-input v-model="employee.salary" placeholder="请输入员工薪资"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第四行 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="所属部门">
              <el-select v-model="employee.deptId" placeholder="请选择部门" style="width: 100%;">
                <el-option v-for="dept in depts" :key="dept.id" :label="dept.name" :value="dept.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="入职日期">
              <el-date-picker v-model="employee.entryDate" type="date" style="width: 100%;" placeholder="选择日期" format="YYYY-MM-DD" value-format="YYYY-MM-DD"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第五行 -->
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="头像">
              <el-upload
                class="avatar-uploader"
                action="/api/upload"
                :headers="{'token':token}"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
                >
                <img v-if="employee.image" :src="employee.image" class="avatar" />
                <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>
        

        <!-- 工作经历 -->
        <!-- 第六行 -->
        <el-row :gutter="10">
          <el-col :span="24">
            <el-form-item label="工作经历">
              <el-button type="success" size="small" @click="addExprItem">+ 添加工作经历</el-button>
            </el-form-item>
          </el-col>
        </el-row>
        
        <!-- 第七行 ...  工作经历 -->
        <el-row :gutter="3" v-for="(item, index) in employee.exprList" :key="index">
          <el-col :span="10">
            <el-form-item size="small" label="时间" label-width="80px">
              <el-date-picker v-model="item.exprDate" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" format="YYYY-MM-DD" value-format="YYYY-MM-DD" ></el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item size="small" label="公司" label-width="60px">
              <el-input v-model="item.company" placeholder="请输入公司名称"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item size="small" label="职位" label-width="60px">
              <el-input v-model="item.job" placeholder="请输入职位"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="2">
            <el-form-item size="small" label-width="0px">
              <el-button type="danger" @click="delExprItem(index)">- 删除</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      
      <!-- 底部按钮 -->
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="save">保存</el-button>
        </span>
      </template>
  </el-dialog>



</template>

<style scoped>
.container {
  margin-top: 15px;
  margin-bottom: 12px;
}

.avatar {
  height: 40px;
}
.avatar-uploader .avatar {
  width: 78px;
  height: 78px;
  display: block;
}
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 78px;
  height: 78px;
  text-align: center;
  border-radius: 10px;
  /* 添加灰色的虚线边框 */
  border: 1px dashed var(--el-border-color);
}
</style>