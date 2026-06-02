import { createApp } from 'vue'
import App from './App.vue'

//这个文件是vue项目的入口文件，主要功能是创建一个vue应用实例，并将App.vue组件挂载到id为app的DOM元素上。App.vue是整个应用的根组件，其他组件都将作为它的子组件进行渲染。
// import './assets/main.css'//引入全局样式文件，这样在整个应用中都可以使用这些样式。

createApp(App).mount('#app')
