# userInformation接口
1. ### 修改用户资料
    * url: /verification/user/information
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
2. ### 获取用户资料
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
3. ### 根据条件获取用户资料
    * url: /verification/user/informations
    * 请求方法: get
    * 参数
    ```
    nickname=昵称，可省略。
    gender=性别，可省略。
    minAge=最小年龄，可省略。
    maxAge=最大年龄，可省略。
    ```
    * 返回
        * 成功 200
        ```
        {
            "message": "用户资料请求成功",
            "data": [
                {
                    "id": 3,
                    "nickname": "Marron",
                    "pic": null,
                    "gender": "男",
                    "age": 16
                },
                {
                    "id": 4,
                    "nickname": "Marron",
                    "pic": "sdfdsfdf",
                    "gender": "女",
                    "age": 18
                }
             ],
            "link": {
                "rel": "", 
                "href": "",            
                "title": "",                       
                "type": ""                  
            }
        }
        ```