<template>
  <div class="login">
    <!-- <cube-popup type="my-popup" ref="myPopup" v-model="visible">
      <span class="Msg">{{Msg}}</span>
    </cube-popup>-->
    <mt-popup v-model="visible" position="top">
      <span class="Msg">{{Msg}}</span>
    </mt-popup>
    <form class="login_form">
      <img class="login_image" src="/assets/logo.png"/>
      <div class="content">
        <label class="account_number">
          <i class="iconfont">&#xe62a;</i>
          <input
            type="email"
            name="user_login"
            placeholder=" 请输入邮箱地址"
            required="true"
            title="请输入邮箱地址"
            v-model="email"
            autocomplete="off"
          />
        </label>
        <label class="account_password">
          <i class="iconfont">&#xe638;</i>
          <input
            type="password"
            name="user_pass"
            placeholder=" 请输入密码"
            required="true"
            title="请输入密码"
            v-model="password"
            autocomplete="off"
          />
        </label>
        <div class="user_nav">
          <button type="button" class="btn_login" @click="login">登录</button>
          <router-link to="/register" class="register">注册</router-link>
          <!--<router-link to="/modifyPassword" class="forget">忘记密码</router-link>-->
        </div>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      email: "",
      password: "",
      visible: false,
      Msg: ""
    };
  },
  methods: {
    login() {
      if (this.isEmail(this.email)) {
        let postData = { account: this.email, password: this.password };
        this.axios
          .post("/auth/login", postData)
          .then(this.todoSomething)
          .catch(err => {
            console.log("登录失败err: ", err);
            this.Msg = "登录失败";
            this.Popup();
          });
      } else {
        this.Msg = "邮箱错误";
        this.Popup();
      }
    },
    todoSomething(res) {
      this.Msg = res.data.message;
      this.visible = true;
      if (res) {
        setTimeout(() => {
          localStorage.setItem("chatEmail", this.email);
          localStorage.setItem("chatToken", res.data.token);
          this.$router.push({ path: "/home" });
        }, 500);
      }
    }
  }
};
</script>

<style lang="scss" scoped>
  @import './login.scss'
</style>