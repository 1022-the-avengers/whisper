<template>
  <div class="container">
    <div class="header">
      <span @click="goBack" class="iconfont">&#xe61b;</span>
      这是哈士奇
    </div>
    <div class="content">
      <div v-for="(item, index) in Messages" :key="index">
        <div class="content_item" v-if="item.my">
          <div class="msg">
            <span class="info">{{item.my}}</span>
          </div>
          <img class="avator" alt="你的头像" src="http://img.flura.cn/myAvatar.jpg" />
        </div>

        <div class="content_response" v-else>
          <img class="avator" alt="他的头像" src="http://img.flura.cn/robot.jpg" />
          <div class="msg">
            <span class="info">{{item.he}}</span>
          </div>
        </div>
      </div>
    </div>
    <form class="footer">
      <input type="text" class="footer_inpuit" v-model="inputMessage" />
      <button class="footer_button" v-on:click.prevent="sendMessage">发送</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "chat",
  data() {
    return {
      inputMessage: "",
      Messages: [{ my: "这是发给某人的一条消息" }, { my: "这是另一个" }]
    };
  },
  methods: {
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
    },
    sendMessage() {
      console.log("发送");
      if (this.inputMessage) {
        this.Messages.push({ my: this.inputMessage });

        this.stompClient.send(
          "/socket/chat",
          {},
          JSON.stringify({
            content: { my: this.inputMessage },
            senderId: 3,
            recipientId: 4
          })
        );
        console.log("inputMessage: ", this.inputMessage);
        this.inputMessage = "";
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
    goBack() {
      this.$router.go(-1);
    }
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
@import "./chat.scss";
</style>