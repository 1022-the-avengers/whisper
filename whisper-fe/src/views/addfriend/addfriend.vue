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
      placeholder="输入账号搜索"
      @keyup.enter.native="search"
    ></mt-search>
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
      console.log(this.$data.searchContent);
    }
  }
};
</script>

<style lang="scss" scoped>
@import "./addfriend.scss";
</style>