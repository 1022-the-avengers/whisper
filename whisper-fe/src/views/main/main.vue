<template>
  <div>
    <div class="page-container">
      <mt-tab-container class="page-tabbar-container" v-model="selected">
        <mt-tab-container-item id="message">
          <mt-cell>
            <chatbox @func="getID"></chatbox>
            <!-- <chat></chat> -->
          </mt-cell>
        </mt-tab-container-item>
        <mt-tab-container-item id="friend">
          <mt-cell>
            <friend></friend>
          </mt-cell>
        </mt-tab-container-item>
      </mt-tab-container>
    </div>

    <mt-tabbar v-model="selected">
      <mt-tab-item id="message">
        <img slot="icon" v-show="selected!=='message'" src="/assets/message.png" />
        <img slot="icon" v-show="selected=='message'" src="/assets/message_click.png" />
        消息
      </mt-tab-item>
      <mt-tab-item id="friend">
        <img slot="icon" v-show="selected!=='friend'" src="/assets/friend.png" />
        <img slot="icon" v-show="selected=='friend'" src="/assets/friend_click.png" />
        好友列表
      </mt-tab-item>
    </mt-tabbar>
  </div>
</template>

<script>
import Friend from "../friend/friend.vue";
import Chat from "../chat/chat.vue";
import Chatbox from "../chatbox/chatbox.vue";
export default {
  name: "main",
  data() {
    return {
      selected: "message"
    };
  },
  methods: {
    getID(data) {
      console.log(data);
    }
  },
  watch: {
    selected: function(val, oldVal) {
      sessionStorage.setItem("MainPage", val);
    }
  },
  components: {
    chat: Chat,
    friend: Friend,
    chatbox: Chatbox
  },
  created: function() {
    if (!sessionStorage.getItem("MainPage")) {
      //初始化，判断主页面是消息，还是好友
      sessionStorage.setItem("MainPage", "message");
      
    }
    this.selected = sessionStorage.getItem("MainPage");

    this.axios
      .get("/verification/user/relationships")
      .then((response) => {
        sessionStorage.setItem("friends",JSON.stringify(response.data.data));
      })
      .catch(err => {
        console.log(error);//异常
      });
  }
};
</script>

<style lang="scss" scoped>
@import "./main.scss";
</style>