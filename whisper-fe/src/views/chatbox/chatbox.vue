<template>
  <div class="chatbox-container">
    <mt-header title="消息盒子" />
    <div v-if="unreadMessages">
      <div
        class="message-list-item"
        v-for="(message, id) of unreadMessages"
        v-bind:key="id"
        v-on:click="handleClick(id, message);"
      >
        <img :src="message.pic" alt="头像" class="avatar-img" />
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
      selected: "messages-container",
      unreadMessages: {},
      unreadNum: {},
      userMessage: {}
    };
  },
  methods: {
    handleClick(id, message) {
      console.log("点击了", id, message);
      this.$router.push({
        name: "Chat",
        params: { recipientId: id, message: message }
      });
    },
    getMessages() {
      this.axios("/verification/message/index", {
        params: {
          customer: -1
        }
      }).then(this.handleGetMessages);
    },
    handleGetMessages(res) {
      console.log("消息盒子获取未读信息: ", res.data);

      this.unreadMessages = res.data.unreadMessages;
      this.unreadNum = res.data.unreadNum;
      this.userMessage = res.data.userMessage;

      window.localStorage.setItem("userId", this.userMessage.id);
      window.localStorage.setItem("userPic", this.userMessage.pic);
    }
  },
  mounted() {
    setInterval(() => {
      this.getMessages();
    }, 2000);
  }
};
</script>

<style lang="scss" scoped>
@import "./chatbox.scss";
</style>