# whisper前端文档

## 功能

1) 登录

使用账号密码进行登录，登录成功之后跳转到主页面中的消息盒子的页面

2) 注册

账号采用邮箱格式，密码要求大于八位

3) 消息盒子

消息盒子显示你的所有的未读消息，一旦消息已读就会从消息盒子中去除

4) 好友盒子

好友盒子有如下这些部分组成：新的朋友，我的账号，朋友验证，好友列表

5) 好友列表

按照分组展示所有的好友，点击好友可以进入好友资料卡页面

6) 朋友验证

当你发送的请求别人已经处理完了或者别人向你发送了请求的话此处会有一个红点表示消息数量。点击进入之后进入验证消息模块

7) 我的账号

点击之后进入个人资料卡，在这里可以修改姓名，头像，性别，头像要求小于30kb，年龄要求不能为负数，性别要求只能是男或者女，还可以在此处删除自己的标签，也可以退出登录。

8) 新的朋友

可以进行全局搜索，即不进行任何输入直接回车可以显示所有的好友，并且可以进行模糊搜索，只输入名字的部分也可搜索到。并且可以添加年龄和性别的限制条件。点击搜索结果可以进入好友资料卡。在这里可以填写验证消息，并且发送好友验证，自己不能添加自己，不能添加以及添加的好友，如果已经发送过依次请求对方为响应也不能发送。当这里发送之后对方的朋友验证会出现红点。

9) 验证消息

当我们点击朋友验证之后，进入验证消息页面，如果我们发送的消息被处理了，则会有一个红点标记，别人发送的请求我们可以选择拒绝和接受。如果我们进入了此页面的话，如果存在我们发送的消息被处理了且我们自己之前未读的，则会被设置为已读。对于别人发给自己的请求，则必须在处理完之后才会被设置为已读。

10) 好友资料卡

显示好友的基本信息，好友的标签，点击标签可以进行删除，并且可以在此页面点击发送消息进入聊天框进行聊天，此页面中点击右上角还可以进行删除好友，移动好友，添加标签。

11) 删除好友：将好友从列表中删除，删除后可以再次发送验证消息

12) 移动好友

输入要移动的分组如果不存在则创建分组，若某个分组内没有了用户则删除分组，所有用户默认在默认分组中

13) 添加标签

可以对一个用户添加一个标签，添加重复标签没有用

14) 聊天界面

聊天界面可以双方可以实时发送消息，显示的时候自己的消息在右侧，对方的消息在左侧，且按时间排序，点击下载按钮可以进行聊天记录下载，点击删除按钮可以删除云端数据



## 效果图

![登录](http://img.flura.cn/clip_image002.gif)

![注册](http://img.flura.cn/clip_image004.gif)

![消息盒子](http://img.flura.cn/clip_image008.gif)



![好友列表](http://img.flura.cn/clip_image012.gif)



![添加好友](http://img.flura.cn/clip_image016.gif)





![个人信息](http://img.flura.cn/clip_image018.gif)



![添加好友](http://img.flura.cn/clip_image020.gif)



![系统聊天](http://img.flura.cn/clip_image026.gif)



## 前端技术栈

①Vue作为前端框架

②vue-router进行前端路由管理

③webpack开发SPA（单页面应用）

④mint-UI作为UI框架

⑤STOMP实现Socket通信的框架

⑥axios发送请求

⑦sass(css预处理器，进行css代码的编写)



## Build Setup

```
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# run unit tests
npm run unit

# run e2e tests
npm run e2e

# run all tests
npm test
```