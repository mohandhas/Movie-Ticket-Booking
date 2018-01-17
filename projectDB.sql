-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: OnlineMovieTicket
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
-- Table structure for table `ADMIN`
--

DROP TABLE IF EXISTS `ADMIN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ADMIN` (
  `ADMIN_ID` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  PRIMARY KEY (`ADMIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ADMIN`
--

LOCK TABLES `ADMIN` WRITE;
/*!40000 ALTER TABLE `ADMIN` DISABLE KEYS */;
INSERT INTO `ADMIN` VALUES ('admin','admin');
/*!40000 ALTER TABLE `ADMIN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MOVIE`
--

DROP TABLE IF EXISTS `MOVIE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MOVIE` (
  `MOVIE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MOVIE_NAME` varchar(45) NOT NULL,
  `RATING_HEAD` int(11) NOT NULL DEFAULT '0',
  `RATING_COUNT` double DEFAULT '0',
  `MOVIE_DURATION` int(11) NOT NULL DEFAULT '0',
  `GENRE1` varchar(45) NOT NULL,
  `GENRE2` varchar(45) DEFAULT NULL,
  `GENRE3` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`MOVIE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MOVIE`
--

LOCK TABLES `MOVIE` WRITE;
/*!40000 ALTER TABLE `MOVIE` DISABLE KEYS */;
INSERT INTO `MOVIE` VALUES (1,'Baahubali',0,0,23000,'Action',NULL,NULL),(2,'Thor',0,0,15000,'Action',NULL,NULL),(3,'Jackson',0,0,23000,'Action',NULL,NULL),(4,'3 iditots',0,0,33000,'fantasy',NULL,NULL),(5,'avengers',0,0,150,'action','adventure',NULL);
/*!40000 ALTER TABLE `MOVIE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `THEATRE`
--

DROP TABLE IF EXISTS `THEATRE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `THEATRE` (
  `THEATRE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `THEATRE_NAME` varchar(45) NOT NULL,
  `LATITUDE` double unsigned NOT NULL DEFAULT '0',
  `LONGITUDE` double unsigned NOT NULL DEFAULT '0',
  `NO_OF_SCREENS` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`THEATRE_ID`),
  UNIQUE KEY `index2` (`LATITUDE`,`LONGITUDE`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `THEATRE`
--

LOCK TABLES `THEATRE` WRITE;
/*!40000 ALTER TABLE `THEATRE` DISABLE KEYS */;
INSERT INTO `THEATRE` VALUES (1,'Amba Theatre',17.3916681,78.4413314,3),(3,'PVR',17.4350531,78.3867323,5),(4,'Sridevi Theatre',17.4980238,78.3170593,2),(5,'shanthi',18.3916681,78.4413314,3);
/*!40000 ALTER TABLE `THEATRE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `THEATRE_MOVIE`
--

DROP TABLE IF EXISTS `THEATRE_MOVIE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `THEATRE_MOVIE` (
  `THEATRE_MOVIE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `THEATRE_ID` int(11) NOT NULL,
  `MOVIE_ID` int(11) NOT NULL,
  `SCREEN` int(11) NOT NULL DEFAULT '1',
  `DATE_FROM` date NOT NULL,
  `DATE_TO` date NOT NULL,
  `TIME_FROM` time NOT NULL,
  `TIME_TO` time DEFAULT NULL,
  PRIMARY KEY (`THEATRE_MOVIE_ID`),
  KEY `MOVIE_NAME_idx` (`MOVIE_ID`),
  KEY `THEATRE_ID_idx` (`THEATRE_ID`),
  CONSTRAINT `MOVIE_ID` FOREIGN KEY (`MOVIE_ID`) REFERENCES `MOVIE` (`MOVIE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `THEATRE_ID` FOREIGN KEY (`THEATRE_ID`) REFERENCES `THEATRE` (`THEATRE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `THEATRE_MOVIE`
--

LOCK TABLES `THEATRE_MOVIE` WRITE;
/*!40000 ALTER TABLE `THEATRE_MOVIE` DISABLE KEYS */;
INSERT INTO `THEATRE_MOVIE` VALUES (1,3,5,1,'2018-01-21','2018-01-30','10:30:00','13:00:00'),(2,5,5,2,'2018-01-21','2018-01-30','10:00:00','12:30:00');
/*!40000 ALTER TABLE `THEATRE_MOVIE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ticket_Booking`
--

DROP TABLE IF EXISTS `Ticket_Booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ticket_Booking` (
  `THEATRE_MOVIE_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `SEAT_NO` varchar(3) NOT NULL,
  `DATE` date NOT NULL,
  `GUEST` tinyint(4) DEFAULT '0',
  KEY `THEATER_MOVIE_ID_idx` (`THEATRE_MOVIE_ID`),
  KEY `USER_ID_idx` (`USER_ID`),
  CONSTRAINT `THEATRE_MOVIE_ID` FOREIGN KEY (`THEATRE_MOVIE_ID`) REFERENCES `THEATRE_MOVIE` (`THEATRE_MOVIE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ticket_Booking`
--

LOCK TABLES `Ticket_Booking` WRITE;
/*!40000 ALTER TABLE `Ticket_Booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `Ticket_Booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `PHONE` varchar(45) NOT NULL,
  `GENRE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'OnlineMovieTicket'
--

--
-- Dumping routines for database 'OnlineMovieTicket'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-17 15:55:47
