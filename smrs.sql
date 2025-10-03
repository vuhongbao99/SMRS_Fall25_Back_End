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
-- Table structure for table `award_events`
--

DROP TABLE IF EXISTS `award_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `award_events` (
  `award_event_id` int NOT NULL AUTO_INCREMENT,
  `event_name` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `event_year` int NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`award_event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `award_events`
--

LOCK TABLES `award_events` WRITE;
/*!40000 ALTER TABLE `award_events` DISABLE KEYS */;
/*!40000 ALTER TABLE `award_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `award_winners`
--

DROP TABLE IF EXISTS `award_winners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `award_winners` (
  `award_event_id` int NOT NULL,
  `topic_id` int NOT NULL,
  `award_rank` enum('1','2','3') COLLATE utf8mb4_unicode_ci NOT NULL,
  `final_score` decimal(5,2) NOT NULL,
  `note` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`award_event_id`,`award_rank`),
  UNIQUE KEY `uq_award_topic_once` (`award_event_id`,`topic_id`),
  KEY `topic_id` (`topic_id`),
  CONSTRAINT `award_winners_ibfk_1` FOREIGN KEY (`award_event_id`) REFERENCES `award_events` (`award_event_id`) ON DELETE CASCADE,
  CONSTRAINT `award_winners_ibfk_2` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `award_winners`
--

LOCK TABLES `award_winners` WRITE;
/*!40000 ALTER TABLE `award_winners` DISABLE KEYS */;
/*!40000 ALTER TABLE `award_winners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blogs`
--

DROP TABLE IF EXISTS `blogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogs` (
  `blog_id` int NOT NULL AUTO_INCREMENT,
  `topic_id` int NOT NULL,
  `blog_title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `blog_content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `visibility` enum('Public','Private') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Public',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`blog_id`),
  KEY `topic_id` (`topic_id`),
  CONSTRAINT `blogs_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
  `lecturer_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  KEY `blog_id` (`blog_id`),
  KEY `lecturer_id` (`lecturer_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`blog_id`) ON DELETE CASCADE,
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturers` (`lecturer_id`),
  CONSTRAINT `comments_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
  `member_role` enum('Chairman','Secretary','Reviewer') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`council_id`,`lecturer_id`),
  KEY `lecturer_id` (`lecturer_id`),
  CONSTRAINT `council_members_ibfk_1` FOREIGN KEY (`council_id`) REFERENCES `evaluation_councils` (`council_id`) ON DELETE CASCADE,
  CONSTRAINT `council_members_ibfk_2` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturers` (`lecturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
  `council_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `established_date` date NOT NULL,
  PRIMARY KEY (`council_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation_councils`
--

LOCK TABLES `evaluation_councils` WRITE;
/*!40000 ALTER TABLE `evaluation_councils` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluation_councils` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation_criteria`
--

DROP TABLE IF EXISTS `evaluation_criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluation_criteria` (
  `criteria_id` int NOT NULL AUTO_INCREMENT,
  `criteria_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `is_active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`criteria_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation_criteria`
--

LOCK TABLES `evaluation_criteria` WRITE;
/*!40000 ALTER TABLE `evaluation_criteria` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluation_criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturers`
--

DROP TABLE IF EXISTS `lecturers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturers` (
  `lecturer_id` int NOT NULL,
  `full_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lecturer_code` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `department` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `avatar_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`lecturer_id`),
  UNIQUE KEY `lecturer_code` (`lecturer_code`),
  CONSTRAINT `lecturers_ibfk_1` FOREIGN KEY (`lecturer_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturers`
--

LOCK TABLES `lecturers` WRITE;
/*!40000 ALTER TABLE `lecturers` DISABLE KEYS */;
/*!40000 ALTER TABLE `lecturers` ENABLE KEYS */;
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
  `publication_title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `journal_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `publication_type` enum('Conference','Journal','Other') COLLATE utf8mb4_unicode_ci NOT NULL,
  `publication_date` date NOT NULL,
  `status` enum('Submitted','Accepted','Rejected') COLLATE utf8mb4_unicode_ci DEFAULT 'Submitted',
  PRIMARY KEY (`publication_id`),
  KEY `topic_id` (`topic_id`),
  CONSTRAINT `publications_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
  `report_type` enum('Progress','Final') COLLATE utf8mb4_unicode_ci NOT NULL,
  `report_file_url` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `submitted_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `comments` text COLLATE utf8mb4_unicode_ci,
  `score` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  KEY `topic_id` (`topic_id`),
  CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
  `role_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(3,'LECTURER'),(2,'STUDENT');
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
  `full_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_code` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `major` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `class_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `faculty` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `avatar_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `student_code` (`student_code`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
  `evaluation_id` int NOT NULL AUTO_INCREMENT,
  `topic_id` int NOT NULL,
  `council_id` int NOT NULL,
  `reviewer_id` int NOT NULL,
  `score` decimal(5,2) NOT NULL,
  `result` enum('Passed','Failed','NeedsRevision') COLLATE utf8mb4_unicode_ci NOT NULL,
  `comments` text COLLATE utf8mb4_unicode_ci,
  `evaluation_date` date NOT NULL,
  PRIMARY KEY (`evaluation_id`),
  KEY `topic_id` (`topic_id`),
  KEY `council_id` (`council_id`),
  KEY `reviewer_id` (`reviewer_id`),
  CONSTRAINT `topic_evaluations_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`) ON DELETE CASCADE,
  CONSTRAINT `topic_evaluations_ibfk_2` FOREIGN KEY (`council_id`) REFERENCES `evaluation_councils` (`council_id`),
  CONSTRAINT `topic_evaluations_ibfk_3` FOREIGN KEY (`reviewer_id`) REFERENCES `lecturers` (`lecturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
  `topic_id` int NOT NULL,
  `student_id` int NOT NULL,
  `member_role` enum('Leader','Member') COLLATE utf8mb4_unicode_ci NOT NULL,
  `joined_at` date DEFAULT (curdate()),
  PRIMARY KEY (`topic_id`,`student_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `topic_members_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`) ON DELETE CASCADE,
  CONSTRAINT `topic_members_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_members`
--

LOCK TABLES `topic_members` WRITE;
/*!40000 ALTER TABLE `topic_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_requirements`
--

DROP TABLE IF EXISTS `topic_requirements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic_requirements` (
  `requirement_id` int NOT NULL AUTO_INCREMENT,
  `topic_id` int NOT NULL,
  `criteria_id` int NOT NULL,
  `weight` decimal(4,2) DEFAULT '1.00',
  `is_mandatory` tinyint(1) DEFAULT '0',
  `min_score` decimal(5,2) DEFAULT '0.00',
  `note` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`requirement_id`),
  UNIQUE KEY `uq_topic_criteria` (`topic_id`,`criteria_id`),
  KEY `criteria_id` (`criteria_id`),
  CONSTRAINT `topic_requirements_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`) ON DELETE CASCADE,
  CONSTRAINT `topic_requirements_ibfk_2` FOREIGN KEY (`criteria_id`) REFERENCES `evaluation_criteria` (`criteria_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_requirements`
--

LOCK TABLES `topic_requirements` WRITE;
/*!40000 ALTER TABLE `topic_requirements` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic_requirements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics`
--

DROP TABLE IF EXISTS `topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topics` (
  `topic_id` int NOT NULL AUTO_INCREMENT,
  `topic_title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `topic_description` text COLLATE utf8mb4_unicode_ci,
  `student_id` int NOT NULL,
  `mentor_id` int NOT NULL,
  `approval_flow` enum('Council','Mentor') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Council',
  `approval_status` enum('Pending','Approved','Rejected') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Pending',
  `rejection_reason` text COLLATE utf8mb4_unicode_ci,
  `approved_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `mentor_score` decimal(5,2) DEFAULT NULL,
  `council_score` decimal(5,2) DEFAULT NULL,
  `final_score` decimal(5,2) GENERATED ALWAYS AS (round(((coalesce(`mentor_score`,0) * 0.40) + (coalesce(`council_score`,0) * 0.60)),2)) STORED,
  `pending_student_id` int GENERATED ALWAYS AS ((case when (`approval_status` = _utf8mb4'Pending') then `student_id` else NULL end)) STORED,
  PRIMARY KEY (`topic_id`),
  UNIQUE KEY `uq_one_pending_topic` (`pending_student_id`),
  KEY `student_id` (`student_id`),
  KEY `mentor_id` (`mentor_id`),
  CONSTRAINT `topics_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`),
  CONSTRAINT `topics_ibfk_2` FOREIGN KEY (`mentor_id`) REFERENCES `lecturers` (`lecturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password_hash` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_status` enum('Active','Inactive') COLLATE utf8mb4_unicode_ci DEFAULT 'Active',
  `role_id` int NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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

-- Dump completed on 2025-10-02 15:59:53
