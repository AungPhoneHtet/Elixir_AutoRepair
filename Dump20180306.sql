-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: autorepair
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
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expense` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `related_voucher_no` varchar(255) DEFAULT NULL,
  `expense_date` datetime NOT NULL,
  `amount` decimal(13,2) DEFAULT NULL,
  `expense_type_id` mediumint(9) NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  `updated_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense`
--

LOCK TABLES `expense` WRITE;
/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
INSERT INTO `expense` VALUES (1,'test','20180305-00001','2018-03-05 00:00:00',5000.00,1,'paid','admin','2018-03-05 15:00:09','admin','2018-03-05 15:00:09'),(2,'test1','20180305-0011','2018-03-05 00:00:00',10000.00,3,'paid','admin','2018-03-05 15:00:19','admin','2018-03-05 15:00:19'),(3,'test','Test','2018-03-05 00:00:00',6000.00,2,'save','admin','2018-03-05 15:08:31','admin','2018-03-05 15:08:31');
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense_type`
--

DROP TABLE IF EXISTS `expense_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expense_type` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense_type`
--

LOCK TABLES `expense_type` WRITE;
/*!40000 ALTER TABLE `expense_type` DISABLE KEYS */;
INSERT INTO `expense_type` VALUES (1,'Type 1'),(2,'Type 2'),(3,'Type 3');
/*!40000 ALTER TABLE `expense_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `voucher_no` varchar(45) NOT NULL,
  `item` varchar(255) DEFAULT NULL,
  `price` decimal(16,0) DEFAULT NULL,
  `qty` varchar(255) DEFAULT NULL,
  `amount` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES ('20180306-00001','test-1',8000,'2','16000.0'),('20180306-00001','test-2',4500,'3','13500.0'),('20180306-0002','test-1',8700,'1','8700.0'),('20180306-0002','test-2',9000,'2','18000.0'),('20180306-0003','test-1',75000,'2','150000.0'),('20180306-0003','test-2',9600,'2','19200.0');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `brand` varchar(255) NOT NULL,
  `buy_price` decimal(13,2) DEFAULT NULL,
  `sell_price` decimal(13,2) DEFAULT NULL,
  `qty` mediumint(9) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (10,'STKEO00001','Engine Oil','Stallion',5000.00,7000.00,60),(11,'STKEO00002','Engine Oil','Luxtron',4000.00,6000.00,50);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `trans_ref` varchar(255) NOT NULL,
  `trans_desc` varchar(255) DEFAULT NULL,
  `amount` decimal(13,2) NOT NULL,
  `trans_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'20180305-00001','Voucher Trans For : 20180305-00001',22000.00,'2018-03-05 10:36:46'),(2,'20180305-0002','Voucher Trans For : 20180305-0011',8700.00,'2018-03-05 10:36:56'),(3,'20180305-0003','Expense Trans For : 9',5000.00,'2018-03-05 14:23:43'),(4,'20180305-0004','Expense Trans For : 9',5000.00,'2018-03-05 14:26:50'),(5,'20180305-0005','Expense Trans For : 1',5000.00,'2018-03-05 15:00:59'),(6,'20180305-0006','Expense Trans For : 2',10000.00,'2018-03-05 15:07:52'),(7,'20180306-00001','Voucher Trans For : 20180306-00001',18000.00,'2018-03-06 15:59:54'),(8,'20180306-0008','Voucher Trans For : 20180306-0002',26700.00,'2018-03-06 16:05:09');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  `updated_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,'admin','Administrator','$2a$10$WbmZoc92hEQ5V4gcBnfOGOhs4FS9i0XnUNBqH1Aqjw4JX3f29OE1G','admin','admin','2018-01-23 00:00:00','admin','2018-01-23 00:00:00');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voucher` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `voucher_no` varchar(45) NOT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `car_no` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  `updated_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `voucher_no_UNIQUE` (`voucher_no`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,'20180306-00001','AUNG PHONE','5N-12345','2018-01-31 00:00:00','save','admin','2018-03-06 00:00:00','admin','2018-03-06 00:00:00'),(2,'20180306-0002','AUNT THAN','4N-9632','2018-02-27 00:00:00','paid','admin','2018-03-06 00:00:00','admin','2018-03-06 00:00:00'),(3,'20180306-0003','AUNG','3I-9687','2018-03-06 00:00:00','save','admin','2018-03-06 00:00:00','admin','2018-03-06 00:00:00');
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-06 17:25:24
