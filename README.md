# ZhiYi-Cloud

文档说明 [https://zhiyi.zone]

# 开发

host 配置:
```java
127.0.0.1 discovery-server discovery1-server
```

# 启动顺序
| Microservice  | Port | Desc |
| ----------- | ----------- | ----------- | 
| GatewayApplication  | 8008      | 网关 |
| UpmsApplication | 6513        | 通用用户权限管理系统
| AuthApplication   | 6628| 统一认证中心|
| AdminApplication   | 6222| 服务管理 |


# 数据库

```sql
create database `zhiyi-cloud-upms` default character set utf8mb4 collate utf8mb4_general_ci;
create database `zhiyi-cloud-auth` default character set utf8mb4 collate utf8mb4_general_ci;
```

# 测试脚本

```shell script
curl --location --request POST 'localhost:6628/oauth/token' \
--header 'Authorization: Basic Y2xpZW50OnNlY3JldA==' \
--form 'username=user' \
--form 'password=password' \
--form 'grant_type=password'
```

```shell script
curl --location --request GET 'localhost:6513/check_token' \
--header 'Authorization: Bearer fab92feb-9fae-4658-989c-d07835dbff79'
```

