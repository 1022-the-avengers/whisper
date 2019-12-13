# impression接口
1. ### 更新好友关系
    * url: /verification/user/relationship
    * 请求方法: post
    * 参数
    ```
    {
        "groupName": "小学",
        "hostId": "3",
        "customId": "4"
    }
    ```
    * 返回
        * 成功 200
        ```
        {
            "message": "好友关系更新成功",
            "data": null
            "link": {
                "rel": "", 
                "href": "",            
                "title": "",                       
                "type": ""                  
            }
        }
        ```
2. ### 解除好友关系
* url: /verification/user/relationship
    * 请求方法: delete
    * 参数
    ```
    hostId=用户id
    customId=被删除好友id
    ```
    * 返回
        * 成功 200
        ```
        {
            "message": "好友关系解除成功",
            "data": null,
            "link": {
                "rel": "", 
                "href": "",            
                "title": "",                       
                "type": ""                  
            }
        }
        ```
2. ### 获取好友列表
    * url: /verification/user/relationship
    * 请求方法: get
    * 参数
    ```
    hostId=用户id
    ```
    * 返回
        * 成功 200
        ```
        {
            "message": "好友印象列表获取成功",
            "data": {
                "高中": [
                    5,
                    6
                ],
                "小学": [
                    4
                ]
            },
            "link": {
                "rel": "", 
                "href": "",            
                "title": "",                       
                "type": ""                  
            }
        }
        ```