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
  `name` varchar(255) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (9,'Автоэлектроника'),(8,'Игры и софт, развлечения'),(7,'Красота и здоровье'),(1,'Ноутбуки и компьютеры'),(3,'Смартфоны и гаджеты'),(2,'Телевизоры и аудио'),(6,'Техника для дома'),(5,'Техника для кухни'),(4,'Фото и видео');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loyalty`
--

DROP TABLE IF EXISTS `loyalty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loyalty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userinfo_id` int(11) NOT NULL,
  `balance` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`userinfo_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `userinfo_id_UNIQUE` (`userinfo_id`),
  CONSTRAINT `loyalty_fk` FOREIGN KEY (`userinfo_id`) REFERENCES `userinfo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loyalty`
--

LOCK TABLES `loyalty` WRITE;
/*!40000 ALTER TABLE `loyalty` DISABLE KEYS */;
INSERT INTO `loyalty` VALUES (1,1,0),(2,2,32),(3,3,0),(4,4,0),(5,5,0);
/*!40000 ALTER TABLE `loyalty` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `minorcategory`
--

LOCK TABLES `minorcategory` WRITE;
/*!40000 ALTER TABLE `minorcategory` DISABLE KEYS */;
INSERT INTO `minorcategory` VALUES (2,'4К-телевизоры',7),(3,'8К-телевизоры',7),(63,'Apple iPad',14),(26,'Apple Mac',2),(22,'Apple MacBook',1),(88,'Flash HD',19),(56,'Galaxy A',13),(53,'Galaxy FOLD',13),(55,'Galaxy Note',13),(52,'Galaxy Note 10',13),(54,'Galaxy S',13),(65,'Galaxy Tab',14),(85,'GoPro',18),(148,'GPS-навигаторы',46),(45,'Honor',11),(43,'Huawei',11),(64,'Huawei MediaPad',14),(49,'iPhone',12),(50,'iPhone 11',12),(6,'OLED-телевизоры',7),(5,'QLED-телевизоры',7),(86,'Sony',18),(44,'Xiaomi',11),(149,'Автомагнитолы',47),(150,'Автомагнитолы с навигацией',47),(102,'Автоматические кофемашины',26),(143,'Аксессуары Nintendo',44),(137,'Аксессуары PS4',42),(140,'Аксессуары Xbox One',43),(10,'Антенны телевизионные',8),(17,'Беспроводные аудиосистемы',10),(33,'Блоки питания',3),(131,'Весы',39),(29,'Видеокарты',3),(132,'Видеоняни',40),(147,'Видеорегистраторы',46),(16,'Винил и проигрыватели',10),(97,'Винные шкафы',24),(83,'Вспышки',17),(101,'Встраиваемые СВЧ',25),(100,'Вытяжки',25),(135,'Гироскутеры',41),(155,'Держатели для смартфонов',50),(74,'Детские наушники',15),(73,'Детские часы',15),(129,'Для ухода за лицом',38),(14,'Домашние кинотеатры',9),(46,'Домашние телефоны',11),(99,'Духовые шкафы',25),(28,'Жёсткие диски',3),(60,'Зарядные устройства',22),(51,'Зарядные устройства для iPhone',12),(76,'Зеркальные',16),(130,'Зубные щетки',39),(24,'Игровые мониторы',2),(21,'Игровые ноутбуки',1),(146,'Игровые рули',45),(1,'Игровые системные блоки',2),(138,'Игры PS4',42),(144,'Игры для Nintendo',44),(145,'Игры для PC',45),(141,'Игры для Xbox One',43),(133,'Ингаляторы',40),(20,'Кабели и переходники',23),(153,'Камеры заднего вида',49),(103,'Капсульные кофемашины',26),(38,'Картриджи',5),(62,'Карты памяти',22),(87,'Квадрокоптеры',18),(40,'Клавиатуры и мыши',6),(47,'Кнопочные телефоны',11),(15,'Колонки Hi-Fi и сабвуферы',9),(91,'Компактные фотопринтеры',20),(78,'Компактные цифровые',16),(72,'Комплекты для блогеров',15),(11,'Комплекты спутникого ТВ',8),(42,'Компьютерная мебель',6),(114,'Кондиционеры',31),(142,'Консоли Nintendo',44),(136,'Консоли PlayStation',42),(139,'Консоли Xbox One',43),(32,'Корпуса',3),(19,'Кронштейны для ТВ',23),(31,'Материнские платы',3),(104,'Микроволновые печи',27),(105,'Миксеры и блендеры',27),(23,'Мониторы',2),(27,'Моноблоки',2),(96,'Морозильники',24),(117,'Моющие пылесосы',32),(18,'Музыкальные центры',10),(36,'МФУ',5),(34,'Мыши и клавиатуры Apple',4),(109,'Наборы посуды',29),(58,'Наушники для смартфонов',22),(13,'НТВ плюс',8),(113,'Обогреватели',31),(82,'Объективы',17),(9,'Онлайн-кинотеатры',8),(84,'Оптика',17),(156,'Органайзеры',50),(120,'Освещение и LED-лампы',34),(119,'Парогенераторы',33),(66,'Планшеты на Android',14),(67,'Планшеты на Windows',14),(98,'Плиты',24),(124,'Плойки',36),(59,'Портативное аудио',22),(152,'Преобразователи напряжения',48),(37,'Принтеры',5),(8,'Приставки Apple TV',8),(7,'Приставки Smart TV',8),(30,'Процессоры',3),(151,'Пуско-зарядные устройства',48),(115,'Пылесосы с контейнером для пыли',32),(116,'Роботы-пылесосы',32),(41,'Роутеры и сетевое оборудование',6),(121,'Сетевые фильтры и удлинители',34),(77,'Системные',16),(25,'Системные блоки',2),(154,'Системы парковки',49),(39,'Сканеры',5),(110,'Сковороды',29),(4,'Смарт-телевизоры',7),(69,'Смарт-часы',15),(48,'Смартфоны',11),(108,'Соковыжималки',28),(71,'Спортивные часы',15),(111,'Стиральные машины',30),(35,'Сумки для Apple MacBook',4),(93,'Сумки и чехлы',21),(112,'Сушильные машины',30),(107,'Тостеры и ростеры',28),(12,'Триколор ТВ',8),(127,'Триммеры',37),(81,'Ультразумы',16),(134,'Умные весы',41),(75,'Умные устройства',15),(122,'Умный дом',35),(123,'Умный свет',35),(118,'Утюги',33),(125,'Фены',36),(70,'Фитнес-браслеты',15),(79,'Фотоаппараты моментальной печати',16),(80,'Фотоаппараты премиум-класса',16),(128,'Фотоэпиляторы',38),(95,'Холодильники',24),(89,'Цифровые видеокамеры 4K',19),(90,'Цифровые видеокамеры Full HD',19),(61,'Чехлы',22),(57,'Чехлы для Samsung',13),(94,'Чистящие средства для оптики',21),(92,'Штативы',21),(126,'Электробритвы',37),(68,'Электронные книги и асексуары',14),(106,'Электрочайники',28);
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
  `img` longtext DEFAULT NULL,
  `minorcategory_id` int(11) NOT NULL,
  `count` int(11) NOT NULL DEFAULT '100',
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
INSERT INTO `product` VALUES (1,'Flexotron',123,'Некоторое описание','https://avatars.mds.yandex.net/get-zen_doc/27036/pub_5d2d6e1d14f98000ac62352a_5d2d6e7c4e057700ad3040c7/scale_1200',1,87),(2,'Roge',321,'qwe','https://5bucks.ru/wp-content/uploads/2019/05/1.png',1,96);
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
  `value` varchar(255) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (1,2,888),(2,2,888),(3,2,123),(4,2,321);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_has_product`
--

LOCK TABLES `sales_has_product` WRITE;
/*!40000 ALTER TABLE `sales_has_product` DISABLE KEYS */;
INSERT INTO `sales_has_product` VALUES (1,2,1,2,0,246),(2,2,2,2,0,642),(3,3,1,1,0,123),(4,4,2,1,0,321);
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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategory`
--

LOCK TABLES `subcategory` WRITE;
/*!40000 ALTER TABLE `subcategory` DISABLE KEYS */;
INSERT INTO `subcategory` VALUES (12,'Apple',3),(44,'Nintendo',8),(45,'PC',8),(42,'PlayStation',8),(13,'Samsung',3),(43,'Xbox',8),(50,'Автоаксессуары',9),(47,'Автоакустика',9),(49,'Автодиагностика и безопасность',9),(48,'Автомобильная электрика',9),(6,'Аксессуары',1),(34,'Аксессуары для дома',6),(22,'Аксессуары для смартфонов',3),(23,'Аксессуары для телевизоров',2),(21,'Аксессуары для фото- и видеотехники',4),(10,'Аудио',2),(37,'Бритьё и стрижка',7),(19,'Видеокамеры',4),(46,'Видеорегистраторы и навигаторы',9),(25,'Встраиваемая техника',5),(15,'Гаджеты',3),(9,'Домашний кинотеатр',2),(31,'Климатическая техника',6),(4,'Компьютерная техника Apple',1),(3,'Компьютерные комплектующие',1),(2,'Компьютеры и мониторы',1),(24,'Крупная кухонная техника',5),(28,'Мелкая кухонная техника',5),(1,'Ноутбуки',1),(17,'Объективы, вспышки и оптика',4),(5,'Периферийные устройства',1),(14,'Планшеты и электронные книги',3),(29,'Посуда и аксессуары',5),(26,'Приготовление кофе',5),(27,'Приготовление пищи',5),(32,'Пылесосы',6),(11,'Смартфоны, мобильные телефоны',3),(8,'Спутниковое ТВ',2),(30,'Стиральные и сушильные машины',6),(7,'Телевизоры',2),(40,'Товары для детей',7),(35,'Товары для дома и сада',6),(39,'Товары для здоровья',7),(38,'Товары для красоты',7),(33,'Товары для ухода за одеждой',6),(41,'Товары для фитнеса',7),(36,'Укладка волос',7),(16,'Фотоаппараты',4),(20,'Фотопринтеры и цифровые фоторамки',4),(18,'Экшн-камеры',4);
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
INSERT INTO `user` VALUES (1,'spivak@yandex.ru','$2a$10$NcMe4vVenbCJvQ7VwHicBu/OvQh0Fev7i6Z.ZoU6ufK.9Z.a9Bpga','ADMIN',1,'dart647',NULL,NULL),(2,'test@mail.ru','$2a$10$FfaAufw9xBu6Ruy1rNEltu2gvE6Cy.f7uQmjCmXmq5mTs6X83QJY6','ADMIN',1,'test',NULL,NULL),(3,'testM@mail.com','$2a$10$FybbEfi0Z7EmKXbWDpRazuLgL6afzk/z5rVVB7xLsVAw7h3XdKgIS','MANAGER',1,'testM',NULL,NULL),(4,'testU@mail.com','$2a$10$C2IsBKkQ9dLi/QMj3ZTZne20UIwS94oSH1c2a4NgZhEy0o3XADueK','USER',1,'testU',NULL,NULL),(5,'kavo@yandex.ru','$2a$10$iFBi7g.LJ3rEc1LQuXi.I.6UB5dbNXrHVbLWcwlVLNWobJt9xaCUe','USER',1,'bulanov',NULL,NULL);
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
INSERT INTO `user_roles` VALUES (1,'ADMIN'),(2,'ADMIN'),(3,'MANAGER'),(4,'USER'),(5,'USER');
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
  `wallet` int(11) NOT NULL DEFAULT '0',
  `phonenumber` varchar(255) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_userinfo_user1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (1,'Спивак А.А.','1999-01-04','Шоссе Энтузиастов',0,'0'),(2,'Жмышенко Валерий Альбертович','1984-07-10','Самара, ул. Ленина, д.14',0,'0'),(3,'new user',NULL,NULL,0,'0'),(4,'new user',NULL,NULL,0,'0'),(5,'new user',NULL,NULL,0,NULL);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'db_etsm'
--

--
-- Dumping routines for database 'db_etsm'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-26 20:11:53
