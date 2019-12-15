<template>
  <div class="chatbox-container">
    <mt-header title="消息盒子" />
    <div class="search-container">
      <input type="text" v-model="searchValue" />
    </div>
    <div v-if="unreadMessages">
      <div
        class="message-list-item"
        v-for="(message,id) of unreadMessages"
        v-bind:key="id"
        v-on:click="handleClick(id);"
      >
        <img
          src="https://avatars3.githubusercontent.com/u/32596089?s=40&v=4"
          alt="头像"
          class="avatar-img"
        />
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
      userMessage: {}
    };
  },
  methods: {
    handleClick(id) {
      this.$emit("func", id);
    },
    handleGetMessages(res) {
      if (res) {
        const { data } = res;
        const { unreadMessages, unreadNum, userMessage } = data;
        this.unreadMessages = unreadMessages;
        this.unreadNum = unreadNum;
        this.userMessage = userMessage;
        // console.log(this.messageData);
      }
    },

    connection() {
      // 建立连接对象
      let scoket = new this.SockJS("http://192.168.1.105:8080/chat"); //连接服务端提供的通信接口，连接以后才可以订阅广播消息和个人消息
      // 获取STOMP子协议的客户端对象
      this.stompClient = this.Stomp.over(scoket);
      // 定义客户端的认证信息,按需求配置
      var headers = {
        // additional header
        userId: 3
      };
      // 向服务器发起websocket连接
      this.stompClient.connect(
        headers,
        frame => {
          this.stompClient.subscribe("/subscription/3", msg => {
            console.log("返回信息: ", msg);
            console.log(JSON.parse(msg.body));
            if (
              JSON.parse(msg.body).content.he !== "发送成功" &&
              JSON.parse(msg.body).content.he !== undefined
            ) {
              this.Messages.push(JSON.parse(msg.body).content);
            }
            // 订阅服务端提供的某个topic  // msg.body存放的是服务端发送给我们的信息
          });
        },
        err => {
          // 连接发生错误时的处理函数
          console.log(err);
        }
      );
    },
    disconnect() {
      if (this.stompClient != null) {
        this.stompClient.disconnect();
        console.log("Disconnected");
      }
    },
    initWebSocket() {
      this.connection();
      let self = this;
      // 断开重连机制,尝试发送消息,捕获异常发生时重连
      this.timer = setInterval(() => {
        try {
          self.stompClient.send("test");
        } catch (err) {
          console.log("断线了: " + err);
          self.connection();
        }
      }, 5000);
    }
  },
  created: function() {
    // this.axios
    //   .get("/verification/message/index?customer=-1")
    //   .then(this.handleGetMessages)
    //   .catch(err => {
    //       console.log("获取失败err: ", err);
    //   })
    this.unreadMessages = {
      "4": {
        nickname: "臭弟弟",
        pic: null,
        content: "日尼玛"
      }
    };
    this.unreadNum = {
      "4": 1
    };
    this.userMessage = {
      id: 3,
      nickname: "Marron",
      pic: null,
      gender: null,
      age: null
    };
  },
  mounted() {
    this.initWebSocket();
  },
  beforeDestroy() {
    // 页面离开时断开连接,清除定时器
    this.disconnect();
    clearInterval(this.timer);
  }
};
</script>

<style lang="scss" scoped>
@import "./chatbox.scss";
</style>