import Vue from 'vue';
import Router from 'vue-router';
Vue.use(Router)
Vue.prototype.router = Router

const Login = () => import('../views/login/login.vue')

export default new Router({
  routes: [
    {
      path: '/',
      name: Login,
      component: Login
    }
  ]
})
