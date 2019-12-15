<template>
  <div class="fri">
    <mt-header title="link聊天" />

    <div>
      <mt-cell to="/main/addfriend">
        <span>新的朋友</span>
        <img slot="icon" src="/assets/friend/new_friend.png" width="28" height="28" />
      </mt-cell>
      <mt-cell to="/main/modify">
        <span>我的账号</span>
        <img slot="icon" src="/assets/friend/tag.png" width="28" height="28" />
      </mt-cell>
    </div>
    <div class="split"></div>
    <div>
      <div v-for="(value,key) in friends" :key="key">
        <mt-cell :title="key"></mt-cell>
        <mt-cell v-for="(value2,key2) in value" :key="key2" :to="'/main/showfriend?group='+key+'&id='+key2" >
          <span>{{value2.nickname}}</span>
          <img slot="icon" v-bind:src="value2.pic" width="28" height="28"/>
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
      friends:{}
    };
  },
  created:function(){
    console.log(JSON.parse(sessionStorage.getItem("friends")));
    this.friends=JSON.parse(sessionStorage.getItem("friends"));
  },
  methods:{
  }
};
</script>

<style lang="scss" scoped>
@import "./friend.scss";
</style>