/*
Navicat MySQL Data Transfer

Source Server         : play
Source Server Version : 50617
Source Host           : 192.168.1.58:3306
Source Database       : hrmanager

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-07-08 18:22:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for deparment
-- ----------------------------
DROP TABLE IF EXISTS `deparment`;
CREATE TABLE `deparment` (
  `deparmentid` int(11) NOT NULL AUTO_INCREMENT,
  `deparmentname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`deparmentid`),
  UNIQUE KEY `deparmentname_UNIQUE` (`deparmentname`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of deparment
-- ----------------------------
INSERT INTO `deparment` VALUES ('3', 'ANDROID');
INSERT INTO `deparment` VALUES ('2', 'IOS');
INSERT INTO `deparment` VALUES ('1', 'JAVA');
INSERT INTO `deparment` VALUES ('4', 'PHP');

-- ----------------------------
-- Table structure for employee_apply
-- ----------------------------
DROP TABLE IF EXISTS `employee_apply`;
CREATE TABLE `employee_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emailEmployee` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `emailManager` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `deparmentid` int(11) NOT NULL,
  `fromDate` datetime NOT NULL,
  `toDate` datetime NOT NULL,
  `submitDate` datetime DEFAULT NULL,
  `reasonId` int(255) NOT NULL,
  `statusId` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1` (`emailEmployee`),
  KEY `FK2` (`emailManager`),
  KEY `FK3` (`deparmentid`),
  KEY `FK4` (`reasonId`),
  KEY `FK5` (`statusId`),
  CONSTRAINT `FK5` FOREIGN KEY (`statusId`) REFERENCES `status` (`statusId`),
  CONSTRAINT `FK1` FOREIGN KEY (`emailEmployee`) REFERENCES `user` (`email`),
  CONSTRAINT `FK2` FOREIGN KEY (`emailManager`) REFERENCES `user` (`email`),
  CONSTRAINT `FK3` FOREIGN KEY (`deparmentid`) REFERENCES `deparment` (`deparmentid`),
  CONSTRAINT `FK4` FOREIGN KEY (`reasonId`) REFERENCES `reason` (`reasonId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of employee_apply
-- ----------------------------
INSERT INTO `employee_apply` VALUES ('1', 'kiengcan_lovely@yahoo.com.vn', 'thuyttt@aa1309', '1', '2016-07-07 00:00:00', '2016-07-07 00:00:00', null, '1', '1');
INSERT INTO `employee_apply` VALUES ('2', 'kiengcan_lovely@yahoo.com.vn', 'thuyttt@aa1309', '1', '2016-07-07 00:00:00', '2016-07-07 00:00:00', null, '2', '1');
INSERT INTO `employee_apply` VALUES ('3', 'ptdung0312@gmail.com', 'kiengcan_lovely@yahoo.com.vn', '2', '2016-07-08 00:00:00', '2016-07-08 00:00:00', '2016-07-08 00:00:00', '3', '1');
INSERT INTO `employee_apply` VALUES ('4', 'ptdung0312@gmail.com', 'dng@gma', '2', '2016-07-08 00:00:00', '2016-07-08 00:00:00', '2016-07-08 00:00:00', '4', '2');
INSERT INTO `employee_apply` VALUES ('5', 'ptdung0312@gmail.com', 'kiengcan_lovely@yahoo.com.vn', '2', '2016-07-08 00:00:00', '2016-07-08 00:00:00', '2016-07-14 17:37:38', '4', '1');
INSERT INTO `employee_apply` VALUES ('6', 'ptdung0312@gmail.com', 'kiengcan_lovely@yahoo.com.vn', '2', '2016-07-08 00:00:00', '2016-07-08 00:00:00', '2016-07-08 00:00:00', '10', '1');

-- ----------------------------
-- Table structure for reason
-- ----------------------------
DROP TABLE IF EXISTS `reason`;
CREATE TABLE `reason` (
  `reasonId` int(11) NOT NULL AUTO_INCREMENT,
  `reasonName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`reasonId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of reason
-- ----------------------------
INSERT INTO `reason` VALUES ('1', 'Nghỉ phép');
INSERT INTO `reason` VALUES ('2', 'Nghỉ ốm');
INSERT INTO `reason` VALUES ('3', 'Nghỉ con ốm');
INSERT INTO `reason` VALUES ('4', 'Nghỉ thai sản');
INSERT INTO `reason` VALUES ('5', 'Nghỉ không lương');
INSERT INTO `reason` VALUES ('6', 'Nghỉ dưỡng sức sau ốm đau');
INSERT INTO `reason` VALUES ('7', 'Nghỉ hội nghị, học tập');
INSERT INTO `reason` VALUES ('8', 'Nghỉ dưỡng sức sau thai sản');
INSERT INTO `reason` VALUES ('9', 'Nghỉ dưỡng sức sau điều trị thương tật, tai nạn');
INSERT INTO `reason` VALUES ('10', 'Nghỉ bù');
INSERT INTO `reason` VALUES ('11', 'Nghỉ tai nạn');
INSERT INTO `reason` VALUES ('12', 'Nghỉ khác');
INSERT INTO `reason` VALUES ('13', 'Nghỉ kết hôn');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleid` int(1) NOT NULL,
  `rolename` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`roleid`),
  UNIQUE KEY `rolename_UNIQUE` (`rolename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', 'user');

-- ----------------------------
-- Table structure for status
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `statusId` int(11) NOT NULL AUTO_INCREMENT,
  `statusName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`statusId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of status
-- ----------------------------
INSERT INTO `status` VALUES ('1', 'Chưa được duyệt');
INSERT INTO `status` VALUES ('2', 'Đã duyệt');
INSERT INTO `status` VALUES ('3', 'Bị từ chối');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateborn` date DEFAULT NULL,
  `roleid` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `deparmentid` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emailupper` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('dng@gma', 'ada', '123', '2019-10-19', '2', '2', 'ptdung0312@gmail.com', 'asdas');
INSERT INTO `user` VALUES ('dung@gmail', 'adas', '123', '2019-10-19', '2', '1', 'ptdung0312@gmail.com', 'asdas');
INSERT INTO `user` VALUES ('kiengcan_lovely@yahoo.com.vn', 'Thùy TTT', '123456', '1992-09-14', '1', '1', 'ptdung0312@gmail.com', 'Trần Thị Thanh Thùy1');
INSERT INTO `user` VALUES ('ptdung0312@gmail.com', 'dung pt1', '1234', '2016-07-10', '2', '2', 'thuyttt@aa1309', 'Phan Thanh Dung1');
INSERT INTO `user` VALUES ('thuyttt@aa1309', 'Thủy', '123', '1992-09-13', '2', '2', 'kiengcan_lovely@yahoo.com.vn', 'Nguyễn Thị Thủy');
INSERT INTO `user` VALUES ('tonytrjdung@gmail.com', 'trjdung tony111', '123', '2016-07-07', '2', '1', 'thuyttt@aa1309', 'Phan Thanh Dung');
