-- MySQL dump 10.13  Distrib 5.7.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: zhiyi-cloud-mall-v1
-- ------------------------------------------------------
-- Server version	5.7.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin_authority`
--

DROP TABLE IF EXISTS `admin_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` int(11) NOT NULL COMMENT '父菜单ID',
  `path` varchar(128) NOT NULL,
  `title` varchar(32) DEFAULT NULL COMMENT 'the name show in sidebar and breadcrumb (recommend set)',
  `name` varchar(350) DEFAULT NULL COMMENT 'the name is used by <keep-alive> (must set!!!)',
  `component` varchar(64) DEFAULT NULL,
  `always_show` varchar(255) DEFAULT '0' COMMENT '0-开启，1- 关闭',
  `redirect` varchar(32) DEFAULT NULL COMMENT 'if set noRedirect will no redirect in the breadcrumb',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `weight` int(11) NOT NULL DEFAULT '1' COMMENT '排序值',
  `type` char(1) NOT NULL DEFAULT '0' COMMENT '菜单类型 （0菜单 1按钮）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `breadcrumb` int(1) DEFAULT '0',
  `hidden` varchar(255) DEFAULT '0' COMMENT '0-开启，1- 关闭',
  `authority` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_authority`
--

INSERT INTO `admin_authority` (`id`, `parent_id`, `path`, `title`, `name`, `component`, `always_show`, `redirect`, `icon`, `weight`, `type`, `create_time`, `update_time`, `breadcrumb`, `hidden`, `authority`) VALUES (10,-1,'/external-link','动态路由',NULL,'Layout',NULL,'/iframe/index','link',2,'0','2020-08-18 02:13:56','2020-09-05 02:28:27',NULL,'0',NULL),(11,10,'https://github.com/taoroot/zhiyi-cloud','github','github','iframe/index',NULL,NULL,'github',1,'0','2020-08-18 02:14:08','2020-09-05 02:36:58',NULL,'0',NULL),(12,10,'vuepress','vuepress','https://zhiyi.zone','iframe/index',NULL,NULL,'education',1,'0','2020-08-18 02:14:08','2020-10-07 02:55:28',NULL,'0',NULL),(13,10,'swagger','swagger','http://api.cloud.zhiyi.zone/doc.html','iframe/index',NULL,NULL,'swagger',1,'0','2020-08-18 02:14:08','2020-10-07 02:55:28',NULL,'0',NULL),(14,10,'http://monitor.cloud.zhiyi.zone','monitor','monitor','Layout',NULL,NULL,'chart',1,'0','2020-08-18 02:14:08','2020-10-07 03:00:09',NULL,'0',NULL),(1000,-1,'/system','系统设置',NULL,'Layout','0','/authority/index','example',1,'0','2020-08-24 08:24:45','2020-08-26 01:11:11',NULL,'0',NULL),(1001,1000,'authority','权限管理','Authority','authority/index','0',NULL,'tree-table',4,'0','2020-08-24 08:26:50','2020-08-24 08:40:01',NULL,'0',NULL),(1002,1000,'dept','部门管理','Dept','dept/index','0',NULL,'tree',3,'0','2020-08-24 08:35:28','2020-08-24 08:39:57',0,'0',NULL),(1003,1000,'role','角色管理','Role','role/index','0',NULL,'peoples',2,'0','2020-08-24 08:35:28','2020-08-24 08:39:51',0,'0',NULL),(1004,1000,'user','用户管理','User','user/index','0',NULL,'user',1,'0','2020-08-24 08:36:57','2020-08-24 08:39:32',0,'0',NULL),(1005,1000,'dict','数据字典','Dict','dict/index','0',NULL,'dict',5,'0','2020-10-08 05:13:54','2020-10-08 05:15:48',0,'0',NULL);

--
-- Table structure for table `admin_dept`
--

DROP TABLE IF EXISTS `admin_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_dept` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '部门主键ID',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部门名称',
  `weight` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级部门',
  `email` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `leader` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `enabled` char(1) COLLATE utf8mb4_bin NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='部门管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_dept`
--

INSERT INTO `admin_dept` (`id`, `name`, `weight`, `create_time`, `update_time`, `parent_id`, `email`, `phone`, `leader`, `enabled`) VALUES (1000,'软件一部',1,'2020-08-20 07:36:35','2020-08-26 03:01:04',-1,NULL,NULL,NULL,'0'),(1001,'软一前端',1,'2020-08-20 08:30:17','2020-08-20 09:23:16',1000,NULL,NULL,NULL,'1'),(1002,'软一后端',1,'2020-08-20 08:30:17','2020-08-20 09:09:45',1000,NULL,NULL,NULL,'1'),(2000,'软件二部',1,'2020-08-20 08:32:35','2020-08-20 09:23:16',-1,NULL,NULL,NULL,'1'),(2001,'软二前端',1,'2020-08-20 08:32:35','2020-08-26 01:46:16',2000,NULL,NULL,NULL,'1'),(2002,'软二后端',2,'2020-08-20 08:32:35','2020-08-26 03:06:17',2000,'123@qq.com','13111333333',NULL,'1');

--
-- Table structure for table `admin_dict_data`
--

DROP TABLE IF EXISTS `admin_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_dict_data`
--

INSERT INTO `admin_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1,1,'全部数据权限','1','admin_data_scope','','','Y','0','admin','2018-03-16 11:33:00',NULL,'2018-03-16 11:33:00',NULL),(2,2,'本部门数据权限','2','admin_data_scope','','','N','0','admin','2018-03-16 11:33:00',NULL,'2018-03-16 11:33:00',NULL),(3,3,'本部门及以下数据权限','3','admin_data_scope','','','N','0','admin','2018-03-16 11:33:00',NULL,'2018-03-16 11:33:00',NULL),(4,4,'自定数据权限','4','admin_data_scope','','','N','0','admin','2018-03-16 11:33:00',NULL,'2018-03-16 11:33:00',NULL),(5,5,'仅本人数据权限','5','admin_data_scope','','','N','0','admin','2018-03-16 11:33:00',NULL,'2018-03-16 11:33:00',NULL);

--
-- Table structure for table `admin_dict_type`
--

DROP TABLE IF EXISTS `admin_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_dict_type`
--

INSERT INTO `admin_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1,'数据权限','admin_data_scope','0','admin','2018-03-16 11:33:00','admin','2020-03-16 11:33:00','数据权限');

--
-- Table structure for table `admin_role`
--

DROP TABLE IF EXISTS `admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `role` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `scope_type` int(1) DEFAULT NULL,
  `scope` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `role_idx1_role_code` (`role`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_role`
--

INSERT INTO `admin_role` (`id`, `name`, `role`, `scope_type`, `scope`, `description`, `create_time`, `update_time`) VALUES (1,'普通角色','ROLE_USER',1,'[]','','2020-08-18 11:36:19','2020-08-26 08:46:15'),(2,'个人角色','ROLE_OWN',5,'[]','','2020-08-27 08:40:44',NULL);

--
-- Table structure for table `admin_role_authority`
--

DROP TABLE IF EXISTS `admin_role_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_role_authority` (
  `role_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`authority_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_role_authority`
--

INSERT INTO `admin_role_authority` (`role_id`, `authority_id`) VALUES (1,10),(1,11),(1,12),(1,13),(1,14),(1,1000),(1,1001),(1,1002),(1,1003),(1,1004),(1,1005),(2,1000),(2,1004);

--
-- Table structure for table `admin_user`
--

DROP TABLE IF EXISTS `admin_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `phone` varchar(20) DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_user`
--

INSERT INTO `admin_user` (`id`, `username`, `password`, `enabled`, `phone`, `avatar`, `dept_id`, `nickname`, `email`) VALUES (1,'user','{bcrypt}$2a$10$F.8BYXYzdKEGXPEZ0WzzR.21DWMZyzsg3nEsc3ZNHoJ/GR3A71ETm',1,'13131333333','https://avatars0.githubusercontent.com/u/14340565?v=4',1000,'超级管理员','1@qq.com'),(100,'aaaaaa','$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2',1,'1234567890','http://localhost:9528/logo.jpg',1000,'11111','1@qq.com'),(101,'bbbbbb','$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2',1,'1234567890','http://localhost:9528/logo.jpg',1001,'22222','1@qq.com'),(102,'cccccc','$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2',0,'1234567890','http://localhost:9528/logo.jpg',1002,'33333','1@qq.com'),(200,'dddddd','$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2',1,'1234567890','http://localhost:9528/logo.jpg',2002,'44444','1@qq.com'),(201,'eeeeee','$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2',1,'1234567890','http://localhost:9528/logo.jpg',2001,'55555','1@qq.com'),(202,'ffffff','$2a$10$TStD92Aw2enUZWjtBzdeCu5mpOH4YnmvotMt5vwy3niP2NY8PNGr2',1,'1234567890','http://localhost:9528/logo.jpg',2000,'66666','1@qq.com');

--
-- Table structure for table `admin_user_role`
--

DROP TABLE IF EXISTS `admin_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_user_role`
--

INSERT INTO `admin_user_role` (`user_id`, `role_id`) VALUES (1,1),(1,2);

--
-- Table structure for table `mall_user`
--

DROP TABLE IF EXISTS `mall_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mall_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `openId` varchar(64) NOT NULL DEFAULT '' COMMENT '微信唯一编码',
  `nickname` varchar(256) NOT NULL DEFAULT '' COMMENT '昵称',
  `sex` tinyint(1) NOT NULL DEFAULT '0' COMMENT '性别 1男 2女 0未知',
  `country` varchar(32) NOT NULL DEFAULT '' COMMENT '国家',
  `province` varchar(32) NOT NULL DEFAULT '' COMMENT '省份',
  `city` varchar(32) NOT NULL DEFAULT '' COMMENT '城市',
  `headImgUrl` varchar(256) NOT NULL DEFAULT '' COMMENT '头像',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `regTime` datetime DEFAULT NULL COMMENT '注册时间',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `mobile` (`mobile`) USING BTREE,
  KEY `openId` (`openId`) USING BTREE,
  KEY `uid` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mall_user`
--

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-08 16:41:39
