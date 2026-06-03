import { createApp } from 'vue'
import App from './App.vue'

// import './assets/main.css'

// 引入ElementPlus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 引入ElementPlus的中文语言包
import zhCn from 'element-plus/es/locale/lang/zh-cn'

// 创建Vue实例并挂载到#app元素上，且使用ElementPlus插件use(ElementPlus),并配置中文语言包{locale: zhCn,}
createApp(App).use(ElementPlus, {locale: zhCn,}).mount('#app')
