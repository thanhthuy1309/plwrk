/*
Navicat MySQL Data Transfer

Source Server         : play
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : playdb

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-05-19 12:21:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for person_tb
-- ----------------------------
DROP TABLE IF EXISTS `person_tb`;
CREATE TABLE `person_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person_tb
-- ----------------------------
INSERT INTO `person_tb` VALUES ('1', 'aa');
INSERT INTO `person_tb` VALUES ('2', 'ssss');
INSERT INTO `person_tb` VALUES ('3', '11111111');
INSERT INTO `person_tb` VALUES ('4', '1');
INSERT INTO `person_tb` VALUES ('5', '8h');
INSERT INTO `person_tb` VALUES ('6', '33');
INSERT INTO `person_tb` VALUES ('7', '3');
INSERT INTO `person_tb` VALUES ('8', 'thuy');
INSERT INTO `person_tb` VALUES ('9', 'gfgfg');
INSERT INTO `person_tb` VALUES ('10', 'yyyyyyyyy');
INSERT INTO `person_tb` VALUES ('11', 'dung hiu');
INSERT INTO `person_tb` VALUES ('12', 'Mahendra');

-- ----------------------------
-- Table structure for student_tb
-- ----------------------------
DROP TABLE IF EXISTS `student_tb`;
CREATE TABLE `student_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `birthDate` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `lastUpdate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_tb
-- ----------------------------
INSERT INTO `student_tb` VALUES ('1', 'Tran Thi Thanh Thùy', 'Binh Tan', 'Female', '09-13-1992', 'Textile Engineering', '2016-05-18');
INSERT INTO `student_tb` VALUES ('2', 'Phan Thanh Dũng', 'Binh Phuoc', 'Male', '12-03-1991', 'Computer Engineering', '2016-05-17');
INSERT INTO `student_tb` VALUES ('9', 'Trần Văn Giàu', 'Hà Tĩnh', 'Male', '05-11-2016', 'Electronix & TeleCommunications', '2016-05-17');
INSERT INTO `student_tb` VALUES ('10', '下記の行を修正しま', 'heo heo', 'Female', '05-03-2016', 'Information Technology', '2016-05-17');
