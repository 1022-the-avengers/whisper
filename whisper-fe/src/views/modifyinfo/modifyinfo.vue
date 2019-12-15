<template>
  <div>
    <mt-header title="修改个人信息">
      <router-link to="/main" slot="left">
        <mt-button icon="back">返回</mt-button>
      </router-link>
    </mt-header>
    <div class="pic" :style="'background-image:url('+info.pic+')'" @click="getImg"></div>
    <input type="file" @change="chose" class="imgFile" style="display:none" />
    <div class="field">
      <mt-cell :title="info.account"></mt-cell>
      <mt-field label="昵称 :" placeholder="输入昵称" v-model="info.nickname"></mt-field>
      <mt-field label="年龄 :" placeholder="输入年龄" v-model="info.age"></mt-field>
      <mt-field label="性别 :" placeholder="输入性别" v-model="info.gender"></mt-field>
    </div>
    <div class="but">
      <mt-button type="primary" size="normal" @click="modify">修改信息</mt-button>
    </div>
    <div class="tag">
      <div class="tag-title">印象</div>
      <div class="tag-items">
        <div
          class="tag-item"
          v-for="(value,key) in impressions"
          :key="key"
          @click="removeTag(key)"
        >{{value}}</div>
      </div>
    </div>
  </div>
</template>

<script>
import { Cell } from 'mint-ui';
import { MessageBox } from "mint-ui";
export default {
  name: "modifyinfo",
  data() {
    return {
      info: {},
      impressions: []
    };
  },
  methods: {
    modify(event) {
      if (
        this.info.nickname == "" ||
        this.info.account == "" ||
        this.info.pic == "" ||
        this.info.gender == ""
      ) {
        MessageBox.alert("所填项不能为空").then(action => {});
        return false;
      }
      console.log(this.info);
      this.axios
        .post("/verification/user/information", this.info)
        .then(response => {
          console.log(response.data);
          MessageBox.alert("更改成功！！").then(action => {});
        })
        .catch(err => {
          console.log(err); //异常
        });
    },
    getImg(event) {
      var file = document.getElementsByClassName("imgFile")[0];
      file.click();
    },
    chose(e) {
      var img = e.target.files[0];
      if (
        img.type !== "image/jpeg" &&
        img.type !== "image/png" &&
        img.type !== "image/gif"
      ) {
        MessageBox.alert("请选择图片文件").then(action => {});
        return false;
      } else if (img.size > 1024 * 30) {
        console.log(img.size);
        MessageBox.alert("选择小于30kb的图片").then(action => {});
        return false;
      }
      var image = new Image();
      image.src = window.URL.createObjectURL(img);
      image.onload = () => {
        this.info.pic = this.getBase64Img(image);
      };
    },
    getBase64Img(img) {
      var canvas = document.createElement("canvas");
      canvas.width = img.width;
      canvas.height = img.height;
      var ctx = canvas.getContext("2d");
      ctx.drawImage(img, 0, 0, img.width, img.height);
      var ext = img.src.substring(img.src.lastIndexOf(".") + 1).toLowerCase();
      var dataURL = canvas.toDataURL("image/" + ext);
      return dataURL;
    },
    removeTag(key) {
      MessageBox.confirm("此操作将删除标签")
        .then(action => {
          this.impressions.splice(key, 1);
          var add = {};
          add.targetId = this.info.id;
          add.contents = this.impressions;
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
          // console.log("删除取消");
        });
    }
  },
  created: function() {
    this.axios
      .get("/verification/user/information")
      .then(response => {
        this.info = response.data.data;
        this.impressions = response.data.data.impressions;
        delete this.info.impressions;
        console.log(this.info);
        console.log(this.impressions);
      })
      .catch(err => {
        console.log(err); //异常
      });
  }
};
</script>

<style lang="scss" scoped>
@import "./modifyinfo.scss";
</style>