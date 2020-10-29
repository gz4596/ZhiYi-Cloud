# ZhiYi-Cloud

学习 Spring Cloud, 并搭建小型商城项目.

基于动态数据源,实现多租户

个人博客 [https://zhiyi.zone]

## 后台功能

v1版本后台演示 [https://cloud.zhiyi.zone/mall/v1/]

v1版本前台演示 []

# 微服务端口说明

| Microservice  | Port | Desc |
| ----------- | ----------- | ----------- | 
| Nacos  | 8848      | 注册服务 |
| GatewayApplication  | 8008 | 网关 |
| AuthApplication   | 6628| 认证中心 |
| MonitorApplication   | 6222 | 服务监控 |
| V1UserApplication   | 6222 | 商城前台接口(v1) |
| V1AdminApplication   | 6222 | 商城后台接口(v1) |
