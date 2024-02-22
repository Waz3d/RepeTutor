CREATE DATABASE  IF NOT EXISTS `repetutor` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `repetutor`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: repetutor
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `'lesson_details`
--

DROP TABLE IF EXISTS `'lesson_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `'lesson_details` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `start` varchar(255) DEFAULT NULL,
  `student` int(11) DEFAULT NULL,
  `student_email` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `student_surname` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `tutor` int(11) DEFAULT NULL,
  `tutor_email` varchar(255) DEFAULT NULL,
  `tutor_name` varchar(255) DEFAULT NULL,
  `tutor_surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `'lesson_details`
--

LOCK TABLES `'lesson_details` WRITE;
/*!40000 ALTER TABLE `'lesson_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `'lesson_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `evaluation` int(11) DEFAULT NULL,
  `id_tutor` int(11) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmrb6vl3eg0554bihc02p81up5` (`id_tutor`),
  CONSTRAINT `FKmrb6vl3eg0554bihc02p81up5` FOREIGN KEY (`id_tutor`) REFERENCES `tutor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'Ottima insegnante. Paziente e coinvolgente. Molto preparata e disponibile',5,302,'Marta','Italiano'),(2,'Professionale, capace e paziente',4,352,'Alessio','Inglese'),(3,'Mi sono trovata molto bene',4,302,'Caterina','Greco'),(152,'Valentina è molto brava e preparata.',4,302,'Marta','Italiano');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_seq`
--

DROP TABLE IF EXISTS `comment_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_seq`
--

LOCK TABLES `comment_seq` WRITE;
/*!40000 ALTER TABLE `comment_seq` DISABLE KEYS */;
INSERT INTO `comment_seq` VALUES (251);
/*!40000 ALTER TABLE `comment_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `start` varchar(255) DEFAULT NULL,
  `student` int(11) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `tutor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`tutor`),
  KEY `id_idx1` (`student`),
  CONSTRAINT `student` FOREIGN KEY (`student`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tutor` FOREIGN KEY (`tutor`) REFERENCES `tutor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (1,'2023-04-19','2','14:00',2,'Italiano',302),(2,'2023-04-19','1','16:00',3,'Greco',302),(3,'2023-04-19','1','16:00',4,'Informatica',353),(52,'2023-04-23','1','15:00',3,'Greco',302),(102,'2023-05-19','1','14:30',2,'Geografia',352),(152,'2023-06-10','1','14:00',2,'Greco',302);
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `lesson_details`
--

DROP TABLE IF EXISTS `lesson_details`;
/*!50001 DROP VIEW IF EXISTS `lesson_details`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `lesson_details` AS SELECT 
 1 AS `id`,
 1 AS `date`,
 1 AS `duration`,
 1 AS `start`,
 1 AS `subject`,
 1 AS `student`,
 1 AS `student_name`,
 1 AS `student_surname`,
 1 AS `student_email`,
 1 AS `tutor`,
 1 AS `tutor_name`,
 1 AS `tutor_surname`,
 1 AS `tutor_email`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `lesson_seq`
--

DROP TABLE IF EXISTS `lesson_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_seq`
--

LOCK TABLES `lesson_seq` WRITE;
/*!40000 ALTER TABLE `lesson_seq` DISABLE KEYS */;
INSERT INTO `lesson_seq` VALUES (251);
/*!40000 ALTER TABLE `lesson_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `psw_hash` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fe0i52si7ybu0wjedj6motiim` (`email`),
  UNIQUE KEY `UK_jyet50p17q01ks2bv4sn8i5r7` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (2,'marta@gmail.com','Marta','photo','946d8dcd71d0b442763ae6538184c72aa2768a0a8e751b96bb399f2967e79911','SJCO3VK4dKWfRnt9sf0ymQ==','S.','marta'),(3,'alessio@gmail.com','Alessio','photo','0f3316e9120fc5625b5cf73436ebc3dff7cb948d4e7093a1c26cd87555b81096','rjzXQUce8vWy25+PnDn1og==','V.','alessio'),(4,'paolo@gmail.com','Paolo','photo','060b0d0f41f06cf02b552539d0bfe80485ab24fd9780d3ed1a115e85d0aff408','aHvvYW++KIjDrYonmWAhAA==','T.','paolo'),(5,'emma@gmail.com','Emma','photo','aa00a38e2813066f4575ebde70b848626e4ff0543e6a926ef9aa444275b8420b','XKnAzaAXuBQqQS/iJfXXaw==','R.','emma'),(6,'luca@gmail.com','Luca','photo','834da9d3ded89cb680b4b677c123ba7e4206c3433035892cd968fe5f929d2016','Fh16VMLtwPAjguJPEQiZqw==','D.','luca'),(52,'francesca@gmail.com','Francesca','photo','3b10c670c13045947432324110f4d94a3061ac8534ad36384bb6d1b9b3ed993d','giPIaJ82LUZzujrLAVL7Ew==','T.','francesca'),(102,'chiara@gmail.com','Chiara','photo','47eb63ca940c12d5a9d2f765ad2a867cec3732ad53340c0ebcfc78323d99e125','VvX9Z2Ldh8QP4iK7yUhhZQ==','G.','chiara');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_seq`
--

DROP TABLE IF EXISTS `student_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_seq`
--

LOCK TABLES `student_seq` WRITE;
/*!40000 ALTER TABLE `student_seq` DISABLE KEYS */;
INSERT INTO `student_seq` VALUES (201);
/*!40000 ALTER TABLE `student_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutor`
--

DROP TABLE IF EXISTS `tutor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutor` (
  `id` int(11) NOT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `psw_hash` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `subjects` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pp2llp0tf8f3ap5iy3q3c77g1` (`email`),
  UNIQUE KEY `UK_gce7e52js8a17df6nyrdo31ct` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutor`
--

LOCK TABLES `tutor` WRITE;
/*!40000 ALTER TABLE `tutor` DISABLE KEYS */;
INSERT INTO `tutor` VALUES (302,'€','Sono una docente di 37 anni. Forte di anni di esperienza certificata, svolta anche in collaborazione con un\'associazione socio-culturale e un istituto a Roma, insegno con passione e professionalità a studenti di diverso ordine e grado.','valentina@gmail.com','Roma','Valentina','photo',16,'f2bbf7b5e3423f95df874a097281b5ff01fb37b9ce5a72cecd9e86440f2160be','HNS7+k6OmDL/q/1Na6wIOQ==','Greco, Italiano, Latino, Storia','Neri','valentina'),(352,'€','Sono un\'interprete e traduttrice di ingliese dal 2004. Le mie lezioni sono molto pratiche volte a superare la barriera del soggetto nello speaking. Si ascoltano interviste, si guardano parti di film e si parla.','giulia@gmail.com','Milano','Giulia','photo',20,'719f5ad4ad6d9d24534225abd6b100c3fa9c7b490d7b20c412e24432992dbcc8','arofCsxWejmxAYjRPkWWjQ==','Geografia, Inglese, Matematica, Algebra','Verdi','giulia'),(353,'€','Ciao, mi chiamo Eugenio e con due lauree in Ingegneria Informatica ed un dottorato a tema Intelligenza Artificiale appena intrapreso presso il Politecnico di Milano.','eugenio@gmail.com','Milano','Eugenio','photo',15,'f56ff94501bbc1910d7615579b5883482adf56f6fb2ea437aad784706705da5b','VbSWOS7X3blikioSFU0G5Q==','Informatica, Elettronica, Programmazione, Matematica','L.','eugenio'),(402,'€','Già insegnante nei principali istituti tecnici di Milano, quali Mattioli, Zappa, e sopratutto Verri, con circa 35 anni di insegnamento. Attualmente in pensione svolgo anche piccoli attività di collaborazione nel settore amministrativo e contabile.','marco@gmail.com','Milano','Marco','photo',15,'0dfa01887b150bad7cc5816046b25ae4b5ca7d5e3e0f1b50a50bb1c20a2ecc2b','5+uftXe80gBKin67iY6iSg==','Economia, Analisi, Matematica','O.','marco'),(403,'€','Docente di Lettere nella scuola superiore; breve esperienza anche nella scuola media. Si garantisce massima serietà ed esperienza sia nella docenza sia in attività di sostegno e recupero per scuole medie e superiori.','chiara@gmail.com','Torino','Chiara','photo',18,'153782d79fc29d424938babd68dd89c862bd1335ecda3786503163e84bfd81e7','l/4PMgU2djPgTACuVT4S6w==','Greco, Italiano, Latino, Storia','P.','chiara');
/*!40000 ALTER TABLE `tutor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutor_seq`
--

DROP TABLE IF EXISTS `tutor_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tutor_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutor_seq`
--

LOCK TABLES `tutor_seq` WRITE;
/*!40000 ALTER TABLE `tutor_seq` DISABLE KEYS */;
INSERT INTO `tutor_seq` VALUES (501);
/*!40000 ALTER TABLE `tutor_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `lesson_details`
--

/*!50001 DROP VIEW IF EXISTS `lesson_details`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `lesson_details` AS select `l`.`id` AS `id`,`l`.`date` AS `date`,`l`.`duration` AS `duration`,`l`.`start` AS `start`,`l`.`subject` AS `subject`,`l`.`student` AS `student`,`s`.`name` AS `student_name`,`s`.`surname` AS `student_surname`,`s`.`email` AS `student_email`,`l`.`tutor` AS `tutor`,`t`.`name` AS `tutor_name`,`t`.`surname` AS `tutor_surname`,`t`.`email` AS `tutor_email` from ((`lesson` `l` join `student` `s` on((`l`.`student` = `s`.`id`))) join `tutor` `t` on((`l`.`tutor` = `t`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-16 10:21:41
