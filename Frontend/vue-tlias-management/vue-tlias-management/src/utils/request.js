import axios from 'axios'
import router from '@/router'
import { ElMessage } from 'element-plus'

//创建axios实例对象，以后所有的请求都通过这个实例对象来发送
const request = axios.create({
  baseURL: '/api',//基础路径（前面的基本不变，要变的时候才修改）：后续请求地址会拼接在这个基础路径后面
  timeout: 600000//请求超时时间（单位：毫秒）
})

//axios的请求 request 拦截器, 每次请求获取localStorage中的loginUser, 从中获取到token, 在请求头token中携带到服务端
request.interceptors.request.use(
  (config) => {
    let loginUser = JSON.parse(localStorage.getItem('loginUser'))
    // 判断loginUser是否存在，如果存在，就将token添加到请求头中
    console.log(localStorage.getItem('loginUser'))//console.log("")：是否打印，console.log()：打印loginUser对象
    if (loginUser) {
      config.headers.token = loginUser.token
    }
    return config
  }
)



//axios的响应 response 拦截器
request.interceptors.response.use(
  (response) => { //成功回调
    return response.data//只返回响应体数据（后续调用接口时，拿到的就是响应体数据了）：data列表
  },
  (error) => { //失败回调
    //如果响应的状态码为401, 则路由到登录页面
    if (error.response.status === 401) {
      ElMessage.error('登录失效, 请重新登录')
      router.push('/login')
    }else{
      ElMessage.error('接口访问异常')
    }
    return Promise.reject(error)
  }
)

export default request//导出axios实例对象，后续在其他组件中调用接口时，直接导入这个实例对象即可