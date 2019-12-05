# auth模块接口
### 登录
* 请求方法: post
* 参数
```
{
    "account":"账号",
    "password":"密码"
}
```
* 返回
    * 成功 200
    ```
    {
        "message": "登录成功",
        "token": "eyJhbGciOiJIUzI1NiIsIlR5cGUiOiJKd3QiLCJ0eXAiOiJKV1QifQ.eyJleHAiOjE1NzUzNTAzNDksInVzZXJJZCI6MH0.xWeYg_8pvVkMpd5ZZQpzMqdBl2tf3Xz5H7WDhfR8vMs",
        "link": {
            "rel": "messages /management/messages",     (下一个api的名字和路径)
            "href": "/management/messages",             (下一个api的路径)
            "title": "messages",                        (下一个api的名字)
            "type": "application/json"                  (下一个api返回的数据类型)
        }
    }
    ```
    * 失败 401
    ```
    {
        "message": "账号密码不匹配",
        "token": "",
        "link": {
            "rel": "login /auth/login",
            "href": "/auth/login",
            "title": "login",
            "type": "application/json"
        }
    }
    ```
    or
    ```
    {
        "message": "账号未注册",
        "token": "",
        "link": {
            "rel": "registration /auth/registratio",
            "href": "/auth/registration",
            "title": "registration",
            "type": "application/json"
        }
    }
    ```
### 注册
* 请求方法: post
* 参数
```
{
	"account": 账号,
	"password": 密码,
	"nickname": 昵称
}
```
* 返回
    * 成功 201
    ```
    {
        "message": "注册成功",
        "token": "eyJhbGciOiJIUzI1NiIsIlR5cGUiOiJKd3QiLCJ0eXAiOiJKV1QifQ.eyJleHAiOjE1NzUzNTI2ODAsInVzZXJJZCI6NH0.T6RXAOwpKvzoRR387IGlT1KE7gbTi8USpfoaXrBnCJM",
        "link": {
            "rel": "messages /management/messages",
            "href": "/management/messages",
            "title": "messages",
            "type": "application/json"
        }
    }
    ```
    * 失败 403
    ```
    {
        "message": "账号已注册",
        "token": "",
        "link": {
            "rel": "registration /auth/registration",
            "href": "/auth/registration",
            "title": "registration",
            "type": "application/json"
        }
    }
    ```
    or
    ```
    {
        "message": "密码过短",
        "token": "",
        "link": {
            "rel": "registration /auth/registration",
            "href": "/auth/registration",
            "title": "registration",
            "type": "application/json"
        }
    }
    ```