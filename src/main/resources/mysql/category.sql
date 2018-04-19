/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : secoo

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 19/04/2018 15:14:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `listUrl` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (1, '包袋', 'http://list.secoo.com/bags/30-0-0-0-0-1-0-0-1-10-0-0.shtml#pageTitle');
INSERT INTO `category` VALUES (2, '腕表', 'http://list.secoo.com/watches/93-0-0-0-0-1-0-0-1-10-0-0.shtml#pageTitle');
INSERT INTO `category` VALUES (3, '珠宝首饰', 'http://list.secoo.com/jewelry/66-0-0-0-0-1-0-0-1-10-0-0.shtml#pageTitle');
INSERT INTO `category` VALUES (4, '服装鞋靴', 'http://list.secoo.com/undefined/1690-0-0-0-0-1-0-0-1-10-0-0.shtml#pageTitle');
INSERT INTO `category` VALUES (5, '配饰', 'http://list.secoo.com/accessories/857-0-0-0-0-4-0-0-1-10-0-0.shtml#pageTitle');
INSERT INTO `category` VALUES (6, '家居生活', 'http://list.secoo.com/undefined/1292-0-0-0-0-1-0-0-1-10-0-0-100-0.shtml#pageTitle');
INSERT INTO `category` VALUES (7, '生活方式', 'http://list.secoo.com/undefined/2195-0-0-0-0-1-0-0-1-10-0-0-100-0.shtml#pageTitle');
INSERT INTO `category` VALUES (8, '母婴美妆', 'http://list.secoo.com/undefined/1806-0-0-0-0-1-0-0-1-10-0-0-100-0.shtml#pageTitle');
INSERT INTO `category` VALUES (9, '豪车频道', 'http://list.secoo.com/undefined/2336-0-0-0-0-1-0-0-1-10-0-0-100-0.shtml#pageTitle');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
