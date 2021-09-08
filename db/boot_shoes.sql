/*
 Navicat Premium Data Transfer

 Source Server         : 111
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : boot_shoes

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 08/09/2021 20:38:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `perms` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `perms` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO `user_table` VALUES (1, 'admin', '123456', 'user:index');
INSERT INTO `user_table` VALUES (2, 'hyy', '123456', NULL);

SET FOREIGN_KEY_CHECKS = 1;
