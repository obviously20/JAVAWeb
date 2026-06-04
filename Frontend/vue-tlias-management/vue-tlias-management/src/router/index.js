import { createRouter, createWebHistory } from 'vue-router'

//引入路由组件
import IndexView from '@/views/index/index.vue'
import ClazzView from '@/views/clazz/index.vue'
import DeptView from '@/views/dept/index.vue'
import EmpView from '@/views/emp/index.vue'
import LogView from '@/views/log/index.vue'
import StuView from '@/views/stu/index.vue'
import EmpReportView from '@/views/report/emp/index.vue'
import StuReportView from '@/views/report/stu/index.vue'
import LayoutView from '@/views/layout/index.vue'
import LoginView from '@/views/login/index.vue'


const router = createRouter({
  
  //history模式，使用HTML5的History API来实现路由的导航，不会在URL中出现#符号
  history: createWebHistory(import.meta.env.BASE_URL),
  //配置路由规则
  routes: [
    {path: '/',
    name: '',
    component: LayoutView,
    //如果访问根路径，会重定向到/index
    redirect: '/index',
    //路由嵌套，LayoutView组件中会有一个<router-view>，用来展示子路由的组件
    children:[
      {path: '/index',name: 'index',component: IndexView},
      {path: '/clazz',name: 'clazz',component: ClazzView},
      {path: '/dept',name: 'dept',component: DeptView},
      {path: '/emp',name: 'emp',component: EmpView},
      {path: '/log',name: 'log',component: LogView},
      {path: '/stu',name: 'stu',component: StuView},
      {path: '/empReport',name: 'empReport',component: EmpReportView},
      {path: '/stuReport',name: 'stuReport',component: StuReportView}
    ]},
    //登录路由，单独展示登录页面，不嵌套在LayoutView中
    {path: '/login',name: 'login',component: LoginView}
  ]
})

export default router
