import Vue from 'vue';
import Router from 'vue-router';
Vue.use(Router)
Vue.prototype.router = Router

const Home = () => import('../views/home/home.vue')
const Login = () => import('../views/login/login.vue')
const Register = () => import('../views/register/register.vue')

export default new Router({
  routes: [
    {
      path: '/',
      name: Login,
      component: Login
    },
    {
      path: '/register',
      name: Register,
      component: Register
    },
    {
      path: '/home',
      name: Home,
      component: Home
    }
  ]
})
