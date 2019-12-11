<template>
  <div class="container">
    <div class="header">这是哈士奇</div>
    <div class="content">
      
      <div class="content_item">
        <div class="msg">
          <span class="info">这是发给某人的一条消息</span>
        </div>
         <img class="avator" alt='你的头像' src="http://img.flura.cn/myAvatar.jpg"/>>
      </div>
    </div>
    <form class="footer">
      <input type="text" class="footer_inpuit">
      <button class="footer_button">发送</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "chat",
  data() {
    return {};
  }
};
</script>

<style lang="scss" scoped>
@import "./chat.scss";
</style>