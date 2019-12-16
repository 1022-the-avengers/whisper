<template>
  <div class="show">
    <mt-header title="好友信息">
      <router-link to="/home" slot="left">
        <mt-button icon="back">返回</mt-button>
      </router-link>
      <mt-button icon="more" slot="right" @click.native="actionSheet"></mt-button>
    </mt-header>
    <div class="info">
      <div class="info-head">
        <img :src="info.pic" width="60px" height="60px" />
        <div>
          <div class="name">{{info.nickname}}</div>
          <div>账号：{{info.account}}</div>
          <div>性别：{{info.gender}}</div>
        </div>
      </div>

      <div class="tag">
        <div class="tag-title">印象</div>
        <div class="tag-items">
          <div
            class="tag-item"
            v-for="(value,key) in info.impressions"
            :key="key"
            @click="removeTag(key)"
          >{{value}}</div>
        </div>
      </div>
      <mt-button size="large" type="primary" @click="sendMes">发送消息</mt-button>
    </div>
    <mt-actionsheet :actions="actions" v-model="sheetVisible"></mt-actionsheet>
  </div>
</template>

<script>
import { MessageBox } from "mint-ui";
export default {
  name: "chat",
  data() {
    return {
      info: {},
      sheetVisible: false,
      actions: [
        {
          name: "删除好友",
          method: this.deleteFriend
        },
        {
          name: "移动好友",
          method: this.moveFriend
        },
        {
          name: "添加标签",
          method: this.addTag
        }
      ]
    };
  },
  methods: {
    actionSheet() {
      this.sheetVisible = true;
    },
    deleteFriend() {
      var param = { customId: this.info.id };
      MessageBox.confirm("此操作将删除好友")
        .then(action => {
          this.axios
            .delete("/verification/user/relationship", { params: param })
            .then(response => {
              if (response.data.message == "好友关系解除成功") {
                MessageBox.alert("好友关系解除成功").then(action => {
                  this.$router.push({ path: "/home" });
                });
              }
            })
            .catch(err => {
              console.log(err); //异常
            });
        })
        .catch(err => {
          // console.log("删除取消");
        });
    },
    addTag() {
      MessageBox.prompt(" ", "添加印象", {
        inputPlaceholder: "输入印象的名字"
      })
        .then(({ value, action }) => {
          var add = {};
          this.info.impressions.push(value);
          add.targetId = this.info.id;
          add.contents = this.info.impressions;
          console.log(add);
          this.axios
            .post("/verification/user/impression", add)
            .then(response => {
              console.log(response.data);
            })
            .catch(err => {
              console.log(err); //异常
            });
        })
        .catch(err => {
          // console.log("取消标签");
        });
    },
    moveFriend() {
      MessageBox.prompt(" ", "移动好友", {
        inputPlaceholder: "输入移动至的分组名字"
      })
        .then(({ value, action }) => {
          var move = {};
          move.customId = this.info.id;
          move.groupName = value;
          console.log(move);
          this.axios
            .post("/verification/user/relationship", move)
            .then(response => {
              console.log(response.data);
            })
            .catch(err => {
              console.log(err); //异常
            });
        })
        .catch(err => {
          // console.log("取消标签");
        });
    },
    removeTag(key) {
      MessageBox.confirm("此操作将删除标签")
        .then(action => {
          this.info.impressions.splice(key, 1);
          var add = {};
          add.targetId = this.info.id;
          add.contents = this.info.impressions;
          this.axios
            .post("/verification/user/impression", add)
            .then(response => {
              console.log(response.data);
            })
            .catch(err => {
              console.log(err); //异常
            });
        })
        .catch(err => {
          // console.log("删除取消");
        });
    },
    sendMes(event){
      var message ={};
      message.nickname = this.info.nickname;
      message.pic = this.info.pic;
      this.$router.push({
        name: "Chat",
        params: { recipientId:this.info.id, message:message}
      });
    }
  },
  created: function() {
    let group = this.$route.query.group;
    let id = this.$route.query.id;
    this.info = JSON.parse(sessionStorage.getItem("friends"))[group][id]; //根据url中地址获取用户信息
    console.log(this.info);
  }
};
</script>

<style lang="scss" scoped>
@import "./showfriend.scss";
</style>