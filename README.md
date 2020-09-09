# ZhiYi-Cloud

文档说明 [https://zhiyi.zone]

# 开发

host 配置:
```java
127.0.0.1 eureka-server
```

# 启动顺序
| Microservice  | Port | Desc |
| ----------- | ----------- | ----------- | 
| EurekaApplication   | 8848       | 注册中心
| ConfigApplication   | 8849       | 配置中心
| GatewayApplication  | 8008      | 网关 |
| UpmsApplication | 6513        | 通用用户权限管理系统
| AuthApplication   | 6628| 统一认证中心|
| AdminApplication   | 6222| 服务管理 |
