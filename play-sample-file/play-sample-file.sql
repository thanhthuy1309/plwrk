/*
Navicat MySQL Data Transfer

Source Server         : play
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : play-sample-file

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-06-02 17:35:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('1', 'thuy', 'upload/Capture.PNG');
INSERT INTO `students` VALUES ('2', 'dung', 'upload/13233135_930072527120278_6967721991219894610_n.jpg');
