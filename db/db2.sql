/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.6.35 : Database - Fast2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`Fast2` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `Fast2`;

/*Table structure for table `Permission` */

DROP TABLE IF EXISTS `Permission`;

CREATE TABLE `Permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `permission` varchar(20) DEFAULT NULL,
  `parentId` int(10) DEFAULT NULL,
  `parentIds` varchar(100) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `Permission` */

insert  into `Permission`(`id`,`type`,`url`,`permission`,`parentId`,`parentIds`,`status`,`name`) values (1,'url','index','system:index',0,NULL,1,NULL),(2,'menu','','',NULL,NULL,1,'用户管理'),(3,'url','/home/userquery','system:user:query',2,NULL,1,NULL);

/*Table structure for table `Role` */

DROP TABLE IF EXISTS `Role`;

CREATE TABLE `Role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role` varchar(20) DEFAULT NULL,
  `description` varchar(20) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `Role` */

insert  into `Role`(`id`,`role`,`description`,`status`) values (1,'admin','admin',1);

/*Table structure for table `User` */

DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(30) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `User` */

insert  into `User`(`id`,`userName`,`name`,`password`,`salt`,`status`) values (1,'zhang','zhang','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','11',1);

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
  `Role` int(10) DEFAULT NULL,
  `Permission` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_role_permission` */

insert  into `t_role_permission`(`Role`,`Permission`) values (1,1),(1,2),(1,3);

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `user` int(10) DEFAULT NULL,
  `role` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`user`,`role`) values (1,1);

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `test` */

insert  into `test`(`id`,`name`) values (1,'name'),(2,'sd ');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
