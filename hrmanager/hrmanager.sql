-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hrmanager
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.13-MariaDB

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
-- Table structure for table `deparment`
--

DROP TABLE IF EXISTS `deparment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deparment` (
  `deparmentid` int(11) NOT NULL AUTO_INCREMENT,
  `deparmentname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`deparmentid`),
  UNIQUE KEY `deparmentname_UNIQUE` (`deparmentname`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deparment`
--

LOCK TABLES `deparment` WRITE;
/*!40000 ALTER TABLE `deparment` DISABLE KEYS */;
INSERT INTO `deparment` VALUES (3,'ANDROID'),(2,'IOS'),(1,'JAVA'),(4,'PHP');
/*!40000 ALTER TABLE `deparment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_apply`
--

DROP TABLE IF EXISTS `employee_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emailEmployee` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `emailManager` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `deparmentid` int(11) NOT NULL,
  `fromDate` date NOT NULL,
  `toDate` date NOT NULL,
  `submitDate` date DEFAULT NULL,
  `reasonId` int(255) NOT NULL,
  `statusId` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1` (`emailEmployee`),
  KEY `FK2` (`emailManager`),
  KEY `FK3` (`deparmentid`),
  KEY `FK4` (`reasonId`),
  KEY `FK5` (`statusId`),
  CONSTRAINT `FK1` FOREIGN KEY (`emailEmployee`) REFERENCES `user` (`email`),
  CONSTRAINT `FK2` FOREIGN KEY (`emailManager`) REFERENCES `user` (`email`),
  CONSTRAINT `FK3` FOREIGN KEY (`deparmentid`) REFERENCES `deparment` (`deparmentid`),
  CONSTRAINT `FK4` FOREIGN KEY (`reasonId`) REFERENCES `reason` (`reasonId`),
  CONSTRAINT `FK5` FOREIGN KEY (`statusId`) REFERENCES `status` (`statusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_apply`
--

LOCK TABLES `employee_apply` WRITE;
/*!40000 ALTER TABLE `employee_apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reason`
--

DROP TABLE IF EXISTS `reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reason` (
  `reasonId` int(11) NOT NULL AUTO_INCREMENT,
  `reasonName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`reasonId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reason`
--

LOCK TABLES `reason` WRITE;
/*!40000 ALTER TABLE `reason` DISABLE KEYS */;
INSERT INTO `reason` VALUES (1,'Nghỉ phép'),(2,'Nghỉ ốm'),(3,'Nghỉ con ốm'),(4,'Nghỉ thai sản'),(5,'Nghỉ không lương'),(6,'Nghỉ dưỡng sức sau ốm đau'),(7,'Nghỉ hội nghị, học tập'),(8,'Nghỉ dưỡng sức sau thai sản'),(9,'Nghỉ dưỡng sức sau điều trị thương tật, tai nạn'),(10,'Nghỉ bù'),(11,'Nghỉ tai nạn'),(12,'Nghỉ khác'),(13,'Nghỉ kết hôn');
/*!40000 ALTER TABLE `reason` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `roleid` int(1) NOT NULL,
  `rolename` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`roleid`),
  UNIQUE KEY `rolename_UNIQUE` (`rolename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `statusId` int(11) NOT NULL AUTO_INCREMENT,
  `statusName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`statusId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Chưa được duyệt'),(2,'Đã duyệt'),(3,'Bị từ chối');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('ptdung0312@gmail.com','dung pt','123',NULL,'1','1','ptdung0312@gmail.com','dung pt'),('tonytrjdung@gmail.com','dungpt','123','2000-10-10','2','3','ptdung0312@gmail.com','Phan Thanh Dũng');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'hrmanager'
--

--
-- Dumping routines for database 'hrmanager'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-10 15:36:22
