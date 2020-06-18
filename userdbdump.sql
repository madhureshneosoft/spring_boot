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

/*Table structure for table `user_detail_tbl` */

DROP TABLE IF EXISTS `user_detail_tbl`;

CREATE TABLE `user_detail_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `email_id` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `pincode` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_py1go6uhax7okwi9w8jqgfoat` (`email_id`),
  UNIQUE KEY `UK_r8l4ckesqa0a3pfs0lieeu6gt` (`mobile_number`),
  KEY `FKbi4fs914pes9smgxyrvjetk8v` (`user_id`),
  CONSTRAINT `FKbi4fs914pes9smgxyrvjetk8v` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_detail_tbl` */

insert  into `user_detail_tbl`(`id`,`address`,`date_of_birth`,`email_id`,`first_name`,`last_name`,`mobile_number`,`pincode`,`user_id`) values 
(1,'test3 address','1998-09-19','test31.newtest@gmail.com','test3fnewupdated','test3lnnewasd','9978607890',380061,1);

/*Table structure for table `user_education_tbl` */

DROP TABLE IF EXISTS `user_education_tbl`;

CREATE TABLE `user_education_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cgpa` float NOT NULL,
  `hsc_board_name` varchar(255) NOT NULL,
  `hsc_percentage` float NOT NULL,
  `ssc_board_name` varchar(255) NOT NULL,
  `ssc_percentage` float NOT NULL,
  `university_name` varchar(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5dn13h2pnlsgfgpgkny7ks40r` (`user_id`),
  CONSTRAINT `FK5dn13h2pnlsgfgpgkny7ks40r` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_education_tbl` */

insert  into `user_education_tbl`(`id`,`cgpa`,`hsc_board_name`,`hsc_percentage`,`ssc_board_name`,`ssc_percentage`,`university_name`,`user_id`) values 
(1,8.44,'gsheb',89.13,'gseb',79.14,'GTU',1),
(3,8.44,'gsheb',84.13,'gseb',72.14,'GTU',1);

/*Table structure for table `user_employment_tbl` */

DROP TABLE IF EXISTS `user_employment_tbl`;

CREATE TABLE `user_employment_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_of_join` date NOT NULL,
  `department` varchar(255) NOT NULL,
  `experience` tinyint(4) NOT NULL,
  `salary` bigint(20) NOT NULL,
  `work_email` varchar(255) NOT NULL,
  `work_mobile_number` varchar(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9h65metdw0i2x8xse1w1r65db` (`work_email`),
  UNIQUE KEY `UK_i5txoyr7cdt0v4l0fnifbkpm` (`work_mobile_number`),
  KEY `FK76exlc85cyqex6g2tymaglgtc` (`user_id`),
  CONSTRAINT `FK76exlc85cyqex6g2tymaglgtc` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_employment_tbl` */

insert  into `user_employment_tbl`(`id`,`date_of_join`,`department`,`experience`,`salary`,`work_email`,`work_mobile_number`,`user_id`) values 
(1,'2020-01-07','JAVA',2,200000,'update@neosoft.com','1111111111',1);

/*Table structure for table `user_project_tbl` */

DROP TABLE IF EXISTS `user_project_tbl`;

CREATE TABLE `user_project_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `end_date` date DEFAULT NULL,
  `project_company` varchar(255) NOT NULL,
  `project_detail` varchar(255) NOT NULL,
  `project_name` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK36rhsef1tioec0bcf8cotoeac` (`user_id`),
  CONSTRAINT `FK36rhsef1tioec0bcf8cotoeac` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_project_tbl` */

insert  into `user_project_tbl`(`id`,`active`,`end_date`,`project_company`,`project_detail`,`project_name`,`start_date`,`user_id`) values 
(1,'',NULL,'E NEW COMMUNICATE','SMS NEW PROJECT FOR BRAZIL','SMS NEW','2020-03-13',1);

/*Table structure for table `user_role_tbl` */

DROP TABLE IF EXISTS `user_role_tbl`;

CREATE TABLE `user_role_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK53b8oon0dkfbdetl5bsqfq0wn` (`user_id`),
  CONSTRAINT `FK53b8oon0dkfbdetl5bsqfq0wn` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_role_tbl` */

insert  into `user_role_tbl`(`id`,`role`,`user_id`) values 
(1,'Developer',1);

/*Table structure for table `user_tbl` */

DROP TABLE IF EXISTS `user_tbl`;

CREATE TABLE `user_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `create_date` date NOT NULL,
  `password` varchar(255) NOT NULL,
  `update_date` date NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_detail_id` int(11) DEFAULT NULL,
  `user_education_detail_id` int(11) DEFAULT NULL,
  `user_employment_detail_id` int(11) DEFAULT NULL,
  `user_role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r0s0txs2bqvs26pnfuye8nwyt` (`user_name`),
  KEY `FKedndhw56jym8979av093psf4i` (`user_detail_id`),
  KEY `FK8ofdrag8cnk8nal9uhf4wcy7h` (`user_education_detail_id`),
  KEY `FKp5dq346y4agmfw1e8mo0ix3bv` (`user_employment_detail_id`),
  KEY `FK8h8u04yrc71fpcwqh0f8cf5sw` (`user_role_id`),
  CONSTRAINT `FK8h8u04yrc71fpcwqh0f8cf5sw` FOREIGN KEY (`user_role_id`) REFERENCES `user_role_tbl` (`id`),
  CONSTRAINT `FK8ofdrag8cnk8nal9uhf4wcy7h` FOREIGN KEY (`user_education_detail_id`) REFERENCES `user_education_tbl` (`id`),
  CONSTRAINT `FKedndhw56jym8979av093psf4i` FOREIGN KEY (`user_detail_id`) REFERENCES `user_detail_tbl` (`id`),
  CONSTRAINT `FKp5dq346y4agmfw1e8mo0ix3bv` FOREIGN KEY (`user_employment_detail_id`) REFERENCES `user_employment_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_tbl` */

insert  into `user_tbl`(`id`,`active`,`create_date`,`password`,`update_date`,`user_name`,`user_detail_id`,`user_education_detail_id`,`user_employment_detail_id`,`user_role_id`) values 
(1,'','2020-06-17','P@ssw0rd','2020-06-18','updatemethod',1,1,1,1);

/*Table structure for table `user_tbl_user_project_detail` */

DROP TABLE IF EXISTS `user_tbl_user_project_detail`;

CREATE TABLE `user_tbl_user_project_detail` (
  `user_tbl_id` int(11) NOT NULL,
  `user_project_detail_id` int(11) NOT NULL,
  UNIQUE KEY `UK_au7eddv5rrswn59fe0ewr0yes` (`user_project_detail_id`),
  KEY `FKdyonl171k0873juy85p7d5ukc` (`user_tbl_id`),
  CONSTRAINT `FKdyonl171k0873juy85p7d5ukc` FOREIGN KEY (`user_tbl_id`) REFERENCES `user_tbl` (`id`),
  CONSTRAINT `FKoigtryd15odh65j65kjj2dqlm` FOREIGN KEY (`user_project_detail_id`) REFERENCES `user_project_tbl` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_tbl_user_project_detail` */

insert  into `user_tbl_user_project_detail`(`user_tbl_id`,`user_project_detail_id`) values 
(1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
