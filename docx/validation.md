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
3. ### 获取验证信息
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
                        "pic": "..."
                        "content": "woca",
                        "status": 1
                    },
                    {
                        "id": 3,
                        "account": "fc@gmail.com",
                        "nickname": "康王",
                        "pic": ".."
                        "content": "nmb",
                        "status": 1
                    }
                ],
                "receiver": [
                    {
                        "id": 7,
                        "account": "zhengyujie0806@gmail.com",
                        "nickname": "Marron",
                        "pic": "..."
                        content": "woca",
                        "status": 1
                    },
                    {
                        "id": 3,
                        "account": "fc@gmail.com",
                        "nickname": "康王",
                        "pic": "..."
      content": "nmb",
                        "status": 1
                    }
                ]
            },
            "type": "",
            "title": "",
            "rel": "",
            "href": ""
        }
        ```