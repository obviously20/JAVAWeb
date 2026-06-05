<script setup>
import { ref ,onMounted,reactive} from 'vue'
// import axios from 'axios'
import { getDeptList, addDeptApi, queryByIdApi, updateApi, deleteApi } from '@/api/dept.js'
import { ElMessage ,ElMessageBox} from 'element-plus'

// ---------------------------------------------获取部门列表数据------------------------------------------------------------------
// 钩子
onMounted(() => {
  fetchDeptList()
})  

//从后端获取部门列表数据
const fetchDeptList = async () => {
  // const result = await axios.get('https://apifoxmock.com/m1/3128855-1224313-default/depts');
  // if(result.data.code ==1){
  //   DeptList.value = result.data.data
  // }

  // 优化
  const result = await getDeptList();
  if(result.code ==1){
    DeptList.value = result.data
  }
}
const DeptList = ref([])

// ---------------------------------------------新增部门------------------------------------------------------------------------

// 新增部门Dialog对话框
  const dialogFormVisible = ref(false)//1
  const fromtitle = ref('');//3
  const deptForm = ref({name:''});//2

  //4绑定新增部门按钮的点击事件
  const addDept = () => {
    dialogFormVisible.value = true
    fromtitle.value = '新增部门';
    deptForm.value = {
      name: ''
    }
    //8：index.vue中，新增部门时，清空表单数据
    deptFormRef.value.resetFields()
  }
  

  //5绑定新增按钮的点击事件"确定"按钮
  const save = async () => {
    //7：判断表单是否校验通过
    await deptFormRef.value.validate(async validator => {
      if(validator){//7：根据校验结果判断成功=>validator为true
        
        let result;
        if(deptForm.value.id){//xg2:校验deptFrom对象里面是否有id这个属性，有就执行修改，没有就执行新增
          result = await updateApi(deptForm.value)
        }else{ 
          result = await addDeptApi(deptForm.value)
        }

        if(result.code ==1){
        //提示信息
        ElMessage.success('新增成功')
        //关闭对话框
        dialogFormVisible.value = false
        //重新获取部门列表数据
        fetchDeptList()
        }else{
          //提示信息
          ElMessage.error(result.msg)
        }
      }else{//index.vue中，表单校验失败时，validator为false
        ElMessage.error('表单校验失败')
      }
})
    
  }


  // 6：表单的规则校验
  const rules = reactive({
  name: [
    //required:true表示此项必填，message是提示信息，trigger是触发校验的时机（blur表示失去焦点时触发）
    { required: true, message: '部门名称不能为空', trigger: 'blur' },
    //最大值、最小值
    { min: 2, max: 10, message: '部门名称长度必须在2到10个字符之间', trigger: 'blur' }
  ]
})

//7：表单引用
const deptFormRef = ref()
//index.vue中，表单标签的宽度为100px
// const formLabelWidth = '100px'


//----------------------------------------------修改部门------------------------------------------------------------------------
// xg1:根据id查询部门信息
const searchById = async (id) => {
  //点击击编辑按钮，对话框弹出
  dialogFormVisible.value = true
  //对话框标题
  fromtitle.value = '修改部门';
  // 点击编辑按钮，如果表单引用存在，清空表单数据
  if(deptFormRef.value){
    deptFormRef.value.resetFields()
  }
  //根据id查询部门信息，赋值给表单
  const result = await queryByIdApi(id)
  if(result.code ==1){
    deptForm.value = result.data
  }else{
    ElMessage.error(result.msg)
  }
}

//----------------------------------------------删除部门------------------------------------------------------------------------

const deleteById = async(id) =>{
  //点击删除按钮
  ElMessageBox.confirm(
    '你确定删除该部门吗？','提示',
    {confirmButtonText: '确定',cancelButtonText: '取消',type: 'warning',}
  ).then(async() => {//确定按钮的点击事件
      //调用删除接口
      const result = await deleteApi(id);
      if(result.code ==1){
        //提示信息
        ElMessage.success('删除成功')
        //重新获取部门列表数据
        fetchDeptList()
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
  <div id="title">部门管理</div>
  
  <div id="bt">
    <el-button type="primary" @click="addDept">新增部门</el-button><!--4:新增部门按钮的点击事件-->
  </div>

  <div id="table">
    <el-table :data="DeptList" border style="width: 100%">
    <el-table-column type="index" label="序号" width="100" align="center" />
    <el-table-column prop="name" label="部门名称" width="260" align="center" />
    <el-table-column prop="updateTime" label="最后操作时间" width="260" align="center" />
    <el-table-column fixed="right" label="操作" align="center">
      <template #default="scope">
        <el-button type="primary" size="mini" @click="searchById(scope.row.id)">编辑</el-button><!--xg1:编辑按钮的点击事件-->
        <el-button type="danger" size="mini" @click="deleteById(scope.row.id)">删除</el-button>
      </template>
    </el-table-column>

    </el-table>
  </div>

  <!-- 新增部门Dialog对话框 -->   <!--2-->
  <el-dialog v-model="dialogFormVisible" :title="fromtitle" width="500"><!--3:title属性绑定变量fromtitle,可复用-->
    <!-- 6：表单项的校验规则，在el-form标签中定义rules属性 -->
     <!-- 7：表单引用ref，用于在js中调用表单校验方法,validate()方法可以校验表单是否符合规则 -->
    <el-form :model="deptForm" :rules="rules" ref="deptFormRef">
      <!-- 6：在el-form-item标签中定义prop属性，用于指定校验的字段 -->
       <!-- {{ deptForm }} -->
      <el-form-item label="部门名称" :label-width="formLabelWidth" prop="name">
        <el-input v-model="deptForm.name" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确认</el-button><!--xg2:根据id修改部门信息，但这和新增部门大差不差，只需要看deptFrom这个对象里面是否有id这个属性，有就执行修改，没有就执行新增---->
      </div>
    </template>
  </el-dialog>

  <!-- 修改部门 -->
  <!-- 更具id查询部门信息 -->
  <!-- 修改部门Dialog对话框 -->

  <!-- 根据id删除部门 -->
  
  


</template>

<style scoped>
#title {
  font-size: 19px;
  font-weight: bold;/* 加粗字体 */
  margin-bottom: 20px;
}
#bt {
  margin-bottom: 20px;
}
</style>
