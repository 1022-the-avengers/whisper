import Vue from 'vue';
import Router from 'vue-router';
Vue.use(Router)
Vue.prototype.router = Router


const Login = () => import('../views/login/login.vue')    //登录界面
const Main = () => import('../views/main/main.vue')    //主界面
const AddFriend = () => import('../views/addfriend/addfriend.vue')    //添加好友界面
const ShowFriend = () => import('../views/showfriend/showfriend.vue')    //展示好友信息 
// const Home = () => import('../views/home/home.vue')
// const Login = () => import('../views/login/login.vue')
const Register = () => import('../views/register/register.vue')
const Chat = () => import('../views/chat/chat.vue')

export default new Router({
  routes: [
    {
      path: '/',
      name: Login,
      component: Login
    },
    {
      path: '/main',
      name: Main,
      component: Main
    },
    {
      path: '/main/addfriend',
      name: AddFriend,
      component: AddFriend
    },
    {
      path: '/main/showfriend',
      name: ShowFriend,
      component: ShowFriend},
    {  path: '/register',
      name: Register,
      component: Register
    },
    {
      path: '/chat',
      name: Chat,
      component: Chat
    }
  ]
})
