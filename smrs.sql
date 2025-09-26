-- MySQL dump 10.13  Distrib 8.0.42, for macos15 (x86_64)
--
-- Host: 127.0.0.1    Database: smrs
-- ------------------------------------------------------
-- Server version	9.3.0-commercial

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
-- Table structure for table `blogs`
--

DROP TABLE IF EXISTS `blogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogs` (
  `blog_id` int NOT NULL AUTO_INCREMENT,
  `topic_id` int NOT NULL,
  `blog_title` varchar(255) NOT NULL,
  `blog_content` text NOT NULL,
  `publication_type` enum('Public','Private') NOT NULL,
  `created_at` date DEFAULT (curdate()),
  PRIMARY KEY (`blog_id`),
  KEY `topic_id` (`topic_id`),
  CONSTRAINT `blogs_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogs`
--

LOCK TABLES `blogs` WRITE;
/*!40000 ALTER TABLE `blogs` DISABLE KEYS */;
/*!40000 ALTER TABLE `blogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `lecturer_id` int NOT NULL,
  `student_id` int NOT NULL,
  `content` text NOT NULL,
  `created_at` date DEFAULT (curdate()),
  PRIMARY KEY (`comment_id`),
  KEY `blog_id` (`blog_id`),
  KEY `lecturer_id` (`lecturer_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`blog_id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturers` (`lecturer_id`),
  CONSTRAINT `comments_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `council_members`
--

DROP TABLE IF EXISTS `council_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `council_members` (
  `council_id` int NOT NULL,
  `lecturer_id` int NOT NULL,
  `member_role` enum('Chairman','Secretary','Reviewer') NOT NULL,
  PRIMARY KEY (`council_id`,`lecturer_id`),
  KEY `lecturer_id` (`lecturer_id`),
  CONSTRAINT `council_members_ibfk_1` FOREIGN KEY (`council_id`) REFERENCES `evaluation_councils` (`council_id`),
  CONSTRAINT `council_members_ibfk_2` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturers` (`lecturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `council_members`
--

LOCK TABLES `council_members` WRITE;
/*!40000 ALTER TABLE `council_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `council_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation_councils`
--

DROP TABLE IF EXISTS `evaluation_councils`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluation_councils` (
  `council_id` int NOT NULL AUTO_INCREMENT,
  `council_name` varchar(100) NOT NULL,
  `established_date` date NOT NULL,
  PRIMARY KEY (`council_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation_councils`
--

LOCK TABLES `evaluation_councils` WRITE;
/*!40000 ALTER TABLE `evaluation_councils` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluation_councils` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturers`
--

DROP TABLE IF EXISTS `lecturers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturers` (
  `lecturer_id` int NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `lecturer_code` varchar(50) NOT NULL,
  `department` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`lecturer_id`),
  UNIQUE KEY `lecturer_code` (`lecturer_code`),
  CONSTRAINT `lecturers_ibfk_1` FOREIGN KEY (`lecturer_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturers`
--

LOCK TABLES `lecturers` WRITE;
/*!40000 ALTER TABLE `lecturers` DISABLE KEYS */;
/*!40000 ALTER TABLE `lecturers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mentorships`
--

DROP TABLE IF EXISTS `mentorships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mentorships` (
  `mentorship_id` int NOT NULL AUTO_INCREMENT,
  `topic_id` int NOT NULL,
  `lecturer_id` int NOT NULL,
  `invitation_status` enum('Pending','Accepted','Rejected') DEFAULT 'Pending',
  `accepted_date` date DEFAULT NULL,
  PRIMARY KEY (`mentorship_id`),
  KEY `topic_id` (`topic_id`),
  KEY `lecturer_id` (`lecturer_id`),
  CONSTRAINT `mentorships_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`),
  CONSTRAINT `mentorships_ibfk_2` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturers` (`lecturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mentorships`
--

LOCK TABLES `mentorships` WRITE;
/*!40000 ALTER TABLE `mentorships` DISABLE KEYS */;
/*!40000 ALTER TABLE `mentorships` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publications`
--

DROP TABLE IF EXISTS `publications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publications` (
  `publication_id` int NOT NULL AUTO_INCREMENT,
  `topic_id` int NOT NULL,
  `publication_title` varchar(255) NOT NULL,
  `journal_name` varchar(255) DEFAULT NULL,
  `publication_type` enum('Domestic','International') NOT NULL,
  `publication_date` date NOT NULL,
  PRIMARY KEY (`publication_id`),
  KEY `topic_id` (`topic_id`),
  CONSTRAINT `publications_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publications`
--

LOCK TABLES `publications` WRITE;
/*!40000 ALTER TABLE `publications` DISABLE KEYS */;
/*!40000 ALTER TABLE `publications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `topic_id` int NOT NULL,
  `report_type` enum('Periodic','Final') NOT NULL,
  `report_file_url` varchar(255) NOT NULL,
  `submission_date` datetime NOT NULL,
  `comments` text,
  PRIMARY KEY (`report_id`),
  KEY `topic_id` (`topic_id`),
  CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `student_id` int NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `student_code` varchar(50) NOT NULL,
  `major` varchar(100) DEFAULT NULL,
  `class` varchar(50) DEFAULT NULL,
  `faculty` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `student_code` (`student_code`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_evaluations`
--

DROP TABLE IF EXISTS `topic_evaluations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic_evaluations` (
  `topic_id` int NOT NULL,
  `council_id` int NOT NULL,
  `reviewer_id` int NOT NULL,
  `score` decimal(5,2) NOT NULL,
  `result` enum('Passed','Failed','NeedsRevision') NOT NULL,
  `comments` text,
  `evaluation_date` date NOT NULL,
  PRIMARY KEY (`topic_id`,`council_id`),
  KEY `council_id` (`council_id`),
  KEY `reviewer_id` (`reviewer_id`),
  CONSTRAINT `topic_evaluations_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`),
  CONSTRAINT `topic_evaluations_ibfk_2` FOREIGN KEY (`council_id`) REFERENCES `evaluation_councils` (`council_id`),
  CONSTRAINT `topic_evaluations_ibfk_3` FOREIGN KEY (`reviewer_id`) REFERENCES `lecturers` (`lecturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_evaluations`
--

LOCK TABLES `topic_evaluations` WRITE;
/*!40000 ALTER TABLE `topic_evaluations` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic_evaluations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_members`
--

DROP TABLE IF EXISTS `topic_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic_members` (
  `topic_member_id` int NOT NULL AUTO_INCREMENT,
  `topic_id` int NOT NULL,
  `lecturer_id` int NOT NULL,
  `student_id` int NOT NULL,
  `member_role` enum('GroupLeader','Member','Mentor') NOT NULL,
  PRIMARY KEY (`topic_member_id`),
  KEY `topic_id` (`topic_id`),
  KEY `lecturer_id` (`lecturer_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `topic_members_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`),
  CONSTRAINT `topic_members_ibfk_2` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturers` (`lecturer_id`),
  CONSTRAINT `topic_members_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_members`
--

LOCK TABLES `topic_members` WRITE;
/*!40000 ALTER TABLE `topic_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics`
--

DROP TABLE IF EXISTS `topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topics` (
  `topic_id` int NOT NULL AUTO_INCREMENT,
  `topic_title` varchar(255) NOT NULL,
  `topic_description` text,
  `lecturer_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `approval_status` enum('Pending','Approved','Rejected') NOT NULL,
  `rejection_reason` text,
  `created_date` date NOT NULL,
  PRIMARY KEY (`topic_id`),
  KEY `lecturer_id` (`lecturer_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `topics_ibfk_1` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturers` (`lecturer_id`),
  CONSTRAINT `topics_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics`
--

LOCK TABLES `topics` WRITE;
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-26  9:56:38
