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

 Date: 19/04/2018 15:14:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` float(10,0) DEFAULT NULL,
  `isSpecialPro` int(2) DEFAULT NULL,
  `discount` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `productId` varchar(50) DEFAULT NULL,
  `spcialStatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57462 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
