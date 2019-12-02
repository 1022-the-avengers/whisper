# whisper聊天系统后端

* git提交没有特殊要求，最好自己开个分支然后提交到分支上后合并
* 模块划分说明：
    * config用于存放Config文件
    * controller用于存放后端api代码
    * dao存放jpa操作
    * dto存放的类的属性为entity类中的一部分
    * entity和数据库的映射
    * processor存放过滤器拦截器和监听器
    * service存放各种具体的操作，供其他各个包调用，遵循接口实现的规则
    * utils存放通用工具
    * vo用于和前端交互时候用到的类型