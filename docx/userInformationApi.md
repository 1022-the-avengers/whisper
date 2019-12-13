# userInformation接口
1. ### 修改用户资料
    * url: /user/information
    * 请求方法: post
    * 参数
    ```
    {
        "id" : "账号"
        "nickname":"昵称",
        "age":"年龄",
        "gender" : "性别",
        "pic" : "图片"
    }
    ```
    * 返回
        * 成功 200
        ```
        {
            "message": "用户资料修改成功",
            "data": null
            "link": {
                "rel": "", 
                "href": "",            
                "title": "",                       
                "type": ""                  
            }
        }
        ```
1. ### 获取用户资料
    * url: /user/information
    * 请求方法: get
    * 参数
    ```
    id=用户id
    ```
    * 返回
        * 成功 200
        ```
        {
            "message": "用户资料请求成功",
            "data": {
                    "id" : "账号"
                    "nickname":"昵称",
                    "age":"年龄",
                    "gender" : "性别",
                    "pic" : "图片"
            }
            "link": {
                "rel": "", 
                "href": "",            
                "title": "",                       
                "type": ""                  
            }
        }
        ```