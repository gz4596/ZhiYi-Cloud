/*
 Navicat Premium Data Transfer

 Source Server         : 122.51.85.179(rds)
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:7222
 Source Schema         : zhiyi-cloud-config

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 29/10/2020 17:05:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'mall-v1-api-admin-test.yml', 'DEFAULT_GROUP', 'spring:\r\n  datasource:\r\n    dynamic:\r\n      primary: zhiyi-cloud-mall-v1_1\r\n      strict: true\r\n      tenant:\r\n        prefix: zhiyi-cloud-mall-v1_\r\n        enable: true\r\n      datasource:\r\n        zhiyi-cloud-mall-v1_1:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://192.168.31.222:3306/zhiyi-cloud-mall-v1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n        zhiyi-cloud-mall-v1_2:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://192.168.31.222:3306/zhiyi-cloud-mall-v1_1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n  redis:\r\n    host: 192.168.31.222\r\nmybatis-plus:\r\n  mapper-locations: classpath:/mapper/*Mapper.xml\r\n  global-config:\r\n    banner: false\r\n    db-config:\r\n      table-underline: true\r\n  configuration:\r\n    cache-enabled: false\r\n    map-underscore-to-camel-case: true\r\n    default-enum-type-handler: com.baomidou.mybatisplus.extension.handlers.MybatisEnumTypeHandler\r\n\r\nlogging:\r\n  level:\r\n    root: INFO\r\n#    org.springframework.web: DEBUG\r\n#    org.springframework.security: DEBUG\r\n#    org.springframework.security.oauth2: DEBUG\r\n    org.springframework.boot.autoconfigure: DEBUG\r\n    com.github.taoroot: DEBUG', 'f7ceb5f66f90b23a5dbd12fc08f7f114', '2020-10-09 13:54:50', '2020-10-28 23:59:44', NULL, '10.244.2.1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (2, 'mall-v1-api-user-test.yml', 'DEFAULT_GROUP', 'spring:\r\n  datasource:\r\n    dynamic:\r\n      primary: zhiyi-cloud-mall-v1_1\r\n      strict: true\r\n      tenant:\r\n        prefix: zhiyi-cloud-mall-v1_\r\n        enable: true\r\n      datasource:\r\n        zhiyi-cloud-mall-v1_1:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://127.0.0.1:3306/zhiyi-cloud-mall-v1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n        zhiyi-cloud-mall-v1_2:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://127.0.0.1:3306/zhiyi-cloud-mall-v1_1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n\r\nmybatis-plus:\r\n  mapper-locations: classpath:/mapper/*Mapper.xml\r\n  global-config:\r\n    banner: false\r\n    db-config:\r\n      table-underline: true\r\n  configuration:\r\n    cache-enabled: false\r\n    map-underscore-to-camel-case: true\r\n    default-enum-type-handler: com.baomidou.mybatisplus.extension.handlers.MybatisEnumTypeHandler', '25b341a2f133e77d6e0b29735fd1d2df', '2020-10-09 13:55:01', '2020-10-28 13:34:14', NULL, '10.244.2.1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (4, 'zhiyi-authorization-test.yml', 'DEFAULT_GROUP', 'spring:\r\n  thymeleaf:\r\n    cache: false\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    username: root\r\n    password: root\r\n    url: jdbc:mysql://192.168.31.222:3306/zhiyi-cloud-auth?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n  redis:\r\n    host: 192.168.31.222\r\n\r\nlogging:\r\n  level:\r\n    root: INFO\r\n#    org.springframework.web: DEBUG\r\n#    org.springframework.security: DEBUG\r\n#    org.springframework.security.oauth2: DEBUG\r\n#    org.springframework.boot.autoconfigure: DEBUG\r\n    com.github.taoroot: DEBUG', '9b2a30a3106f80a178afbcbe4ccbe3a0', '2020-10-09 13:55:31', '2020-10-26 16:36:09', NULL, '10.244.2.1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (5, 'zhiyi-gateway-test.yml', 'DEFAULT_GROUP', 'spring:\r\n  cloud:\r\n    gateway:\r\n      globalcors:\r\n        corsConfigurations:\r\n          \'[/**]\':\r\n            allowedOrigins: \'*\'\r\n            allowedMethods: \'*\'\r\n            allowedHeaders: \'*\'\r\n      x-forwarded:\r\n        enabled: true\r\n        host-enabled: true\r\n      discovery:\r\n        locator:\r\n          enabled: false\r\n      routes:\r\n        - id: zhiyi-authorization\r\n          uri: lb://zhiyi-authorization\r\n          predicates:\r\n            - Path=/auth/**\r\n          filters:\r\n            - StripPrefix=1\r\n            - SwaggerHeaderFilter\r\n        - id: mall-api-v1-user\r\n          uri: lb://mall-v1-api-user\r\n          predicates:\r\n            - Path=/mall/v1/user/**\r\n          filters:\r\n            - StripPrefix=3\r\n            - SwaggerHeaderFilter\r\n        - id: mall-api-v1-admin\r\n          uri: lb://mall-v1-api-admin\r\n          predicates:\r\n            - Path=/mall/v1/admin/**\r\n          filters:\r\n            - StripPrefix=3\r\n            - SwaggerHeaderFilter\r\n            - AddRequestHeader=preview_tenant, 1\r\n', 'aa12e00c986b24cbd78526fba8c3392b', '2020-10-09 13:55:40', '2020-10-18 00:32:41', NULL, '39.189.59.181', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (6, 'zhiyi-monitor-test.yml', 'DEFAULT_GROUP', 'spring:\r\n  security:\r\n    user:\r\n      name: admin\r\n      password: 123456\r\n', '19cdad0fccb711bf7fd0606f70cc1493', '2020-10-09 13:55:49', '2020-10-28 13:14:58', NULL, '10.244.2.1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (8, 'application-test.yml', 'DEFAULT_GROUP', 'management:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \"*\"\r\n    enabled-by-default: true\r\n\r\nsecurity:\r\n  ignore:\r\n    key: zhiyi-cloud\r\n  oauth2:\r\n    authorization:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\n    resource:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\nlogging:\r\n  level:\r\n    root: INFO\r\n    com.github.taoroot: DEBUG', '44ca07c59626f9729a46d2d2d2c05f3e', '2020-10-13 03:05:56', '2020-10-29 02:13:36', NULL, '10.244.2.1', '', '', '123123', '', '', 'yaml', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(64) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (1, 45, 'mall-v1-api-admin-test.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    dynamic:\r\n      primary: zhiyi-cloud-mall-v1_1\r\n      strict: true\r\n      tenant:\r\n        prefix: zhiyi-cloud-mall-v1_\r\n        enable: true\r\n      datasource:\r\n        zhiyi-cloud-mall-v1_1:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://127.0.0.1:3306/zhiyi-cloud-mall-v1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n        zhiyi-cloud-mall-v1_2:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://127.0.0.1:3306/zhiyi-cloud-mall-v1_1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n\r\nmybatis-plus:\r\n  mapper-locations: classpath:/mapper/*Mapper.xml\r\n  global-config:\r\n    banner: false\r\n    db-config:\r\n      table-underline: true\r\n  configuration:\r\n    cache-enabled: false\r\n    map-underscore-to-camel-case: true\r\n    default-enum-type-handler: com.baomidou.mybatisplus.extension.handlers.MybatisEnumTypeHandler\r\n\r\nlogging:\r\n  level:\r\n    root: INFO\r\n#    org.springframework.web: DEBUG\r\n#    org.springframework.security: DEBUG\r\n#    org.springframework.security.oauth2: DEBUG\r\n#    org.springframework.boot.autoconfigure: DEBUG\r\n    com.github.taoroot: DEBUG', '356dcab6f138de30f3bdb8a8fad9485c', '2020-10-24 13:38:42', '2020-10-24 00:38:42', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 46, 'mall-v1-api-admin-test.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    dynamic: #123\r\n      primary: zhiyi-cloud-mall-v1_1\r\n      strict: true\r\n      tenant:\r\n        prefix: zhiyi-cloud-mall-v1_\r\n        enable: true\r\n      datasource:\r\n        zhiyi-cloud-mall-v1_1:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://127.0.0.1:3306/zhiyi-cloud-mall-v1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n        zhiyi-cloud-mall-v1_2:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://127.0.0.1:3306/zhiyi-cloud-mall-v1_1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n\r\nmybatis-plus:\r\n  mapper-locations: classpath:/mapper/*Mapper.xml\r\n  global-config:\r\n    banner: false\r\n    db-config:\r\n      table-underline: true\r\n  configuration:\r\n    cache-enabled: false\r\n    map-underscore-to-camel-case: true\r\n    default-enum-type-handler: com.baomidou.mybatisplus.extension.handlers.MybatisEnumTypeHandler\r\n\r\nlogging:\r\n  level:\r\n    root: INFO\r\n#    org.springframework.web: DEBUG\r\n#    org.springframework.security: DEBUG\r\n#    org.springframework.security.oauth2: DEBUG\r\n#    org.springframework.boot.autoconfigure: DEBUG\r\n    com.github.taoroot: DEBUG', 'ea9e3777838be80d580d17fecf37a665', '2020-10-24 13:38:48', '2020-10-24 00:38:48', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (8, 47, 'application-test.yml', 'DEFAULT_GROUP', '', 'management:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \"*\"\r\n    enabled-by-default: true\r\n\r\nsecurity:\r\n  ignore:\r\n    key: zhiyi-cloud\r\n  oauth2:\r\n    authorization:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\n    resource:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\nlogging:\r\n  level:\r\n    root: INFO\r\n    com.github.taoroot: DEBUG', '44ca07c59626f9729a46d2d2d2c05f3e', '2020-10-24 13:39:15', '2020-10-24 00:39:15', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 48, 'zhiyi-authorization-test.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  thymeleaf:\r\n    cache: false\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    username: root\r\n    password: root\r\n    url: jdbc:mysql://127.0.0.1:3306/zhiyi-cloud-auth?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n', '1157bca7d96428babd15ac321aa145c8', '2020-10-26 15:56:04', '2020-10-26 15:56:04', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 49, 'zhiyi-authorization-test.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  thymeleaf:\r\n    cache: false\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    username: root\r\n    password: root\r\n    url: jdbc:mysql://192.168.31.222:3306/zhiyi-cloud-auth?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n', '00e09a6b2ed6c8a59004d2f8eb4ff244', '2020-10-26 15:57:38', '2020-10-26 15:57:38', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 50, 'mall-v1-api-admin-test.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    dynamic:\r\n      primary: zhiyi-cloud-mall-v1_1\r\n      strict: true\r\n      tenant:\r\n        prefix: zhiyi-cloud-mall-v1_\r\n        enable: true\r\n      datasource:\r\n        zhiyi-cloud-mall-v1_1:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://127.0.0.1:3306/zhiyi-cloud-mall-v1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n        zhiyi-cloud-mall-v1_2:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://127.0.0.1:3306/zhiyi-cloud-mall-v1_1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n\r\nmybatis-plus:\r\n  mapper-locations: classpath:/mapper/*Mapper.xml\r\n  global-config:\r\n    banner: false\r\n    db-config:\r\n      table-underline: true\r\n  configuration:\r\n    cache-enabled: false\r\n    map-underscore-to-camel-case: true\r\n    default-enum-type-handler: com.baomidou.mybatisplus.extension.handlers.MybatisEnumTypeHandler\r\n\r\nlogging:\r\n  level:\r\n    root: INFO\r\n#    org.springframework.web: DEBUG\r\n#    org.springframework.security: DEBUG\r\n#    org.springframework.security.oauth2: DEBUG\r\n#    org.springframework.boot.autoconfigure: DEBUG\r\n    com.github.taoroot: DEBUG', '356dcab6f138de30f3bdb8a8fad9485c', '2020-10-26 16:09:32', '2020-10-26 16:09:32', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 51, 'mall-v1-api-admin-test.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    dynamic:\r\n      primary: zhiyi-cloud-mall-v1_1\r\n      strict: true\r\n      tenant:\r\n        prefix: zhiyi-cloud-mall-v1_\r\n        enable: true\r\n      datasource:\r\n        zhiyi-cloud-mall-v1_1:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://192.168.31.222:3306/zhiyi-cloud-mall-v1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n        zhiyi-cloud-mall-v1_2:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://192.168.31.222:3306/zhiyi-cloud-mall-v1_1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n  redis:\r\n  host: 192.168.31.222\r\nmybatis-plus:\r\n  mapper-locations: classpath:/mapper/*Mapper.xml\r\n  global-config:\r\n    banner: false\r\n    db-config:\r\n      table-underline: true\r\n  configuration:\r\n    cache-enabled: false\r\n    map-underscore-to-camel-case: true\r\n    default-enum-type-handler: com.baomidou.mybatisplus.extension.handlers.MybatisEnumTypeHandler\r\n\r\nlogging:\r\n  level:\r\n    root: INFO\r\n#    org.springframework.web: DEBUG\r\n#    org.springframework.security: DEBUG\r\n#    org.springframework.security.oauth2: DEBUG\r\n#    org.springframework.boot.autoconfigure: DEBUG\r\n    com.github.taoroot: DEBUG', '4bebf0488b08b70608ac31da405ddb62', '2020-10-26 16:32:32', '2020-10-26 16:32:33', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (4, 52, 'zhiyi-authorization-test.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  thymeleaf:\r\n    cache: false\r\n  datasource:\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    username: root\r\n    password: root\r\n    url: jdbc:mysql://192.168.31.222:3306/zhiyi-cloud-auth?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n  redis:\r\n    host: 192.168.31.222', '219c7f446be25f7df88a58f8ac56fef9', '2020-10-26 16:36:09', '2020-10-26 16:36:09', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (6, 53, 'zhiyi-monitor-test.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  security:\r\n    user:\r\n      name: admin\r\n      password: adminadmin\r\n', 'e0e05730a722cda50fc90741f83a30ce', '2020-10-28 13:14:58', '2020-10-28 13:14:58', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (2, 54, 'mall-v1-api-user-test.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    dynamic:\r\n      primary: zhiyi-cloud-mall-v1_1\r\n      strict: true\r\n      tenant:\r\n        prefix: zhiyi-cloud-mall-v1_\r\n        enable: true\r\n      datasource:\r\n        zhiyi-cloud-mall-v1_1:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://127.0.0.1:3306/zhiyi-cloud-mall-v1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n        zhiyi-cloud-mall-v1_2:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://127.0.0.1:3306/zhiyi-cloud-mall-v1_1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n\r\nmybatis-plus:\r\n  mapper-locations: classpath:/mapper/*Mapper.xml\r\n  global-config:\r\n    banner: false\r\n    db-config:\r\n      table-underline: true\r\n  configuration:\r\n    cache-enabled: false\r\n    map-underscore-to-camel-case: true\r\n    default-enum-type-handler: com.baomidou.mybatisplus.extension.handlers.MybatisEnumTypeHandler\r\n\r\nmall:\r\n  v1:\r\n    user:\r\n      wx:\r\n        app-id: wxfc4b369dbf9c4d95\r\n        app-secret: f5833dab8aa23308e58cda18ac57a783', '7779fb2aa4471539e115bde9cb461841', '2020-10-28 13:34:14', '2020-10-28 13:34:14', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 55, 'mall-v1-api-admin-test.yml', 'DEFAULT_GROUP', '', 'spring:\r\n  datasource:\r\n    dynamic:\r\n      primary: zhiyi-cloud-mall-v1_1\r\n      strict: true\r\n      tenant:\r\n        prefix: zhiyi-cloud-mall-v1_\r\n        enable: true\r\n      datasource:\r\n        zhiyi-cloud-mall-v1_1:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://192.168.31.222:3306/zhiyi-cloud-mall-v1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n        zhiyi-cloud-mall-v1_2:\r\n          driver-class-name: com.mysql.cj.jdbc.Driver\r\n          username: root\r\n          password: root\r\n          url: jdbc:mysql://192.168.31.222:3306/zhiyi-cloud-mall-v1_1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8\r\n  redis:\r\n    host: 192.168.31.222\r\nmybatis-plus:\r\n  mapper-locations: classpath:/mapper/*Mapper.xml\r\n  global-config:\r\n    banner: false\r\n    db-config:\r\n      table-underline: true\r\n  configuration:\r\n    cache-enabled: false\r\n    map-underscore-to-camel-case: true\r\n    default-enum-type-handler: com.baomidou.mybatisplus.extension.handlers.MybatisEnumTypeHandler\r\n\r\nlogging:\r\n  level:\r\n    root: INFO\r\n#    org.springframework.web: DEBUG\r\n#    org.springframework.security: DEBUG\r\n#    org.springframework.security.oauth2: DEBUG\r\n#    org.springframework.boot.autoconfigure: DEBUG\r\n    com.github.taoroot: DEBUG', 'acbdd64b8ffc17da1bffccbeeb7da0ae', '2020-10-28 23:59:44', '2020-10-28 23:59:44', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (8, 56, 'application-test.yml', 'DEFAULT_GROUP', '', 'management:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \"*\"\r\n    enabled-by-default: true\r\n\r\nsecurity:\r\n  ignore:\r\n    key: zhiyi-cloud\r\n  oauth2:\r\n    authorization:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\n    resource:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\nlogging:\r\n  level:\r\n    root: INFO\r\n    com.github.taoroot: DEBUG', '44ca07c59626f9729a46d2d2d2c05f3e', '2020-10-29 01:32:24', '2020-10-29 01:32:24', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (8, 57, 'application-test.yml', 'DEFAULT_GROUP', '', 'management:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \"*\"\r\n    enabled-by-default: true\r\n\r\nsecurity:\r\n  ignore:\r\n    key: zhiyi-cloud\r\n  oauth2:\r\n    authorization:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\n    resource:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\nlogging:\r\n  level:\r\n    root: INFO\r\n    com.github.taoroot: DEBUG\r\n  file:\r\n    path: ${spring.application.name}-1', '8ecbe82de1b3c2c7227cff1067fe5954', '2020-10-29 01:43:46', '2020-10-29 01:43:47', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (8, 58, 'application-test.yml', 'DEFAULT_GROUP', '', 'management:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \"*\"\r\n    enabled-by-default: true\r\n\r\nsecurity:\r\n  ignore:\r\n    key: zhiyi-cloud\r\n  oauth2:\r\n    authorization:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\n    resource:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\nlogging:\r\n  level:\r\n    root: INFO\r\n    com.github.taoroot: DEBUG\r\n  file:\r\n    path: logs/${spring.application.name}-1/debug.log', 'cf861911b159918a08b80bb331bfd24a', '2020-10-29 01:47:18', '2020-10-29 01:47:19', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (8, 59, 'application-test.yml', 'DEFAULT_GROUP', '', 'management:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \"*\"\r\n    enabled-by-default: true\r\n\r\nsecurity:\r\n  ignore:\r\n    key: zhiyi-cloud\r\n  oauth2:\r\n    authorization:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\n    resource:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\nlogging:\r\n  level:\r\n    root: INFO\r\n    com.github.taoroot: DEBUG\r\n  file:\r\n    path: /project/logs/${spring.application.name}-1/debug.log', 'c1f61e8edc822d5a4069906b3dc2b807', '2020-10-29 01:52:08', '2020-10-29 01:52:09', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (8, 60, 'application-test.yml', 'DEFAULT_GROUP', '', 'management:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \"*\"\r\n    enabled-by-default: true\r\n\r\nsecurity:\r\n  ignore:\r\n    key: zhiyi-cloud\r\n  oauth2:\r\n    authorization:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\n    resource:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\nlogging:\r\n  level:\r\n    root: INFO\r\n    com.github.taoroot: DEBUG\r\n  file:\r\n    name: /project/logs/${spring.application.name}-1/debug.log', 'f4895eb537cfbb710dfbe2b7b79fdef5', '2020-10-29 02:12:06', '2020-10-29 02:12:06', NULL, '10.244.2.1', 'U', '');
INSERT INTO `his_config_info` VALUES (8, 61, 'application-test.yml', 'DEFAULT_GROUP', '', 'management:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \"*\"\r\n    enabled-by-default: true\r\n\r\nsecurity:\r\n  ignore:\r\n    key: zhiyi-cloud\r\n  oauth2:\r\n    authorization:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\n    resource:\r\n      jwt:\r\n        key-value: zhiyi-cloud\r\nlogging:\r\n  level:\r\n    root: INFO\r\n    com.github.taoroot: DEBUG\r\n  file:\r\n    name: ./logs/${spring.application.name}-1/debug.log', 'dad32821990cd6854f2238bf06f2c1eb', '2020-10-29 02:13:35', '2020-10-29 02:13:36', NULL, '10.244.2.1', 'U', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES ('ROLE_GUEST', ':*:*', 'r');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('guest', 'ROLE_GUEST');
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('guest', '$2a$10$Q7dNhH..v1jfNB0emjodGuPPUUGGxFfF/50x5ua3e3wBUMEj.QJoe', 1);
INSERT INTO `users` VALUES ('nacos', '$2a$10$vJZ/wQAJK9ilN3IZXAAcIegSAvRVlobicbJ0fEsIfbs8NZpbVuYQW', 1);

SET FOREIGN_KEY_CHECKS = 1;
