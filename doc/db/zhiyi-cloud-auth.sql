/*
 Navicat Premium Data Transfer

 Source Server         : 122.51.85.179(rds)
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:7222
 Source Schema         : zhiyi-cloud-auth

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 29/10/2020 17:05:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('mall-v1-api-admin', 'admin', '{noop}secret', 'all', 'password,refresh_token,authorization_code,social', 'https://cloud.zhiyi.zone/mall/v1/auth-redirect.html', NULL, NULL, NULL, '{\"userAuthPath\":\"http://mall-v1-api-admin/auth/username\", \"socialAuthPath\": \"http://mall-v1-api-admin/auth/social\", \"socialAuthListPath\": \"http://mall-v1-api-admin/auth/socials\"}', NULL);
INSERT INTO `oauth_client_details` VALUES ('mall-v1-api-user', NULL, '{noop}secret', 'all', 'password,refresh_token,authorization_code,social', NULL, NULL, NULL, NULL, '{\"userAuthPath\":\"http://mall-v1-api-user/auth/username\", \"socialAuthPath\": \"http://mall-v1-api-user/auth/social\", \"socialAuthListPath\": \"http://mall-v1-api-user/auth/socials\"}', NULL);

SET FOREIGN_KEY_CHECKS = 1;
