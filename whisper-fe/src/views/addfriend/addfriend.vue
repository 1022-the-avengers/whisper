<template>
  <div>
    <mt-header title="添加好友">
      <router-link to="/main" slot="left">
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
  </div>
</template>

<script>
export default {
  name: "addfriend",
  data() {
    return {
      searchContent: ""
    };
  },
  methods: {
    search() {
      var param = {};
      var sex = document.getElementById("sex").value;
      var age = document.getElementById("age").value;
      var name = this.$data.searchContent;
      if(name!=""){
              param.nickname = this.$data.searchContent;
      }
      if(sex!="不限"){
        param.gender = sex;
      }
      if(age!="不限"){
        var strs = new Array();
        strs=age.split("-");
        param.minAge = strs[0];
        param.minAge = strs[1];
      }
      console.log(param);

      this.axios
        .get("/verification/user/informations", { params: param })
        .then(response => {
          console.log(response.data);
        })
        .catch(err => {
          console.log(err); //异常
        });
    }
  }
};
</script>

<style lang="scss" scoped>
@import "./addfriend.scss";
</style>