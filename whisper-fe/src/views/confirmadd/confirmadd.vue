<template>
  <div class="show">
    <mt-header title="好友信息">
      <router-link to="/main/addfriend" slot="left">
        <mt-button icon="back">返回</mt-button>
      </router-link>
    </mt-header>
    <div class="info">
      <div class="info-head">
        <img :src="info.pic" width="60px" height="60px" />
        <div>
          <div class="name">{{info.nickname}}</div>
          <div>账号：{{info.account}}</div>
          <div>性别：{{info.gender}}</div>
        </div>
      </div>

      <div class="tag">
        <div class="tag-title">印象</div>
        <div class="tag-items">
          <div class="tag-item" v-for="(value,key) in info.impressions" :key="key">{{value}}</div>
        </div>
      </div>
      <mt-field placeholder="输入验证消息" v-model="test"></mt-field>
      <div class="block"></div>
      <mt-button size="large" type="primary" @click="sendMes">发送验证消息</mt-button>
    </div>
  </div>
</template>

<script>
import { MessageBox } from "mint-ui";
export default {
  name: "chat",
  data() {
    return {
      info: {},
      test: ""
    };
  },
  methods: {
    sendMes() {
      var send = {};
      send.id = this.info.id;
      send.content = this.test;
      console.log(send);
      this.axios
        .post("/verification/user/validation",send)
        .then(response => {
          console.log(response.data);
            MessageBox.alert("请求已发送").then(action => {
                this.$router.push({ path: "/main/addfriend" });
            });
        })
        .catch(err => {
          MessageBox.alert("不能重复发送请求").then(action => {});
        });
    }
  },
  created: function() {
    this.info = JSON.parse(localStorage.getItem("addfri"));
  }
};
</script>

<style lang="scss" scoped>
@import "./confirmadd.scss";
</style>