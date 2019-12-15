<template>
  <div class="container">
    <div class="header">
      <span @click="goBack" class="iconfont">&#xe61b;</span>
      这是哈士奇
      <span @click="download" class="iconfont">&#xe61b;</span>
    </div>
    <div class="content">
      <div v-for="(item, index) in historyMessages" :key="index">
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
      <div v-for="(item, index) in Messages" :key="index+1000">
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
      historyMessages: [
        { my: "这是一条历史消息" },
        { he: "这别人发过来的历史消息" }
      ],
      Messages: [{ my: "这是发给某人的一条消息" }, { my: "这是另一个" }]
    };
  },
  methods: {
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
            recipientId: 6
          })
        );
        console.log("inputMessage: ", this.inputMessage);
        this.inputMessage = "";
      }
    },
    getHistory() {
      this.axios("/verification/message/history", {
        params: {
          customer: 3, // 和B的聊天记录 customer:B的id
          page: -1
        }
      }).then(res => {
        this.historyMessages = res.data.historyMessage;
      });
    },
    goBack() {
      this.$router.go(-1);
    },
    download() {
      this.axios("/verification/message/history/download", {
        params: {
          customer: 3
        }
      }).then(res => {
        let aTag = document.createElement("a");
        let blob = new Blob([res.data]);
        aTag.download = "download.txt";
        aTag.href = URL.createObjectURL(blob);
        aTag.click();
        URL.revokeObjectURL(blob);
      });
    }
  },
  mounted() {
    // 请求聊天记录
    this.getHistory();
  },

};
</script>

<style lang="scss" scoped>
@import "./chat.scss";
</style>