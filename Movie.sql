-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: onlineMovieTicket
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `ADDONS`
--

DROP TABLE IF EXISTS `ADDONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ADDONS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_NAME` varchar(45) NOT NULL,
  `COST` decimal(10,0) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ADDONS`
--

LOCK TABLES `ADDONS` WRITE;
/*!40000 ALTER TABLE `ADDONS` DISABLE KEYS */;
INSERT INTO `ADDONS` VALUES (1,'Popcorn Large',100),(2,'Popcorn Small',80),(3,'Pepsi',50),(4,'Veg Puffs',50),(5,'Coke',50),(6,'Veg Burger',70);
/*!40000 ALTER TABLE `ADDONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `ADMIN_ID` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  PRIMARY KEY (`ADMIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `GENRE_ID` int(11) NOT NULL,
  `GENRE_NAME` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`GENRE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Action'),(2,'Adventure'),(3,'Comedy'),(4,'Crime'),(5,'Drama'),(6,'Historical'),(7,'Horror'),(8,'Mystery'),(9,'Romance'),(10,'Fiction'),(11,'Social'),(12,'Thriller');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre_movie`
--

DROP TABLE IF EXISTS `genre_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre_movie` (
  `MOVIE_ID` int(11) NOT NULL,
  `GENRE_ID` int(11) NOT NULL,
  PRIMARY KEY (`MOVIE_ID`,`GENRE_ID`),
  KEY `GENRE_ID` (`GENRE_ID`),
  CONSTRAINT `GENRE_ID` FOREIGN KEY (`GENRE_ID`) REFERENCES `genre` (`GENRE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MOVIEVAL` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`MOVIE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 PACK_KEYS=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre_movie`
--

LOCK TABLES `genre_movie` WRITE;
/*!40000 ALTER TABLE `genre_movie` DISABLE KEYS */;
INSERT INTO `genre_movie` VALUES (10,1),(9,2),(10,2),(11,2),(9,3),(10,3),(11,3),(9,5),(11,9);
/*!40000 ALTER TABLE `genre_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `MOVIE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MOVIE_NAME` varchar(45) NOT NULL,
  `RATING_HEAD` int(11) NOT NULL DEFAULT '0',
  `RATING_COUNT` double DEFAULT '0',
  `MOVIE_DURATION` int(11) NOT NULL,
  PRIMARY KEY (`MOVIE_ID`),
  UNIQUE KEY `UNIQUE_INDEX` (`MOVIE_ID`,`MOVIE_NAME`,`MOVIE_DURATION`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (9,'Baahubali',0,0,180),(10,'Rambo',0,0,150),(11,'Cars',0,0,120);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theatre`
--

DROP TABLE IF EXISTS `theatre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `theatre` (
  `THEATRE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `THEATRE_NAME` varchar(45) NOT NULL,
  `LATITUDE` double unsigned NOT NULL,
  `LONGITUDE` double unsigned NOT NULL,
  `NO_OF_SCREENS` int(11) NOT NULL,
  PRIMARY KEY (`THEATRE_ID`),
  UNIQUE KEY `index2` (`LATITUDE`,`LONGITUDE`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theatre`
--

LOCK TABLES `theatre` WRITE;
/*!40000 ALTER TABLE `theatre` DISABLE KEYS */;
INSERT INTO `theatre` VALUES (10,'PVR Inorbit- Cyberabad',78.3781869,17.4488768,2),(11,'Sangeetha Theatre',78.28660409999999,17.513520100000004,3);
/*!40000 ALTER TABLE `theatre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theatre_movie`
--

DROP TABLE IF EXISTS `theatre_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `theatre_movie` (
  `THEATRE_MOVIE_ID` varchar(45) DEFAULT NULL,
  `THEATRE_ID` int(11) NOT NULL,
  `MOVIE_ID` int(11) NOT NULL,
  `SCREEN` int(11) NOT NULL,
  `DATE_FROM` date NOT NULL,
  `DATE_TO` date NOT NULL,
  `TIME_FROM` time NOT NULL,
  `TIME_TO` time DEFAULT NULL,
  PRIMARY KEY (`THEATRE_ID`,`MOVIE_ID`,`DATE_FROM`,`TIME_FROM`),
  KEY `MOVIE_NAME_idx` (`MOVIE_ID`),
  KEY `THEATRE_ID_idx` (`THEATRE_ID`),
  CONSTRAINT `MOVIE_ID` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`MOVIE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `THEATRE_ID` FOREIGN KEY (`THEATRE_ID`) REFERENCES `theatre` (`THEATRE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theatre_movie`
--

LOCK TABLES `theatre_movie` WRITE;
/*!40000 ALTER TABLE `theatre_movie` DISABLE KEYS */;
INSERT INTO `theatre_movie` VALUES ('T10M9',10,9,3,'2018-01-18','2018-01-26','18:00:00','21:00:00'),('T10M9',10,9,2,'2018-01-19','2018-01-29','14:00:00','17:00:00'),('T10M11',10,11,3,'2018-01-19','2018-01-29','10:00:00','12:00:00'),('T11M10',11,10,1,'2018-01-18','2018-01-20','11:00:00','13:30:00'),('T11M10',11,10,1,'2018-01-18','2018-01-20','14:00:00','16:30:00'),('T11M10',11,10,1,'2018-01-18','2018-01-20','18:00:00','20:30:00');
/*!40000 ALTER TABLE `theatre_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_booking`
--

DROP TABLE IF EXISTS `ticket_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_booking` (
  `THEATRE_MOVIE_ID` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `SEAT_NO` varchar(3) NOT NULL,
  `DATE` date NOT NULL,
  `SHOW_TIME` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_booking`
--

LOCK TABLES `ticket_booking` WRITE;
/*!40000 ALTER TABLE `ticket_booking` DISABLE KEYS */;
INSERT INTO `ticket_booking` VALUES ('T10M9','harish@gmail.com','A4','2018-01-24','18:00:00'),('T10M9','harish@gmail.com','A5','2018-01-24','18:00:00'),('T10M9','harish@gmail.com','A6','2018-01-24','18:00:00'),('T10M9','harish@gmail.com','A7','2018-01-24','18:00:00'),('T10M9','harish@gmail.com','A8','2018-01-24','18:00:00');
/*!40000 ALTER TABLE `ticket_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `PHONE` varchar(45) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `unique_phone` (`PHONE`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (11,'Mohandhas','mohandhas@mail.com','sathya123','975467382'),(12,'sathya','sathya@mail.com','sathya123','975464582');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_genre`
--

DROP TABLE IF EXISTS `user_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_genre` (
  `USER_ID` int(11) NOT NULL,
  `GENRE_ID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`,`GENRE_ID`),
  KEY `GENRE_ID_idx` (`GENRE_ID`),
  CONSTRAINT `GENREVALUE` FOREIGN KEY (`GENRE_ID`) REFERENCES `genre` (`GENRE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `USERVALUE` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_genre`
--

LOCK TABLES `user_genre` WRITE;
/*!40000 ALTER TABLE `user_genre` DISABLE KEYS */;
INSERT INTO `user_genre` VALUES (12,1),(11,2),(12,2),(11,3),(12,3),(11,5);
/*!40000 ALTER TABLE `user_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'onlineMovieTicket'
--

--
-- Dumping routines for database 'onlineMovieTicket'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-21 12:24:10
