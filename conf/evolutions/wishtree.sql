-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.24 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 wishtree 的数据库结构
CREATE DATABASE IF NOT EXISTS `wishtree` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `wishtree`;


-- 导出  表 wishtree.card 结构
CREATE TABLE IF NOT EXISTS `card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `summary` varchar(100) DEFAULT NULL,
  `thumbnail` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  `score` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  wishtree.card 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` (`id`, `name`, `summary`, `thumbnail`, `type`, `score`) VALUES
	(2, 'name02', 'summary2', 'thumbnail2', 2, 2),
	(3, 'name04', 'summary4', 'thumbnail4', 4, 4);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;


-- 导出  表 wishtree.person 结构
CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` int(11) NOT NULL DEFAULT '0',
  `avatar` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  wishtree.person 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`id`, `account`, `password`, `level`, `avatar`) VALUES
	(2, '222', '222', 2, '2'),
	(3, '444', '444', 4, '4');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;


-- 导出  表 wishtree.task 结构
CREATE TABLE IF NOT EXISTS `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wishId` int(11) NOT NULL,
  `ownerId` int(11) NOT NULL,
  `assign` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_task_card` (`wishId`),
  KEY `FK_task_person` (`ownerId`),
  KEY `FK_task_person_2` (`assign`),
  CONSTRAINT `FK_task_card` FOREIGN KEY (`wishId`) REFERENCES `card` (`id`),
  CONSTRAINT `FK_task_person` FOREIGN KEY (`ownerId`) REFERENCES `person` (`id`),
  CONSTRAINT `FK_task_person_2` FOREIGN KEY (`assign`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  wishtree.task 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` (`id`, `wishId`, `ownerId`, `assign`) VALUES
	(1, 2, 2, 2),
	(3, 3, 3, 2),
	(4, 3, 3, 3);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
