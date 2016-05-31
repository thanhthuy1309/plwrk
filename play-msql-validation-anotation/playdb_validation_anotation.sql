/*
Navicat MySQL Data Transfer

Source Server         : play
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : playdb_login

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-05-31 16:54:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1', 'ptdung0312@gmail.com', '123', 'dũng');
INSERT INTO `tbl_user` VALUES ('2', 'thuy', '123', 'thùy');

-- ----------------------------
-- Table structure for tbl_user_detail
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_detail`;
CREATE TABLE `tbl_user_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `birthday` varchar(0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user_detail
-- ----------------------------
INSERT INTO `tbl_user_detail` VALUES ('1', 'thanhthuy13091992@gmail.com', 'Thanh Thùy', '123456789', '01666689617', 'Bình Tân', '23', '');
