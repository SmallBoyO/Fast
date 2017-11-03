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

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `permission` varchar(40) DEFAULT NULL,
  `parent_id` int(10) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `component` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `permission` */

insert  into `permission`(`id`,`type`,`url`,`permission`,`parent_id`,`parent_ids`,`status`,`name`,`component`) values (1,'right','index','system:index',0,NULL,1,NULL,NULL),(2,'menu','','',NULL,NULL,1,'系统管理',NULL),(3,'url','/home/userquery','system:user:query',2,NULL,1,'用户管理','/home/UserList'),(4,'url','',NULL,2,NULL,1,'菜单管理',NULL),(5,'right',NULL,'system:user:query',2,NULL,NULL,'用户查询',NULL),(6,'right',NULL,'system:user:update',2,NULL,NULL,'用户修改',NULL),(7,'right',NULL,'system:user:checkUserName',2,NULL,NULL,'用户名检查',NULL),(8,'right',NULL,'system:user:checkName',2,NULL,NULL,'昵称检查',NULL),(9,'right',NULL,'system:user:add',2,NULL,NULL,'添加用户',NULL),(10,'url',NULL,'system:role:query',2,NULL,1,'角色管理','/home/RoleList'),(11,'right',NULL,'system:role:query',10,NULL,NULL,'角色查询',NULL);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role` varchar(20) DEFAULT NULL,
  `description` varchar(20) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `role` */

insert  into `role`(`id`,`role`,`description`,`status`) values (1,'admin','admin',1),(2,'测试','测试',1);

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `role` int(10) DEFAULT NULL,
  `permission` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `role_permission` */

insert  into `role_permission`(`role`,`permission`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11);

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `test` */

insert  into `test`(`id`,`name`) values (1,'name'),(2,'sd ');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(30) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`id`,`userName`,`name`,`password`,`salt`,`status`) values (1,'zhang','zhang','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','11',1),(2,'test','zhang2','59be4bcfe8fb1a84da65fa4c198d6a2387e66b925d8ddf715a6b7a3fa47aef63','45645646',1),(3,'zhanghe2','zhanghe',NULL,'15643513',1),(4,'zhanghe21','zhanghe2',NULL,'15643513',1),(5,'zhangh2312','12312312',NULL,'15643513',1),(6,'zhanghe3','zhanghe3',NULL,'15643513',1),(7,'zhanghe4','zhanghe4',NULL,'15643513',1),(8,'zhanghe5','zhanghe5',NULL,'15643513',1),(9,'zhanghe6','zhanghe6',NULL,'15643513',1),(10,'zhanghe7','zhanghe7',NULL,'15643513',1),(11,'zhanghe8','zhanghe8',NULL,'15643513',1),(14,'zhanghe11','zhanghe11',NULL,'15643513',1);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user` int(10) DEFAULT NULL,
  `role` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_role` */

insert  into `user_role`(`user`,`role`) values (1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
