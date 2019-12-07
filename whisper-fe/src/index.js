import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
Vue.prototype.axios = axios

const root = document.createElement('div') //创建div节点
document.body.appendChild(root) //将div节点添加到body下

new Vue({
  render: (h) => h(App),
  router,
}).$mount(root)