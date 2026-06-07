<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRouter } from 'vue-router'

const loginUserName = ref('')
const router = useRouter()

// 从localStorage中获取登录信息
onMounted(() => {
  let loginUser = JSON.parse(localStorage.getItem('loginUser'));
  if(loginUser){
    loginUserName.value = loginUser.name;
  }
})

// 退出登录方法
const logout = () =>{
  //点击退出登录按钮
  ElMessageBox.confirm(
    '你确定退出登录吗？','提示',
    {confirmButtonText: '确定',cancelButtonText: '取消',type: 'warning',}
  ).then(async() => {//确定按钮的点击事件
    //1.提示信息
    ElMessage.success('退出登录成功');
    //2.删除localStorage中的登录信息
     localStorage.removeItem('loginUser');
     //3.跳转到登录页面
     router.push('/login');
    }).catch(() => {//取消按钮的点击事件
      ElMessage.info('已取消退出登录');
    })
}



</script>

<template>
  <div class="common-layout">
    <el-container>
      <!-- Header 区域 -->
      <el-header class="header">
        <span class="title">Tlias智能学习辅助系统</span>
        <span class="right_tool">
          <a href="">
            <el-icon><EditPen /></el-icon> 修改密码 &nbsp;&nbsp;&nbsp; |  &nbsp;&nbsp;&nbsp;
          </a>
          <a href="javascript:void(0)" @click="logout">
            <el-icon><SwitchButton /></el-icon> 退出登录[{{ loginUserName}}]
          </a>
        </span>
      </el-header>
      
      <el-container>
        <!-- 左侧菜单栏 -->
<el-menu router>
  <!-- 首页菜单 -->
  <el-menu-item index="/index">
    <el-icon><Promotion /></el-icon> 首页
  </el-menu-item>
  
  <!-- 班级管理菜单 -->
  <el-sub-menu index="/manage">
    <template #title>
      <el-icon><Menu /></el-icon> 班级学员管理
    </template>
    <el-menu-item index="/clazz">
      <el-icon><HomeFilled /></el-icon>班级管理
    </el-menu-item>
    <el-menu-item index="/stu">
      <el-icon><UserFilled /></el-icon>学员管理
    </el-menu-item>
  </el-sub-menu>
  
  <!-- 系统信息管理 -->
  <el-sub-menu index="/system">
    <template #title>
      <el-icon><Tools /></el-icon>系统信息管理
    </template>
    <el-menu-item index="/dept">
      <el-icon><HelpFilled /></el-icon>部门管理
    </el-menu-item>
    <el-menu-item index="/emp">
      <el-icon><Avatar /></el-icon>员工管理
    </el-menu-item>
  </el-sub-menu>

  <!-- 数据统计管理 -->
  <el-sub-menu index="/report">
    <template #title>
      <el-icon><Histogram /></el-icon>数据统计管理
    </template>
    <el-menu-item index="/empReport">
      <el-icon><InfoFilled /></el-icon>员工信息统计
    </el-menu-item>
    <el-menu-item index="/stuReport">
      <el-icon><Share /></el-icon>学员信息统计
    </el-menu-item>
    <el-menu-item index="/log">
      <el-icon><Document /></el-icon>日志信息统计
    </el-menu-item>
  </el-sub-menu>
</el-menu>
        
        <el-main>
          <!-- 主要内容区域，用来展示子路由的组件 -->
          <router-view></router-view>
        </el-main>
      </el-container>
      
    </el-container>
  </div>
</template>

<style scoped>
.header {
  background-image: linear-gradient(to right, #00547d, #007fa4, #00aaa0, #00d072, #a8eb12);
}

.title {
  color: white;
  font-size: 40px;
  font-family: 楷体;
  line-height: 60px;
  font-weight: bolder;
}

.right_tool{
  float: right;
  line-height: 60px;
}

a {
  color: white;
  text-decoration: none;
}

.aside {
  width: 220px;
  border-right: 1px solid #ccc;
  height: 730px;
}
</style>
