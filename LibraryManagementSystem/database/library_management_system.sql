-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.23 - MySQL Community Server - GPL
-- Server OS:                    Linux
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for library_management_system
CREATE DATABASE IF NOT EXISTS `library_management_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `library_management_system`;

-- Dumping structure for table library_management_system.account
CREATE TABLE IF NOT EXISTS `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `sq` varchar(50) DEFAULT NULL,
  `sa` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_management_system.account: ~7 rows (approximately)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `name`, `password`, `sq`, `sa`) VALUES
	(1, 'abhi', 'abhi1234', 'what is your pet name?', 'kat'),
	(2, 'Rajeeb Das', '12345678', 'What is your pet name?', 'Tom'),
	(3, 'Monidipa Das', '12345678', 'What is your pet name?', 'dick'),
	(4, 'Samarjit Das', '12345678', 'What is your pet name?', 'tom'),
	(5, 'Anirban Paul', '12345678', 'What is your pet name?', 'tom'),
	(6, 'Touqir', '12345678', 'What is your pet name?', 'tom'),
	(7, 'Tanvir', '12345678', 'What is your pet name?', 'tom'),
	(8, 'toma', '12345678', 'What is your pet name?', 'tom');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- Dumping structure for table library_management_system.book
CREATE TABLE IF NOT EXISTS `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `edition` varchar(10) DEFAULT NULL,
  `price` decimal(18,12) DEFAULT NULL,
  `pages` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_management_system.book: ~2 rows (approximately)
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`id`, `name`, `author`, `edition`, `price`, `pages`) VALUES
	(4, 'One man army', 'unknown', '1', 230.000000000000, '124');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;

-- Dumping structure for table library_management_system.issueBook
CREATE TABLE IF NOT EXISTS `issueBook` (
  `id` int NOT NULL AUTO_INCREMENT,
  `issueDate` date DEFAULT NULL,
  `isReturn` tinyint(1) DEFAULT NULL,
  `returnDate` date DEFAULT NULL,
  `bookID` int DEFAULT NULL,
  `studentID` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_management_system.issueBook: ~2 rows (approximately)
/*!40000 ALTER TABLE `issueBook` DISABLE KEYS */;
INSERT INTO `issueBook` (`id`, `issueDate`, `isReturn`, `returnDate`, `bookID`, `studentID`) VALUES
	(1, '2021-02-17', 0, NULL, 4, 4),
	(2, '2021-02-17', 1, '2021-02-18', 4, 2),
	(3, '2021-02-16', 0, NULL, 4, 11132133);
/*!40000 ALTER TABLE `issueBook` ENABLE KEYS */;

-- Dumping structure for table library_management_system.student
CREATE TABLE IF NOT EXISTS `student` (
  `id` int NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `father_name` varchar(50) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table library_management_system.student: ~6 rows (approximately)
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`id`, `name`, `father_name`, `department`) VALUES
	(2, 'asdf', 'asdf', 'BBA'),
	(3, 'asas', 'asas', 'BBA'),
	(23, '', '', 'BBA'),
	(50, 'qwer', 'qwert', 'BBA'),
	(11132133, 'Abhijeet Das', 'SC Das', 'BSCSE'),
	(11132134, 'asas', 'asas', 'BBA');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
