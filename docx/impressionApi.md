# impression接口
1. ### 更新好友印象
    * url: /verification/user/impression
    * 请求方法: post
    * 参数
    ```
    {
        "commenterId": "3",
        "targetId": "4",
        "contents": [
            "印象1",
            "印象2",
            "印象3"
        ]
    }
    ```
    * 返回
        * 成功 200
        ```
        {
            "message": "好友印象更新成功",
            "data": null
            "link": {
                "rel": "", 
                "href": "",            
                "title": "",                       
                "type": ""                  
            }
        }
        ```
2. ### 获取好友印象
    * url: /verification/user/impression
    * 请求方法: get
    * 参数
    ```
    id=用户id
    ```
    * 返回
        * 成功 200
        ```
        {
            "message": "好友印象列表获取成功",
            "data": [
                    "地方",
                    "阿道夫",
                    "风格"
             ],
            "link": {
                "rel": "", 
                "href": "",            
                "title": "",                       
                "type": ""                  
            }
        }
        ```