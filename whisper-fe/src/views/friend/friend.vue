<template>
  <div class="fri">
    <mt-header title="link聊天" />

    <div>
      <mt-cell to="/home/addfriend">
        <span>新的朋友</span>
        <img slot="icon" src="/assets/friend/new_friend.png" width="28" height="28" />
      </mt-cell>
      <mt-cell to="/home/modify">
        <span>我的账号</span>
        <img slot="icon" src="/assets/friend/tag.png" width="28" height="28" />
      </mt-cell>
      <mt-cell to="/home/testfriend">
        <span>朋友验证</span>
        <img slot="icon" src="/assets/friend/add_friend.png" width="28" height="28" />
      </mt-cell>
    </div>
    <div class="split"></div>
    <div>
      <div v-for="(value,key) in friends" :key="key">
        <mt-cell :title="key"></mt-cell>
        <mt-cell
          v-for="(value2,key2) in value"
          :key="key2"
          :to="'/home/showfriend?group='+key+'&id='+key2"
        >
          <span>{{value2.nickname}}</span>
          <img slot="icon" v-bind:src="value2.pic" width="28" height="28" />
        </mt-cell>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "friend",
  data() {
    return {
      friends: {}
    };
  },
  created: function() {
    this.axios
      .get("/verification/user/relationships")
      .then((response) => {
        this.friends = response.data.data;
        sessionStorage.setItem("friends",JSON.stringify(response.data.data));
      })
      .catch(err => {
        console.log(error);//异常
      });
  },
  methods: {}
};
</script>

<style lang="scss" scoped>
@import "./friend.scss";
</style>