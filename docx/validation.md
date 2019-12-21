# userInformation接口
1. ### 发送验证请求
    * url: /verification/user/validation
    * 请求方法: post
    * 参数
    ```
    {
    	"id" : 接收方id,
    	"content" : 验证信息
    }
    ```
    * 返回
        * 成功 200
        ```
        {
            "message": "验证请求发送成功",
            "data": null
            "link": {
                "rel": "", 
                "href": "",            
                "title": "",                       
                "type": ""                  
            }
        }
        ```
        * 失败
        {
            "message": "不可重复添加",
            "data": null,
            "type": "",
            "title": "",
            "rel": "",
            "href": ""
        } 
2. ### 确认验证请求
    * url: /verification/user/validation-confirm
    * 请求方法: post
    * 参数
    ```
    {
        "id" : 验证消息id,
        "status" : 2表示确认，3表示拒绝
    }
    ```
    * 返回
        * 成功 200
        ```
        {
            "message": "请求确认成功",
            "data": null
            "link": {
                "rel": "", 
                "href": "",            
                "title": "",                       
                "type": ""                  
            }
        }
        ```
3. ### 获取未读验证消息的数量
    * url: /verification/user/validation-amount
    * 请求方法: get
    * 返回
        * 成功 200
        ```
          {
              "message": "未读验证消息数量",
              "data": 5,
              "type": "application/json",
              "rel": "next http://localhost:8080/validation",
              "href": "http://localhost:8080/validation",
              "title": "get validation messages"
          }
        ```

4. ### 获取验证信息
    * url: /verification/user/validation
    * 请求方法: get
    * 返回
        * 成功 200
        ```
        {
            "message": "验证请求列表",
            "data": {
                "sender": [
                    {
                        "id": 7,
                        "account": "zhengyujie0806@gmail.com",
                        "nickname": "Marron",
                        "pic": ""
                        "content": "woca",
                        "status": 1
                    },
                    {
                        "id": 9,
                        "account": "18357922710",
                        "nickname": "Marron",
                        "pic": ""
                        "content": "1234",
                        "status": 2
                    },
                    {
                        "id": 10,
                        "account": "897013045@qq.com",
                        "nickname": "Msbsbsbsb",
                        "pic": ""
                        "content": "sdiao",
                        "status": 3
                    }
                ],
                "receiver": [
                    {
                        "id": 3,
                        "account": "fc@gmail.com",
                        "nickname": "康王",
                        "pic": ""
                        "content": "nmb",
                        "status": 1
                    },
                    {
                        "id": 4,
                        "account": "nanfengyan1308@163.com",
                        "nickname": "sr",
                        "pic": ""
                        "content": "nmb",
                        "status": 1
                    }
                ]
            },
            "type": "application/json",
            "rel": "next http://localhost:8080/validation-reading",
            "href": "http://localhost:8080/validation-reading",
            "title": "read the processed messages you have send"
        }
        ```
3. ### 阅读用户作为发送方的未读验证消息。
    * url: /verification/user/validation-reading
    * 请求方法: post
    * 返回
        * 成功 200
        ```
          {
              "message": "未读验证消息数量",
              "data": 5,
              "type": "application/json",
              "rel": "next http://localhost:8080/validation",
              "href": "http://localhost:8080/validation",
              "title": "get validation messages"
          }
        ```