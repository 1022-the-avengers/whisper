<template>
  <div>
    <mt-header title="添加好友">
      <router-link to="/home" slot="left">
        <mt-button icon="back">返回</mt-button>
      </router-link>
    </mt-header>

    <mt-search
      v-model="searchContent"
      cancel-text="取消"
      placeholder="输入昵称搜索"
      @keyup.enter.native="search"
    ></mt-search>
    <div class="limit">
      <label>性别:</label>
      <select id="sex">
        <option value="不限">不限</option>
        <option value="男">男</option>
        <option value="女">女</option>
      </select>
      <label>年龄:</label>
      <select id="age">
        <option value="不限">不限</option>
        <option value="0-18">18岁以下</option>
        <option value="18-30">18岁到30岁</option>
        <option value="30-45">30岁到45岁</option>
        <option value="45-100">45以上</option>
      </select>
    </div>

    <div class="users">
      <mt-cell v-for="(value,key) in users" :key="key" @click.native="setFri(key)">
        <span>{{value.nickname}}</span>
        <img slot="icon" v-bind:src="value.pic" width="28" height="28" />
      </mt-cell>
    </div>
  </div>
</template>

<script>
import { MessageBox } from "mint-ui";
export default {
  name: "addfriend",
  data() {
    return {
      searchContent: "",
      users: {}
    };
  },
  methods: {
    search() {
      var param = {};
      var sex = document.getElementById("sex").value;
      var age = document.getElementById("age").value;
      var name = this.$data.searchContent;
      if (name != "") {
        param.nickname = this.$data.searchContent;
      }
      if (sex != "不限") {
        param.gender = sex;
      }
      if (age != "不限") {
        var strs = new Array();
        strs = age.split("-");
        param.minAge = strs[0];
        param.maxAge = strs[1];
      }
      this.axios
        .get("/verification/user/informations", { params: param })
        .then(response => {
          if (response.data.message == "用户资料请求成功") {
            this.users = response.data.data;
            if (this.users.length == 0) {
              MessageBox.alert("无符合条件的好友").then(action => {});
            }
          }
        })
        .catch(err => {
          console.log(err); //异常
        });
    },
    setFri(key) {
      console.log(this.users[key]);
      localStorage.setItem("addfri", JSON.stringify(this.users[key]));
      this.$router.push("/home/confirm");
    }
  }
};
</script>

<style lang="scss" scoped>
@import "./addfriend.scss";
</style>