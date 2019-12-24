-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: db_etsm
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attribute`
--

DROP TABLE IF EXISTS `attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `attribute_gr_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`attribute_gr_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `attributes_fk_idx` (`attribute_gr_id`),
  CONSTRAINT `attributes_gr_fk` FOREIGN KEY (`attribute_gr_id`) REFERENCES `attribute_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute`
--

LOCK TABLES `attribute` WRITE;
/*!40000 ALTER TABLE `attribute` DISABLE KEYS */;
INSERT INTO `attribute` VALUES (1,'Цвет',1),(2,'Разрешение',2),(3,'test',3),(4,'testtest',3),(5,'Материал',1),(6,'justTest',4),(7,'test#2',5),(8,'qwe',5);
/*!40000 ALTER TABLE `attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attribute_group`
--

DROP TABLE IF EXISTS `attribute_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attribute_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute_group`
--

LOCK TABLES `attribute_group` WRITE;
/*!40000 ALTER TABLE `attribute_group` DISABLE KEYS */;
INSERT INTO `attribute_group` VALUES (1,'Корпус'),(2,'Экран'),(3,'TEST'),(4,'TestAdding'),(5,'Test#2');
/*!40000 ALTER TABLE `attribute_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'category');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `minorcategory`
--

DROP TABLE IF EXISTS `minorcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `minorcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `subcategory_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`subcategory_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `minorCategory_fk_idx` (`subcategory_id`),
  CONSTRAINT `minorCategory_fk` FOREIGN KEY (`subcategory_id`) REFERENCES `subcategory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `minorcategory`
--

LOCK TABLES `minorcategory` WRITE;
/*!40000 ALTER TABLE `minorcategory` DISABLE KEYS */;
INSERT INTO `minorcategory` VALUES (1,'minor category',1);
/*!40000 ALTER TABLE `minorcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `desc` longtext,
  `img` varchar(255) DEFAULT NULL,
  `minorcategory_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`minorcategory_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `minorcategory_fk_idx` (`minorcategory_id`),
  KEY `product_fk_idx` (`minorcategory_id`),
  CONSTRAINT `minor_fk` FOREIGN KEY (`minorcategory_id`) REFERENCES `minorcategory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Flexotron',123,'aaaaaa aaaaaaaaa aaaaaaaa aaaaaaa aaaaaaaaaaaa aaaaa aaaaa aaaaaa aaaaaaaaaaaaa aaaaaaaaaaaaaaaaa aaaaaaaaaaaaaa','https://avatars.mds.yandex.net/get-zen_doc/27036/pub_5d2d6e1d14f98000ac62352a_5d2d6e7c4e057700ad3040c7/scale_1200',1),(2,'Roge',321,'qwe','https://5bucks.ru/wp-content/uploads/2019/05/1.png',1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_has_attribute_group`
--

DROP TABLE IF EXISTS `product_has_attribute_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_has_attribute_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `attribute_group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`product_id`,`attribute_group_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_product_has_attribute_group_attribute_group1_idx` (`attribute_group_id`),
  KEY `fk_product_has_attribute_group_product1_idx` (`product_id`),
  CONSTRAINT `fk_product_has_attribute_group_attribute_group1` FOREIGN KEY (`attribute_group_id`) REFERENCES `attribute_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_product_has_attribute_group_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_has_attribute_group`
--

LOCK TABLES `product_has_attribute_group` WRITE;
/*!40000 ALTER TABLE `product_has_attribute_group` DISABLE KEYS */;
INSERT INTO `product_has_attribute_group` VALUES (1,1,1),(2,1,2),(5,1,3),(9,2,1),(10,2,2),(11,2,4),(12,2,5);
/*!40000 ALTER TABLE `product_has_attribute_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productattrvalue`
--

DROP TABLE IF EXISTS `productattrvalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productattrvalue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `attribute_id` int(11) NOT NULL,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`id`,`product_id`,`attribute_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `product_fk_idx` (`product_id`),
  KEY `attribute_fk_idx` (`attribute_id`),
  CONSTRAINT `attribute_fk` FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productattrvalue`
--

LOCK TABLES `productattrvalue` WRITE;
/*!40000 ALTER TABLE `productattrvalue` DISABLE KEYS */;
INSERT INTO `productattrvalue` VALUES (1,1,1,'Красный'),(2,1,2,'1980*1600'),(3,2,1,'Зеленый'),(4,2,2,'1600*900'),(5,1,1,'Серый'),(6,1,3,'test1'),(7,1,4,'test2'),(8,1,5,'Металл'),(9,2,5,'Пластик'),(10,2,6,'TestComplete'),(11,2,7,'testAddPass'),(12,2,8,'asd');
/*!40000 ALTER TABLE `productattrvalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userInfo_id` int(11) NOT NULL,
  `sum` int(11) NOT NULL,
  PRIMARY KEY (`id`,`userInfo_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_sales_userInfo1_idx` (`userInfo_id`),
  CONSTRAINT `fk_sales_userInfo1` FOREIGN KEY (`userInfo_id`) REFERENCES `userinfo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_has_product`
--

DROP TABLE IF EXISTS `sales_has_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_has_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sales_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `discount` float NOT NULL,
  `summ` int(11) NOT NULL,
  PRIMARY KEY (`id`,`sales_id`,`product_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_sales_has_product_product1_idx` (`product_id`),
  KEY `fk_sales_has_product_sales1_idx` (`sales_id`),
  CONSTRAINT `fk_sales_has_product_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sales_has_product_sales1` FOREIGN KEY (`sales_id`) REFERENCES `sales` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_has_product`
--

LOCK TABLES `sales_has_product` WRITE;
/*!40000 ALTER TABLE `sales_has_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_has_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategory`
--

DROP TABLE IF EXISTS `subcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`category_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_subCategory_category1_idx` (`category_id`),
  CONSTRAINT `fk_subCategory_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategory`
--

LOCK TABLES `subcategory` WRITE;
/*!40000 ALTER TABLE `subcategory` DISABLE KEYS */;
INSERT INTO `subcategory` VALUES (1,'sub category',1);
/*!40000 ALTER TABLE `subcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `roles` enum('USER','MANAGER','ADMIN') NOT NULL,
  `active` tinyint(4) NOT NULL,
  `login` varchar(255) NOT NULL,
  `googleName` varchar(255) DEFAULT NULL,
  `googleUsername` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`username`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `login_UNIQUE` (`username`),
  CONSTRAINT `fk_user_userinfo1` FOREIGN KEY (`id`) REFERENCES `userinfo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'spivak@yandex.ru','$2a$10$NcMe4vVenbCJvQ7VwHicBu/OvQh0Fev7i6Z.ZoU6ufK.9Z.a9Bpga','ADMIN',1,'dart647',NULL,NULL),(2,'test@mail.ru','$2a$10$FfaAufw9xBu6Ruy1rNEltu2gvE6Cy.f7uQmjCmXmq5mTs6X83QJY6','ADMIN',1,'test',NULL,NULL),(3,'testM@mail.com','$2a$10$FybbEfi0Z7EmKXbWDpRazuLgL6afzk/z5rVVB7xLsVAw7h3XdKgIS','MANAGER',1,'testM',NULL,NULL),(4,'testU@mail.com','$2a$10$C2IsBKkQ9dLi/QMj3ZTZne20UIwS94oSH1c2a4NgZhEy0o3XADueK','USER',1,'testU',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL,
  `roles` enum('USER','MANAGER','ADMIN') DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_rolesFK` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'ADMIN'),(2,'ADMIN'),(3,'MANAGER'),(4,'USER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fio` varchar(255) DEFAULT 'new user',
  `birthDate` varchar(255) DEFAULT NULL,
  `address` longtext,
  `loyaltyCode` varchar(10) DEFAULT NULL,
  `wallet` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `loyaltyCode_UNIQUE` (`loyaltyCode`),
  KEY `fk_userinfo_user1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (1,'Спивак А.А.','1999-01-04','Шоссе Энтузиастов','1235',0),(2,'new user',NULL,NULL,NULL,0),(3,'new user',NULL,NULL,NULL,0),(4,'new user',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-24 12:20:36
