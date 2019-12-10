<template>
  <div class="home-container">
    <a class="avatar-link">
      <img src="https://avatars3.githubusercontent.com/u/32596089?s=40&v=4" alt="头像" class="avatar-img">
    </a>
    <div class="title-container">
        消息盒子
    </div>
    <div class="search-container">
      <input type="text" v-model="searchValue">
    </div>
    <mt-tab-container v-model="selected">
      <mt-tab-container-item id="messages-container">
        <div class="message-list-item">
          <img src="https://avatars3.githubusercontent.com/u/32596089?s=40&v=4" alt="头像" class="avatar-img">
          <div class="message-infomation">
            <span class="message-username">最可爱的xxx</span>
            <span class="message-preview">想你</span>
            <span class="message-unread">1</span>
          </div>
        </div>
      </mt-tab-container-item>
      <mt-tab-container-item id="friends-container">
        朋友
      </mt-tab-container-item>
    </mt-tab-container>
    <mt-tabbar v-model="selected" fixed=true>
        <mt-tab-item id="messages-container">
          消息列表
        </mt-tab-item>
        <mt-tab-item id="friends-container">
          好友列表
        </mt-tab-item>
    </mt-tabbar>
  </div>
</template>

<script>
export default {
    name: "home",
    data() {
        return {
          searchValue: "",
          selected: "messages-container"
        }
    },
    methods: {
      
    }

}
</script>

<style lang="scss" scoped>
  @import './home.scss'
</style>