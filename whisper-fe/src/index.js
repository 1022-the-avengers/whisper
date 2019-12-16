import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
Vue.prototype.axios = axios
// axios.defaults.baseURL = 'http://192.168.1.105:8080' // 本地测试
axios.defaults.baseURL = 'http://192.168.43.85:8080'

import SockJS from "sockjs-client";
import Stomp from "stompjs";
Vue.prototype.SockJS = SockJS
Vue.prototype.Stomp = Stomp

import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'
Vue.use(MintUI)
import './assets/iconfont/iconfont.css'

axios.interceptors.request.use(config => {
  // console.log("request config:  ", config)
  const token = localStorage.getItem("chatToken");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config
}, err => {
  this.$store.state.data = '请求超时！'
  this.Popup(this.$store.state.data)
  return Promise.resolve(err)
})
axios.interceptors.response.use(respone => {
  // console.log("respone :", respone)
  return respone
}, err => {
  if (err.respone.status === 500 || err.respone.status === 504 || err.respone.status === 404) {
    this.$store.state.data = '服务器炸了⊙﹏⊙∥'
    this.Popup(this.$store.state.data)
    console.log("err respone", err.respone.status + this.$store.state.data)
  } else {
    this.$store.state.data = '服务发生错误!'
    this.Popup(this.$store.state.data)
  }
  return Promise.resolve(err)
})

//全局注册，方便在所有组件调用
//验证邮箱格式是否正确
Vue.prototype.isEmail = function (str) {
  let myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/
  if (myReg.test(str)) {
    return true
  } else {
    return false
  }
}
//控制Toast显隐
Vue.prototype.Popup = function (msg) {
  if (msg !== undefined) {
    this.Msg = msg
  }
  this.visible = true
  setTimeout(() => {
    this.visible = false
  }, 1000)
}

const root = document.createElement('div') //创建div节点
document.body.appendChild(root) //将div节点添加到body下

new Vue({
  render: (h) => h(App),
  router,
}).$mount(root)