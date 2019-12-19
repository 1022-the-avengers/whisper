<template>
  <div class="container">
    <div class="header">
      <span @click="goBack" class="iconfont">&#xe61b;</span>
      {{nickName}}
      <span @click="deleteHistory" class="iconfont">&#xe83f;</span>
      <span @click="download" class="iconfont">&#xe64d;</span>
    </div>
    <div class="content">
      <div v-for="(item, index) in historyMessages" :key="index">
        <div class="content_item" v-if="item.my">
          <div class="msg">
            <span class="info">{{item.my}}</span>
          </div>
          <!-- <img class="avator" alt="你的头像" src="http://img.flura.cn/myAvatar.jpg" /> -->
          <img class="avator" alt="你的头像" :src="myPic" />
        </div>

        <div class="content_response" v-else>
          <!-- <img class="avator" alt="他的头像" src="http://img.flura.cn/robot.jpg" /> -->
          <img class="avator" alt="他的头像" :src="hisPic" />
          <div class="msg">
            <span class="info">{{item.he}}</span>
          </div>
        </div>
      </div>
      <div v-for="(item, index) in Messages" :key="index+1000">
        <div class="content_item" v-if="item.my">
          <div class="msg">
            <span class="info">{{item.my}}</span>
          </div>
          <!-- <img class="avator" alt="你的头像" src="http://img.flura.cn/myAvatar.jpg" /> -->
          <img class="avator" alt="你的头像" :src="myPic" />
        </div>

        <div class="content_response" v-else>
          <!-- <img class="avator" alt="他的头像" src="http://img.flura.cn/robot.jpg" /> -->
          <img class="avator" alt="他的头像" :src="hisPic" />
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
      historyMessages: [],
      Messages: [],
      myPic: window.localStorage.getItem("userPic"),
      hisPic: '',
      userId: window.localStorage.getItem("userId"),
      recipientId: "",
      nickName: '',
    };
  },
  methods: {
    connection() {
      // 建立连接对象
      let scoket = new this.SockJS("http://192.168.43.85:8080/chat"); //连接服务端提供的通信接口，连接以后才可以订阅广播消息和个人消息
      // 获取STOMP子协议的客户端对象
      this.stompClient = this.Stomp.over(scoket);
      // 定义客户端的认证信息,按需求配置
      var headers = {
        // additional header
        userId: this.userId
      };
      // 向服务器发起websocket连接
      this.stompClient.connect(
        headers,
        frame => {
          this.stompClient.subscribe(`/subscription/${this.userId}`, msg => {
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
            senderId: this.userId,
            recipientId: this.recipientId
          })
        );
        console.log("inputMessage: ", this.inputMessage);
        this.inputMessage = "";
      }
    },
    getHistory() {
      this.axios("/verification/message/history", {
        params: {
          customer: this.recipientId, // 和B的聊天记录 customer:B的id
          page: -1
        }
      }).then(res => {
        this.historyMessages = res.data.historyMessage;
      });
    },
    goBack() {
      this.axios("/verification/message/unread", {
        params: {
          customer: this.recipientId
        }
      })
      this.$router.go(-1);
    },
    download() {
      this.axios("/verification/message/history/download", {
        params: {
          customer: this.recipientId
        }
      }).then(res => {
        let aTag = document.createElement("a");
        let blob = new Blob([res.data]);
        aTag.download = "download.txt";
        aTag.href = URL.createObjectURL(blob);
        aTag.click();
        URL.revokeObjectURL(blob);
      });
    },
    deleteHistory() {
      this.axios
        .delete("/verification/message/history", {
          params: {
            customer: this.recipientId
          }
        })
        .then(
          function(params) {
            alert("删除成功");
            this.$router.go(-1);
          }.bind(this)
        )
        .catch((err) => {
          alert('删除失败', err)
          console.log('删除失败', err)
        });
    }
  },
  created() {
    this.initWebSocket();
    console.log(
      "this.$route.params.recipientId: ",
      this.$route.params.recipientId,
      "this.$route.params.message:  ",
      this.$route.params.message
    );
    this.hisPic = this.$route.params.message.pic
    this.recipientId = this.$route.params.recipientId;
    this.nickName = this.$route.params.message.nickname
    console.log('this.nickName : ', this.nickName )
    // window.localStorage.setItem("hisPic", this.$route.params.message.pic);
  },
  mounted() {
    // 请求聊天记录
    this.getHistory();
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