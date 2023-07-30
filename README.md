# Compare-Database

# 功能
    数据库对比工具，用于比较两个不同环境中的数据库结构差异
### Mysql对比效果
![Mysql](https://github.com/Addddddii/Compare-Database/blob/master/imgs/Mysql.jpg)
### SqlServer对比效果
![SqlServer](https://github.com/Addddddii/Compare-Database/blob/master/imgs/SqlServer.jpg)
# 未来计划
    1、索引比较
    2、生成DDL脚本
    3、根据表字段顺序比较
    4、Oracle支持
    5、PostgreSql支持
    6、DDL提交与执行
    7、记住用户名/密码(可能会支持)
    ...
## 环境
    JDK > 1.8
    spring-boot > 2.1.2.RELEASE
### 运行


```shell

mvn install

mvn package 

cd target

java -jar Compare-Database.jar

```

## 致谢

        由于本人在工作中需要面对不同的项目数据库环境,特别是在QA中复现问题时经常会遇到数据库结构差异导致线上问题,
    在GitHub中找到了一个不错的开源项目地址如下, 本项目部分功能和界面都是借鉴参考该项目经行了开发，在此由衷感谢
    该开源项目作者
[项目参考](https://github.com/fireflyhoo/moonlight-chariot.git)