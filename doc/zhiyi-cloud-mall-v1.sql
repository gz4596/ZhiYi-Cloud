/*
 Navicat Premium Data Transfer

 Source Server         : tao-dev
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : zhiyi-cloud-mall-v1

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 09/10/2020 22:50:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_authority
-- ----------------------------
DROP TABLE IF EXISTS `admin_authority`;
CREATE TABLE `admin_authority`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` int(11) NOT NULL COMMENT '父菜单ID',
  `path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'the name show in sidebar and breadcrumb (recommend set)',
  `name` varchar(350) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'the name is used by <keep-alive> (must set!!!)',
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `always_show` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0-开启，1- 关闭',
  `redirect` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'if set noRedirect will no redirect in the breadcrumb',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `weight` int(11) NOT NULL DEFAULT 1 COMMENT '排序值',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '菜单类型 （0菜单 1按钮）',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `breadcrumb` int(1) NULL DEFAULT 0,
  `hidden` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0-开启，1- 关闭',
  `authority` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1007 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_authority
-- ----------------------------
INSERT INTO `admin_authority` VALUES (10, -1, '/external-link', '动态路由', NULL, 'Layout', NULL, '/iframe/index', 'link', 2, '0', '2020-08-18 10:13:56', '2020-09-05 10:28:27', NULL, '0', NULL);
INSERT INTO `admin_authority` VALUES (11, 10, 'https://github.com/taoroot/zhiyi-cloud', 'github', 'github', 'iframe/index', NULL, NULL, 'github', 1, '0', '2020-08-18 10:14:08', '2020-09-05 10:36:58', NULL, '0', NULL);
INSERT INTO `admin_authority` VALUES (12, 10, 'vuepress', 'vuepress', 'https://zhiyi.zone', 'iframe/index', NULL, NULL, 'education', 1, '0', '2020-08-18 10:14:08', '2020-10-07 10:55:28', NULL, '0', NULL);
INSERT INTO `admin_authority` VALUES (13, 10, 'swagger', 'swagger', 'http://localhost:8008/doc.html', 'iframe/index', NULL, NULL, 'swagger', 1, '0', '2020-08-18 10:14:08', '2020-10-09 11:46:15', NULL, '0', NULL);
INSERT INTO `admin_authority` VALUES (14, 10, 'http://localhost:6222', 'monitor', 'monitor', 'Layout', NULL, NULL, 'chart', 1, '0', '2020-08-18 10:14:08', '2020-10-09 11:45:38', NULL, '0', NULL);
INSERT INTO `admin_authority` VALUES (1000, -1, '/system', '系统设置', NULL, 'Layout', '0', '/authority/index', 'example', 1, '0', '2020-08-24 16:24:45', '2020-08-26 09:11:11', NULL, '0', NULL);
INSERT INTO `admin_authority` VALUES (1001, 1000, 'authority', '权限管理', 'Authority', 'authority/index', '0', NULL, 'tree-table', 4, '0', '2020-08-24 16:26:50', '2020-08-24 16:40:01', NULL, '0', NULL);
INSERT INTO `admin_authority` VALUES (1002, 1000, 'dept', '部门管理', 'Dept', 'dept/index', '0', NULL, 'tree', 3, '0', '2020-08-24 16:35:28', '2020-08-24 16:39:57', 0, '0', NULL);
INSERT INTO `admin_authority` VALUES (1003, 1000, 'role', '角色管理', 'Role', 'role/index', '0', NULL, 'peoples', 2, '0', '2020-08-24 16:35:28', '2020-08-24 16:39:51', 0, '0', NULL);
INSERT INTO `admin_authority` VALUES (1004, 1000, 'user', '用户管理', 'User', 'user/index', '0', NULL, 'user', 1, '0', '2020-08-24 16:36:57', '2020-08-24 16:39:32', 0, '0', NULL);
INSERT INTO `admin_authority` VALUES (1005, 1000, 'dict', '字典管理', 'Dict', 'dict/index', '0', NULL, 'dict', 5, '0', '2020-10-08 13:13:54', '2020-10-09 17:19:07', 0, '0', NULL);
INSERT INTO `admin_authority` VALUES (1006, 1000, 'log', '系统日志', 'Log', 'log/index', '0', NULL, 'druid', 6, '0', '2020-10-09 11:13:22', NULL, 0, '0', NULL);

-- ----------------------------
-- Table structure for admin_dept
-- ----------------------------
DROP TABLE IF EXISTS `admin_dept`;
CREATE TABLE `admin_dept`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '部门主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '部门名称',
  `weight` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '上级部门',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `enabled` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '部门管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_dept
-- ----------------------------
INSERT INTO `admin_dept` VALUES (1000, '软件一部', 1, '2020-08-20 15:36:35', '2020-10-09 17:23:48', -1, NULL, NULL, NULL, '1');
INSERT INTO `admin_dept` VALUES (1001, '软一前端', 1, '2020-08-20 16:30:17', '2020-10-09 17:24:06', 1000, NULL, NULL, NULL, '1');
INSERT INTO `admin_dept` VALUES (1002, '软一后端', 2, '2020-08-20 16:30:17', '2020-10-09 17:29:26', 1000, NULL, NULL, NULL, '1');
INSERT INTO `admin_dept` VALUES (2000, '软件二部', 2, '2020-08-20 16:32:35', '2020-10-09 17:29:41', -1, NULL, NULL, NULL, '1');
INSERT INTO `admin_dept` VALUES (2001, '软二前端', 1, '2020-08-20 16:32:35', '2020-08-26 09:46:16', 2000, NULL, NULL, NULL, '1');
INSERT INTO `admin_dept` VALUES (2002, '软二后端', 2, '2020-08-20 16:32:35', '2020-08-26 11:06:17', 2000, '123@qq.com', '13111333333', NULL, '1');

-- ----------------------------
-- Table structure for admin_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `admin_dict_data`;
CREATE TABLE `admin_dict_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `type_id` bigint(20) NOT NULL,
  `sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `is_default` int(1) NULL DEFAULT NULL COMMENT '是否默认（Y是 N否）',
  `enabled` int(1) NULL DEFAULT 0 COMMENT '状态（1正常 0停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_dict_data
-- ----------------------------
INSERT INTO `admin_dict_data` VALUES (1, 1, 1, '全部数据权限', '1', 1, 1, 'admin', '2018-03-16 11:33:00', NULL, '2018-03-16 11:33:00', NULL);
INSERT INTO `admin_dict_data` VALUES (2, 1, 2, '本部门数据权限', '2', 0, 1, 'admin', '2018-03-16 11:33:00', NULL, '2018-03-16 11:33:00', NULL);
INSERT INTO `admin_dict_data` VALUES (3, 1, 3, '本部门及以下数据权限', '3', 0, 1, 'admin', '2018-03-16 11:33:00', NULL, '2018-03-16 11:33:00', NULL);
INSERT INTO `admin_dict_data` VALUES (4, 1, 4, '自定数据权限', '4', 0, 1, 'admin', '2018-03-16 11:33:00', NULL, '2018-03-16 11:33:00', NULL);
INSERT INTO `admin_dict_data` VALUES (5, 1, 5, '仅本人数据权限', '5', 0, 0, 'admin', '2018-03-16 11:33:00', NULL, '2018-03-16 11:33:00', NULL);
INSERT INTO `admin_dict_data` VALUES (9, 23, 1, '男', '1', 1, 1, '', NULL, '', NULL, '');
INSERT INTO `admin_dict_data` VALUES (10, 23, 2, '女', '2', 0, 1, '', NULL, '', NULL, NULL);
INSERT INTO `admin_dict_data` VALUES (11, 23, 3, '未知', '3', 0, 1, '', NULL, '', NULL, NULL);

-- ----------------------------
-- Table structure for admin_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `admin_dict_type`;
CREATE TABLE `admin_dict_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典名称',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典类型',
  `enabled` int(1) NULL DEFAULT 0 COMMENT '状态（1正常 0停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_dict_type
-- ----------------------------
INSERT INTO `admin_dict_type` VALUES (1, '数据权限', 'admin_data_scope', 1, 'admin', '2018-03-16 11:33:00', 'admin', '2020-03-16 11:33:00', '');
INSERT INTO `admin_dict_type` VALUES (23, '性别', 'admin_sex', 1, '', NULL, '', NULL, '');

-- ----------------------------
-- Table structure for admin_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_log`;
CREATE TABLE `admin_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别',
  `user_id` bigint(20) NULL DEFAULT -1 COMMENT '操作人员',
  `dept_id` bigint(20) NULL DEFAULT -1 COMMENT '部门名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态',
  `error` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `time` int(10) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `scope_type` int(1) NULL DEFAULT NULL,
  `scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_idx1_role_code`(`role`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, '普通角色', 'ROLE_USER', 1, '[]', '', '2020-08-18 19:36:19', '2020-08-26 16:46:15');
INSERT INTO `admin_role` VALUES (2, '个人角色', 'ROLE_OWN', 5, '[]', '', '2020-08-27 16:40:44', NULL);

-- ----------------------------
-- Table structure for admin_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_authority`;
CREATE TABLE `admin_role_authority`  (
  `role_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`, `authority_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role_authority
-- ----------------------------
INSERT INTO `admin_role_authority` VALUES (1, 10);
INSERT INTO `admin_role_authority` VALUES (1, 11);
INSERT INTO `admin_role_authority` VALUES (1, 12);
INSERT INTO `admin_role_authority` VALUES (1, 13);
INSERT INTO `admin_role_authority` VALUES (1, 14);
INSERT INTO `admin_role_authority` VALUES (1, 1000);
INSERT INTO `admin_role_authority` VALUES (1, 1001);
INSERT INTO `admin_role_authority` VALUES (1, 1002);
INSERT INTO `admin_role_authority` VALUES (1, 1003);
INSERT INTO `admin_role_authority` VALUES (1, 1004);
INSERT INTO `admin_role_authority` VALUES (1, 1005);
INSERT INTO `admin_role_authority` VALUES (1, 1006);
INSERT INTO `admin_role_authority` VALUES (2, 1000);
INSERT INTO `admin_role_authority` VALUES (2, 1004);

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT 1,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dept_id` int(11) NULL DEFAULT NULL,
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 203 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, 'user', '{bcrypt}$2a$10$F.8BYXYzdKEGXPEZ0WzzR.21DWMZyzsg3nEsc3ZNHoJ/GR3A71ETm', 1, '13131333333', 'https://avatars0.githubusercontent.com/u/14340565?v=4', 1000, '超级管理员', '1@qq.com');
INSERT INTO `admin_user` VALUES (100, 'aaaaaa', '$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2', 1, '1234567890', 'http://localhost:9528/logo.jpg', 1000, '11111', '1@qq.com');
INSERT INTO `admin_user` VALUES (101, 'bbbbbb', '$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2', 1, '1234567890', 'http://localhost:9528/logo.jpg', 1001, '22222', '1@qq.com');
INSERT INTO `admin_user` VALUES (102, 'cccccc', '$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2', 0, '1234567890', 'http://localhost:9528/logo.jpg', 1002, '33333', '1@qq.com');
INSERT INTO `admin_user` VALUES (200, 'dddddd', '$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2', 1, '1234567890', 'http://localhost:9528/logo.jpg', 2002, '44444', '1@qq.com');
INSERT INTO `admin_user` VALUES (201, 'eeeeee', '$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2', 1, '1234567890', 'http://localhost:9528/logo.jpg', 2001, '55555', '1@qq.com');
INSERT INTO `admin_user` VALUES (202, 'ffffff', '$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2', 1, '1234567890', 'http://localhost:9528/logo.jpg', 2000, '66666', '1@qq.com');

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES (1, 1);
INSERT INTO `admin_user_role` VALUES (1, 2);

-- ----------------------------
-- Table structure for mall_user
-- ----------------------------
DROP TABLE IF EXISTS `mall_user`;
CREATE TABLE `mall_user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `openId` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '微信唯一编码',
  `nickname` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `sex` tinyint(1) NOT NULL DEFAULT 0 COMMENT '性别 1男 2女 0未知',
  `country` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '国家',
  `province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '省份',
  `city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '城市',
  `headImgUrl` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `regTime` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `lastLoginTime` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `mobile`(`mobile`) USING BTREE,
  INDEX `openId`(`openId`) USING BTREE,
  INDEX `uid`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
