# message模块接口

### 主页(也就是消息盒子)获取未读信息
* url: /verification/message/index
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
            "4": {
                "nickname": "臭弟弟",
                "pic": null,
                "content": "日尼玛"
            }
        },
        "unreadNum": {
            "4": 1
        },
        "userMessage": {
            "id": 3,
            "nickname": "Marron",
            "pic": null,
            "gender": null,
            "age": null
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
* url: /verification/message/history
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
        "historyMessage": [
            {
                "content": "日尼玛",
                "time": "2019-12-07T03:05:41.257+0000",
                "senderId": 4,
                "recipientId": 3
            },
            {
                "content": "日尼玛",
                "time": "2019-12-07T03:04:21.479+0000",
                "senderId": 4,
                "recipientId": 3
            },
            {
                "content": "沃日",
                "time": "2019-12-07T03:04:05.107+0000",
                "senderId": 3,
                "recipientId": 4
            },
            {
                "content": "沃日",
                "time": "2019-12-07T03:02:46.515+0000",
                "senderId": 3,
                "recipientId": 4
            },
            {
                "content": "说话啊",
                "time": "2019-12-07T01:15:43.115+0000",
                "senderId": 3,
                "recipientId": 4
            },
            {
                "content": "沃日",
                "time": "2019-12-07T01:15:08.746+0000",
                "senderId": 3,
                "recipientId": 4
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
