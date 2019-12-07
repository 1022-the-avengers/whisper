# Socket模块接口

### connect
* 方法 connect
* url: /chat
* headers
```
{
    userId: 当前用户Id
}
```

### 订阅
* 方法 subscribe
* url: /subscription/当前userId

### 发送消息
* 方法 send
* url: /socket/chat
* body
```
{
   'content': 发送内容,
   'senderId': 发送者Id,
   'recipientId': 接受者Id
}
```
* 返回
    * 成功   
    ```
        {"content","发送成功"}
    ```
    * 失败   
    ```
        {"content","对方不是你好友"}
    ```

### disconnect
* 方法先send后disconnect
* url: /socket/disconnection
* headers
```
{
    userId: 当前用户Id
}
```