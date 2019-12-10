<template>
  <div class="fri">
    <mt-header title="link聊天"/>

    <div>
      <mt-cell to="/main/addfriend">
        <span>新的朋友</span>
        <img slot="icon" src="/assets/friend/new_friend.png" width="28" height="28" />
      </mt-cell>
      <mt-cell>
        <span>我的账号</span>
        <img slot="icon" src="/assets/friend/tag.png" width="28" height="28" />
      </mt-cell>
    </div>
    <div class="split"></div>
    <div v-for="item in friends">
      <mt-cell to="/main/showfriend">
        <span>{{item.name}}</span>
        <img slot="icon" v-bind:src="item.photo" width="28" height="28" />
      </mt-cell>
    </div>
  </div>
</template>

<script>
export default {
  name: "friend",
  data() {
    return {
      friends: [
        {
          photo: "/assets/friend/photo.jfif",
          name: "沙雕郑"
        },
        {
          photo: "/assets/friend/photo.jfif",
          name: "郑沙雕"
        },
        {
          photo: "/assets/friend/photo.jfif",
          name: "沙郑雕"
        },
        {
          photo: "/assets/friend/photo.jfif",
          name: "雕沙郑"
        }
      ]
    };
  }
};
</script>

<style lang="scss" scoped>
@import "./friend.scss";
</style>