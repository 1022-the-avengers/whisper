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
customer:要清除聊天记录的对象的id
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
                "senderId": 3,
                "recipientId": 4,
                "content": {
                    "my": "10086"
                }
            },
            {
                "senderId": 4,
                "recipientId": 3,
                "content": {
                    "he": "asd"
                }
            },
            {
                "senderId": 4,
                "recipientId": 3,
                "content": {
                    "he": "shift"
                }
            },
            {
                "senderId": 4,
                "recipientId": 3,
                "content": {
                    "he": "asd"
                }
            },
            {
                "senderId": 4,
                "recipientId": 3,
                "content": {
                    "he": "asdf"
                }
            },
            {
                "senderId": 4,
                "recipientId": 3,
                "content": {
                    "he": "asdf"
                }
            },
            {
                "senderId": 4,
                "recipientId": 3,
                "content": {
                    "he": "asdf"
                }
            },
            {
                "senderId": 4,
                "recipientId": 3,
                "content": {
                    "he": "asd"
                }
            },
            {
                "senderId": 4,
                "recipientId": 3,
                "content": {
                    "he": "s"
                }
            },
            {
                "senderId": 4,
                "recipientId": 3,
                "content": {
                    "he": "asdfsd"
                }
            },
            {
                "senderId": 3,
                "recipientId": 4,
                "content": {
                    "my": "a"
                }
            },
            {
                "senderId": 3,
                "recipientId": 4,
                "content": {
                    "my": "345"
                }
            },
            {
                "senderId": 3,
                "recipientId": 4,
                "content": {
                    "my": "123"
                }
            },
            {
                "senderId": 3,
                "recipientId": 4,
                "content": {
                    "my": "54645"
                }
            },
            {
                "senderId": 3,
                "recipientId": 4,
                "content": {
                    "my": "1243"
                }
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
