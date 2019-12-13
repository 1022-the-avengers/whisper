<template>
  <div class="container">
    <mt-header title="消息盒子"/>
    <div class="search-container">
      <input type="text" v-model="searchValue">
    </div>
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
  @import './chatbox.scss'
</style>