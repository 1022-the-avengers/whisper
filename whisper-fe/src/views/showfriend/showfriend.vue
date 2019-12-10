<template>
  <div class="show">
    <mt-header title="好友信息">
      <router-link to="/main" slot="left">
        <mt-button icon="back">返回</mt-button>
      </router-link>
      <mt-button icon="more" slot="right" @click.native="actionSheet"></mt-button>
    </mt-header>
    <div class="info">
      <div class="info-head">
        <img :src="info.photo" width="60px" height="60px" />
        <div>
          <div class="name">{{info.name}}</div>
          <div>账号：{{info.id}}</div>
          <div>地区：{{info.address}}</div>
        </div>
      </div>

      <div class="tag">
        <div class="tag-title">标签</div>
        <div class="tag-items">
          <div class="tag-item" v-for="item in info.tag">{{item}}</div>
        </div>
      </div>
      <mt-button size="large" type="primary">发送消息</mt-button>
    </div>
    <mt-actionsheet :actions="actions" v-model="sheetVisible"></mt-actionsheet>
  </div>
</template>

<script>
export default {
  name: "chat",
  data() {
    return {
      sheetVisible: false,
      actions: [
        {
          name: "删除好友",
          method: function() {
            console.log("删除好友");
          }
        },
        {
          name: "添加标签",
          method: function() {
            console.log("添加标签");
          }
        },
        {
          name: "添加标签",
          method: function() {
            console.log("添加标签");
          }
        }
      ],
      info: {
        name: "郑沙雕",
        id: "17236716237",
        photo: "/assets/friend/photo.jfif",
        address: "江西南昌",
        tag: [
          "沙雕",
          "钓沙",
          "舔狗",
          "妇女之友",
          "沙雕",
          "钓沙",
          "舔狗",
          "妇女之友"
        ]
      }
    };
  },
  methods: {
    actionSheet() {
      this.sheetVisible = true;
    }
  }
};
</script>

<style lang="scss" scoped>
@import "./showfriend.scss";
</style>