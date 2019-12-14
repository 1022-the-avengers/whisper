<template>
  <div class="fri">
    <mt-header title="link聊天" />

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
    <div>
      <div v-for="(value,key) in friends" :key="key">
        <mt-cell :title="key"></mt-cell>
        <mt-cell v-for="item in value" :key="item.key" to="/main/showfriend">
          <span>{{item.nickname}}</span>
          <img slot="icon" v-bind:src="item.pic" width="28" height="28" />
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
      friends: {
        高中: [
          {
            id: 5,
            nickname: "Marron",
            pic: null,
            gender: "男",
            age: 38
          },
          {
            id: 6,
            nickname: "康王",
            pic: null,
            gender: null,
            age: 45
          }
        ],
        小学: [
          {
            id: 4,
            nickname: "Marron",
            pic: "sdfdsfdf",
            gender: "女",
            age: 18
          }
        ]
      }
    };
  }
};
</script>

<style lang="scss" scoped>
@import "./friend.scss";
</style>