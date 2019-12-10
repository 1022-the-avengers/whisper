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
        <div v-if="unreadMessages">
          <div class="message-list-item" v-for="(message,id) of unreadMessages" v-bind:key="id">
            <img src="https://avatars3.githubusercontent.com/u/32596089?s=40&v=4" alt="头像" class="avatar-img">
              <div class="message-infomation">
                <span class="message-username">{{ message.nickname }}</span>
                <span class="message-preview">{{ message.content }}</span>
                <span class="message-unread">{{ unreadNum[id] }}</span>
              </div>
          </div>
        </div>
      </mt-tab-container-item>
      <mt-tab-container-item id="friends-container">
        朋友
      </mt-tab-container-item>
    </mt-tab-container>
    <mt-tabbar v-model="selected" fixed>
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
          selected: "messages-container",
          unreadMessages: {},
          unreadNum: {},
          userMessage: {},
        }
    },
    methods: {
      handleGetMessages(res) {
        if (res) {
          const { data } = res;
          const { unreadMessages,unreadNum, userMessage } = data;
          this.unreadMessages = unreadMessages;
          this.unreadNum = unreadNum;
          this.userMessage = userMessage;
          // console.log(this.messageData);
        }
      }
    },
    created: function() {
      this.axios
        .get("/verification/message/index")
        .then(this.handleGetMessages)
        .catch(err => {
            console.log("获取失败err: ", err);
        })
    }

}
</script>

<style lang="scss" scoped>
  @import './home.scss'
</style>