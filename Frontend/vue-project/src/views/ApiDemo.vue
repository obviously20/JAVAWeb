<!-- 这标签内写JavaScript 代码 -->
<!-- 然后vue工程化一般会用的api风格是组合式API，故在<script setup> -->
<!-- setup：是一个标识，告诉Vue需要进行一些处理，让我们可以更简洁的使用组合式API -->
<script setup>
import { onMounted } from 'vue'//用于在组件挂载后执行某些操作，onMounted函数接受一个回调函数作为参数，当组件被挂载到DOM中时，这个回调函数会被调用。
// - ref()：接收一个内部值，返回一个响应式的ref对象，此对象只有一个指向内部值的属性 value。
import { ref } from 'vue'//用于创建响应式数据，ref函数接受一个初始值，并返回一个响应式对象。当这个对象的值发生变化时，Vue会自动更新相关的组件视图。
const count = ref(0)// 定义一个函数来增加计数器的值
//定义一个函数function increment()
function increment() {
  // 在Vue中的组合式API使用时，是没有this对象的，this对象是undefined。 因此，我们直接使用count.value来访问和修改计数器的值，而不是使用this.count。
  count.value++
}

//调用钩子函数
// - onMounted()：在组合式API中的钩子方法，注册一个回调函数，在组件挂载完成后执行。
onMounted(() => {
  console.log('组件已挂载')
})
</script>

<!-- 这个标签内写HTML 代码 -->
<template>
  <button @click="increment">点击我增加计数器{{ count }}</button>
</template>

<!-- 这个标签内写CSS 样式 -->
 <!-- scoped：用于将样式作用域限制在当前组件内，不会影响到其他组件的样式 -->
<style scoped>

</style>