<template>
  <div>
    <mt-header title="修改个人信息">
      <router-link to="/main" slot="left">
        <mt-button icon="back">返回</mt-button>
      </router-link>
    </mt-header>
    <div class="pic" :style="'background-image:url('+info.pic+')'" @click="getImg"></div>
    <div class="field">
      <mt-field label="昵称 :" placeholder="输入昵称" v-model="info.nickname"></mt-field>
      <mt-field label="账号 :" placeholder="输入账号" v-model="info.account"></mt-field>
      <mt-field label="性别 :" placeholder="输入性别" v-model="info.gender"></mt-field>
    </div>
    <div class="but">
      <mt-button type="primary" size="normal" @click="modify">修改信息</mt-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "modifyinfo",
  data() {
    return {
      info: {
        nickname: "Mroono",
        account: "12345678901@qq.com",
        pic: "/assets/friend/photo.jfif",
        gender: "男",
        impressions: ["123", "123", "123"]
      }
    };
  },
  methods: {
    modify(event) {},
    getImg(event) {
      var file = document.createElement("input");
      file.setAttribute("type", "file");
      file.click();
      var img = file.files[0];
      var imgStr = /\.(jpg|jpeg|png|bmp|BMP|JPG|PNG|JPEG)$/;
      if (!imgStr.test(img.name)) {
        MessageBox.alert("请选择图片文件").then(action => {});
        return false;
      } else if (img.size > 1024 * 25) {
        MessageBox.alert("选择小于25kb的图片").then(action => {});
        return false;
      }
      
    }
  }
};
</script>

<style lang="scss" scoped>
@import "./modifyinfo.scss";
</style>