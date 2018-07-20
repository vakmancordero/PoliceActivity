CREATE DATABASE  IF NOT EXISTS `qbits_police_activity` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `qbits_police_activity`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: qbits_police_activity
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Dumping data for table `environment`
--

LOCK TABLES `environment` WRITE;
/*!40000 ALTER TABLE `environment` DISABLE KEYS */;
INSERT INTO `environment` VALUES (1,'2018-06-28 16:10:43','2018-06-28 16:10:43','\0','84fd3f57-9a4a-40e2-8aa0-648f91f20006','Ambiente de dev','dev'),(2,'2018-06-28 16:10:48','2018-06-28 16:10:48','\0','49cd0c40-56f3-4e5f-a34c-4eb8bd2efb38','Ambiente de qa','qa'),(3,'2018-06-28 16:54:50','2018-06-28 16:54:50','\0','262b7ac9-284c-4190-98f3-e9f069f8856e','Environment description 1','Environment name 1');
/*!40000 ALTER TABLE `environment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'2018-06-28 16:12:31','2018-06-28 16:12:31','\0','f0691556-2ccf-44a6-8335-98fa60bbea72','Activity 1','Some message 1',1,1,1),(2,'2018-06-28 17:08:58','2018-06-28 17:08:58','\0','d5e8647c-a54c-4087-b46a-595c72a07026','activity1','entrada',1,2,1),(3,'2018-07-02 16:23:12','2018-07-02 16:23:12','\0','eefca1a2-53e2-4978-abb4-3e80976e139e','Test Activity 1','Some message',1,1,1),(4,'2018-07-02 16:23:53','2018-07-02 16:23:53','\0','7fdcb542-f83d-449d-832a-46576063c5a1','Test Activity 1','Some message',1,1,1),(5,'2018-07-02 16:29:00','2018-07-02 16:29:00','\0','76adf29c-7af2-4ccf-b87e-9a7c2dd6944e','Test Activity 1','Some message',1,1,1),(6,'2018-07-02 16:29:53','2018-07-02 16:29:53','\0','4bb6fe7f-61c9-4fb0-b230-59f2911fa5a2','Test Activity 1','Some message',1,1,1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (1,'2018-06-28 16:12:01','2018-06-28 17:04:48','\0','741fc366-2724-4c05-90b9-a8fe73255322','Backend de Restaurantes','back-restaurantes'),(2,'2018-06-28 16:12:10','2018-06-28 17:05:02','\0','6ac8fac0-36b0-4592-9212-35fce2c77b63','Dispatcher','dispatcher'),(3,'2018-06-28 17:05:28','2018-06-28 17:05:28','\0','da766703-b851-4fff-ace3-788fd45f3c3a','Cordinador','cordinador'),(4,'2018-06-28 17:05:37','2018-06-28 17:05:37','\0','e69042e0-74d6-4aa9-9938-0467476ecbe7','Agente','agente'),(5,'2018-06-28 17:05:44','2018-06-28 17:05:44','\0','0ccf2b5d-f5b4-4094-b2bc-985ef8e954d7','Push','push'),(6,'2018-06-28 17:06:02','2018-06-28 17:06:02','\0','5458ef10-9cd8-4fa0-bd1a-e644c848572e','QBits App','qbits-app');
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-20 17:26:09
