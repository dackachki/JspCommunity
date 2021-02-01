/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.4.6-MariaDB : Database - jspCommunity
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jspCommunity` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `jspCommunity`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `memberId` int(10) unsigned NOT NULL,
  `boardId` int(10) unsigned NOT NULL,
  `title` char(100) COLLATE utf8_unicode_ci NOT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `hitsCount` int(10) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `article` */

insert  into `article`(`id`,`regDate`,`updateDate`,`memberId`,`boardId`,`title`,`body`,`hitsCount`) values 
(2,'2021-01-08 09:01:36','2021-01-08 09:01:36',1,1,'제목2','내용2',0),
(3,'2021-01-08 09:01:36','2021-01-08 09:01:36',1,1,'제목3','내용3',0),
(4,'2021-01-08 09:01:36','2021-01-08 09:01:36',2,1,'제목4','내용4',0),
(5,'2021-01-08 09:01:36','2021-01-08 09:01:36',2,1,'제목5','내용5',0),
(6,'2021-01-12 10:19:50','2021-01-18 11:26:40',1,1,'제목~~','  asdasda',0),
(7,'2021-01-12 10:20:09','2021-01-18 10:54:59',1,3,'dfdfd','  dfdfdfa',0),
(13,'2021-01-12 13:51:05','2021-01-12 13:51:05',1,2,'cxvxcv','  bvbvb',0),
(14,'2021-01-18 09:46:55','2021-01-18 09:46:55',1,2,'asdasd','  asdsadasd',0),
(15,'2021-01-20 11:24:22','2021-01-20 11:24:22',1,2,'adsd','  asdasd',0),
(16,'2021-01-22 11:10:36','2021-01-22 11:10:36',1,3,'asdasda','  saddasd',0),
(17,'2021-01-28 09:08:30','2021-01-28 09:08:30',1,1,'aaa','bbb',0),
(18,'2021-01-28 09:08:31','2021-01-28 09:08:31',1,1,'aaa','bbb',0),
(19,'2021-01-28 09:08:31','2021-01-28 09:08:31',1,1,'aaa','bbb',0),
(20,'2021-01-28 09:08:31','2021-01-28 09:08:31',1,1,'aaa','bbb',0),
(21,'2021-01-28 09:08:31','2021-01-28 09:08:31',1,1,'aaa','bbb',0),
(22,'2021-01-28 09:08:31','2021-01-28 09:08:31',1,1,'aaa','bbb',0),
(23,'2021-01-28 09:08:31','2021-01-28 09:08:31',1,1,'aaa','bbb',0),
(24,'2021-01-28 09:08:31','2021-01-28 09:08:31',1,1,'aaa','bbb',0),
(25,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(26,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(27,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(28,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(29,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(30,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(31,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(32,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(33,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(34,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(35,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(36,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(37,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(38,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(39,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(40,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(41,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(42,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(43,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(44,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(45,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(46,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(47,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(48,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(49,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(50,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(51,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(52,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(53,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(54,'2021-01-28 09:08:32','2021-01-28 09:08:32',1,1,'aaa','bbb',0),
(55,'2021-01-28 09:08:33','2021-01-28 09:08:33',1,1,'aaa','bbb',0),
(56,'2021-01-28 09:08:33','2021-01-28 09:08:33',1,1,'aaa','bbb',0),
(57,'2021-01-28 09:08:59','2021-01-28 09:08:59',1,1,'aaa','bbb',0),
(58,'2021-01-28 09:08:59','2021-01-28 09:08:59',2,1,'ccc','ddd',0),
(59,'2021-01-28 09:08:59','2021-01-28 09:08:59',2,1,'eee','fff',0),
(60,'2021-01-28 09:09:00','2021-01-28 09:09:00',1,1,'aaa','bbb',0),
(61,'2021-01-28 09:09:00','2021-01-28 09:09:00',2,1,'ccc','ddd',0),
(62,'2021-01-28 09:09:00','2021-01-28 09:09:00',2,1,'eee','fff',0),
(63,'2021-01-28 09:09:00','2021-01-28 09:09:00',1,1,'aaa','bbb',0),
(64,'2021-01-28 09:09:00','2021-01-28 09:09:00',2,1,'ccc','ddd',0),
(65,'2021-01-28 09:09:00','2021-01-28 09:09:00',2,1,'eee','fff',0),
(66,'2021-01-28 09:09:00','2021-01-28 09:09:00',1,1,'aaa','bbb',0),
(67,'2021-01-28 09:09:00','2021-01-28 09:09:00',2,1,'ccc','ddd',0),
(68,'2021-01-28 09:09:00','2021-01-28 09:09:00',2,1,'eee','fff',0),
(69,'2021-01-28 09:09:00','2021-01-28 09:09:00',1,1,'aaa','bbb',0),
(70,'2021-01-28 09:09:00','2021-01-28 09:09:00',2,1,'ccc','ddd',0),
(71,'2021-01-28 09:09:00','2021-01-28 09:09:00',2,1,'eee','fff',0),
(72,'2021-01-28 09:09:00','2021-01-28 09:09:00',1,1,'aaa','bbb',0),
(73,'2021-01-28 09:09:00','2021-01-28 09:09:00',2,1,'ccc','ddd',0),
(74,'2021-01-28 09:09:00','2021-01-28 09:09:00',2,1,'eee','fff',0),
(75,'2021-01-28 09:09:00','2021-01-28 09:09:00',1,1,'aaa','bbb',0),
(76,'2021-01-28 09:09:00','2021-01-28 09:09:00',2,1,'ccc','ddd',0),
(77,'2021-01-28 09:09:00','2021-01-28 09:09:00',2,1,'eee','fff',0),
(78,'2021-01-28 09:09:01','2021-01-28 09:09:01',1,1,'aaa','bbb',0),
(79,'2021-01-28 09:09:01','2021-01-28 09:09:01',2,1,'ccc','ddd',0),
(80,'2021-01-28 09:09:01','2021-01-28 09:09:01',2,1,'eee','fff',0),
(81,'2021-01-28 09:09:01','2021-01-28 09:09:01',1,1,'aaa','bbb',0),
(82,'2021-01-28 09:09:01','2021-01-28 09:09:01',2,1,'ccc','ddd',0),
(83,'2021-01-28 09:09:01','2021-01-28 09:09:01',2,1,'eee','fff',0),
(84,'2021-01-28 09:34:43','2021-01-28 09:34:43',2,1,'eee','fff',0),
(85,'2021-01-28 09:34:43','2021-01-28 09:34:43',2,1,'eee','fff',0),
(86,'2021-01-28 09:34:44','2021-01-28 09:34:44',2,1,'eee','fff',0),
(87,'2021-01-28 09:34:44','2021-01-28 09:34:44',2,1,'eee','fff',0),
(88,'2021-01-28 09:41:52','2021-01-28 09:41:52',13,1,'aaaaaa','',0),
(89,'2021-01-28 09:44:05','2021-02-01 11:14:29',13,1,'bbbb','# dasdsada\r\n\r\n## aaaa',6),
(90,'2021-01-28 09:48:48','2021-01-28 09:48:48',13,1,'aaasdas','# asdasd\r\n\r\n## adssddd\r\n- abbb',15);

/*Table structure for table `attr` */

DROP TABLE IF EXISTS `attr`;

CREATE TABLE `attr` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL DEFAULT current_timestamp(),
  `updateDate` datetime NOT NULL DEFAULT current_timestamp(),
  `expireDate` datetime NOT NULL,
  `relId` int(10) unsigned NOT NULL,
  `relTypeCode` char(30) COLLATE utf8_unicode_ci NOT NULL,
  `typeCode` char(30) COLLATE utf8_unicode_ci NOT NULL,
  `type2Code` char(30) COLLATE utf8_unicode_ci NOT NULL,
  `value` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `attr` */

insert  into `attr`(`id`,`regDate`,`updateDate`,`expireDate`,`relId`,`relTypeCode`,`typeCode`,`type2Code`,`value`) values 
(6,'2021-02-01 11:03:58','2021-02-01 11:03:58','2021-05-02 00:00:00',13,'member','extra','isUsingTempPassword',1);

/*Table structure for table `board` */

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `code` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `name` char(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `board` */

insert  into `board`(`id`,`regDate`,`updateDate`,`code`,`name`) values 
(1,'2021-01-08 09:01:35','2021-01-08 09:01:35','notice','공지사항'),
(2,'2021-01-08 09:01:35','2021-01-08 09:01:35','guestBook','방명록'),
(3,'2021-01-08 09:01:35','2021-01-08 09:01:35','free','자유');

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `name` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `nickname` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `loginId` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `loginPw` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `adminLevel` tinyint(1) unsigned NOT NULL DEFAULT 2 COMMENT '0=탈퇴/1=로그인정지/2=일반/3=인증된,4=관리자',
  `cellphoneNo` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `member` */

insert  into `member`(`id`,`regDate`,`updateDate`,`name`,`nickname`,`email`,`loginId`,`loginPw`,`adminLevel`,`cellphoneNo`) values 
(1,'2021-01-08 09:01:35','2021-01-08 09:01:35','김민수','강바람','skatmddnjs77@gmail.com','user1','b1bede312aad27dd46ffa3f87a8a44d1b114b37b77e3dc0b6cd885739d82255b',2,0),
(2,'2021-01-08 09:01:35','2021-01-08 09:01:35','김미소','이또한지나가리라','jangka512@gmail.com','user2','user2',2,0),
(10,'2021-01-22 11:42:48','2021-01-22 11:42:48','user3','user3','user3@aaa.com','user3','user3',2,0),
(11,'2021-01-22 12:13:17','2021-01-22 12:13:17','user34','user34','test@test.com','user34','user34',2,0),
(12,'2021-01-26 11:17:40','2021-01-26 11:17:40','user11','user11','user11@aaa.com','user11','81115e31e22a5801b197750ec12d7a51ad693aa017ecc8bca033cbd500a928b6',2,1234512311),
(13,'2021-01-28 09:41:26','2021-01-28 09:41:26','admin','admin','skatmddnjs77@gmail.com','admin','79a7d6672d664380bfee311ab80f98e86d5172f4787fb401f25e45e413ac0137',2,1234512311);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
