import axios from 'axios'

//创建axios实例对象，以后所有的请求都通过这个实例对象来发送
const request = axios.create({
  baseURL: '/api',//基础路径（前面的基本不变，要变的时候才修改）：后续请求地址会拼接在这个基础路径后面
  timeout: 600000//请求超时时间（单位：毫秒）
})

//axios的响应 response 拦截器
request.interceptors.response.use(
  (response) => { //成功回调
    return response.data//只返回响应体数据（后续调用接口时，拿到的就是响应体数据了）：data列表
  },
  (error) => { //失败回调
    return Promise.reject(error)
  }
)

export default request//导出axios实例对象，后续在其他组件中调用接口时，直接导入这个实例对象即可