/*
 Navicat Premium Data Transfer

 Source Server         : 122.51.85.179(rds)
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:7222
 Source Schema         : zhiyi-cloud-mall-v1

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 29/10/2020 17:06:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_authority
-- ----------------------------
DROP TABLE IF EXISTS `admin_authority`;
CREATE TABLE `admin_authority`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `authority` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(350) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'the name is used by <keep-alive> (must set!!!)',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `path` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_authority
-- ----------------------------
INSERT INTO `admin_authority` VALUES (1, 'admin:dept:create', '部门创建', '2020-10-15 13:20:05', '2020-10-14 21:41:49', '/dept', 'POST');
INSERT INTO `admin_authority` VALUES (2, 'admin:dept:delete', '部门删除', '2020-10-15 13:20:05', '2020-10-15 13:16:54', '/dept', 'DELETE');
INSERT INTO `admin_authority` VALUES (3, 'admin:dept:get', '部门详情', '2020-10-15 13:20:05', '2020-10-15 13:16:54', '/dept', 'GET');
INSERT INTO `admin_authority` VALUES (4, 'admin:dept:update', '部门更新', '2020-10-15 13:20:05', '2020-10-15 13:16:54', '/dept', 'PUT');
INSERT INTO `admin_authority` VALUES (5, 'admin:dept:page', '部门分页', '2020-10-15 13:20:05', '2020-10-15 13:16:54', '/depts', 'GET');
INSERT INTO `admin_authority` VALUES (6, 'admin:dict_type:page', '字典类型分页', '2020-10-15 13:23:54', '2020-10-15 13:16:54', '/dict/types', 'GET');
INSERT INTO `admin_authority` VALUES (7, 'admin:dict_type:create', '字典类型创建', '2020-10-15 13:23:54', '2020-10-15 13:16:54', '/dict/type', 'POST');
INSERT INTO `admin_authority` VALUES (8, 'admin:dict_type:delete', '字典类型删除', '2020-10-15 13:23:54', '2020-10-15 13:16:54', '/dict/type', 'DELETE');
INSERT INTO `admin_authority` VALUES (9, 'admin:dict_type:update', '字典类型更新', '2020-10-15 13:23:54', '2020-10-15 13:16:54', '/dict/type', 'PUT');
INSERT INTO `admin_authority` VALUES (10, 'admin:dict_data:page', '字典数据分页', '2020-10-28 09:50:58', '2020-10-15 13:16:54', '/dict/datas', 'GET');
INSERT INTO `admin_authority` VALUES (11, 'admin:dict_data:create', '字典类型创建', '2020-10-15 13:23:54', '2020-10-15 13:20:59', '/dict/data', 'POST');
INSERT INTO `admin_authority` VALUES (12, 'admin:dict_data:delete', '字典类型删除', '2020-10-15 13:23:54', '2020-10-15 13:21:36', '/dict/data', 'DELETE');
INSERT INTO `admin_authority` VALUES (13, 'admin:dict_data:update', '字典数据更新', '2020-10-28 10:20:47', '2020-10-15 13:21:36', '/dict/data', 'PUT');
INSERT INTO `admin_authority` VALUES (14, 'admin:dict_type:data', '字典类型的数据', '2020-10-15 13:23:54', '2020-10-15 13:21:36', '/dict/data', 'GET');
INSERT INTO `admin_authority` VALUES (15, 'admin:log:page', '日志分页', '2020-10-15 13:27:03', '2020-10-15 13:24:45', '/logs', 'GET');
INSERT INTO `admin_authority` VALUES (16, 'admin:menu:tree', '菜单树', '2020-10-15 13:27:03', '2020-10-15 13:26:05', '/menus', 'GET');
INSERT INTO `admin_authority` VALUES (17, 'admin:menu:get', '菜单详情', '2020-10-15 13:27:03', '2020-10-15 13:26:05', '/menu/{id}', 'GET');
INSERT INTO `admin_authority` VALUES (18, 'admin:menu:delete', '菜单删除', '2020-10-15 13:27:03', '2020-10-15 13:26:05', '/menu/{id}', 'DELETE');
INSERT INTO `admin_authority` VALUES (19, 'admin:menu:create', '菜单创建', '2020-10-15 13:27:03', '2020-10-15 13:26:05', '/menu', 'POST');
INSERT INTO `admin_authority` VALUES (20, 'admin:menu:update', '菜单更新', '2020-10-15 13:27:03', '2020-10-15 13:26:05', '/menu', 'PUT');
INSERT INTO `admin_authority` VALUES (21, 'admin:menu:sort', '菜单排序', '2020-10-15 13:27:03', '2020-10-15 13:26:05', '/menu/sort', 'PUT');
INSERT INTO `admin_authority` VALUES (22, 'admin:menu:authoritys', '菜单的权限', '2020-10-15 13:27:03', '2020-10-15 13:26:05', '/menu/authoritys', 'GET');
INSERT INTO `admin_authority` VALUES (23, 'admin:post:create', '岗位创建', '2020-10-15 13:28:00', '2020-10-15 13:26:35', '/post', 'POST');
INSERT INTO `admin_authority` VALUES (24, 'admin:post:delete\'', '岗位删除', NULL, '2020-10-15 13:28:00', '/post', 'DELETE');
INSERT INTO `admin_authority` VALUES (25, 'admin:post:update', '岗位更新', NULL, '2020-10-15 13:28:00', '/post', 'PUT');
INSERT INTO `admin_authority` VALUES (26, 'admin:post:page', '岗位分页', NULL, '2020-10-15 13:28:00', '/posts', 'GET');
INSERT INTO `admin_authority` VALUES (27, 'admin:role:create', '角色创建', '2020-10-15 13:29:04', '2020-10-15 13:28:29', '/role', 'POST');
INSERT INTO `admin_authority` VALUES (28, 'admin:role:delete', '角色删除', '2020-10-15 13:29:04', '2020-10-15 13:28:29', '/role', 'DELETE');
INSERT INTO `admin_authority` VALUES (29, 'admin:role:update', '角色更新', '2020-10-15 13:29:04', '2020-10-15 13:28:29', '/role', 'PUT');
INSERT INTO `admin_authority` VALUES (30, 'admin:role:page', '角色分页', '2020-10-15 13:29:04', '2020-10-15 13:28:29', '/roles', 'GET');
INSERT INTO `admin_authority` VALUES (31, 'admin:user:create', '用户创建', NULL, '2020-10-15 13:29:58', '/user', 'POST');
INSERT INTO `admin_authority` VALUES (32, 'admin:user:delete', '用户删除', NULL, '2020-10-15 13:29:58', '/user', 'DELETE');
INSERT INTO `admin_authority` VALUES (33, 'admin:user:update', '用户更新', NULL, '2020-10-15 13:29:58', '/user', 'PUT');
INSERT INTO `admin_authority` VALUES (34, 'admin:user:page', '用户分页', NULL, '2020-10-15 13:29:58', '/users', 'GET');
INSERT INTO `admin_authority` VALUES (35, 'admin:auth:user_info', '用户详情', NULL, '2020-10-15 13:31:02', '/auth/user_info', 'GET');
INSERT INTO `admin_authority` VALUES (36, 'admin:authority:page', '权限分页', '2020-10-17 17:37:35', '2020-10-17 17:32:33', '/authoritys', 'GET');
INSERT INTO `admin_authority` VALUES (37, 'admin:menu:authority:add', '菜单的权限添加', NULL, '2020-10-18 17:57:00', NULL, NULL);
INSERT INTO `admin_authority` VALUES (38, 'admin:menu:authority:delete', '菜单的权限删除', NULL, '2020-10-18 17:57:22', NULL, NULL);

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
INSERT INTO `admin_dept` VALUES (1000, '软件一部', 1, '2020-08-20 15:36:35', '2020-10-12 23:13:37', -1, '123@qq.com', '13100000001', '刘一', '1');
INSERT INTO `admin_dept` VALUES (1001, '软一前端', 1, '2020-08-20 16:30:17', '2020-10-12 23:13:37', 1000, '123@qq.com', '13100000002', '陈二', '1');
INSERT INTO `admin_dept` VALUES (1002, '软一后端', 2, '2020-08-20 16:30:17', '2020-10-12 23:13:37', 1000, '123@qq.com', '13100000003', '张三', '1');
INSERT INTO `admin_dept` VALUES (2000, '软件二部', 2, '2020-08-20 16:32:35', '2020-10-12 23:13:37', -1, '123@qq.com', '13100000004', '李四', '1');
INSERT INTO `admin_dept` VALUES (2001, '软二前端', 1, '2020-08-20 16:32:35', '2020-10-12 23:13:37', 2000, '123@qq.com', '13100000005', '王五', '1');
INSERT INTO `admin_dept` VALUES (2002, '软二后端', 2, '2020-08-20 16:32:35', '2020-10-12 23:13:37', 2000, '123@qq.com', '13100000006', '赵六', '1');

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
INSERT INTO `admin_dict_data` VALUES (5, 1, 5, '仅本人数据权限', '5', 0, 1, 'admin', '2018-03-16 11:33:00', NULL, '2018-03-16 11:33:00', NULL);
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
  `result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态',
  `error` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `time` int(10) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1314398475466184587 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` int(11) NOT NULL COMMENT '父菜单ID',
  `path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
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
) ENGINE = InnoDB AUTO_INCREMENT = 1035 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES (10, -1, '/external-link', '动态路由', NULL, 'Layout', NULL, '/iframe/index', 'link', 1, '0', '2020-08-18 10:13:56', '2020-10-15 23:22:59', NULL, '0', NULL);
INSERT INTO `admin_menu` VALUES (11, 10, 'https://github.com/taoroot/zhiyi-cloud', 'github', 'github', 'iframe/index', NULL, NULL, 'github', 2, '0', '2020-08-18 10:14:08', '2020-10-14 20:04:27', NULL, '0', NULL);
INSERT INTO `admin_menu` VALUES (12, 10, 'vuepress', 'vuepress', 'https://zhiyi.zone', 'iframe/index', NULL, NULL, 'education', 0, '0', '2020-08-18 10:14:08', '2020-10-14 20:04:25', NULL, '0', NULL);
INSERT INTO `admin_menu` VALUES (13, 10, 'swagger', 'swagger', 'http://localhost:8008/doc.html', 'iframe/index', NULL, NULL, 'swagger', 1, '0', '2020-08-18 10:14:08', '2020-10-14 20:04:27', NULL, '0', NULL);
INSERT INTO `admin_menu` VALUES (14, 10, 'http://localhost:6222', 'monitor', 'monitor', 'Layout', NULL, NULL, 'chart', 3, '0', '2020-08-18 10:14:08', '2020-10-14 20:03:48', NULL, '0', NULL);
INSERT INTO `admin_menu` VALUES (1000, -1, '/system', '系统设置', 'System', 'Layout', '0', '/authority/index', 'example', 0, '0', '2020-08-24 16:24:45', '2020-10-15 23:22:59', NULL, '0', 'system');
INSERT INTO `admin_menu` VALUES (1001, 1000, 'menu', '菜单管理', 'Menu', 'menu/index', '0', NULL, 'tree-table', 3, '0', '2020-08-24 16:26:50', '2020-10-15 07:25:45', NULL, '0', 'system:menu');
INSERT INTO `admin_menu` VALUES (1002, 1000, 'dept', '部门管理', 'Dept', 'dept/index', '0', NULL, 'tree', 2, '0', '2020-08-24 16:35:28', '2020-10-15 09:18:17', 0, '0', 'system:dept');
INSERT INTO `admin_menu` VALUES (1003, 1000, 'role', '角色管理', 'Role', 'role/index', '0', NULL, 'peoples', 1, '0', '2020-08-24 16:35:28', '2020-10-15 13:56:09', 0, '0', 'system:role');
INSERT INTO `admin_menu` VALUES (1004, 1000, 'user', '用户管理', 'User', 'user/index', '0', NULL, 'user', 0, '0', '2020-08-24 16:36:57', '2020-10-15 13:56:09', 0, '0', 'system:user');
INSERT INTO `admin_menu` VALUES (1005, 1000, 'dict', '字典管理', 'Dict', 'dict/index', '0', NULL, 'dict', 6, '0', '2020-10-08 13:13:54', '2020-10-15 07:25:28', 0, '0', 'system:dict');
INSERT INTO `admin_menu` VALUES (1006, 1000, 'log', '系统日志', 'Log', 'log/index', '0', NULL, 'druid', 5, '0', '2020-10-09 11:13:22', '2020-10-15 07:25:29', 0, '0', 'system:log');
INSERT INTO `admin_menu` VALUES (1007, 1000, 'post', '岗位管理', 'Post', 'post/index', '0', NULL, 'post', 4, '0', '2020-10-14 09:02:11', '2020-10-15 07:25:35', 0, '0', 'system:post');
INSERT INTO `admin_menu` VALUES (1013, 1004, NULL, '用户创建', NULL, '', '0', NULL, NULL, 0, '1', '2020-10-18 17:46:35', '2020-10-18 17:46:44', 0, '0', 'admin:user:create');
INSERT INTO `admin_menu` VALUES (1014, 1004, NULL, '用户删除', NULL, '', '0', NULL, NULL, 1, '1', '2020-10-18 18:41:27', NULL, 0, '0', 'admin:user:delete');
INSERT INTO `admin_menu` VALUES (1015, 1004, NULL, '用户更新', NULL, '', '0', NULL, NULL, 2, '1', '2020-10-18 18:41:49', '2020-10-18 18:41:49', 0, '0', 'admin:user:update');
INSERT INTO `admin_menu` VALUES (1016, 1004, NULL, '密码重置', NULL, '', '0', NULL, NULL, 3, '1', '2020-10-18 18:42:48', '2020-10-18 18:42:48', 0, '0', 'admin:user:resetPass');
INSERT INTO `admin_menu` VALUES (1017, 1003, NULL, '角色创建', NULL, '', '0', NULL, NULL, 0, '1', '2020-10-28 09:08:47', '2020-10-28 09:08:47', 0, '0', 'admin:role:create');
INSERT INTO `admin_menu` VALUES (1018, 1003, NULL, '用户删除', NULL, '', '0', NULL, NULL, 1, '1', '2020-10-28 09:09:11', NULL, 0, '0', 'admin:role:delete');
INSERT INTO `admin_menu` VALUES (1019, 1003, NULL, '角色更新', NULL, '', '0', NULL, NULL, 2, '1', '2020-10-28 09:09:34', '2020-10-28 09:09:34', 0, '0', 'admin:role:update');
INSERT INTO `admin_menu` VALUES (1020, 1002, NULL, '部门创建', NULL, '', '0', NULL, NULL, 0, '1', '2020-10-28 09:11:23', '2020-10-28 09:11:23', 0, '0', 'system:dept:create');
INSERT INTO `admin_menu` VALUES (1021, 1002, NULL, '部门删除', NULL, '', '0', NULL, NULL, 1, '1', '2020-10-28 09:11:56', NULL, 0, '0', 'system:dept:delete');
INSERT INTO `admin_menu` VALUES (1022, 1002, NULL, '部门更新', NULL, '', '0', NULL, NULL, 2, '1', '2020-10-28 09:13:28', '2020-10-28 09:13:28', 0, '0', 'system:dept:update');
INSERT INTO `admin_menu` VALUES (1023, 1001, NULL, '菜单创建', NULL, '', '0', NULL, NULL, 0, '1', '2020-10-28 09:14:52', '2020-10-28 10:29:03', 0, '0', 'system:menu:create');
INSERT INTO `admin_menu` VALUES (1024, 1001, NULL, '菜单删除', NULL, '', '0', NULL, NULL, 1, '1', '2020-10-28 09:15:18', '2020-10-28 10:29:04', 0, '0', 'system:menu:delete');
INSERT INTO `admin_menu` VALUES (1025, 1001, NULL, '菜单更新', NULL, '', '0', NULL, NULL, 2, '1', '2020-10-28 09:15:33', '2020-10-28 10:29:06', 0, '0', 'system:menu:update');
INSERT INTO `admin_menu` VALUES (1026, 1007, NULL, '岗位创建', NULL, '', '0', NULL, NULL, 0, '1', '2020-10-28 09:24:33', '2020-10-28 09:24:33', 0, '0', 'system:post:create');
INSERT INTO `admin_menu` VALUES (1027, 1007, NULL, '岗位删除', NULL, '', '0', NULL, NULL, 1, '1', '2020-10-28 09:25:12', NULL, 0, '0', 'system:post:delete');
INSERT INTO `admin_menu` VALUES (1028, 1007, NULL, '岗位更新', NULL, '', '0', NULL, NULL, 2, '1', '2020-10-28 09:25:29', '2020-10-28 09:25:29', 0, '0', 'system:post:update');
INSERT INTO `admin_menu` VALUES (1029, 1001, NULL, '权限添加', NULL, '', '0', NULL, NULL, 5, '1', '2020-10-28 09:14:52', '2020-10-28 10:29:11', 0, '0', 'system:menu:create');
INSERT INTO `admin_menu` VALUES (1030, 1005, NULL, '字典类型更新', NULL, '', '0', NULL, NULL, 0, '1', '2020-10-28 10:18:26', '2020-10-28 10:20:11', 0, '0', 'system:dict_type:create');
INSERT INTO `admin_menu` VALUES (1031, 1005, NULL, '字典数据更新', NULL, '', '0', NULL, NULL, 1, '1', '2020-10-28 10:19:45', '2020-10-28 10:20:04', 0, '0', 'system:dict_data:update');
INSERT INTO `admin_menu` VALUES (1033, 1001, NULL, '权限删除', NULL, '', '0', NULL, NULL, 4, '1', '2020-10-28 10:25:52', '2020-10-28 10:29:13', 0, '0', 'admin:menu:authority:delete');
INSERT INTO `admin_menu` VALUES (1034, 1001, NULL, '菜单排序', NULL, '', '0', NULL, NULL, 3, '1', '2020-10-28 10:27:04', '2020-10-28 10:29:13', 0, '0', 'admin:menu:sort');

-- ----------------------------
-- Table structure for admin_menu_authority
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu_authority`;
CREATE TABLE `admin_menu_authority`  (
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `authority_id` int(11) NOT NULL COMMENT '权限ID',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `menu_id`(`menu_id`, `authority_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 253 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_menu_authority
-- ----------------------------
INSERT INTO `admin_menu_authority` VALUES (1001, 16, 197);
INSERT INTO `admin_menu_authority` VALUES (1001, 22, 198);
INSERT INTO `admin_menu_authority` VALUES (1001, 36, 204);
INSERT INTO `admin_menu_authority` VALUES (1003, 5, 250);
INSERT INTO `admin_menu_authority` VALUES (1003, 14, 251);
INSERT INTO `admin_menu_authority` VALUES (1003, 30, 190);
INSERT INTO `admin_menu_authority` VALUES (1004, 34, 252);
INSERT INTO `admin_menu_authority` VALUES (1005, 6, 202);
INSERT INTO `admin_menu_authority` VALUES (1005, 10, 206);
INSERT INTO `admin_menu_authority` VALUES (1006, 15, 249);
INSERT INTO `admin_menu_authority` VALUES (1013, 31, 187);
INSERT INTO `admin_menu_authority` VALUES (1014, 32, 188);
INSERT INTO `admin_menu_authority` VALUES (1015, 33, 189);
INSERT INTO `admin_menu_authority` VALUES (1017, 27, 191);
INSERT INTO `admin_menu_authority` VALUES (1018, 32, 192);
INSERT INTO `admin_menu_authority` VALUES (1019, 29, 193);
INSERT INTO `admin_menu_authority` VALUES (1020, 1, 194);
INSERT INTO `admin_menu_authority` VALUES (1021, 2, 195);
INSERT INTO `admin_menu_authority` VALUES (1022, 4, 196);
INSERT INTO `admin_menu_authority` VALUES (1023, 19, 199);
INSERT INTO `admin_menu_authority` VALUES (1024, 18, 200);
INSERT INTO `admin_menu_authority` VALUES (1025, 20, 201);
INSERT INTO `admin_menu_authority` VALUES (1029, 37, 205);
INSERT INTO `admin_menu_authority` VALUES (1030, 9, 207);
INSERT INTO `admin_menu_authority` VALUES (1031, 13, 208);
INSERT INTO `admin_menu_authority` VALUES (1033, 38, 209);
INSERT INTO `admin_menu_authority` VALUES (1034, 21, 210);

-- ----------------------------
-- Table structure for admin_post
-- ----------------------------
DROP TABLE IF EXISTS `admin_post`;
CREATE TABLE `admin_post`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `post` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `sort` int(10) NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `post_idx1_post_code`(`post`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '岗位' ROW_FORMAT = Dynamic;

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
-- Table structure for admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu`  (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1304 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role_menu
-- ----------------------------
INSERT INTO `admin_role_menu` VALUES (2, 1000, 14);
INSERT INTO `admin_role_menu` VALUES (2, 1004, 15);
INSERT INTO `admin_role_menu` VALUES (1, 1000, 1270);
INSERT INTO `admin_role_menu` VALUES (1, 1004, 1271);
INSERT INTO `admin_role_menu` VALUES (1, 1013, 1272);
INSERT INTO `admin_role_menu` VALUES (1, 1014, 1273);
INSERT INTO `admin_role_menu` VALUES (1, 1015, 1274);
INSERT INTO `admin_role_menu` VALUES (1, 1016, 1275);
INSERT INTO `admin_role_menu` VALUES (1, 1003, 1276);
INSERT INTO `admin_role_menu` VALUES (1, 1017, 1277);
INSERT INTO `admin_role_menu` VALUES (1, 1018, 1278);
INSERT INTO `admin_role_menu` VALUES (1, 1019, 1279);
INSERT INTO `admin_role_menu` VALUES (1, 1002, 1280);
INSERT INTO `admin_role_menu` VALUES (1, 1020, 1281);
INSERT INTO `admin_role_menu` VALUES (1, 1021, 1282);
INSERT INTO `admin_role_menu` VALUES (1, 1022, 1283);
INSERT INTO `admin_role_menu` VALUES (1, 1001, 1284);
INSERT INTO `admin_role_menu` VALUES (1, 1023, 1285);
INSERT INTO `admin_role_menu` VALUES (1, 1024, 1286);
INSERT INTO `admin_role_menu` VALUES (1, 1025, 1287);
INSERT INTO `admin_role_menu` VALUES (1, 1034, 1288);
INSERT INTO `admin_role_menu` VALUES (1, 1033, 1289);
INSERT INTO `admin_role_menu` VALUES (1, 1029, 1290);
INSERT INTO `admin_role_menu` VALUES (1, 1007, 1291);
INSERT INTO `admin_role_menu` VALUES (1, 1026, 1292);
INSERT INTO `admin_role_menu` VALUES (1, 1027, 1293);
INSERT INTO `admin_role_menu` VALUES (1, 1028, 1294);
INSERT INTO `admin_role_menu` VALUES (1, 1006, 1295);
INSERT INTO `admin_role_menu` VALUES (1, 1005, 1296);
INSERT INTO `admin_role_menu` VALUES (1, 1030, 1297);
INSERT INTO `admin_role_menu` VALUES (1, 1031, 1298);
INSERT INTO `admin_role_menu` VALUES (1, 10, 1299);
INSERT INTO `admin_role_menu` VALUES (1, 12, 1300);
INSERT INTO `admin_role_menu` VALUES (1, 13, 1301);
INSERT INTO `admin_role_menu` VALUES (1, 11, 1302);
INSERT INTO `admin_role_menu` VALUES (1, 14, 1303);

-- ----------------------------
-- Table structure for admin_social_details
-- ----------------------------
DROP TABLE IF EXISTS `admin_social_details`;
CREATE TABLE `admin_social_details`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `app_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `app_secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `authorize_uri` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `icon` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `title` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `proxy_uri` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_proxy` int(1) NOT NULL DEFAULT 0 COMMENT '0',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社交登录账号' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_social_details
-- ----------------------------
INSERT INTO `admin_social_details` VALUES (5, 'WX_OPEN', 'wx6cb5e779a9523765', '11d81a1dd9521600bfe3d6a3af2b7820', 'https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s&connect_redirect=1#wechat_redirect', 'data:image/png;base64,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', '微信登录', 'http://localhost:6628/social/proxy/authorize', 1, '微信');
INSERT INTO `admin_social_details` VALUES (6, 'ZHIYI_CLOUD', 'mall-v1-api-admin', 'secret', 'http://localhost:6628/oauth/authorize?response_type=code&client_id=%s&state=xyz&redirect_uri=%s', NULL, 'ZhiYi-Cloud OAuth2.0 统一登录', NULL, 0, '知一');
INSERT INTO `admin_social_details` VALUES (7, 'QQ', 'mall-v1-api-admin', 'secret', 'http://localhost:6628/oauth/authorize?response_type=code&client_id=%s&state=xyz&redirect_uri=%s', 'data:image/png;base64,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0p8zAABAh2lUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4KPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS41LWMwMTQgNzkuMTUxNDgxLCAyMDEzLzAzLzEzLTEyOjA5OjE1ICAgICAgICAiPgogICA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPgogICAgICA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIgogICAgICAgICAgICB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iCiAgICAgICAgICAgIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIKICAgICAgICAgICAgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iCiAgICAgICAgICAgIHhtbG5zOnN0RXZ0PSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VFdmVudCMiCiAgICAgICAgICAgIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIgogICAgICAgICAgICB4bWxuczpwaG90b3Nob3A9Imh0dHA6Ly9ucy5hZG9iZS5jb20vcGhvdG9zaG9wLzEuMC8iCiAgICAgICAgICAgIHhtbG5zOnRpZmY9Imh0dHA6Ly9ucy5hZG9iZS5jb20vdGlmZi8xLjAvIgogICAgICAgICAgICB4bWxuczpleGlmPSJodHRwOi8vbnMuYWRvYmUuY29tL2V4aWYvMS4wLyI+CiAgICAgICAgIDx4bXA6Q3JlYXRvclRvb2w+QWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKTwveG1wOkNyZWF0b3JUb29sPgogICAgICAgICA8eG1wOkNyZWF0ZURhdGU+MjAxNi0xMC0xMFQxNDoyNzo0OCswODowMDwveG1wOkNyZWF0ZURhdGU+CiAgICAgICAgIDx4bXA6TWV0YWRhdGFEYXRlPjIwMTYtMTAtMTBUMTQ6MzIrMDg6MDA8L3htcDpNZXRhZGF0YURhdGU+CiAgICAgICAgIDx4bXA6TW9kaWZ5RGF0ZT4yMDE2LTEwLTEwVDE0OjMyKzA4OjAwPC94bXA6TW9kaWZ5RGF0ZT4KICAgICAgICAgPGRjOmZvcm1hdD5pbWFnZS9wbmc8L2RjOmZvcm1hdD4KICAgICAgICAgPHhtcE1NOkluc3RhbmNlSUQ+eG1wLmlpZDoxMzE1NjU3My1iM2E2LTUwNDUtYWY2MC1mM2QxN2FjMzMxNDQ8L3htcE1NOkluc3RhbmNlSUQ+CiAgICAgICAgIDx4bXBNTTpEb2N1bWVudElEPnhtcC5kaWQ6ZDc2YWJmN2ItNDE0Zi1jYTRiLTllOTMtNmZjOTRhZWZhMTkyPC94bXBNTTpEb2N1bWVudElEPgogICAgICAgICA8eG1wTU06T3JpZ2luYWxEb2N1bWVudElEPnhtcC5kaWQ6ZDc2YWJmN2ItNDE0Zi1jYTRiLTllOTMtNmZjOTRhZWZhMTkyPC94bXBNTTpPcmlnaW5hbERvY3VtZW50SUQ+CiAgICAgICAgIDx4bXBNTTpIaXN0b3J5PgogICAgICAgICAgICA8cmRmOlNlcT4KICAgICAgICAgICAgICAgPHJkZjpsaSByZGY6cGFyc2VUeXBlPSJSZXNvdXJjZSI+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDphY3Rpb24+Y3JlYXRlZDwvc3RFdnQ6YWN0aW9uPgogICAgICAgICAgICAgICAgICA8c3RFdnQ6aW5zdGFuY2VJRD54bXAuaWlkOmQ3NmFiZjdiLTQxNGYtY2E0Yi05ZTkzLTZmYzk0YWVmYTE5Mjwvc3RFdnQ6aW5zdGFuY2VJRD4KICAgICAgICAgICAgICAgICAgPHN0RXZ0OndoZW4+MjAxNi0xMC0xMFQxNDoyNzo0OCswODowMDwvc3RFdnQ6d2hlbj4KICAgICAgICAgICAgICAgICAgPHN0RXZ0OnNvZnR3YXJlQWdlbnQ+QWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKTwvc3RFdnQ6c29mdHdhcmVBZ2VudD4KICAgICAgICAgICAgICAgPC9yZGY6bGk+CiAgICAgICAgICAgICAgIDxyZGY6bGkgcmRmOnBhcnNlVHlwZT0iUmVzb3VyY2UiPgogICAgICAgICAgICAgICAgICA8c3RFdnQ6YWN0aW9uPnNhdmVkPC9zdEV2dDphY3Rpb24+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDppbnN0YW5jZUlEPnhtcC5paWQ6NGUzYzRhMzAtNDZkZS1kNDQwLTg1NjQtNDYwODM0ODlhMjE5PC9zdEV2dDppbnN0YW5jZUlEPgogICAgICAgICAgICAgICAgICA8c3RFdnQ6d2hlbj4yMDE2LTEwLTEwVDE0OjMyKzA4OjAwPC9zdEV2dDp3aGVuPgogICAgICAgICAgICAgICAgICA8c3RFdnQ6c29mdHdhcmVBZ2VudD5BZG9iZSBQaG90b3Nob3AgQ0MgKFdpbmRvd3MpPC9zdEV2dDpzb2Z0d2FyZUFnZW50PgogICAgICAgICAgICAgICAgICA8c3RFdnQ6Y2hhbmdlZD4vPC9zdEV2dDpjaGFuZ2VkPgogICAgICAgICAgICAgICA8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaSByZGY6cGFyc2VUeXBlPSJSZXNvdXJjZSI+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDphY3Rpb24+Y29udmVydGVkPC9zdEV2dDphY3Rpb24+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDpwYXJhbWV0ZXJzPmZyb20gYXBwbGljYXRpb24vdm5kLmFkb2JlLnBob3Rvc2hvcCB0byBpbWFnZS9wbmc8L3N0RXZ0OnBhcmFtZXRlcnM+CiAgICAgICAgICAgICAgIDwvcmRmOmxpPgogICAgICAgICAgICAgICA8cmRmOmxpIHJkZjpwYXJzZVR5cGU9IlJlc291cmNlIj4KICAgICAgICAgICAgICAgICAgPHN0RXZ0OmFjdGlvbj5kZXJpdmVkPC9zdEV2dDphY3Rpb24+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDpwYXJhbWV0ZXJzPmNvbnZlcnRlZCBmcm9tIGFwcGxpY2F0aW9uL3ZuZC5hZG9iZS5waG90b3Nob3AgdG8gaW1hZ2UvcG5nPC9zdEV2dDpwYXJhbWV0ZXJzPgogICAgICAgICAgICAgICA8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaSByZGY6cGFyc2VUeXBlPSJSZXNvdXJjZSI+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDphY3Rpb24+c2F2ZWQ8L3N0RXZ0OmFjdGlvbj4KICAgICAgICAgICAgICAgICAgPHN0RXZ0Omluc3RhbmNlSUQ+eG1wLmlpZDoxMzE1NjU3My1iM2E2LTUwNDUtYWY2MC1mM2QxN2FjMzMxNDQ8L3N0RXZ0Omluc3RhbmNlSUQ+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDp3aGVuPjIwMTYtMTAtMTBUMTQ6MzIrMDg6MDA8L3N0RXZ0OndoZW4+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDpzb2Z0d2FyZUFnZW50PkFkb2JlIFBob3Rvc2hvcCBDQyAoV2luZG93cyk8L3N0RXZ0OnNvZnR3YXJlQWdlbnQ+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDpjaGFuZ2VkPi88L3N0RXZ0OmNoYW5nZWQ+CiAgICAgICAgICAgICAgIDwvcmRmOmxpPgogICAgICAgICAgICA8L3JkZjpTZXE+CiAgICAgICAgIDwveG1wTU06SGlzdG9yeT4KICAgICAgICAgPHhtcE1NOkRlcml2ZWRGcm9tIHJkZjpwYXJzZVR5cGU9IlJlc291cmNlIj4KICAgICAgICAgICAgPHN0UmVmOmluc3RhbmNlSUQ+eG1wLmlpZDo0ZTNjNGEzMC00NmRlLWQ0NDAtODU2NC00NjA4MzQ4OWEyMTk8L3N0UmVmOmluc3RhbmNlSUQ+CiAgICAgICAgICAgIDxzdFJlZjpkb2N1bWVudElEPnhtcC5kaWQ6ZDc2YWJmN2ItNDE0Zi1jYTRiLTllOTMtNmZjOTRhZWZhMTkyPC9zdFJlZjpkb2N1bWVudElEPgogICAgICAgICAgICA8c3RSZWY6b3JpZ2luYWxEb2N1bWVudElEPnhtcC5kaWQ6ZDc2YWJmN2ItNDE0Zi1jYTRiLTllOTMtNmZjOTRhZWZhMTkyPC9zdFJlZjpvcmlnaW5hbERvY3VtZW50SUQ+CiAgICAgICAgIDwveG1wTU06RGVyaXZlZEZyb20+CiAgICAgICAgIDxwaG90b3Nob3A6RG9jdW1lbnRBbmNlc3RvcnM+CiAgICAgICAgICAgIDxyZGY6QmFnPgogICAgICAgICAgICAgICA8cmRmOmxpPmFkb2JlOmRvY2lkOnBob3Rvc2hvcDphNDY5NDQxZC02MzNiLTExNzktYTAxZS1lMDIxM2MxNmQ0OGM8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaT54bXAuZGlkOjQwNzNkYWU2LWQ1MjUtNGYyNS1iMmFiLWZhZmZhY2Y3MWRiNDwvcmRmOmxpPgogICAgICAgICAgICA8L3JkZjpCYWc+CiAgICAgICAgIDwvcGhvdG9zaG9wOkRvY3VtZW50QW5jZXN0b3JzPgogICAgICAgICA8cGhvdG9zaG9wOkNvbG9yTW9kZT4zPC9waG90b3Nob3A6Q29sb3JNb2RlPgogICAgICAgICA8cGhvdG9zaG9wOklDQ1Byb2ZpbGU+c1JHQiBJRUM2MTk2Ni0yLjE8L3Bob3Rvc2hvcDpJQ0NQcm9maWxlPgogICAgICAgICA8dGlmZjpPcmllbnRhdGlvbj4xPC90aWZmOk9yaWVudGF0aW9uPgogICAgICAgICA8dGlmZjpYUmVzb2x1dGlvbj43MjAwMDAvMTAwMDA8L3RpZmY6WFJlc29sdXRpb24+CiAgICAgICAgIDx0aWZmOllSZXNvbHV0aW9uPjcyMDAwMC8xMDAwMDwvdGlmZjpZUmVzb2x1dGlvbj4KICAgICAgICAgPHRpZmY6UmVzb2x1dGlvblVuaXQ+MjwvdGlmZjpSZXNvbHV0aW9uVW5pdD4KICAgICAgICAgPGV4aWY6Q29sb3JTcGFjZT4xPC9leGlmOkNvbG9yU3BhY2U+CiAgICAgICAgIDxleGlmOlBpeGVsWERpbWVuc2lvbj4xNjwvZXhpZjpQaXhlbFhEaW1lbnNpb24+CiAgICAgICAgIDxleGlmOlBpeGVsWURpbWVuc2lvbj4xNjwvZXhpZjpQaXhlbFlEaW1lbnNpb24+CiAgICAgIDwvcmRmOkRlc2NyaXB0aW9uPgogICA8L3JkZjpSREY+CjwveDp4bXBtZXRhPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgIAo8P3hwYWNrZXQgZW5kPSJ3Ij8+abr3UwAAACBjSFJNAAB6JQAAgIMAAPn/AACA6QAAdTAAAOpgAAA6mAAAF2+SX8VGAAACM0lEQVR42nyTzUtUURjGf/djxqvOh44fdyYHcSOTiFRGgUmILoqIoHIjrlLc1K6J1v0FIQjWzoHZjwktgghdTEIEEuHCGVsUjkUfNNngmM7X26J7LzM364HDORye5znve85zEBFcaAcWgS+AWPOite9ARBCRPwuXOGMJ3SNTb/IvgwVA4vG4bGxsiGEYsr29LfF43DZZcBuodeJmYAYgFouRSCSIRCJsbm7S2dlpc2YsnoN6g9OAD2B+fh5Dr3D48wOvXr4gmUzaHJ/Fa7wMC1OAjA6osrPUJEfLhjN2lppkdEC125iqb0G31VeafYG3pcPK+lZFv/ygxLXzGj0hhY954enrKls54YSmV055jcCzX/tOAYqIoCgKX6OxJ8D1skj+e636ba9WLRWkVgsoqtqmat4OVevyKEoIWOnezd6wK9cBenWPmS2X+vo93gOPooTCmh4Ka7o7H9Tg4F251Nere0wrH04Ft4FHE+PjrJwd4ej5GtXBk9XymaGS6vNpLdGo7h8aVC/N3mJ1bQ3gjog8dioArgIEgkGU1lYkn6cl3K35799teLJAMEgdv8FgBCgUi8WA9+IFKFfwnBv+q4VisQhQsPgNz5gB5kzTlP/BNE0B5oCMO8p+K2G5VCp1rDiVSgmQs3j+Y//C9Jg2GTa7Cul0ukGcTqclbHYVpse0SfdfcHJwtGyMAquffwj3lqrZvDbcFunp833afb/fUXuz93BWi4XbFYCJppuH6/bBvwcAoERK0y/o5pAAAAAASUVORK5CYII=', 'QQ 登录', '', 0, 'QQ');

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
INSERT INTO `admin_user` VALUES (1, 'admin', '{bcrypt}$2a$10$F.8BYXYzdKEGXPEZ0WzzR.21DWMZyzsg3nEsc3ZNHoJ/GR3A71ETm', 1, '1310210000123', 'https://zhiyi.zone/zymy.jpg', 1000, '租户1超管(想修改数据,登出后选择租户2登录!! )', '1@qq.com');
INSERT INTO `admin_user` VALUES (100, 'liuyi', '$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2', 1, '1234567891', 'http://localhost:9528/logo.jpg', 1000, '11111', '1@qq.com');
INSERT INTO `admin_user` VALUES (101, 'chenger', '$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2', 1, '1234567892', 'http://localhost:9528/logo.jpg', 1001, '22222', '1@qq.com');
INSERT INTO `admin_user` VALUES (102, 'zhangsan', '$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2', 0, '1234567893', 'http://localhost:9528/logo.jpg', 1002, '33333', '1@qq.com');
INSERT INTO `admin_user` VALUES (200, 'lisi', '$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2', 1, '1234567890', 'http://localhost:9528/logo.jpg', 2002, '44444', '1@qq.com');
INSERT INTO `admin_user` VALUES (201, 'wangwu', '$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2', 1, '1234567890', 'http://localhost:9528/logo.jpg', 2001, '55555', '1@qq.com');
INSERT INTO `admin_user` VALUES (202, 'zhaoliu', '$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2', 1, '1234567890', 'http://localhost:9528/logo.jpg', 2000, '66666', '1@qq.com');

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for admin_user_social
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_social`;
CREATE TABLE `admin_user_social`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_user_id` int(11) NOT NULL,
  `social_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `social_id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `social_nickname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `social_avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `us_index`(`admin_user_id`, `social_type`) USING BTREE,
  UNIQUE INDEX `tu_index`(`social_type`, `social_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user_social
-- ----------------------------
INSERT INTO `admin_user_social` VALUES (2, 2, 'WX_OPEN', 'ozDLn1J4oCyKEx4B43biLorzIvk1', '12345', NULL);
INSERT INTO `admin_user_social` VALUES (3, 3, 'WX_OPEN', 'WX_OPEN', 'WX_OPEN', NULL);

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
