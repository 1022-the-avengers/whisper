<template>
  <div class="register">
    <div class="header">
      <span @click="goBack" class="iconfont">&#xe61b;</span>
    </div>
    <!--显示信息-->
    <mt-popup type="my-popup" ref="myPopup" v-model="visible">
      <span class="Msg">{{Msg}}</span>
    </mt-popup>

    <div class="register_form">
      <form>
        <img class="register_image" src="assets/logo.png" />
        <div class="content">
          <label class="account_number">
            <i class="iconfont">&#xe62a;</i>
            <input
              type="email"
              name="user_register"
              placeholder=" 请输入邮箱地址"
              required="true"
              title="请输入帐号"
              v-model="email"
              autocomplete="on"
            />
          </label>
          <label class="account_name">
            <i class="iconfont">&#xe62a;</i>
            <input
              type="text"
              name="user_name"
              placeholder=" 请输入帐号昵称"
              required="true"
              title="请输入帐号昵称"
              v-model="nickname"
              autocomplete="on"
            />
          </label>
          <label class="account_password">
            <i class="iconfont">&#xe638;</i>
            <input
              type="password"
              name="user_pass"
              placeholder=" 请输入密码(长度大于八位)"
              required="true"
              title="请输入密码"
              v-model="password"
              autocomplete="on"
            />
          </label>
          <mt-button class="btn_register" @click.prevent="register">注册</mt-button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "register",
  data() {
    return {
      email: "",
      nickname: "",
      password: "",
      Msg: "",
      visible: false
    };
  },
  methods: {
    register() {
      if (this.isEmail(this.email)) {
        if (this.password !== "") {
          let postData = {
            account: this.email,
            password: this.password,
            nickname: this.nickname
          };
          this.axios
            .post("/auth/registration", postData)
            .then(this.todoSomething)
            .catch(err => {
              console.log("error: ", err.response.status);
                this.Msg = '密码少于8位或账号已注册';
                this.Popup();
            });
        } else {
          this.Msg = "密码不能为空";
          this.Popup();
        }
      } else {
        this.Msg = "邮箱有误";
        this.Popup();
      }
    },
    todoSomething(res) {
      this.Msg = res.data.message; //ajax请求发送后返回的信息，看是否注册成功
      this.visible = true;
      if (res) {
        setTimeout(() => {
          localStorage.setItem("chatToken", res.data.token); //本地存储Token注册后直接进入首页
          localStorage.setItem("chatEmail", this.email);
          this.$router.push({ path: "/" });
        }, 2500);
      }
    },
    goBack() {
      this.$router.go(-1);
    }
  }
};
</script>

<style lang="scss" scoped>
@import "./register.scss";
</style>