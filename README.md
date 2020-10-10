# ZhiYi-Cloud

开发中...

文档说明 [https://zhiyi.zone]

利用 Spring Cloud 搭建小型商城.

v1 后台演示 [https://cloud.zhiyi.zone/mall/v1/]

v1 前台演示 [xx]

# 开发

host 配置:
```java
127.0.0.1 discovery-server
```

## 服务端口

| Microservice  | Port | Desc |
| ----------- | ----------- | ----------- | 
| Nacos  | 8848      | 注册服务 |
| GatewayApplication  | 8008      | 网关 |
| AuthApplication   | 6628| 认证中心 |
| MonitorApplication   | 6222 | 服务监控 |
| V1UserApplication   | 6222 | 商城前台接口(v1) |
| V1AdminApplication   | 6222 | 商城后台接口(v1) |
