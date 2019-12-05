# message模块接口

### 主页(也就是消息盒子)获取未读信息
* 请求方法: get
* headers
```
Authorization: "Bearer "+token
```
* Query Params
```
无
```
* 返回
    * 成功 200
    ```
    {
        "message": "获取成功",
        "unreadMessages": {
            "4": {                         // 4为未读消息发送者的id
                "nickname": "臭弟弟",      //未读消息中消息发送者昵称
                "pic": "asdfsadfsadf",    //未读消息中消息发送者的头像 base64
                "content": "沃日"          //未读消息内容
            }
        },
        "userMessage": {                //userMessage 为用户信息
            "id": 3,                    //用户id
            "nickname": "Marron",       //用户昵称
            "pic": null,                //用户头像
            "gender": null,             //用户性别
            "age": null                 //用户年龄
        },
        "link": {
            "rel": "index /verification/message/index",
            "href": "/verification/message/index",
            "title": "index",
            "type": "application/json"
        }
    }
    ```
  * 失败
  ```
  {
    "link": {
      "rel": "login /auth/login",
      "href": "/auth/login",
      "title": "login",
      "type": "application/json"
    },
    "message": "请先登录",
    "token": ""
  }
  ```
  
### 和B的聊天记录
* 请求方法: get
* headers
```
Authorization: "Bearer "+token
```
* Query Params
```
customer:B的id
page: 第几页，一页默认返回15条
```
* 返回
    * 成功 200
    ```
    {
        "message": "获取成功",
        "historyMessage": [             //历史消息，按照时间近远排序
            {
                "content": "沃日",        //消息内容
                "time": "2019-12-05T03:54:43.000+0000",     //消息发送时间
                "senderId": 4,                          //消息发送者id
                "recipientId": 3                        //消息接受者id
            }
        ],
        "link": {
            "rel": "index /verification/message/index",
            "href": "/verification/message/index",
            "title": "index",
            "type": "application/json"
        }
    }
    ```
  * 失败
  ```
  {
    "link": {
      "rel": "login /auth/login",
      "href": "/auth/login",
      "title": "login",
      "type": "application/json"
    },
    "message": "请先登录",
    "token": ""
  }
  ```
