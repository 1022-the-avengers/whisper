<template>
  <div>
    <div class="page-container">
      <mt-tab-container class="page-tabbar-container" v-model="selected">
        <mt-tab-container-item id="chatbox">
          <mt-cell>
            <chatbox></chatbox>
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
      <mt-tab-item id="chatbox">
        <img slot="icon" v-show="selected!=='chatbox'" src="/assets/message.png" />
        <img slot="icon" v-show="selected=='chatbox'" src="/assets/message_click.png" />
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
  name: "home",
  data() {
    return {
      selected: "chatbox",
      id: -1
    };
  },
  methods: {
    getID(data) {
      this.id = data;
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
      sessionStorage.setItem("MainPage", "chatbox");
    }
    this.selected = sessionStorage.getItem("MainPage");

  }
};
</script>

<style lang="scss" scoped>
@import "./home.scss";
</style>