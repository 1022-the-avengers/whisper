<template>
  <div>
    <mt-header title="验证好友">
      <router-link to="/home" slot="left">
        <mt-button icon="back">返回</mt-button>
      </router-link>
    </mt-header>

    <mt-cell title="发送的请求"></mt-cell>
    <div class="list">
      <mt-cell :title="value.nickname" v-for="(value,key) in sender" :key="key">
        <mt-badge size="small" type="error" v-show="!value.ifSenderRead">1</mt-badge>
        <span style="padding-left:5px;">{{value.content}}</span>
        <div class="block"></div>
        <span v-show="value.status==1">处理中</span>
        <span v-show="value.status==2">已同意</span>
        <span v-show="value.status==3">已拒绝</span>
        <img slot="icon" :src="value.pic" width="24" height="24" />

      </mt-cell>
    </div>
    <mt-cell title="接收的请求"></mt-cell>
    <div class="list">
      <mt-cell
        :title="value.nickname"
        :label="value.content"
        v-for="(value,key) in receiver"
        :key="key"
      >
        <mt-button type="primary" @click="testFir(key,2)">接收</mt-button>
        <div class="block"></div>
        <mt-button type="danger" @click="testFir(key,3)">拒绝</mt-button>
        <img slot="icon" :src="value.pic" width="24" height="24" />
      </mt-cell>
    </div>
  </div>
</template>

<script>
import { MessageBox } from 'mint-ui';
export default {
  name: "testfriend",
  data() {
    return {
      sender: [],
      receiver: []
    };
  },
  methods: {
    testFir(key,isAgree) {
      var send = {};
      send.id = this.receiver[key].id; 
      send.status = isAgree;
      this.axios
        .post("/verification/user/validation-confirm",send)
        .then(response => {
            MessageBox.alert("已处理请求").then(action => {});
            this.receiver.splice(key,1);
        })
        .catch(err => {
          console.log(err); //异常
        });
    }
  },
  created() {
    this.axios
      .get("/verification/user/validation")
      .then(response => {
        this.sender = response.data.data.sender;
        this.receiver = response.data.data.receiver;
        console.log(this.sender);
      })
      .catch(err => {
        console.log(err); //异常
      });

    this.axios    //标记好友请求已读
      .post("/verification/user/validation-reading")
      .then(response => {
      })
      .catch(err => {
        console.log(err); //异常
      });
    
  }
};
</script>

<style lang="scss" scoped>
@import "./testfriend.scss";
</style>