<template>
  <div class="container">
    <div class="header">这是哈士奇</div>
    <div class="content">
      
      <div class="content_item" v-for="(item, index) in myMessage" :key="index">
        <div class="msg">
          <span class="info">{{item}}</span>
        </div>
         <img class="avator" alt='你的头像' src="http://img.flura.cn/myAvatar.jpg"/>>
      </div>
    </div>
    <form class="footer">
      <input type="text" class="footer_inpuit" v-model="inputMessage">
      <button class="footer_button"  v-on:click.prevent="sendMessage">发送</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "chat",
  data() {
    return {
      inputMessage: '',
      myMessage: ['这是发给某人的一条消息', '这是另一个']
    };
  },
  methods: {
    sendMessage() {
      console.log('发送')
      this.myMessage.push(this.inputMessage)
      this.inputMessage = ''
    }
  },
};
</script>

<style lang="scss" scoped>
@import "./chat.scss";
</style>