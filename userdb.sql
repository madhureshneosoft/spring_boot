/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 8.0.17 : Database - userdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`userdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `userdb`;

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values 
(13);

/*Table structure for table `user_tbl` */

DROP TABLE IF EXISTS `user_tbl`;

CREATE TABLE `user_tbl` (
  `id` int(11) NOT NULL,
  `active` bit(1) NOT NULL,
  `address` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `date_of_join` date NOT NULL,
  `email_id` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `pincode` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r1crh0374vmj44dmkk03e7xx3` (`email_id`),
  UNIQUE KEY `UK_61c5bspcxqhvrobychrijhe96` (`mobile_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_tbl` */

insert  into `user_tbl`(`id`,`active`,`address`,`date_of_birth`,`date_of_join`,`email_id`,`first_name`,`last_name`,`mobile_number`,`pincode`) values 
(1,'','test1 address','1998-09-19','2020-01-07','test1@gmail.com','test1','test1','9978607891',380061),
(2,'','test2 address','1998-03-12','2020-01-08','test2@gmail.com','test2','test2','9978607892',380061),
(3,'','test3 address','1998-05-01','2020-02-17','test3@gmail.com','test3','test3','9978607893',380061),
(4,'\0','test4 address','1998-12-21','2020-01-27','test4@gmail.com','test4','test4','9978607894',380061),
(5,'','test5 address','1998-01-07','2020-01-27','test5@gmail.com','test5','test5','9978607895',380061),
(7,'','test7 address','1999-12-29','2020-08-17','test7@gmail.com','test7','test7','9978607897',380061),
(9,'\0','test8 address','1999-12-28','2020-08-16','test8@gmail.com','test8','test8','9978607898',380061),
(10,'','test76 address','2000-01-01','2020-01-01','test76@gmail.com','test76','test76','9978607876',380076);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
