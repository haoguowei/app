# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.18)
# Database: app
# Generation Time: 2020-05-17 09:34:25 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table area
# ------------------------------------------------------------

DROP TABLE IF EXISTS `area`;

CREATE TABLE `area` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域';

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;

INSERT INTO `area` (`id`, `name`, `remark`, `creater`, `create_time`, `update_time`)
VALUES
	(1,'天津区域','','admin','2020-05-05 17:37:54','2020-05-15 14:20:13'),
	(2,'河北区域','','admin','2020-05-15 14:20:29','2020-05-15 14:20:28'),
	(3,'河南区域','','admin','2020-05-15 14:20:46','2020-05-15 14:20:45');

/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table assets
# ------------------------------------------------------------

DROP TABLE IF EXISTS `assets`;

CREATE TABLE `assets` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `type` int(10) NOT NULL DEFAULT '0' COMMENT '资产类型-车辆/电动人力/箱/桶/辅助类',
  `projects` int(10) NOT NULL DEFAULT '0' COMMENT '所属项目',
  `projects_name` varchar(50) NOT NULL DEFAULT '' COMMENT '项目名称',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `name_id` int(11) DEFAULT NULL COMMENT '名称',
  `number` varchar(50) NOT NULL DEFAULT '' COMMENT '资产编号',
  `license` varchar(50) NOT NULL DEFAULT '' COMMENT '牌照号',
  `brand` varchar(50) NOT NULL DEFAULT '' COMMENT '品牌',
  `car_type` varchar(50) NOT NULL DEFAULT '' COMMENT '规格型号（车型）',
  `buy_time` date NOT NULL DEFAULT '0000-01-01' COMMENT '采购时间',
  `in_out` varchar(200) NOT NULL DEFAULT '' COMMENT '转入/借用',
  `quantity` int(10) NOT NULL DEFAULT '0' COMMENT '数量',
  `quo_quantity` int(10) NOT NULL DEFAULT '0' COMMENT '现况数量',
  `owner` int(10) NOT NULL DEFAULT '0' COMMENT '责任人',
  `storage_location` varchar(200) NOT NULL DEFAULT '' COMMENT '存放地点',
  `engine_number` varchar(50) NOT NULL DEFAULT '' COMMENT '发动机号',
  `engine_number_type` int(10) NOT NULL DEFAULT '0' COMMENT '号牌种类',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '单价',
  `staging` int(11) NOT NULL DEFAULT '0' COMMENT '分期月数？',
  `pur_tax` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '购置税',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `tanxiao` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '摊销金额',
  `zhejiu` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '折旧金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资产';

LOCK TABLES `assets` WRITE;
/*!40000 ALTER TABLE `assets` DISABLE KEYS */;

INSERT INTO `assets` (`id`, `type`, `projects`, `projects_name`, `name`, `name_id`, `number`, `license`, `brand`, `car_type`, `buy_time`, `in_out`, `quantity`, `quo_quantity`, `owner`, `storage_location`, `engine_number`, `engine_number_type`, `price`, `staging`, `pur_tax`, `remark`, `creater`, `create_time`, `update_time`, `tanxiao`, `zhejiu`)
VALUES
	(1,0,1,'宝坻乡镇','3吨压缩车',NULL,'lj-001','津mf3079','2','1','2020-05-05','',23,23,1,'宝坻大白镇','09802830283023',2,180000.00,48,18000.00,'','admin','2020-05-05 17:57:11','2020-05-05 17:57:10',6500.00,6500.00),
	(2,0,1,'宝坻乡镇','勾臂转运车\n',3,'0001','0002','2','2','2020-05-14','',10,10,1,'','',0,0.00,0,0.00,'测试','admin','2020-05-15 08:13:09','2020-05-15 08:13:23',0.00,0.00),
	(3,0,1,'宝坻乡镇','勾臂转运车',3,'yyy','','3','1','2020-05-13','',7,7,1,'','',0,0.00,0,0.00,'','admin','2020-05-15 08:39:57','2020-05-15 08:39:57',0.00,0.00);

/*!40000 ALTER TABLE `assets` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table assets_other
# ------------------------------------------------------------

DROP TABLE IF EXISTS `assets_other`;

CREATE TABLE `assets_other` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `type` int(10) NOT NULL DEFAULT '0' COMMENT '资产类型-电动人力/箱/桶/辅助类',
  `projects` int(10) NOT NULL DEFAULT '0' COMMENT '所属项目',
  `projects_name` varchar(50) NOT NULL DEFAULT '' COMMENT '项目名称',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `name_id` int(11) DEFAULT NULL COMMENT '名称id',
  `brand` varchar(50) NOT NULL DEFAULT '' COMMENT '品牌',
  `car_type` varchar(50) NOT NULL DEFAULT '' COMMENT '规格型号（车型）',
  `buy_time` date NOT NULL DEFAULT '0000-01-01' COMMENT '采购时间',
  `in_out` varchar(200) NOT NULL DEFAULT '' COMMENT '转入/借用',
  `quantity` int(10) NOT NULL DEFAULT '0' COMMENT '数量',
  `quo_quantity` int(10) NOT NULL DEFAULT '0' COMMENT '现况数量',
  `owner` int(10) NOT NULL DEFAULT '0' COMMENT '责任人',
  `storage_location` varchar(200) NOT NULL DEFAULT '' COMMENT '存放地点',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '单价',
  `staging` int(11) NOT NULL DEFAULT '0' COMMENT '分期月数？',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `tanxiao` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '摊销金额',
  `zhejiu` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '折旧金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='其他资产';

LOCK TABLES `assets_other` WRITE;
/*!40000 ALTER TABLE `assets_other` DISABLE KEYS */;

INSERT INTO `assets_other` (`id`, `type`, `projects`, `projects_name`, `name`, `name_id`, `brand`, `car_type`, `buy_time`, `in_out`, `quantity`, `quo_quantity`, `owner`, `storage_location`, `price`, `staging`, `remark`, `creater`, `create_time`, `update_time`, `tanxiao`, `zhejiu`)
VALUES
	(1,1,2,'阜平','1千1',NULL,'3','1','2020-01-05','1',2,3,1,'4',5.00,6,'9','admin','2020-01-05 15:00:07','2020-01-05 15:05:28',7.00,8.00),
	(2,1,2,'阜平','1111',NULL,'2','1','2020-01-05','1',2,3,2,'4',5.00,6,'999','admin','2020-01-05 15:19:36','2020-01-05 15:19:36',7.00,8.00),
	(3,2,2,'宝坻城区扫保','压缩箱',1,'3','1','2020-02-01','',0,0,1,'',0.00,0,'','admin','2020-02-01 11:35:36','2020-05-17 16:49:13',0.00,0.00),
	(4,3,2,'阜平','电动自行车',6,'2','2','2020-05-05','',10,0,3,'',0.00,0,'','admin','2020-05-07 22:43:41','2020-05-07 22:43:55',0.00,0.00);

/*!40000 ALTER TABLE `assets_other` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table costs
# ------------------------------------------------------------

DROP TABLE IF EXISTS `costs`;

CREATE TABLE `costs` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `enter_date` date NOT NULL DEFAULT '0000-01-01' COMMENT '录入日期',
  `status` int(10) NOT NULL DEFAULT '0' COMMENT '状态；0-录入；1-提交',
  `projects` int(10) NOT NULL DEFAULT '0' COMMENT '所属项目',
  `projects_name` varchar(50) NOT NULL DEFAULT '' COMMENT '项目名称',
  `type_1` int(10) NOT NULL DEFAULT '0' COMMENT '费用类型1',
  `type_2` int(10) NOT NULL DEFAULT '0' COMMENT '费用类型2',
  `type_3` int(10) NOT NULL DEFAULT '0' COMMENT '费用类型3',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '费用金额',
  `numb` varchar(100) NOT NULL DEFAULT '' COMMENT '费用单号',
  `useful` varchar(200) NOT NULL DEFAULT '' COMMENT '费用用途',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='费用';

LOCK TABLES `costs` WRITE;
/*!40000 ALTER TABLE `costs` DISABLE KEYS */;

INSERT INTO `costs` (`id`, `enter_date`, `status`, `projects`, `projects_name`, `type_1`, `type_2`, `type_3`, `amount`, `numb`, `useful`, `remark`, `creater`, `create_time`, `update_time`)
VALUES
	(1,'2020-01-15',1,1,'宝坻乡镇',1,3,13,500.00,'','','','admin','2020-05-15 08:20:31','2020-05-15 08:20:31'),
	(2,'2020-03-16',1,1,'宝坻乡镇',2,10,51,800.00,'','','','admin','2020-05-15 08:21:00','2020-05-15 08:21:44'),
	(3,'2020-05-04',1,1,'宝坻乡镇',2,11,54,700.50,'','','','admin','2020-05-15 08:21:19','2020-05-15 08:21:34'),
	(4,'2020-04-08',1,1,'宝坻乡镇',2,6,26,888.00,'','','','admin','2020-05-15 08:44:16','2020-05-15 08:44:16'),
	(5,'2020-05-06',1,1,'宝坻乡镇',2,7,34,800.00,'','','','admin','2020-05-15 12:39:32','2020-05-15 12:39:31'),
	(6,'2020-04-01',0,3,'信阳分公司',2,6,24,1000.00,'','','','admin','2020-05-15 14:27:18','2020-05-15 14:27:17'),
	(7,'2020-04-09',1,3,'信阳分公司',2,6,24,300.00,'','','','admin','2020-05-15 14:44:13','2020-05-15 14:44:40'),
	(8,'2020-04-24',1,3,'信阳分公司',2,6,24,280.00,'','','','admin','2020-05-15 14:45:33','2020-05-15 14:45:32'),
	(9,'2020-04-24',1,3,'信阳分公司',2,6,24,105.00,'','','','admin','2020-05-15 14:46:10','2020-05-15 14:46:10'),
	(10,'2020-04-30',1,3,'信阳分公司',2,6,24,560.00,'','','','admin','2020-05-15 14:47:45','2020-05-15 14:47:45'),
	(11,'2020-04-30',1,3,'信阳分公司',2,6,24,330.00,'','','','admin','2020-05-15 14:48:36','2020-05-15 14:48:35'),
	(12,'2020-04-09',1,3,'信阳分公司',2,6,26,50.00,'','','','admin','2020-05-15 14:50:34','2020-05-15 14:50:34'),
	(13,'2020-04-09',1,3,'信阳分公司',2,6,27,200.00,'','','','admin','2020-05-15 14:51:13','2020-05-15 14:51:12'),
	(14,'2020-04-09',1,3,'信阳分公司',2,6,29,255.00,'','','','admin','2020-05-15 14:52:24','2020-05-15 14:52:24'),
	(15,'2020-04-22',1,3,'信阳分公司',2,6,29,520.00,'','','','admin','2020-05-15 14:53:10','2020-05-15 14:53:10'),
	(16,'2020-04-22',1,3,'信阳分公司',2,6,29,830.00,'','','','admin','2020-05-15 14:53:43','2020-05-15 14:53:43'),
	(17,'2020-04-30',1,3,'信阳分公司',2,6,30,739.00,'','','','admin','2020-05-15 14:55:01','2020-05-15 14:55:01'),
	(18,'2020-04-09',1,3,'信阳分公司',2,6,33,335.00,'','','','admin','2020-05-15 14:56:02','2020-05-15 14:56:02'),
	(19,'2020-04-29',1,3,'信阳分公司',2,6,33,39.50,'','','','admin','2020-05-15 14:56:37','2020-05-15 14:56:37'),
	(20,'2020-05-24',1,3,'信阳分公司',2,7,34,20000.00,'','','','admin','2020-05-15 14:57:17','2020-05-15 14:57:16'),
	(21,'2020-04-16',1,3,'信阳分公司',2,7,35,1120.00,'','','4.1-4.15','admin','2020-05-15 14:59:15','2020-05-15 14:59:15'),
	(22,'2020-04-30',1,3,'信阳分公司',2,7,35,1670.00,'','','4.16-4.30','admin','2020-05-15 15:00:39','2020-05-15 15:00:39'),
	(23,'2020-04-16',1,21,'信阳分公司',2,7,36,525.00,'','','4.1-4.15','admin','2020-05-15 15:27:36','2020-05-15 15:27:35'),
	(24,'2020-04-30',1,21,'信阳分公司',2,7,36,1615.00,'','','4.16-4.30','admin','2020-05-15 15:30:17','2020-05-15 15:30:17'),
	(25,'2020-04-16',1,21,'信阳分公司',2,7,37,2320.00,'','','4.1-4.15','admin','2020-05-15 15:31:17','2020-05-15 15:31:34'),
	(26,'2020-04-30',1,21,'信阳分公司',2,7,37,760.00,'','','','admin','2020-05-15 15:32:10','2020-05-15 15:32:09'),
	(27,'2020-04-22',1,21,'信阳分公司',2,7,38,500.00,'','','','admin','2020-05-15 15:32:50','2020-05-15 15:32:49'),
	(28,'2020-04-22',1,21,'信阳分公司',2,8,42,1000.00,'','','','admin','2020-05-15 15:33:33','2020-05-15 15:33:33'),
	(29,'2020-04-22',1,21,'信阳分公司',2,8,46,200.00,'','','','admin','2020-05-15 15:34:41','2020-05-15 15:34:40'),
	(30,'2020-04-22',1,21,'信阳分公司',2,8,46,1410.00,'','','','admin','2020-05-15 15:35:07','2020-05-15 15:35:07'),
	(31,'2020-04-30',1,21,'信阳分公司',2,8,46,1150.00,'','','','admin','2020-05-15 15:35:34','2020-05-15 15:35:34'),
	(32,'2020-04-08',1,20,'漯河分公司',2,6,24,5210.00,'','','','admin','2020-05-15 15:49:41','2020-05-15 15:49:41'),
	(33,'2020-04-08',1,20,'漯河分公司',2,6,24,7197.00,'','','','admin','2020-05-15 15:50:11','2020-05-15 15:50:10'),
	(34,'2020-04-09',1,20,'漯河分公司',2,6,25,828.00,'','','','admin','2020-05-15 15:50:45','2020-05-15 15:50:45'),
	(35,'2020-04-09',1,20,'漯河分公司',2,6,26,100.00,'','','','admin','2020-05-15 15:51:18','2020-05-15 15:51:18'),
	(36,'2020-04-24',1,20,'漯河分公司',2,6,26,20.00,'','','','admin','2020-05-15 15:51:39','2020-05-15 15:51:38'),
	(37,'2020-04-09',1,20,'漯河分公司',2,6,27,200.00,'','','','admin','2020-05-15 15:52:13','2020-05-15 15:52:13'),
	(38,'2020-04-24',1,20,'漯河分公司',2,6,27,150.00,'','','','admin','2020-05-15 15:52:46','2020-05-15 15:52:45'),
	(39,'2020-04-24',1,20,'漯河分公司',2,6,27,400.00,'','','','admin','2020-05-15 15:53:13','2020-05-15 15:53:13'),
	(40,'2020-04-09',1,20,'漯河分公司',2,6,29,5000.00,'','','','admin','2020-05-15 15:53:46','2020-05-15 15:53:45'),
	(41,'2020-04-16',1,20,'漯河分公司',2,6,29,360.00,'','','','admin','2020-05-15 15:54:07','2020-05-15 15:54:06'),
	(42,'2020-04-16',1,20,'漯河分公司',2,6,29,349.00,'','','','admin','2020-05-15 15:54:36','2020-05-15 15:54:35'),
	(43,'2020-04-16',1,20,'漯河分公司',2,6,29,1900.00,'','','','admin','2020-05-15 15:55:08','2020-05-15 15:55:08'),
	(44,'2020-04-16',1,20,'漯河分公司',2,6,29,280.00,'','','','admin','2020-05-15 15:55:42','2020-05-15 15:55:53'),
	(45,'2020-04-16',1,20,'漯河分公司',2,6,29,660.00,'','','','admin','2020-05-15 15:56:30','2020-05-15 15:56:29'),
	(46,'2020-04-16',1,20,'漯河分公司',2,6,29,330.00,'','','','admin','2020-05-15 15:56:56','2020-05-15 15:56:55'),
	(47,'2020-04-24',1,20,'漯河分公司',2,6,29,400.00,'','','','admin','2020-05-15 15:57:23','2020-05-15 15:57:23'),
	(48,'2020-04-24',1,20,'漯河分公司',2,6,29,664.00,'','','','admin','2020-05-15 15:57:53','2020-05-15 15:58:11'),
	(49,'2020-05-24',1,20,'漯河分公司',2,6,29,400.00,'','','','admin','2020-05-15 15:58:50','2020-05-15 15:58:50'),
	(50,'2020-04-24',1,20,'漯河分公司',2,6,29,595.00,'','','','admin','2020-05-15 15:59:29','2020-05-15 15:59:28'),
	(51,'2020-04-24',1,20,'漯河分公司',2,6,29,1297.00,'','','','admin','2020-05-15 16:00:08','2020-05-15 16:00:08'),
	(52,'2020-04-20',1,20,'漯河分公司',2,6,30,20.00,'','','','admin','2020-05-15 16:02:21','2020-05-15 16:02:30'),
	(53,'2020-04-20',1,20,'漯河分公司',2,6,30,12.00,'','','','admin','2020-05-15 16:02:56','2020-05-15 16:02:56'),
	(54,'2020-04-07',1,20,'漯河分公司',2,6,33,511.74,'','','','admin','2020-05-15 16:03:54','2020-05-15 16:03:54'),
	(55,'2020-04-11',1,20,'漯河分公司',2,7,34,20000.00,'','','','admin','2020-05-15 16:04:37','2020-05-15 16:04:37'),
	(56,'2020-04-28',1,20,'漯河分公司',2,7,34,20000.00,'','','','admin','2020-05-15 16:05:05','2020-05-15 16:05:05'),
	(57,'2020-04-05',1,20,'漯河分公司',2,7,35,130.00,'','','4.1-4.4','admin','2020-05-15 16:05:59','2020-05-15 16:06:52'),
	(58,'2020-04-12',1,20,'漯河分公司',2,7,35,720.00,'','','4.5-4.11','admin','2020-05-15 16:06:38','2020-05-15 16:06:38'),
	(59,'2020-04-17',1,20,'漯河分公司',2,7,35,2900.00,'','','4.12-4.16','admin','2020-05-15 16:07:43','2020-05-15 16:07:42'),
	(60,'2020-04-29',1,20,'漯河分公司',2,7,35,10.00,'','','','admin','2020-05-15 16:08:24','2020-05-15 16:08:23'),
	(61,'2020-04-30',1,20,'漯河分公司',2,7,35,4000.00,'','','','admin','2020-05-15 16:09:00','2020-05-15 16:08:59'),
	(62,'2020-04-30',1,20,'漯河分公司',2,7,35,100.00,'','','','admin','2020-05-15 16:09:19','2020-05-15 16:09:18'),
	(63,'2020-04-05',1,20,'漯河分公司',2,7,36,1715.00,'','','4.1-4.15','admin','2020-05-15 16:14:37','2020-05-15 16:14:37'),
	(64,'2020-04-12',1,20,'漯河分公司',2,7,36,870.00,'','','4.5-4.11','admin','2020-05-15 16:16:39','2020-05-15 16:16:39'),
	(65,'2020-04-29',1,20,'漯河分公司',2,7,36,360.00,'','','','admin','2020-05-15 16:17:26','2020-05-15 16:17:25');

/*!40000 ALTER TABLE `costs` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table costs_type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `costs_type`;

CREATE TABLE `costs_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT 'name',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='费用类型';

LOCK TABLES `costs_type` WRITE;
/*!40000 ALTER TABLE `costs_type` DISABLE KEYS */;

INSERT INTO `costs_type` (`id`, `name`, `parent_id`)
VALUES
	(1,'固定费用',0),
	(2,'项目可支配费用',0),
	(3,'人事费用',1),
	(4,'运营费用',1),
	(5,'财务费用',1),
	(6,'日常费用',2),
	(7,'车辆费用',2),
	(8,'运营费用',2),
	(9,'人力费用',2),
	(10,'固定资产性费用',2),
	(11,'扣款',2),
	(12,'节日福利',3),
	(13,'统筹险',3),
	(14,'公积金',3),
	(15,'个税',3),
	(16,'商业保险费',3),
	(17,'车辆保险',3),
	(18,'项目投入各种设备、设施',4),
	(19,'外包费用',4),
	(20,'房租',4),
	(21,'折旧费',4),
	(22,'税金及附加',5),
	(23,'上缴公司管理费',5),
	(24,'办公费',6),
	(25,'通讯费',6),
	(26,'办公水费',6),
	(27,'办公电费',6),
	(28,'办公燃气费',6),
	(29,'业务招待费',6),
	(30,'交通费',6),
	(31,'差旅费',6),
	(32,'食堂费用',6),
	(33,'手续费',6),
	(34,'燃料费',7),
	(35,'修理厂修理费',7),
	(36,'保养费',7),
	(37,'补胎费',7),
	(38,'购零部件',7),
	(39,'车辆理赔',7),
	(40,'车务费',7),
	(41,'电动车等日常小维修',8),
	(42,'扫保工具',8),
	(43,'运营水费',8),
	(44,'运营电费',8),
	(45,'运营修理费',8),
	(46,'临时费用',8),
	(47,'员工工资',9),
	(48,'临时用工',9),
	(49,'工伤费',9),
	(50,'房屋建筑物',10),
	(51,'机器设备',10),
	(52,'运输设备',10),
	(53,'电脑设备',10),
	(54,'项目扣款',11);

/*!40000 ALTER TABLE `costs_type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table employee
# ------------------------------------------------------------

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `status` int(10) NOT NULL DEFAULT '0' COMMENT '状态；0-正常；1-离职',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `phone` varchar(50) NOT NULL DEFAULT '' COMMENT '手机号',
  `id_card` varchar(50) NOT NULL DEFAULT '' COMMENT '身份证号',
  `projects` int(10) NOT NULL DEFAULT '0' COMMENT '所属项目',
  `projects_name` varchar(50) DEFAULT '' COMMENT '项目名',
  `job_type` int(10) NOT NULL DEFAULT '0' COMMENT '经理/主管/文员/助理/队长/司机/装卸工/保洁员',
  `ethnic` int(10) NOT NULL DEFAULT '0' COMMENT '民族',
  `entry_date` date NOT NULL DEFAULT '0000-01-01' COMMENT '入职日期',
  `leave_date` date NOT NULL DEFAULT '0000-01-01' COMMENT '离职日期',
  `edu_type` int(10) NOT NULL DEFAULT '0' COMMENT '学历？',
  `hukou_type` int(10) NOT NULL DEFAULT '0' COMMENT '0-农村；1-城镇',
  `hetong` int(10) DEFAULT NULL COMMENT '0-否，1-是',
  `huji_address` varchar(200) NOT NULL DEFAULT '' COMMENT '户籍地址',
  `address` varchar(200) NOT NULL DEFAULT '' COMMENT '现住地址',
  `emergency_contact` varchar(50) NOT NULL DEFAULT '' COMMENT '紧急联系人',
  `emergency_contact_phone` varchar(50) NOT NULL DEFAULT '' COMMENT '紧急联系人手机号',
  `safe_type` varchar(50) NOT NULL DEFAULT '' COMMENT '保险类型',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `shenqing` varchar(500) NOT NULL DEFAULT '' COMMENT '申请说明',
  `descr` varchar(500) NOT NULL DEFAULT '' COMMENT '审批说明',
  `birth_day` date NOT NULL DEFAULT '0000-01-01' COMMENT '生日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人事';

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;

INSERT INTO `employee` (`id`, `status`, `name`, `phone`, `id_card`, `projects`, `projects_name`, `job_type`, `ethnic`, `entry_date`, `leave_date`, `edu_type`, `hukou_type`, `hetong`, `huji_address`, `address`, `emergency_contact`, `emergency_contact_phone`, `safe_type`, `remark`, `creater`, `create_time`, `update_time`, `shenqing`, `descr`, `birth_day`)
VALUES
	(1,1,'刘平','1111232313','120222198211106212',1,'宝坻乡镇',5,1,'2013-10-01','0000-01-01',4,2,1,'','','李四','1232020202020','','','admin','2020-05-05 17:40:47','2020-05-05 17:54:53','','','1982-11-10');

/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table income
# ------------------------------------------------------------

DROP TABLE IF EXISTS `income`;

CREATE TABLE `income` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `projects` int(10) NOT NULL DEFAULT '0',
  `projects_name` varchar(100) NOT NULL DEFAULT '',
  `income_day` date NOT NULL COMMENT '回款日期',
  `income_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '回款金额',
  `contract_numb` varchar(100) NOT NULL DEFAULT '' COMMENT '合同编号',
  `contract_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '合同金额',
  `jiafang` varchar(200) NOT NULL DEFAULT '' COMMENT '甲方',
  `jiafang_info` varchar(200) NOT NULL DEFAULT '' COMMENT '甲方联系方式',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收入管理';

LOCK TABLES `income` WRITE;
/*!40000 ALTER TABLE `income` DISABLE KEYS */;

INSERT INTO `income` (`id`, `projects`, `projects_name`, `income_day`, `income_amount`, `contract_numb`, `contract_amount`, `jiafang`, `jiafang_info`, `remark`, `creater`, `create_time`, `update_time`)
VALUES
	(1,1,'宝坻乡镇','2020-03-09',10000.00,'11111',2000000.00,'abc','130xxxxxx','测试','admin','2020-05-15 08:15:34','2020-05-15 08:24:04'),
	(2,1,'宝坻乡镇','2020-05-14',1200.50,'2222',50000.00,'','','','admin','2020-05-15 08:16:04','2020-05-15 08:16:03'),
	(3,1,'宝坻乡>镇','2020-01-17',5000.00,'',5000.50,'','','','admin','2020-05-15 08:16:26','2020-05-15 08:16:37'),
	(4,1,'宝坻乡镇','2020-05-07',1000.00,'7777',99999.00,'','','7777','admin','2020-05-15 08:42:41','2020-05-15 08:43:23');

/*!40000 ALTER TABLE `income` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pay
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pay`;

CREATE TABLE `pay` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `projects` int(10) NOT NULL DEFAULT '0' COMMENT '所属项目',
  `projects_name` varchar(50) DEFAULT '' COMMENT '项目名',
  `pay_month` date NOT NULL DEFAULT '0000-01-01' COMMENT '月份',
  `pay_status` int(10) NOT NULL DEFAULT '0' COMMENT '总发放状态；0-否；1-是',
  `total_man` int(10) NOT NULL DEFAULT '0' COMMENT '总人数',
  `total_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总工资',
  `payed_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总已发工资',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资单';

LOCK TABLES `pay` WRITE;
/*!40000 ALTER TABLE `pay` DISABLE KEYS */;

INSERT INTO `pay` (`id`, `projects`, `projects_name`, `pay_month`, `pay_status`, `total_man`, `total_amount`, `payed_amount`, `remark`, `creater`, `create_time`, `update_time`)
VALUES
	(1,2,'阜平','2020-01-01',0,2,3010.15,100.00,'1tt','admin','2020-01-21 13:03:12','2020-01-21 13:11:08'),
	(2,3,'行唐','2020-01-01',0,1,500.00,500.00,'11','admin','2020-01-26 12:30:50','2020-01-26 12:30:49');

/*!40000 ALTER TABLE `pay` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pay_detail
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pay_detail`;

CREATE TABLE `pay_detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `pay_id` int(10) NOT NULL DEFAULT '0' COMMENT '所属工资单',
  `projects` int(10) NOT NULL DEFAULT '0' COMMENT '所属项目',
  `projects_name` varchar(50) DEFAULT '' COMMENT '项目名',
  `pay_month` date NOT NULL DEFAULT '0000-01-01' COMMENT '月份',
  `emp_id` int(10) NOT NULL DEFAULT '0' COMMENT '员工id',
  `emp_name` varchar(50) DEFAULT '' COMMENT '员工名',
  `gender` int(10) NOT NULL DEFAULT '0' COMMENT '性别；0-男；1-女',
  `job_name` varchar(50) DEFAULT '' COMMENT '职务',
  `fix_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '固定工资',
  `jiaban_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '加班费',
  `jixiao_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '绩效费',
  `jiangjin_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '奖金',
  `fakuan_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '奖金',
  `total_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `payed_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '已发金额',
  `pay_status` int(10) NOT NULL DEFAULT '0' COMMENT '发放状态；0-否；1-是',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资明细';

LOCK TABLES `pay_detail` WRITE;
/*!40000 ALTER TABLE `pay_detail` DISABLE KEYS */;

INSERT INTO `pay_detail` (`id`, `pay_id`, `projects`, `projects_name`, `pay_month`, `emp_id`, `emp_name`, `gender`, `job_name`, `fix_amount`, `jiaban_amount`, `jixiao_amount`, `jiangjin_amount`, `fakuan_amount`, `total_amount`, `payed_amount`, `pay_status`, `remark`, `creater`, `create_time`, `update_time`)
VALUES
	(1,1,2,'阜平','2020-01-01',2,'司机我是',1,'司机',3000.00,30.00,0.05,0.10,50.00,2980.15,100.00,0,'','','2020-01-21 13:40:16','2020-01-23 20:11:23'),
	(2,1,2,'阜平','2020-01-01',2,'司机我是',1,'司机',0.00,20.00,10.00,0.00,0.00,30.00,0.00,0,'','admin','2020-01-23 19:34:47','2020-01-23 20:12:13'),
	(3,2,3,'行唐','2020-01-01',3,'刘德华',1,'经理',100.00,200.00,300.00,400.00,500.00,500.00,500.00,0,'','admin','2020-01-26 12:31:25','2020-01-26 12:31:45');

/*!40000 ALTER TABLE `pay_detail` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table projects
# ------------------------------------------------------------

DROP TABLE IF EXISTS `projects`;

CREATE TABLE `projects` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `area_id` int(11) NOT NULL DEFAULT '0' COMMENT '父id',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目';

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;

INSERT INTO `projects` (`id`, `area_id`, `name`, `remark`, `creater`, `create_time`, `update_time`)
VALUES
	(1,1,'宝坻乡镇','','admin','2020-05-05 17:38:10','2020-05-05 17:38:10'),
	(2,1,'宝坻城区扫保','','admin','2020-05-15 14:21:26','2020-05-15 15:14:40'),
	(3,1,'宝坻城区钰\n华','','admin','2020-05-15 14:21:46','2020-05-15 15:15:47'),
	(4,1,'宝坻城区宝平','','admin','2020-05-15 14:29:01','2020-05-15 15:16:24'),
	(5,1,'宝坻城区公厕','','admin','2020-05-15 15:16:48','2020-05-15 15:16:47'),
	(6,1,'宝坻城区套袋','','admin','2020-05-15 15:17:47','2020-05-15 15:17:46'),
	(7,1,'河西1期','','admin','2020-05-15 15:20:45','2020-05-15 15:20:45'),
	(8,1,'河西2期','','admin','2020-05-15 15:21:01','2020-05-15 15:21:01'),
	(9,1,'红桥项目','','admin','2020-05-15 15:21:24','2020-05-15 15:21:23'),
	(10,1,'宁河项目','','admin','2020-05-15 15:21:42','2020-05-15 15:21:42'),
	(11,2,'唐山分公司','','admin','2020-05-15 15:22:25','2020-05-15 15:22:24'),
	(12,2,'行唐分公司','','admin','2020-05-15 15:22:43','2020-05-15 15:22:43'),
	(13,2,'冠润分公司','','admin','2020-05-15 15:23:03','2020-05-15 15:23:03'),
	(14,2,'正定分公司','','admin','2020-05-15 15:23:22','2020-05-15 15:23:21'),
	(15,2,'晋州分公司','','admin','2020-05-15 15:24:12','2020-05-15 15:24:11'),
	(16,2,'武安分公司','','admin','2020-05-15 15:24:47','2020-05-15 15:24:46'),
	(17,2,'景县分公司','','admin','2020-05-15 15:25:02','2020-05-15 15:25:02'),
	(18,2,'吴桥分公司','','admin','2020-05-15 15:25:19','2020-05-15 15:25:19'),
	(19,2,'阜平分公司','','admin','2020-05-15 15:25:46','2020-05-15 15:25:46'),
	(20,3,'漯河\n分公司','','admin','2020-05-15 15:26:08','2020-05-15 15:26:07'),
	(21,3,'信阳分公司','','admin','2020-05-15 15:26:22','2020-05-15 15:26:22'),
	(22,3,'淅川分公司','','admin','2020-05-15 15:26:42','2020-05-15 15:26:42');

/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_logs
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_logs`;

CREATE TABLE `sys_logs` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `operator_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operator` varchar(50) DEFAULT NULL COMMENT '操作人',
  `description` varchar(5000) DEFAULT NULL COMMENT '操作内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

LOCK TABLES `sys_logs` WRITE;
/*!40000 ALTER TABLE `sys_logs` DISABLE KEYS */;

INSERT INTO `sys_logs` (`id`, `operator_time`, `operator`, `description`)
VALUES
	(6,'2019-12-19 08:23:11','admin','用户登录'),
	(7,'2019-12-19 08:25:54','admin','用户登录'),
	(8,'2019-12-19 08:27:48','admin','新增或修改区域:AreaDO(id=0, name=入门语法, remark=qwesafer为而为, creater=admin, createTime=Thu Dec 19 08:27:48 CST 2019, updateTime=Thu Dec 19 08:27:48 CST 2019)'),
	(9,'2019-12-19 08:28:06','admin','新增或修改区域:AreaDO(id=1, name=入门语法, remark=qwesafer为而为情趣, creater=admin, createTime=Thu Dec 19 08:28:05 CST 2019, updateTime=Thu Dec 19 08:28:05 CST 2019)'),
	(10,'2019-12-19 12:59:19','admin','用户登录'),
	(11,'2019-12-19 12:59:54','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,105,104'),
	(12,'2019-12-19 13:00:03','admin','用户登录'),
	(13,'2019-12-19 13:00:20','admin','新增或修改区域:AreaDO(id=0, name=ddd, remark=df, creater=admin, createTime=Thu Dec 19 13:00:20 CST 2019, updateTime=null)'),
	(14,'2019-12-19 13:00:28',NULL,'新增或修改区域:AreaDO(id=2, name=dddf, remark=dfdf, creater=null, createTime=null, updateTime=Thu Dec 19 13:00:27 CST 2019)'),
	(15,'2019-12-19 13:17:28','admin','用户登录'),
	(16,'2019-12-19 13:19:42','admin','用户登录'),
	(17,'2019-12-19 13:20:04','admin','新增或修改项目:ProjectsDO(id=0, areaId=1, name=123, areaName=null, remark=, creater=admin, createTime=Thu Dec 19 13:20:03 CST 2019, updateTime=null)'),
	(18,'2019-12-19 13:22:39','admin','用户登录'),
	(19,'2019-12-19 13:25:17','admin','用户登录'),
	(20,'2019-12-19 13:25:33',NULL,'新增或修改项目:ProjectsDO(id=1, areaId=2, name=123, areaName=null, remark=zz, creater=null, createTime=null, updateTime=Thu Dec 19 13:25:32 CST 2019)'),
	(21,'2019-12-19 13:27:26','admin','用户登录'),
	(22,'2019-12-19 13:27:41',NULL,'新增或修改项目:ProjectsDO(id=1, areaId=1, name=123, areaName=null, remark=zz你好, creater=null, createTime=null, updateTime=Thu Dec 19 13:27:40 CST 2019)'),
	(23,'2019-12-19 22:26:35','admin','用户登录'),
	(24,'2019-12-19 22:26:50',NULL,'新增或修改项目:ProjectsDO(id=1, areaId=1, name=123, areaName=null, remark=zz你好1, creater=null, createTime=null, updateTime=Thu Dec 19 22:26:49 CST 2019)'),
	(25,'2019-12-19 22:51:36','admin','用户登录'),
	(26,'2019-12-19 22:51:59','admin','新增或修改系统用户:SysMember(id=1, name=admin, imgs=null, valid=0, pwd=null, showName=管理员, roleId=1, phone=123, email=admin@edu.com.cn, projectsId=1, areaId=0, priUrls=null, roleName=null, roleIntor=null)'),
	(27,'2019-12-19 22:58:48','admin','用户登录'),
	(28,'2019-12-19 23:03:30','admin','用户登录'),
	(29,'2019-12-19 23:07:32','admin','新增或修改区域:AreaDO(id=0, name=河北, remark=, creater=admin, createTime=Thu Dec 19 23:07:32 CST 2019, updateTime=null)'),
	(30,'2019-12-19 23:07:41','admin','新增或修改区域:AreaDO(id=0, name=河南, remark=, creater=admin, createTime=Thu Dec 19 23:07:40 CST 2019, updateTime=null)'),
	(31,'2019-12-19 23:07:47','admin','新增或修改区域:AreaDO(id=0, name=天津, remark=, creater=admin, createTime=Thu Dec 19 23:07:47 CST 2019, updateTime=null)'),
	(32,'2019-12-19 23:08:03','admin','新增或修改项目:ProjectsDO(id=0, areaId=3, name=阜平, areaName=null, remark=, creater=admin, createTime=Thu Dec 19 23:08:03 CST 2019, updateTime=null)'),
	(33,'2019-12-19 23:08:16','admin','新增或修改项目:ProjectsDO(id=0, areaId=3, name=行唐, areaName=null, remark=, creater=admin, createTime=Thu Dec 19 23:08:15 CST 2019, updateTime=null)'),
	(34,'2019-12-19 23:08:44',NULL,'新增或修改项目:ProjectsDO(id=1, areaId=3, name=保定, areaName=null, remark=zz你好1, creater=null, createTime=null, updateTime=Thu Dec 19 23:08:43 CST 2019)'),
	(35,'2019-12-19 23:09:43','admin','新增或修改系统用户:SysMember(id=1, name=admin, imgs=null, valid=0, pwd=null, showName=管理员, roleId=1, phone=123, email=admin@edu.com.cn, projectsId=2, areaId=0, priUrls=null, roleName=null, roleIntor=null, projectsName=null)'),
	(36,'2019-12-19 23:10:20','admin','保存角色，result : true;role：SysRole(id=0, name=运营部, intro=)'),
	(37,'2019-12-19 23:10:43','admin','设置角色权限，角色：2;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16'),
	(38,'2019-12-19 23:16:51','admin','用户登录'),
	(39,'2019-12-19 23:17:26',NULL,'新增或修改区域:AreaDO(id=2, name=四川, remark=dfdf, creater=null, createTime=null, updateTime=Thu Dec 19 23:17:25 CST 2019)'),
	(40,'2019-12-19 23:17:36',NULL,'新增或修改区域:AreaDO(id=1, name=广州, remark=qwesafer为而为情趣, creater=null, createTime=null, updateTime=Thu Dec 19 23:17:36 CST 2019)'),
	(41,'2019-12-19 23:21:24','admin','新增或修改系统用户:SysMember(id=0, name=zhangsan, imgs=null, valid=0, pwd=202cb962ac59075b964b07152d234b70, showName=张三, roleId=1, phone=, email=admin, projectsId=1, areaId=0, priUrls=null, roleName=null, roleIntor=null, projectsName=null)'),
	(42,'2019-12-19 23:21:34','zhangsan','用户登录'),
	(43,'2019-12-19 23:25:34',NULL,'新增或修改区域:AreaDO(id=2, name=四川, remark=dfdf, creater=null, createTime=null, updateTime=Thu Dec 19 23:25:33 CST 2019)'),
	(44,'2019-12-21 22:22:03','admin','用户登录'),
	(45,'2019-12-21 22:31:27','admin','用户登录'),
	(46,'2019-12-21 22:49:29','admin','用户登录'),
	(47,'2019-12-21 22:55:49','admin','用户登录'),
	(48,'2019-12-21 23:10:15','admin','用户登录'),
	(49,'2019-12-26 07:44:53','admin','用户登录'),
	(50,'2019-12-26 08:27:25','admin','用户登录'),
	(51,'2019-12-26 08:53:28','admin','用户登录'),
	(52,'2019-12-26 08:58:42','admin','用户登录'),
	(53,'2019-12-26 09:05:23','admin','用户登录'),
	(54,'2019-12-26 13:21:04','admin','用户登录'),
	(55,'2019-12-27 08:01:33','admin','用户登录'),
	(56,'2019-12-27 08:03:10','admin','用户登录'),
	(57,'2019-12-27 08:04:30','admin','用户登录'),
	(58,'2019-12-27 08:06:04','admin','用户登录'),
	(59,'2019-12-27 08:09:11','admin','用户登录'),
	(60,'2019-12-27 08:11:07','admin','用户登录'),
	(61,'2019-12-27 08:18:24','admin','用户登录'),
	(62,'2019-12-27 08:27:55','admin','用户登录'),
	(63,'2019-12-27 08:32:08','admin','用户登录'),
	(64,'2019-12-27 08:36:00','admin','用户登录'),
	(65,'2019-12-27 08:38:33','admin','用户登录'),
	(66,'2019-12-27 08:43:26','admin','用户登录'),
	(67,'2019-12-27 08:47:22','admin','用户登录'),
	(68,'2019-12-27 12:59:25','admin','用户登录'),
	(69,'2019-12-27 13:03:01','admin','用户登录'),
	(70,'2019-12-27 13:06:53','admin','用户登录'),
	(71,'2019-12-27 19:37:57','admin','用户登录'),
	(72,'2019-12-27 19:50:21','admin','用户登录'),
	(73,'2019-12-27 19:58:34','admin','用户登录'),
	(74,'2019-12-27 20:01:45','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=31, name=资产管理, url=initAssets.do)'),
	(75,'2019-12-27 20:02:12','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=33, name=新增资产, url=initYYCostEdit.do)'),
	(76,'2019-12-27 20:02:34','admin','保存权限记录，result : true;privilege：SysPrivilege(id=107, menuId=33, name=编辑资产, url=initYYCostEdit.do)'),
	(77,'2019-12-27 20:02:54','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=34, name=资产汇总, url=initYYCost.do)'),
	(78,'2019-12-27 20:04:09','admin','保存权限记录，result : true;privilege：SysPrivilege(id=106, menuId=31, name=编辑资产, url=initAssetsEdit.do)'),
	(79,'2019-12-27 20:04:29','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=32, name=资产信息, url=initAssets.do)'),
	(80,'2019-12-27 20:04:51','admin','保存权限记录，result : true;privilege：SysPrivilege(id=107, menuId=33, name=编辑消费, url=initYYCostEdit.do)'),
	(81,'2019-12-27 20:05:13','admin','保存权限记录，result : true;privilege：SysPrivilege(id=108, menuId=34, name=消费汇总, url=initYYCost.do)'),
	(82,'2019-12-27 20:05:38','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,105,104,106,109,107,108'),
	(83,'2019-12-27 20:06:00','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=41, name=录入收支, url=initFinanceEdit.do)'),
	(84,'2019-12-27 20:06:19','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=42, name=收支清单, url=initFinance.do)'),
	(85,'2019-12-27 20:06:23','admin','用户登录'),
	(86,'2019-12-27 20:06:50','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,105,104,106,109,107,108,110,111'),
	(87,'2019-12-27 20:06:55','admin','用户登录'),
	(88,'2019-12-27 20:07:59',NULL,'新增或修改区域:AreaDO(id=2, name=四川, remark=, creater=null, createTime=null, updateTime=Fri Dec 27 20:07:58 CST 2019)'),
	(89,'2019-12-27 20:08:12',NULL,'新增或修改区域:AreaDO(id=1, name=广州, remark=你好，测试数据, creater=null, createTime=null, updateTime=Fri Dec 27 20:08:11 CST 2019)'),
	(90,'2019-12-27 20:38:18','admin','用户登录'),
	(91,'2019-12-27 20:45:20','admin','用户登录'),
	(92,'2019-12-27 20:56:24','admin','用户登录'),
	(93,'2019-12-27 20:57:21','admin','用户登录'),
	(94,'2019-12-27 21:07:01','admin','用户登录'),
	(95,'2019-12-27 21:26:10','admin','用户登录'),
	(96,'2019-12-27 23:03:06','admin','用户登录'),
	(97,'2019-12-27 23:39:04','admin','用户登录'),
	(98,'2019-12-28 00:13:01','admin','用户登录'),
	(99,'2019-12-28 09:06:55','admin','用户登录'),
	(100,'2019-12-28 09:09:04','admin','用户登录'),
	(101,'2019-12-28 09:16:22','admin','用户登录'),
	(102,'2019-12-28 09:23:12','admin','用户登录'),
	(103,'2019-12-28 09:26:28','admin','用户登录'),
	(104,'2019-12-28 09:31:05','admin','用户登录'),
	(105,'2019-12-28 09:36:36',NULL,'新增或修改员工:EmployeeDO(id=1, status=null, name=张三, projectsName=阜平, phone=15010166787, idCard=sdfdsf, projects=2, jobType=1, gender=0, ethnic=0, age=null, birthDate=Sat Dec 28 00:00:00 CST 2019, entryDate=Sat Dec 28 00:00:00 CST 2019, leaveDate=Sat Dec 28 00:00:00 CST 2019, eduType=0, hukouType=0, hujiAddress=, address=, emergencyContact=, emergencyContactPhone=, safeType=, remark=, creater=null, createTime=null, updateTime=Sat Dec 28 09:36:31 CST 2019)'),
	(106,'2019-12-28 09:41:37',NULL,'新增或修改员工:EmployeeDO(id=1, status=null, name=张三, projectsName=阜平, phone=15010166787, idCard=sdfdsf, projects=2, jobType=1, gender=0, ethnic=2, age=null, birthDate=Sat Dec 28 00:00:00 CST 2019, entryDate=Wed Dec 25 00:00:00 CST 2019, leaveDate=Mon Dec 16 00:00:00 CST 2019, eduType=0, hukouType=0, hujiAddress=, address=, emergencyContact=, emergencyContactPhone=, safeType=, remark=, creater=null, createTime=null, updateTime=Sat Dec 28 09:41:37 CST 2019)'),
	(107,'2019-12-28 09:43:17','admin','用户登录'),
	(108,'2019-12-28 09:43:45',NULL,'新增或修改员工:EmployeeDO(id=1, status=null, name=张三, projectsName=阜平, phone=15010166787, idCard=sdfdsf, projects=2, jobType=1, gender=0, ethnic=2, age=null, birthDate=Sat Dec 28 00:00:00 CST 2019, entryDate=Wed Dec 25 00:00:00 CST 2019, leaveDate=Mon Dec 16 00:00:00 CST 2019, eduType=2, hukouType=2, hujiAddress=, address=, emergencyContact=, emergencyContactPhone=, safeType=, remark=, creater=null, createTime=null, updateTime=Sat Dec 28 09:43:45 CST 2019)'),
	(109,'2019-12-28 09:44:12',NULL,'新增或修改员工:EmployeeDO(id=1, status=null, name=张三, projectsName=阜平, phone=15010166787, idCard=sdfdsf, projects=2, jobType=1, gender=0, ethnic=2, age=null, birthDate=Sat Dec 28 00:00:00 CST 2019, entryDate=Wed Dec 25 00:00:00 CST 2019, leaveDate=Mon Dec 16 00:00:00 CST 2019, eduType=2, hukouType=2, hujiAddress=嗷嗷, address=等等, emergencyContact=第三方, emergencyContactPhone=豆腐, safeType=沈德符, remark=豆腐, creater=null, createTime=null, updateTime=Sat Dec 28 09:44:12 CST 2019)'),
	(110,'2019-12-28 09:44:23',NULL,'新增或修改员工:EmployeeDO(id=1, status=null, name=张三, projectsName=阜平, phone=15010166787, idCard=sdfdsf, projects=2, jobType=1, gender=1, ethnic=2, age=null, birthDate=Sat Dec 28 00:00:00 CST 2019, entryDate=Wed Dec 25 00:00:00 CST 2019, leaveDate=Mon Dec 16 00:00:00 CST 2019, eduType=2, hukouType=2, hujiAddress=嗷嗷, address=等等, emergencyContact=第三方, emergencyContactPhone=豆腐, safeType=沈德符, remark=豆腐, creater=null, createTime=null, updateTime=Sat Dec 28 09:44:23 CST 2019)'),
	(111,'2019-12-28 09:52:21','admin','用户登录'),
	(112,'2019-12-28 09:52:41',NULL,'新增或修改员工:EmployeeDO(id=1, status=null, name=张三, projectsName=阜平, phone=15010166787, idCard=sdfdsf, projects=2, jobType=1, gender=1, ethnic=2, age=null, birthDate=Fri Dec 06 00:00:00 CST 2019, entryDate=Sat Dec 07 00:00:00 CST 2019, leaveDate=Sun Dec 08 00:00:00 CST 2019, eduType=2, hukouType=2, hujiAddress=嗷嗷, address=等等, emergencyContact=第三方, emergencyContactPhone=豆腐, safeType=沈德符, remark=豆腐, creater=null, createTime=null, updateTime=Sat Dec 28 09:52:41 CST 2019)'),
	(113,'2019-12-28 09:52:56',NULL,'新增或修改员工:EmployeeDO(id=1, status=null, name=张三, projectsName=阜平, phone=15010166787, idCard=sdfdsf, projects=2, jobType=1, gender=1, ethnic=2, age=null, birthDate=Wed Dec 04 00:00:00 CST 2019, entryDate=Thu Dec 05 00:00:00 CST 2019, leaveDate=Tue Dec 03 00:00:00 CST 2019, eduType=2, hukouType=2, hujiAddress=嗷嗷, address=等等, emergencyContact=第三方, emergencyContactPhone=豆腐, safeType=沈德符, remark=豆腐, creater=null, createTime=null, updateTime=Sat Dec 28 09:52:55 CST 2019)'),
	(114,'2019-12-28 10:00:24','admin','用户登录'),
	(115,'2019-12-28 10:01:16','admin','用户登录'),
	(116,'2019-12-28 10:04:36','admin','用户登录'),
	(117,'2019-12-28 10:08:09','admin','用户登录'),
	(118,'2019-12-28 10:10:37','admin','用户登录'),
	(119,'2019-12-28 10:15:14','admin','用户登录'),
	(120,'2019-12-28 10:18:29','admin','用户登录'),
	(121,'2019-12-28 10:28:17','admin','用户登录'),
	(122,'2019-12-28 10:28:53','admin','用户登录'),
	(123,'2019-12-28 10:31:22','admin','用户登录'),
	(124,'2019-12-28 10:33:02','admin','用户登录'),
	(125,'2019-12-28 10:35:07','admin','用户登录'),
	(126,'2019-12-28 10:38:04','admin','用户登录'),
	(127,'2019-12-28 10:41:02','admin','用户登录'),
	(128,'2019-12-28 10:44:00','admin','用户登录'),
	(129,'2019-12-28 10:45:51','admin','用户登录'),
	(130,'2019-12-28 10:49:48','admin','用户登录'),
	(131,'2019-12-28 10:52:30','admin','用户登录'),
	(132,'2019-12-28 10:55:10','admin','用户登录'),
	(133,'2019-12-28 10:56:38','admin','用户登录'),
	(134,'2019-12-28 10:59:52','admin','用户登录'),
	(135,'2019-12-28 11:02:32','admin','用户登录'),
	(136,'2019-12-28 11:04:05','admin','用户登录'),
	(137,'2019-12-28 11:04:18',NULL,'新增或修改员工:EmployeeDO(id=1, status=null, name=张三, projectsName=阜平, phone=15010166787, idCard=sdfdsf, projects=2, jobType=1, jobTypeStr=经理, gender=1, genderStr=女, ethnic=2, age=null, birthDate=Thu Dec 05 00:00:00 CST 2019, entryDate=Thu Dec 05 00:00:00 CST 2019, leaveDate=Tue Dec 03 00:00:00 CST 2019, eduType=2, hukouType=2, hujiAddress=嗷嗷, address=等等, emergencyContact=第三方, emergencyContactPhone=豆腐, safeType=沈德符, remark=豆腐, creater=null, createTime=null, updateTime=Sat Dec 28 11:04:17 CST 2019)'),
	(138,'2019-12-28 11:20:18','admin','用户登录'),
	(139,'2019-12-28 11:22:26','admin','用户登录'),
	(140,'2019-12-28 13:26:33','admin','用户登录'),
	(141,'2019-12-28 13:26:51',NULL,'新增或修改员工:EmployeeDO(id=1, status=null, name=张三, projectsName=阜平, phone=15010166787, idCard=sdfdsf, projects=2, jobType=1, jobTypeStr=经理, gender=1, genderStr=女, ethnic=2, age=null, birthDate=Thu Dec 05 00:00:00 CST 2019, entryDate=Thu Dec 05 00:00:00 CST 2019, leaveDate=Tue Dec 03 00:00:00 CST 2019, eduType=2, hukouType=2, hujiAddress=嗷嗷, address=等等, emergencyContact=第三方, emergencyContactPhone=豆腐, safeType=沈德符, remark=豆腐, creater=null, createTime=null, updateTime=Sat Dec 28 13:26:50 CST 2019)'),
	(142,'2019-12-28 13:47:06','admin','用户登录'),
	(143,'2019-12-28 13:49:23',NULL,'新增或修改资产信息:AssetsDO(id=1, projects=2, type=1, typeStr=车辆, name=小三轮, projectsName=阜平, number=123, license=A-120, brand=宝马, carType=A111, buyTime=Sat Dec 28 00:00:00 CST 2019, inOut=第三方的身份, quantity=0, quoQuantity=0, owner=1, storageLocation=, engineNumber=, engineNumberType=0, price=0.00, staging=0, purTax=0.00, remark=, creater=null, createTime=null, updateTime=Sat Dec 28 13:49:22 CST 2019)'),
	(144,'2019-12-28 13:49:23',NULL,'新增或修改资产信息:AssetsDO(id=1, projects=2, type=1, typeStr=车辆, name=小三轮, projectsName=阜平, number=123, license=A-120, brand=宝马, carType=A111, buyTime=Sat Dec 28 00:00:00 CST 2019, inOut=第三方的身份, quantity=0, quoQuantity=0, owner=1, storageLocation=, engineNumber=, engineNumberType=0, price=0.00, staging=0, purTax=0.00, remark=, creater=null, createTime=null, updateTime=Sat Dec 28 13:49:22 CST 2019)'),
	(145,'2019-12-28 13:51:30','admin','用户登录'),
	(146,'2019-12-28 14:01:37',NULL,'新增或修改资产信息:AssetsDO(id=1, projects=2, type=1, typeStr=车辆, name=小三轮, projectsName=阜平, number=123, license=A-120, brand=宝马, carType=A111, buyTime=Sat Dec 28 00:00:00 CST 2019, inOut=第三方的身份, quantity=0, quoQuantity=0, owner=1, storageLocation=, engineNumber=, engineNumberType=0, price=0.00, staging=0, purTax=0.00, remark=, creater=null, createTime=null, updateTime=Sat Dec 28 14:01:37 CST 2019)'),
	(147,'2019-12-28 14:03:04','admin','用户登录'),
	(148,'2019-12-28 14:03:31',NULL,'新增或修改资产信息:AssetsDO(id=1, projects=2, type=1, typeStr=车辆, name=小三轮, projectsName=阜平, number=123, license=A-120, brand=宝马, carType=A111, buyTime=Sat Dec 28 00:00:00 CST 2019, inOut=第三方的身份, quantity=0, quoQuantity=0, owner=1, storageLocation=, engineNumber=, engineNumberType=0, price=0.00, staging=0, purTax=0.00, remark=, creater=null, createTime=null, updateTime=Sat Dec 28 14:03:30 CST 2019)'),
	(149,'2019-12-28 14:04:17',NULL,'新增或修改资产信息:AssetsDO(id=1, projects=2, type=1, typeStr=车辆, name=小三轮, projectsName=阜平, number=123, license=A-120, brand=宝马, carType=A111, buyTime=Sat Dec 28 00:00:00 CST 2019, inOut=第三方的身份, quantity=10, quoQuantity=0, owner=1, storageLocation=啊啊啊啊, engineNumber=点点点, engineNumberType=1, price=-1, staging=0, purTax=0.00, remark=, creater=null, createTime=null, updateTime=Sat Dec 28 14:04:17 CST 2019)'),
	(150,'2019-12-28 14:05:59',NULL,'新增或修改资产信息:AssetsDO(id=1, projects=2, type=1, typeStr=车辆, name=小三轮, projectsName=阜平, number=123, license=A-120, brand=宝马, carType=A111, buyTime=Sat Dec 28 00:00:00 CST 2019, inOut=第三方的身份, quantity=10, quoQuantity=0, owner=1, storageLocation=啊啊啊啊, engineNumber=点点点, engineNumberType=2, price=-1.00, staging=0, purTax=0.00, remark=, creater=null, createTime=null, updateTime=Sat Dec 28 14:05:58 CST 2019)'),
	(151,'2019-12-28 14:06:27',NULL,'新增或修改资产信息:AssetsDO(id=1, projects=2, type=1, typeStr=车辆, name=小三轮, projectsName=阜平, number=123, license=A-120, brand=宝马, carType=A111, buyTime=Sat Dec 28 00:00:00 CST 2019, inOut=第三方的身份, quantity=10, quoQuantity=0, owner=1, storageLocation=啊啊啊啊, engineNumber=点点点, engineNumberType=2, price=-1.00, staging=0, purTax=0.00, remark=的点点滴滴, creater=null, createTime=null, updateTime=Sat Dec 28 14:06:26 CST 2019)'),
	(152,'2019-12-28 14:07:42','admin','用户登录'),
	(153,'2019-12-28 14:08:33',NULL,'新增或修改资产信息:AssetsDO(id=1, projects=2, type=1, typeStr=车辆, name=小三轮, projectsName=阜平, number=123, license=A-120, brand=宝马, carType=A111, buyTime=Sat Dec 28 00:00:00 CST 2019, inOut=第三方的身份, quantity=10, quoQuantity=0, owner=1, storageLocation=啊啊啊啊, engineNumber=点点点, engineNumberType=2, price=6000.60, staging=0, purTax=0.00, remark=的点点滴滴, creater=null, createTime=null, updateTime=Sat Dec 28 14:08:33 CST 2019)'),
	(154,'2019-12-28 14:08:48',NULL,'新增或修改资产信息:AssetsDO(id=1, projects=2, type=1, typeStr=车辆, name=小三轮, projectsName=阜平, number=123, license=A-120, brand=宝马, carType=A111, buyTime=Sat Dec 28 00:00:00 CST 2019, inOut=第三方的身份, quantity=10, quoQuantity=0, owner=1, storageLocation=啊啊啊啊, engineNumber=点点点, engineNumberType=2, price=6000.60, staging=0, purTax=-40, remark=的点点滴滴, creater=null, createTime=null, updateTime=Sat Dec 28 14:08:48 CST 2019)'),
	(155,'2019-12-28 14:08:58',NULL,'新增或修改资产信息:AssetsDO(id=1, projects=2, type=1, typeStr=车辆, name=小三轮, projectsName=阜平, number=123, license=A-120, brand=宝马, carType=A111, buyTime=Sat Dec 28 00:00:00 CST 2019, inOut=第三方的身份, quantity=10, quoQuantity=0, owner=1, storageLocation=啊啊啊啊, engineNumber=点点点, engineNumberType=2, price=-6000.60, staging=0, purTax=-40.00, remark=的点点滴滴, creater=null, createTime=null, updateTime=Sat Dec 28 14:08:57 CST 2019)'),
	(156,'2019-12-28 14:10:39','admin','用户登录'),
	(157,'2019-12-28 14:11:16',NULL,'新增或修改资产信息:AssetsDO(id=1, projects=2, type=1, typeStr=车辆, name=小三轮, projectsName=阜平, number=123, license=A-120, brand=宝马, carType=A111, buyTime=Sat Dec 28 00:00:00 CST 2019, inOut=第三方的身份, quantity=10, quoQuantity=0, owner=1, storageLocation=啊啊啊啊, engineNumber=点点点, engineNumberType=2, price=6000.88, staging=0, purTax=40.88, remark=的点点滴滴, creater=null, createTime=null, updateTime=Sat Dec 28 14:11:16 CST 2019)'),
	(158,'2019-12-28 14:18:34','admin','用户登录'),
	(159,'2019-12-28 14:22:21','admin','用户登录'),
	(160,'2019-12-28 14:22:56','admin','用户登录'),
	(161,'2019-12-28 14:25:23','admin','用户登录'),
	(162,'2019-12-28 14:56:33','admin','用户登录'),
	(163,'2019-12-28 14:56:48',NULL,'新增或修改员工:EmployeeDO(id=1, status=null, name=张三1, projectsName=阜平, phone=15010166787, idCard=sdfdsf, projects=2, jobType=1, jobTypeStr=经理, gender=1, genderStr=女, ethnic=2, age=null, birthDate=Thu Dec 05 00:00:00 CST 2019, entryDate=Thu Dec 05 00:00:00 CST 2019, leaveDate=Tue Dec 03 00:00:00 CST 2019, eduType=2, hukouType=2, hujiAddress=嗷嗷, address=等等, emergencyContact=第三方, emergencyContactPhone=豆腐, safeType=沈德符, remark=豆腐, creater=null, createTime=null, updateTime=Sat Dec 28 14:56:47 CST 2019)'),
	(164,'2019-12-28 14:57:44',NULL,'新增或修改资产信息:AssetsDO(id=1, projects=2, type=1, typeStr=车辆, name=小三轮1, projectsName=阜平, number=123, license=A-120, brand=宝马, carType=A111, buyTime=Sat Dec 28 00:00:00 CST 2019, inOut=第三方的身份, quantity=10, quoQuantity=0, owner=1, storageLocation=啊啊啊啊, engineNumber=点点点, engineNumberType=2, price=6000.88, staging=0, purTax=40.88, remark=的点点滴滴, creater=null, createTime=null, updateTime=Sat Dec 28 14:57:43 CST 2019)'),
	(165,'2019-12-28 15:25:43','admin','用户登录'),
	(166,'2019-12-28 19:20:12','admin','用户登录'),
	(167,'2019-12-28 19:22:20','admin','用户登录'),
	(168,'2019-12-28 19:26:39','admin','用户登录'),
	(169,'2019-12-28 19:27:13','admin','用户登录'),
	(170,'2019-12-28 19:30:55','admin','用户登录'),
	(171,'2019-12-28 19:39:51','admin','用户登录'),
	(172,'2019-12-28 19:44:20','admin','用户登录'),
	(173,'2019-12-28 19:49:19','admin','用户登录'),
	(174,'2019-12-28 19:53:47','admin','用户登录'),
	(175,'2019-12-28 19:55:39','admin','用户登录'),
	(176,'2019-12-28 20:01:43','admin','用户登录'),
	(177,'2019-12-28 20:03:53','admin','用户登录'),
	(178,'2019-12-28 20:23:35','admin','用户登录'),
	(179,'2019-12-28 20:24:31','admin','用户登录'),
	(180,'2019-12-28 20:29:41','admin','用户登录'),
	(181,'2019-12-28 20:37:45','admin','用户登录'),
	(182,'2019-12-28 20:38:11','admin','新增或修改收支:FinanceDO(id=0, projects=2, projectsName=阜平, incomeAmount=1200.50, payoutAmount=600.50, profit=600.00, upDay=Sun Dec 01 00:00:00 CST 2019, remark=sdf , creater=admin, createTime=Sat Dec 28 20:38:10 CST 2019, updateTime=null)'),
	(183,'2019-12-28 20:41:58','admin','用户登录'),
	(184,'2019-12-29 09:13:38','admin','用户登录'),
	(185,'2019-12-29 09:19:25','admin','用户登录'),
	(186,'2019-12-29 09:20:03','admin','用户登录'),
	(187,'2019-12-29 09:23:12','admin','用户登录'),
	(188,'2019-12-29 09:26:44','admin','用户登录'),
	(189,'2019-12-29 09:32:10','admin','用户登录'),
	(190,'2019-12-29 09:36:00','admin','用户登录'),
	(191,'2019-12-29 09:58:04','admin','用户登录'),
	(192,'2019-12-29 10:00:30','admin','用户登录'),
	(193,'2019-12-29 10:01:10','admin','用户登录'),
	(194,'2019-12-29 10:46:42','admin','用户登录'),
	(195,'2019-12-29 11:07:31','admin','用户登录'),
	(196,'2019-12-29 13:38:45','admin','用户登录'),
	(197,'2019-12-29 13:40:28','admin','新增或修改项目:ProjectsDO(id=0, areaId=0, name=null, areaName=null, remark=, creater=admin, createTime=Sun Dec 29 13:40:27 CST 2019, updateTime=null)'),
	(198,'2019-12-29 14:05:12','admin','用户登录'),
	(199,'2019-12-29 14:05:32','admin','新增或修改区域:YYCostDO(id=0, enterDate=Sun Dec 29 00:00:00 CST 2019, assetId=null, employeeId=null, employeeName=null, status=null, projects=null, projectsName=null, startMileage=0, endMileage=0, workload=0, fuel=0, fuelAmount=0, baoyangAmount=0, fixAmount=0, fixFactory=, shiguTimes=0, shiguAmount=0, shiguOutAmount=0, baoxianAmount=0, yearCheckAmount=0, remark=, creater=admin, createTime=Sun Dec 29 14:05:32 CST 2019, updateTime=null)'),
	(200,'2019-12-29 14:19:02','admin','用户登录'),
	(201,'2019-12-29 14:19:41',NULL,'新增或修改员工:EmployeeDO(id=1, status=null, name=张三1, projectsName=阜平, phone=15010166787, idCard=sdfdsf, projects=2, jobType=6, jobTypeStr=司机, gender=0, genderStr=男, ethnic=2, age=null, birthDate=Thu Dec 05 00:00:00 CST 2019, entryDate=Thu Dec 05 00:00:00 CST 2019, leaveDate=Tue Dec 03 00:00:00 CST 2019, eduType=2, hukouType=2, hujiAddress=嗷嗷, address=等等, emergencyContact=第三方, emergencyContactPhone=豆腐, safeType=沈德符, remark=豆腐, creater=null, createTime=null, updateTime=Sun Dec 29 14:19:40 CST 2019)'),
	(202,'2019-12-29 14:19:58','admin','新增或修改区域:YYCostDO(id=0, enterDate=Sun Dec 29 00:00:00 CST 2019, assetId=1, employeeId=null, employeeName=null, status=null, projects=2, projectsName=阜平, startMileage=0, endMileage=0, workload=0, fuel=0, fuelAmount=0, baoyangAmount=0, fixAmount=0, fixFactory=, shiguTimes=0, shiguAmount=0, shiguOutAmount=0, baoxianAmount=0, yearCheckAmount=0, remark=, creater=admin, createTime=Sun Dec 29 14:19:58 CST 2019, updateTime=null)'),
	(203,'2019-12-29 14:31:26','admin','用户登录'),
	(204,'2019-12-29 14:31:41',NULL,'新增或修改区域:YYCostDO(id=2, enterDate=Sun Dec 29 00:00:00 CST 2019, assetId=1, employeeId=1, employeeName=张三1, status=null, projects=2, projectsName=阜平, startMileage=0, endMileage=0, workload=0, fuel=0, fuelAmount=0.00, baoyangAmount=0.00, fixAmount=0.00, fixFactory=, shiguTimes=0, shiguAmount=0.00, shiguOutAmount=0.00, baoxianAmount=0.00, yearCheckAmount=0.00, remark=, creater=null, createTime=null, updateTime=Sun Dec 29 14:31:41 CST 2019, assetsInfo=null)'),
	(205,'2019-12-29 14:38:22',NULL,'新增或修改区域:YYCostDO(id=2, enterDate=Sun Dec 29 00:00:00 CST 2019, assetId=1, employeeId=1, employeeName=张三1, status=null, projects=2, projectsName=阜平, startMileage=0, endMileage=0, workload=0, fuel=0, fuelAmount=0.00, baoyangAmount=0.00, fixAmount=0.00, fixFactory=, shiguTimes=0, shiguAmount=0.00, shiguOutAmount=0.00, baoxianAmount=0.00, yearCheckAmount=0.00, remark=, creater=null, createTime=null, updateTime=Sun Dec 29 14:38:21 CST 2019, assetsInfo=null)'),
	(206,'2019-12-29 14:41:11','admin','用户登录'),
	(207,'2019-12-29 14:42:14','admin','新增或修改区域:YYCostDO(id=0, enterDate=Sun Dec 29 00:00:00 CST 2019, assetId=1, employeeId=1, employeeName=张三1, status=null, projects=2, projectsName=阜平, startMileage=1, endMileage=2, workload=3, fuel=4, fuelAmount=5, baoyangAmount=6, fixAmount=7, fixFactory=8, shiguTimes=9, shiguAmount=10, shiguOutAmount=11, baoxianAmount=12, yearCheckAmount=13, remark=14, creater=admin, createTime=Sun Dec 29 14:42:13 CST 2019, updateTime=null, assetsInfo=null)'),
	(208,'2019-12-29 14:43:42',NULL,'新增或修改区域:YYCostDO(id=3, enterDate=Sat Dec 28 00:00:00 CST 2019, assetId=1, employeeId=1, employeeName=张三1, status=null, projects=2, projectsName=阜平, startMileage=11, endMileage=22, workload=33, fuel=44, fuelAmount=5.00, baoyangAmount=6.00, fixAmount=7.00, fixFactory=8, shiguTimes=9, shiguAmount=10.00, shiguOutAmount=11.00, baoxianAmount=12.00, yearCheckAmount=13.00, remark=14, creater=null, createTime=null, updateTime=Sun Dec 29 14:43:41 CST 2019, assetsInfo=null)'),
	(209,'2019-12-29 14:49:55','admin','用户登录'),
	(210,'2019-12-29 14:49:55','admin','用户登录'),
	(211,'2019-12-29 14:53:46','admin','用户登录'),
	(212,'2019-12-29 20:33:59','admin','用户登录'),
	(213,'2019-12-30 08:27:51','admin','用户登录'),
	(214,'2019-12-30 08:31:50','admin','用户登录'),
	(215,'2019-12-30 08:33:14','admin','用户登录'),
	(216,'2019-12-30 08:36:47','admin','用户登录'),
	(217,'2019-12-30 08:38:17','admin','用户登录'),
	(218,'2019-12-30 08:40:03','admin','用户登录'),
	(219,'2019-12-30 08:41:45','admin','用户登录'),
	(220,'2019-12-30 08:45:24','admin','用户登录'),
	(221,'2019-12-30 08:47:47','admin','用户登录'),
	(222,'2019-12-30 08:57:41','admin','用户登录'),
	(223,'2019-12-30 13:06:04','admin','用户登录'),
	(224,'2019-12-30 13:32:02','admin','用户登录'),
	(225,'2019-12-30 13:37:03','admin','用户登录'),
	(226,'2019-12-30 13:40:23','admin','用户登录'),
	(227,'2019-12-30 22:35:58','admin','用户登录'),
	(228,'2019-12-30 22:38:43','admin','用户登录'),
	(229,'2019-12-30 22:42:48','admin','用户登录'),
	(230,'2019-12-30 22:46:35','admin','用户登录'),
	(231,'2019-12-30 22:48:33','admin','用户登录'),
	(232,'2019-12-30 22:51:53','admin','用户登录'),
	(233,'2019-12-30 22:56:17','admin','用户登录'),
	(234,'2019-12-30 22:58:00','admin','用户登录'),
	(235,'2019-12-31 22:38:03','admin','用户登录'),
	(236,'2019-12-31 22:39:45','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=44, name=录入日常消费, url=initCC.do)'),
	(237,'2019-12-31 22:40:11','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=43, name=日常消费管理, url=initX.do)'),
	(238,'2019-12-31 22:40:28','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=46, name=收支汇总, url=initdd.do)'),
	(239,'2019-12-31 22:40:41','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,105,104,106,109,110,111,107,108,112,113,114'),
	(240,'2019-12-31 22:40:45','admin','用户登录'),
	(241,'2019-12-31 22:50:50','admin','保存权限记录，result : true;privilege：SysPrivilege(id=112, menuId=44, name=录入日常消费, url=initRichang.do)'),
	(242,'2019-12-31 22:51:13','admin','保存权限记录，result : true;privilege：SysPrivilege(id=112, menuId=44, name=录入日常消费, url=initRichangEdit.do)'),
	(243,'2019-12-31 22:51:28','admin','保存权限记录，result : true;privilege：SysPrivilege(id=113, menuId=43, name=日常消费管理, url=initRichang.do)'),
	(244,'2019-12-31 22:51:48','admin','保存权限记录，result : true;privilege：SysPrivilege(id=114, menuId=46, name=收支汇总, url=initHuizong.do)'),
	(245,'2019-12-31 22:53:09','admin','用户登录'),
	(246,'2020-01-01 14:17:49','admin','用户登录'),
	(247,'2020-01-01 14:46:13','admin','用户登录'),
	(248,'2020-01-01 14:46:56','admin','用户登录'),
	(249,'2020-01-01 15:14:45','admin','用户登录'),
	(250,'2020-01-01 15:28:09','admin','用户登录'),
	(251,'2020-01-01 15:31:41','admin','用户登录'),
	(252,'2020-01-01 15:38:06','admin','用户登录'),
	(253,'2020-01-01 15:46:22','admin','用户登录'),
	(254,'2020-01-01 16:02:25','admin','用户登录'),
	(255,'2020-01-01 16:08:13','admin','用户登录'),
	(256,'2020-01-01 16:10:59','admin','用户登录'),
	(257,'2020-01-01 16:19:13','admin','用户登录'),
	(258,'2020-01-01 20:53:47','admin','用户登录'),
	(259,'2020-01-02 08:09:36','admin','用户登录'),
	(260,'2020-01-02 08:11:48','admin','用户登录'),
	(261,'2020-01-02 08:31:39','admin','用户登录'),
	(262,'2020-01-02 08:35:37','admin','用户登录'),
	(263,'2020-01-02 12:20:38','admin','用户登录'),
	(264,'2020-01-02 12:26:02','admin','用户登录'),
	(265,'2020-01-02 12:26:34','admin','用户登录'),
	(266,'2020-01-02 12:36:53','admin','用户登录'),
	(267,'2020-01-02 12:39:29','admin','用户登录'),
	(268,'2020-01-02 12:54:06','admin','用户登录'),
	(269,'2020-01-02 12:54:25','admin','删除菜单，result : true;menuId：46'),
	(270,'2020-01-02 12:54:48','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=0, name=汇总管理, url=#, sort=100)'),
	(271,'2020-01-02 12:55:24','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=47, name=收支汇总, url=initKanban.do, sort=1)'),
	(272,'2020-01-02 12:55:42','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=48, name=收支汇总, url=initKanban.do)'),
	(273,'2020-01-02 12:55:51','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,105,104,106,109,110,111,107,108,112,113,115'),
	(274,'2020-01-02 12:55:56','admin','用户登录'),
	(275,'2020-01-02 13:02:59','admin','用户登录'),
	(276,'2020-01-02 13:07:39','admin','用户登录'),
	(277,'2020-01-02 13:22:38','admin','用户登录'),
	(278,'2020-01-02 13:25:42','admin','用户登录'),
	(279,'2020-01-02 13:32:10','admin','用户登录'),
	(280,'2020-01-02 13:41:23','admin','用户登录'),
	(281,'2020-01-02 13:44:13','admin','用户登录'),
	(282,'2020-01-02 13:46:55','admin','用户登录'),
	(283,'2020-01-02 21:51:02','admin','用户登录'),
	(284,'2020-01-02 23:12:17','admin','用户登录'),
	(285,'2020-01-02 23:17:49','admin','用户登录'),
	(286,'2020-01-02 23:21:47','admin','用户登录'),
	(287,'2020-01-02 23:24:44','admin','用户登录'),
	(288,'2020-01-03 22:37:29','admin','用户登录'),
	(289,'2020-01-03 22:48:17','admin','用户登录'),
	(290,'2020-01-03 22:51:19','admin','用户登录'),
	(291,'2020-01-03 22:53:17','admin','用户登录'),
	(292,'2020-01-04 12:58:55','admin','用户登录'),
	(293,'2020-01-04 13:02:37','admin','用户登录'),
	(294,'2020-01-04 13:03:55','admin','用户登录'),
	(295,'2020-01-04 13:04:49','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=20, name=人事申请, url=initEmpShenqing.do, sort=0)'),
	(296,'2020-01-04 13:05:15','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=49, name=人事申请, url=initEmpShenqing.do)'),
	(297,'2020-01-04 13:05:27','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,116,105,104,106,109,110,111,107,108,112,113,115'),
	(298,'2020-01-04 13:05:33','admin','用户登录'),
	(299,'2020-01-04 13:08:52','admin','用户登录'),
	(300,'2020-01-04 20:33:42','admin','用户登录'),
	(301,'2020-01-04 20:34:00','admin','删除菜单，result : true;menuId：41'),
	(302,'2020-01-04 20:34:06','admin','删除菜单，result : true;menuId：42'),
	(303,'2020-01-04 20:34:27','admin','用户登录'),
	(304,'2020-01-04 20:47:53','admin','用户登录'),
	(305,'2020-01-04 20:48:41','admin','用户登录'),
	(306,'2020-01-04 20:52:35',NULL,'新增或修改区域:YYCostDO(id=1, enterDate=Sat Jan 04 00:00:00 CST 2020, assetId=1, employeeId=1, employeeName=张三, status=null, projects=2, projectsName=阜平, startMileage=1000, endMileage=2000, workload=500, fuel=100, fuelAmount=1000.00, baoyangAmount=0, fixAmount=600.00, fixFactory=123, shiguTimes=1, shiguAmount=2000.00, shiguOutAmount=2000.00, baoxianAmount=1000.00, yearCheckAmount=2000.00, totalAmount=8600.00, remark=, creater=null, createTime=null, updateTime=Sat Jan 04 20:52:35 CST 2020, assetsInfo=null, dayMileage=0, avgFuel=null)'),
	(307,'2020-01-05 08:07:20','admin','用户登录'),
	(308,'2020-01-05 08:45:07','admin','用户登录'),
	(309,'2020-01-05 08:48:48','admin','用户登录'),
	(310,'2020-01-05 08:49:09','admin','新增或修改日常费用:OtherCostDO(id=0, projects=2, projectsName=阜平, payDay=Sun Jan 05 00:00:00 CST 2020, payType=1, payTypeStr=办公费用, payAmount=1000, remark=你好，测试数据, creater=admin, createTime=Sun Jan 05 08:49:08 CST 2020, updateTime=null)'),
	(311,'2020-01-05 08:49:35',NULL,'新增或修改日常费用:OtherCostDO(id=1, projects=2, projectsName=阜平, payDay=Sun Jan 05 00:00:00 CST 2020, payType=1, payTypeStr=办公费用, payAmount=4000.50, remark=你好，测试数据, creater=null, createTime=null, updateTime=Sun Jan 05 08:49:35 CST 2020)'),
	(312,'2020-01-05 09:34:12','admin','用户登录'),
	(313,'2020-01-05 09:43:37','admin','用户登录'),
	(314,'2020-01-05 09:45:58','admin','新增或修改资产信息:AssetsDO(id=0, projects=2, type=1, typeStr=车辆, name=111, projectsName=阜平, number=2222, license=, brand=1, brandStr=A, carType=1, carTypeStr=压缩, buyTime=Sun Jan 05 00:00:00 CST 2020, inOut=1, quantity=12, quoQuantity=23, owner=1, storageLocation=3, engineNumber=4, engineNumberType=2, price=5, staging=6, purTax=7, remark=8, creater=admin, createTime=Sun Jan 05 09:45:57 CST 2020, updateTime=null)'),
	(315,'2020-01-05 10:01:51','admin','用户登录'),
	(316,'2020-01-05 10:15:00','admin','用户登录'),
	(317,'2020-01-05 10:17:27','admin','用户登录'),
	(318,'2020-01-05 10:20:43','admin','用户登录'),
	(319,'2020-01-05 10:21:31','admin','用户登录'),
	(320,'2020-01-05 10:23:14','admin','用户登录'),
	(321,'2020-01-05 10:23:27','admin','用户登录'),
	(322,'2020-01-05 10:24:15','admin','用户登录'),
	(323,'2020-01-05 10:24:32','admin','新增或修改资产信息:AssetsDO(id=0, projects=2, type=1, typeStr=车辆, name=1, projectsName=阜平, number=1, license=2, brand=0, brandStr=null, carType=0, carTypeStr=null, buyTime=Fri Jan 03 00:00:00 CST 2020, inOut=, quantity=0, quoQuantity=0, owner=1, storageLocation=, engineNumber=, engineNumberType=0, price=null, staging=0, purTax=null, remark=, creater=admin, createTime=Sun Jan 05 10:24:31 CST 2020, updateTime=null)'),
	(324,'2020-01-05 10:25:18','admin','保存菜单，result : true;menu：SysMenu(id=31, parent=30, name=录入车辆资产, url=initAssetsEdit.do, sort=1)'),
	(325,'2020-01-05 10:25:36','admin','保存菜单，result : true;menu：SysMenu(id=31, parent=30, name=录入其他资产, url=initAssetsEdit.do, sort=1)'),
	(326,'2020-01-05 10:25:44','admin','保存菜单，result : true;menu：SysMenu(id=32, parent=30, name=其他资产信息, url=initAssets.do, sort=2)'),
	(327,'2020-01-05 10:25:56','admin','保存菜单，result : true;menu：SysMenu(id=32, parent=30, name=其他资产, url=initAssets.do, sort=2)'),
	(328,'2020-01-05 10:44:28','admin','用户登录'),
	(329,'2020-01-05 10:44:58','admin','新增或修改日常费用:OtherCostDO(id=0, projects=2, projectsName=阜平, fukuan=122344, shoukuan=123, payDay=Sun Jan 05 00:00:00 CST 2020, payType=4, payTypeStr=煤水电费用, payAmount=111, remark=324234, creater=admin, createTime=Sun Jan 05 10:44:57 CST 2020, updateTime=null)'),
	(330,'2020-01-05 10:45:08',NULL,'新增或修改日常费用:OtherCostDO(id=2, projects=2, projectsName=阜平, fukuan=122344的, shoukuan=123的, payDay=Sun Jan 05 00:00:00 CST 2020, payType=4, payTypeStr=煤水电费用, payAmount=111.00, remark=324234, creater=null, createTime=null, updateTime=Sun Jan 05 10:45:08 CST 2020)'),
	(331,'2020-01-05 10:48:59','admin','用户登录'),
	(332,'2020-01-05 10:49:36',NULL,'新增或修改日常费用:OtherCostDO(id=1, projects=2, projectsName=阜平, fukuan=11, shoukuan=22, payDay=Sun Jan 05 00:00:00 CST 2020, payType=1, payTypeStr=招待费用, payAmount=4000.50, remark=你好，测试数据, creater=null, createTime=null, updateTime=Sun Jan 05 10:49:36 CST 2020)'),
	(333,'2020-01-05 10:54:16','admin','用户登录'),
	(334,'2020-01-05 10:55:10','admin','用户登录'),
	(335,'2020-01-05 10:57:20','admin','新增或修改区域:YYCostDO(id=0, enterDate=Sun Jan 05 00:00:00 CST 2020, assetId=3, employeeId=2, employeeName=司机我是, status=null, projects=2, projectsName=阜平, startMileage=0, endMileage=0, workload=0, fuel=0, fuelAmount=0, baoyangAmount=0, fixAmount=0, fixFactory=, shiguTimes=0, shiguAmount=0, shiguOutAmount=0, baoxianAmount=0, yearCheckAmount=0, totalAmount=0, remark=, creater=admin, createTime=Sun Jan 05 10:57:20 CST 2020, updateTime=null, assetsInfo=null, dayMileage=0, avgFuel=null)'),
	(336,'2020-01-05 13:37:56','admin','用户登录'),
	(337,'2020-01-05 13:38:21','admin','保存菜单，result : true;menu：SysMenu(id=32, parent=30, name=其他资产管理, url=initAssets.do, sort=2)'),
	(338,'2020-01-05 13:38:25','admin','用户登录'),
	(339,'2020-01-05 13:53:12','admin','用户登录'),
	(340,'2020-01-05 13:53:54',NULL,'新增或修改其他资产信息:AssetsDO(id=3, projects=2, type=1, typeStr=车辆, name=1, projectsName=阜平, number=1, license=2, brand=0, brandStr=null, carType=0, carTypeStr=null, buyTime=Fri Jan 03 00:00:00 CST 2020, inOut=, quantity=0, quoQuantity=0, owner=1, storageLocation=, engineNumber=, engineNumberType=0, price=0.00, staging=0, purTax=10.00, tanxiao=20.00, zhejiu=0.00, remark=, creater=null, createTime=null, updateTime=Sun Jan 05 13:53:54 CST 2020)'),
	(341,'2020-01-05 13:54:13',NULL,'新增或修改其他资产信息:AssetsDO(id=3, projects=2, type=1, typeStr=车辆, name=1, projectsName=阜平, number=1, license=2, brand=0, brandStr=null, carType=0, carTypeStr=null, buyTime=Fri Jan 03 00:00:00 CST 2020, inOut=, quantity=0, quoQuantity=0, owner=1, storageLocation=, engineNumber=, engineNumberType=0, price=0.00, staging=0, purTax=10.00, tanxiao=20.00, zhejiu=30.00, remark=, creater=null, createTime=null, updateTime=Sun Jan 05 13:54:12 CST 2020)'),
	(342,'2020-01-05 13:58:21','admin','新增或修改其他资产信息:AssetsDO(id=0, projects=2, type=4, typeStr=桶, name=1, projectsName=阜平, number=2, license=2, brand=3, brandStr=C, carType=2, carTypeStr=勾臂, buyTime=Sun Jan 05 00:00:00 CST 2020, inOut=1, quantity=2, quoQuantity=3, owner=1, storageLocation=4, engineNumber=5, engineNumberType=1, price=6, staging=7, purTax=8, tanxiao=9, zhejiu=10, remark=11, creater=admin, createTime=Sun Jan 05 13:58:20 CST 2020, updateTime=null)'),
	(343,'2020-01-05 14:05:24','admin','用户登录'),
	(344,'2020-01-05 14:06:17','admin','用户登录'),
	(345,'2020-01-05 14:52:45','admin','用户登录'),
	(346,'2020-01-05 14:53:38','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=30, name=录入其他资产, url=initAssetsOtherEdit.do, sort=3)'),
	(347,'2020-01-05 14:53:52','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=50, name=录入其他资产, url=initAssetsOtherEdit.do)'),
	(348,'2020-01-05 14:54:23','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=30, name=其他资产管理, url=initAssetsOther.do, sort=4)'),
	(349,'2020-01-05 14:54:41','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=51, name=其他资产管理, url=initAssetsOther.do)'),
	(350,'2020-01-05 14:54:53','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,116,105,104,106,109,117,118,107,108,112,113,115'),
	(351,'2020-01-05 14:54:56','admin','用户登录'),
	(352,'2020-01-05 14:56:01','admin','用户登录'),
	(353,'2020-01-05 14:57:54','admin','用户登录'),
	(354,'2020-01-05 14:59:33','admin','用户登录'),
	(355,'2020-01-05 15:00:07','admin','新增或修改其他资产:AssetsOtherDO(id=0, projects=2, type=1, typeStr=电动人力, name=1千, projectsName=阜平, brand=3, brandStr=C, carType=1, carTypeStr=压缩, buyTime=Sun Jan 05 00:00:00 CST 2020, inOut=1, quantity=2, quoQuantity=3, owner=1, storageLocation=4, price=5, staging=6, tanxiao=7, zhejiu=8, remark=9, creater=admin, createTime=Sun Jan 05 15:00:06 CST 2020, updateTime=null)'),
	(356,'2020-01-05 15:05:05','admin','用户登录'),
	(357,'2020-01-05 15:05:28',NULL,'新增或修改其他资产:AssetsOtherDO(id=1, projects=2, type=1, typeStr=电动人力, name=1千1, projectsName=阜平, brand=3, brandStr=C, carType=1, carTypeStr=压缩, buyTime=Sun Jan 05 00:00:00 CST 2020, inOut=1, quantity=2, quoQuantity=3, owner=1, storageLocation=4, price=5.00, staging=6, tanxiao=7.00, zhejiu=8.00, remark=9, creater=null, createTime=null, updateTime=Sun Jan 05 15:05:27 CST 2020)'),
	(358,'2020-01-05 15:19:05','admin','用户登录'),
	(359,'2020-01-05 15:19:36','admin','新增或修改其他资产:AssetsOtherDO(id=0, projects=2, type=1, typeStr=电动人力, name=1111, projectsName=阜平, brand=2, brandStr=B, carType=1, carTypeStr=压缩, buyTime=Sun Jan 05 00:00:00 CST 2020, inOut=1, quantity=2, quoQuantity=3, owner=2, storageLocation=4, price=5, staging=6, tanxiao=7, zhejiu=8, remark=999, creater=admin, createTime=Sun Jan 05 15:19:36 CST 2020, updateTime=null)'),
	(360,'2020-01-05 15:20:13',NULL,'新增或修改车辆管理:AssetsDO(id=4, projects=2, name=11111, projectsName=阜平, number=2, license=2, brand=3, brandStr=C, carType=2, carTypeStr=勾臂, buyTime=Sun Jan 05 00:00:00 CST 2020, inOut=1, quantity=2, quoQuantity=3, owner=1, storageLocation=4, engineNumber=5, engineNumberType=1, price=6.00, staging=7, purTax=8.00, tanxiao=9.00, zhejiu=10.00, remark=11, creater=null, createTime=null, updateTime=Sun Jan 05 15:20:12 CST 2020)'),
	(361,'2020-01-05 15:20:31','admin','新增或修改车辆管理:AssetsDO(id=0, projects=2, name=小心心, projectsName=阜平, number=小, license=123, brand=1, brandStr=A, carType=0, carTypeStr=null, buyTime=Sun Jan 05 00:00:00 CST 2020, inOut=, quantity=0, quoQuantity=0, owner=2, storageLocation=, engineNumber=, engineNumberType=0, price=null, staging=0, purTax=null, tanxiao=null, zhejiu=null, remark=, creater=admin, createTime=Sun Jan 05 15:20:30 CST 2020, updateTime=null)'),
	(362,'2020-01-05 15:20:59',NULL,'新增或修改车辆管理:AssetsDO(id=5, projects=2, name=小心心, projectsName=阜平, number=小, license=123, brand=1, brandStr=A, carType=1, carTypeStr=压缩, buyTime=Sun Jan 05 00:00:00 CST 2020, inOut=1, quantity=10, quoQuantity=2, owner=2, storageLocation=沈德符, engineNumber=玩儿, engineNumberType=1, price=10.00, staging=10, purTax=10.00, tanxiao=10.00, zhejiu=110.00, remark=11, creater=null, createTime=null, updateTime=Sun Jan 05 15:20:59 CST 2020)'),
	(363,'2020-01-05 15:22:45','admin','保存菜单，result : true;menu：SysMenu(id=48, parent=47, name=费用汇总, url=initKanban.do, sort=1)'),
	(364,'2020-01-05 15:22:50','admin','用户登录'),
	(365,'2020-01-05 15:54:14','admin','用户登录'),
	(366,'2020-01-05 16:00:44','admin','用户登录'),
	(367,'2020-01-05 16:04:40','admin','用户登录'),
	(368,'2020-01-05 16:10:32','admin','用户登录'),
	(369,'2020-01-05 16:14:48','admin','用户登录'),
	(370,'2020-01-05 16:15:14','admin','用户登录'),
	(371,'2020-01-05 16:19:54','admin','用户登录'),
	(372,'2020-01-05 16:27:55','admin','用户登录'),
	(373,'2020-01-05 16:29:20','admin','用户登录'),
	(374,'2020-01-05 16:31:40','admin','用户登录'),
	(375,'2020-01-05 16:35:15','admin','用户登录'),
	(376,'2020-01-05 16:39:24','admin','用户登录'),
	(377,'2020-01-05 16:40:45','admin','用户登录'),
	(378,'2020-01-05 22:06:19','admin','用户登录'),
	(379,'2020-01-05 22:10:03','admin','用户登录'),
	(380,'2020-01-05 22:12:53','admin','用户登录'),
	(381,'2020-01-05 22:20:26','admin','用户登录'),
	(382,'2020-01-05 22:22:45','admin','用户登录'),
	(383,'2020-01-05 22:29:35','admin','用户登录'),
	(384,'2020-01-05 23:05:49','admin','用户登录'),
	(385,'2020-01-05 23:08:15','admin','用户登录'),
	(386,'2020-01-05 23:10:29','admin','用户登录'),
	(387,'2020-01-05 23:18:19','admin','用户登录'),
	(388,'2020-01-05 23:21:57','admin','用户登录'),
	(389,'2020-01-05 23:25:31','admin','用户登录'),
	(390,'2020-01-05 23:26:52','admin','用户登录'),
	(391,'2020-01-05 23:29:25','admin','用户登录'),
	(392,'2020-01-05 23:31:50','admin','用户登录'),
	(393,'2020-01-05 23:32:12','admin','用户登录'),
	(394,'2020-01-05 23:33:14','admin','用户登录'),
	(395,'2020-01-05 23:35:31','admin','用户登录'),
	(396,'2020-01-05 23:37:49','admin','用户登录'),
	(397,'2020-01-05 23:41:16','admin','用户登录'),
	(398,'2020-01-05 23:42:44','admin','用户登录'),
	(399,'2020-01-05 23:51:09','admin','用户登录'),
	(400,'2020-01-05 23:53:07','admin','用户登录'),
	(401,'2020-01-05 23:55:14','admin','用户登录'),
	(402,'2020-01-05 23:57:24','admin','用户登录'),
	(403,'2020-01-05 23:59:58','admin','用户登录'),
	(404,'2020-01-06 08:48:51','admin','用户登录'),
	(405,'2020-01-06 08:55:54','admin','用户登录'),
	(406,'2020-01-06 08:58:26','admin','用户登录'),
	(407,'2020-01-06 09:01:00','admin','用户登录'),
	(408,'2020-01-06 09:02:59','admin','用户登录'),
	(409,'2020-01-06 13:18:28','admin','用户登录'),
	(410,'2020-01-06 13:22:19','admin','用户登录'),
	(411,'2020-01-06 13:29:43','admin','用户登录'),
	(412,'2020-01-06 22:35:39','admin','用户登录'),
	(413,'2020-01-06 22:36:24','admin','用户登录'),
	(414,'2020-01-06 22:40:16','admin','用户登录'),
	(415,'2020-01-06 22:42:23','admin','用户登录'),
	(416,'2020-01-06 22:51:12','admin','用户登录'),
	(417,'2020-01-06 22:58:01','admin','用户登录'),
	(418,'2020-01-06 22:58:17','admin','新增或修改系统用户:SysMember(id=1, name=admin, imgs=null, valid=0, pwd=null, showName=管理员, roleId=1, phone=123, email=admin@edu.com.cn, projectsId=-1, areaId=0, priUrls=null, roleName=null, roleIntor=null, projectsName=null)'),
	(419,'2020-01-06 22:59:00','admin','用户登录'),
	(420,'2020-01-06 23:05:46','admin','用户登录'),
	(421,'2020-01-07 08:22:43','admin','用户登录'),
	(422,'2020-01-07 08:29:27','admin','用户登录'),
	(423,'2020-01-07 08:33:39','admin','用户登录'),
	(424,'2020-01-07 08:43:10','admin','用户登录'),
	(425,'2020-01-07 08:43:32','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=20, name=离职员工, url=initEmpLizhi.do, sort=3)'),
	(426,'2020-01-07 08:43:44','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=52, name=离职员工, url=initEmpLizhi.do)'),
	(427,'2020-01-07 08:43:54','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,116,105,104,119,106,109,117,118,107,108,112,113,115'),
	(428,'2020-01-07 08:43:57','admin','用户登录'),
	(429,'2020-01-07 08:46:14','admin','用户登录'),
	(430,'2020-01-07 08:50:45','admin','用户登录'),
	(431,'2020-01-07 09:00:38','admin','用户登录'),
	(432,'2020-01-07 09:00:54','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=20, name=人事审核, url=initEmpShengHe.do, sort=2)'),
	(433,'2020-01-07 09:01:16','admin','保存菜单，result : true;menu：SysMenu(id=49, parent=20, name=人事申请, url=initEmpShenqing.do, sort=1)'),
	(434,'2020-01-07 09:01:27','admin','保存菜单，result : true;menu：SysMenu(id=21, parent=20, name=新增人员, url=initEmployeeEdit.do, sort=0)'),
	(435,'2020-01-07 09:01:38','admin','保存菜单，result : true;menu：SysMenu(id=22, parent=20, name=员工管理, url=initEmployee.do, sort=3)'),
	(436,'2020-01-07 09:01:44','admin','保存菜单，result : true;menu：SysMenu(id=52, parent=20, name=离职员工, url=initEmpLizhi.do, sort=4)'),
	(437,'2020-01-07 09:02:02','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=53, name=人事审核, url=initEmpShengHe.do)'),
	(438,'2020-01-07 09:02:11','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,105,116,120,104,119,106,109,117,118,107,108,112,113,115'),
	(439,'2020-01-07 09:03:28','admin','用户登录'),
	(440,'2020-01-07 09:04:28','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,105,116,120,104,119,106,109,117,118,107,108,112,113,115'),
	(441,'2020-01-07 09:04:50','admin','保存权限记录，result : true;privilege：SysPrivilege(id=105, menuId=21, name=编辑员工信息页, url=initEmployeeEdit.do)'),
	(442,'2020-01-07 09:05:13','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,116,120,104,119,106,109,117,118,107,108,112,113,115'),
	(443,'2020-01-07 09:05:28','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,105,116,120,104,119,106,109,117,118,107,108,112,113,115'),
	(444,'2020-01-07 09:05:32','admin','用户登录'),
	(445,'2020-01-07 13:03:19','admin','用户登录'),
	(446,'2020-01-07 13:06:01','admin','用户登录'),
	(447,'2020-01-07 13:08:21','admin','用户登录'),
	(448,'2020-01-07 13:10:21','admin','用户登录'),
	(449,'2020-01-07 13:11:56','admin','用户登录'),
	(450,'2020-01-07 13:18:40','admin','用户登录'),
	(451,'2020-01-07 21:33:57','admin','用户登录'),
	(452,'2020-01-07 21:38:59','admin','用户登录'),
	(453,'2020-01-07 21:45:56','admin','用户登录'),
	(454,'2020-01-07 21:56:59','admin','用户登录'),
	(455,'2020-01-07 22:03:36','admin','用户登录'),
	(456,'2020-01-07 22:16:42','admin','用户登录'),
	(457,'2020-01-07 22:18:15','admin','用户登录'),
	(458,'2020-01-07 22:18:50','admin','用户登录'),
	(459,'2020-01-07 23:10:36','admin','用户登录'),
	(460,'2020-01-08 08:15:30','admin','用户登录'),
	(461,'2020-01-08 08:20:53','admin','用户登录'),
	(462,'2020-01-08 08:37:38','admin','用户登录'),
	(463,'2020-01-08 08:41:18','admin','用户登录'),
	(464,'2020-01-08 08:45:58','admin','用户登录'),
	(465,'2020-01-08 08:47:45','admin','用户登录'),
	(466,'2020-01-08 08:52:03','admin','用户登录'),
	(467,'2020-01-08 12:38:34','admin','用户登录'),
	(468,'2020-01-09 22:10:37','admin','用户登录'),
	(469,'2020-01-09 22:11:07','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=20, name=工资单管理, url=initPay.do, sort=2)'),
	(470,'2020-01-09 22:11:25','admin','保存菜单，result : true;menu：SysMenu(id=54, parent=20, name=工资单管理, url=initPay.do, sort=12)'),
	(471,'2020-01-09 22:11:55','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=20, name=创建工资单, url=initPayEdit.do, sort=11)'),
	(472,'2020-01-09 22:12:15','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=55, name=编辑工资单, url=initPayEdit.do)'),
	(473,'2020-01-09 22:12:33','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=54, name=工资单管理, url=initPay.do)'),
	(474,'2020-01-09 22:12:43','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,105,116,120,104,119,121,122,106,109,117,118,107,108,112,113,115'),
	(475,'2020-01-09 22:12:48','admin','用户登录'),
	(476,'2020-01-10 12:29:58','admin','用户登录'),
	(477,'2020-01-10 12:30:41','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=47, name=工资汇总, url=initPayChart.do, sort=2)'),
	(478,'2020-01-10 12:30:53','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=56, name=工资汇总, url=initPayChart.do)'),
	(479,'2020-01-10 12:31:02','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,105,116,120,104,119,121,122,106,109,117,118,107,108,112,113,115,123'),
	(480,'2020-01-10 12:31:06','admin','用户登录'),
	(481,'2020-01-10 12:32:35',NULL,'新增或修改日常费用:OtherCostDO(id=1, projects=2, projectsName=阜平, fukuan=11, shoukuan=22, payDay=Sun Jan 05 00:00:00 CST 2020, payType=1, payTypeStr=招待费用, payAmount=4000, remark=你好，测试数据, creater=null, createTime=null, updateTime=Fri Jan 10 12:32:35 CST 2020)'),
	(482,'2020-01-10 12:33:57','admin','用户登录'),
	(483,'2020-01-21 12:42:14','admin','用户登录'),
	(484,'2020-01-21 12:57:14','admin','用户登录'),
	(485,'2020-01-21 12:58:18','admin','用户登录'),
	(486,'2020-01-21 13:00:29','admin','用户登录'),
	(487,'2020-01-21 13:02:57','admin','用户登录'),
	(488,'2020-01-21 13:03:12','admin','新增或修改工资单:PayDO(id=0, projects=2, projectsName=阜平, payMonth=Wed Jan 01 00:00:00 CST 2020, payStatus=null, totalMan=null, totalAmount=null, payedAmount=null, yuAmount=null, remark=tt, creater=admin, createTime=Tue Jan 21 13:03:12 CST 2020, updateTime=null)'),
	(489,'2020-01-21 13:09:43','admin','用户登录'),
	(490,'2020-01-21 13:10:23','admin','用户登录'),
	(491,'2020-01-21 13:11:08',NULL,'新增或修改工资单:PayDO(id=1, projects=2, projectsName=阜平, payMonth=Wed Jan 01 00:00:00 CST 2020, payStatus=null, totalMan=null, totalAmount=null, payedAmount=null, yuAmount=null, remark=1tt, creater=null, createTime=null, updateTime=Tue Jan 21 13:11:07 CST 2020)'),
	(492,'2020-01-21 13:13:25','admin','用户登录'),
	(493,'2020-01-21 13:39:06','admin','用户登录'),
	(494,'2020-01-21 13:41:37','admin','用户登录'),
	(495,'2020-01-21 19:56:53','admin','用户登录'),
	(496,'2020-01-22 21:42:17','admin','用户登录'),
	(497,'2020-01-22 21:48:26','admin','用户登录'),
	(498,'2020-01-22 21:49:44','admin','用户登录'),
	(499,'2020-01-22 21:51:38','admin','用户登录'),
	(500,'2020-01-22 21:53:52','admin','用户登录'),
	(501,'2020-01-22 22:01:51','admin','用户登录'),
	(502,'2020-01-22 22:02:23','admin','用户登录'),
	(503,'2020-01-22 22:08:12','admin','用户登录'),
	(504,'2020-01-22 22:11:42','admin','用户登录'),
	(505,'2020-01-22 22:17:11','admin','用户登录'),
	(506,'2020-01-22 22:19:38','admin','用户登录'),
	(507,'2020-01-22 22:19:54',NULL,'新增或修改工资明细:PayDetailDO(id=1, payId=1, projects=2, projectsName=阜平, payMonth=Wed Jan 01 00:00:00 CST 2020, empId=2, empName=司机我是, gender=1, jobName=司机, fixAmount=0.00, jiabanAmount=0.00, jixiaoAmount=0.00, jiangjinAmount=0.00, fakuanAmount=0.00, totalAmount=0.00, payedAmount=0.00, payStatus=null, remark=, creater=null, createTime=null, updateTime=Wed Jan 22 22:19:54 CST 2020)'),
	(508,'2020-01-22 22:22:19','admin','用户登录'),
	(509,'2020-01-22 22:23:21',NULL,'新增或修改工资明细:PayDetailDO(id=1, payId=1, projects=2, projectsName=阜平, payMonth=Wed Jan 01 00:00:00 CST 2020, empId=2, empName=司机我是, gender=1, jobName=司机, fixAmount=10.00, jiabanAmount=0.00, jixiaoAmount=0.00, jiangjinAmount=0.00, fakuanAmount=0.00, totalAmount=10.00, payedAmount=0.00, payStatus=null, remark=, creater=null, createTime=null, updateTime=Wed Jan 22 22:23:21 CST 2020)'),
	(510,'2020-01-22 22:28:55','admin','用户登录'),
	(511,'2020-01-23 19:32:12','admin','用户登录'),
	(512,'2020-01-23 19:34:47','admin','新增或修改工资明细:PayDetailDO(id=0, payId=1, projects=2, projectsName=阜平, payMonth=Wed Jan 01 00:00:00 CST 2020, empId=2, empName=司机我是, gender=1, jobName=司机, fixAmount=0, jiabanAmount=0, jixiaoAmount=0, jiangjinAmount=0, fakuanAmount=0, totalAmount=0, payedAmount=0, payStatus=null, remark=, creater=admin, createTime=Thu Jan 23 19:34:47 CST 2020, updateTime=null)'),
	(513,'2020-01-23 19:49:52','admin','用户登录'),
	(514,'2020-01-23 19:50:31',NULL,'新增或修改工资明细:PayDetailDO(id=1, payId=1, projects=2, projectsName=阜平, payMonth=Wed Jan 01 00:00:00 CST 2020, empId=2, empName=司机我是, gender=1, jobName=司机, fixAmount=10.00, jiabanAmount=0.00, jixiaoAmount=0.00, jiangjinAmount=0.00, fakuanAmount=0.00, totalAmount=10.00, payedAmount=10.00, yuAmount=null, payStatus=null, remark=, creater=null, createTime=null, updateTime=Thu Jan 23 19:50:30 CST 2020)'),
	(515,'2020-01-23 19:51:05','admin','用户登录'),
	(516,'2020-01-23 19:51:23',NULL,'新增或修改工资明细:PayDetailDO(id=2, payId=1, projects=2, projectsName=阜平, payMonth=Wed Jan 01 00:00:00 CST 2020, empId=2, empName=司机我是, gender=1, jobName=司机, fixAmount=0.00, jiabanAmount=20.00, jixiaoAmount=0.00, jiangjinAmount=0.00, fakuanAmount=0.00, totalAmount=20.00, payedAmount=0.00, yuAmount=null, payStatus=null, remark=, creater=null, createTime=null, updateTime=Thu Jan 23 19:51:23 CST 2020)'),
	(517,'2020-01-23 19:54:57','admin','用户登录'),
	(518,'2020-01-23 19:55:25','admin','用户登录'),
	(519,'2020-01-23 20:03:20','admin','用户登录'),
	(520,'2020-01-23 20:03:36','admin','用户登录'),
	(521,'2020-01-23 20:06:31','admin','用户登录'),
	(522,'2020-01-23 20:10:25','admin','用户登录'),
	(523,'2020-01-23 20:11:23',NULL,'新增或修改工资明细:PayDetailDO(id=1, payId=1, projects=2, projectsName=阜平, payMonth=Wed Jan 01 00:00:00 CST 2020, empId=2, empName=司机我是, gender=1, jobName=司机, fixAmount=3000.00, jiabanAmount=30.00, jixiaoAmount=0.05, jiangjinAmount=0.10, fakuanAmount=50.00, totalAmount=2980.15, payedAmount=100.00, yuAmount=null, payStatus=null, remark=, creater=null, createTime=null, updateTime=Thu Jan 23 20:11:22 CST 2020)'),
	(524,'2020-01-23 20:12:13',NULL,'新增或修改工资明细:PayDetailDO(id=2, payId=1, projects=2, projectsName=阜平, payMonth=Wed Jan 01 00:00:00 CST 2020, empId=2, empName=司机我是, gender=1, jobName=司机, fixAmount=0.00, jiabanAmount=20.00, jixiaoAmount=10.00, jiangjinAmount=0.00, fakuanAmount=0.00, totalAmount=30.00, payedAmount=0.00, yuAmount=null, payStatus=null, remark=, creater=null, createTime=null, updateTime=Thu Jan 23 20:12:12 CST 2020)'),
	(525,'2020-01-26 12:25:10','admin','用户登录'),
	(526,'2020-01-26 12:25:39',NULL,'新增或修改区域:AreaDO(id=2, name=四川, remark=111, creater=null, createTime=null, updateTime=Sun Jan 26 12:25:38 CST 2020)'),
	(527,'2020-01-26 12:25:52',NULL,'新增或修改项目:ProjectsDO(id=1, areaId=3, name=保定, areaName=null, remark=你好123, creater=null, createTime=null, updateTime=Sun Jan 26 12:25:52 CST 2020)'),
	(528,'2020-01-26 12:30:50','admin','新增或修改工资单:PayDO(id=0, projects=3, projectsName=行唐, payMonth=Wed Jan 01 00:00:00 CST 2020, payStatus=null, totalMan=null, totalAmount=null, payedAmount=null, yuAmount=null, remark=11, creater=admin, createTime=Sun Jan 26 12:30:49 CST 2020, updateTime=null)'),
	(529,'2020-01-26 12:31:26','admin','新增或修改工资明细:PayDetailDO(id=0, payId=2, projects=3, projectsName=行唐, payMonth=Wed Jan 01 00:00:00 CST 2020, empId=3, empName=刘德华, gender=1, jobName=经理, fixAmount=100, jiabanAmount=200, jixiaoAmount=300, jiangjinAmount=400, fakuanAmount=500, totalAmount=500, payedAmount=0, yuAmount=null, payStatus=null, remark=, creater=admin, createTime=Sun Jan 26 12:31:25 CST 2020, updateTime=null)'),
	(530,'2020-01-26 12:31:45',NULL,'新增或修改工资明细:PayDetailDO(id=3, payId=2, projects=3, projectsName=行唐, payMonth=Wed Jan 01 00:00:00 CST 2020, empId=3, empName=刘德华, gender=1, jobName=经理, fixAmount=100.00, jiabanAmount=200.00, jixiaoAmount=300.00, jiangjinAmount=400.00, fakuanAmount=500.00, totalAmount=500.00, payedAmount=500.00, yuAmount=null, payStatus=null, remark=, creater=null, createTime=null, updateTime=Sun Jan 26 12:31:45 CST 2020)'),
	(531,'2020-01-26 12:32:24','admin','新增或修改车辆管理:AssetsDO(id=0, projects=3, name=safasd1, projectsName=行唐, number=111, license=111, brand=1, brandStr=A, carType=1, carTypeStr=压缩, buyTime=Sun Jan 26 00:00:00 CST 2020, inOut=, quantity=0, quoQuantity=0, owner=3, storageLocation=, engineNumber=, engineNumberType=0, price=null, staging=0, purTax=null, tanxiao=null, zhejiu=null, remark=, creater=admin, createTime=Sun Jan 26 12:32:23 CST 2020, updateTime=null)'),
	(532,'2020-02-01 11:23:09','admin','用户登录'),
	(533,'2020-02-01 11:31:58','admin','用户登录'),
	(534,'2020-02-01 11:33:00','admin','新增或修改车辆管理:AssetsDO(id=0, projects=2, name=Aq-1, projectsName=阜平, number=AAA-1, license=qqq-101, brand=1, brandStr=福田普罗科, carType=1, carTypeStr=压缩, buyTime=Sat Feb 01 00:00:00 CST 2020, inOut=第三方的身份, quantity=10, quoQuantity=10, owner=3, storageLocation=11111, engineNumber=1111, engineNumberType=2, price=100, staging=5, purTax=10, tanxiao=30, zhejiu=20, remark=, creater=admin, createTime=Sat Feb 01 11:33:00 CST 2020, updateTime=null)'),
	(535,'2020-02-01 11:33:08',NULL,'新增或修改车辆管理:AssetsDO(id=7, projects=2, name=Aq-1, projectsName=阜平, number=AAA-1, license=qqq-101, brand=1, brandStr=福田普罗科, carType=1, carTypeStr=压缩, buyTime=Sat Feb 01 00:00:00 CST 2020, inOut=第三方的身份, quantity=10, quoQuantity=10, owner=3, storageLocation=11111, engineNumber=1111, engineNumberType=2, price=100.00, staging=5, purTax=10.00, tanxiao=30.00, zhejiu=20.00, remark=, creater=null, createTime=null, updateTime=Sat Feb 01 11:33:08 CST 2020)'),
	(536,'2020-02-01 11:35:36','admin','新增或修改其他资产:AssetsOtherDO(id=0, projects=2, type=4, typeStr=辅助类, name=777, projectsName=阜平, brand=3, brandStr=宇通环卫, carType=1, carTypeStr=压缩, buyTime=Sat Feb 01 00:00:00 CST 2020, inOut=, quantity=0, quoQuantity=0, owner=3, storageLocation=, price=null, staging=0, tanxiao=null, zhejiu=null, remark=, creater=admin, createTime=Sat Feb 01 11:35:35 CST 2020, updateTime=null)'),
	(537,'2020-02-01 11:35:42',NULL,'新增或修改其他资产:AssetsOtherDO(id=3, projects=2, type=4, typeStr=辅助类, name=777, projectsName=阜平, brand=3, brandStr=宇通环卫, carType=1, carTypeStr=压缩, buyTime=Sat Feb 01 00:00:00 CST 2020, inOut=, quantity=0, quoQuantity=0, owner=3, storageLocation=, price=0.00, staging=0, tanxiao=0.00, zhejiu=0.00, remark=, creater=null, createTime=null, updateTime=Sat Feb 01 11:35:41 CST 2020)'),
	(538,'2020-02-01 11:44:04','admin','用户登录'),
	(539,'2020-02-01 11:44:39','admin','用户登录'),
	(540,'2020-02-01 11:45:05',NULL,'新增或修改区域:YYCostDO(id=2, enterDate=Sun Jan 05 00:00:00 CST 2020, assetId=3, employeeId=1, employeeName=张三, status=null, projects=2, projectsName=阜平, startMileage=0, endMileage=0, workload=0, fuel=0, fuelAmount=0.00, baoyangAmount=0.00, fixAmount=0.00, fixFactory=, shiguTimes=0, shiguAmount=0.00, shiguOutAmount=0.00, baoxianAmount=0.00, yearCheckAmount=0.00, totalAmount=0.00, remark=, creater=null, createTime=null, updateTime=Sat Feb 01 11:45:04 CST 2020, assetsInfo=null, dayMileage=0, avgFuel=null)'),
	(541,'2020-02-01 11:45:18',NULL,'新增或修改区域:YYCostDO(id=2, enterDate=Sun Jan 05 00:00:00 CST 2020, assetId=3, employeeId=1, employeeName=张三, status=null, projects=2, projectsName=阜平, startMileage=10, endMileage=0, workload=10, fuel=10, fuelAmount=10.00, baoyangAmount=0.00, fixAmount=0.00, fixFactory=, shiguTimes=0, shiguAmount=0.00, shiguOutAmount=0.00, baoxianAmount=0.00, yearCheckAmount=0.00, totalAmount=10.00, remark=, creater=null, createTime=null, updateTime=Sat Feb 01 11:45:18 CST 2020, assetsInfo=null, dayMileage=0, avgFuel=null)'),
	(542,'2020-03-21 14:33:00','admin','用户登录'),
	(543,'2020-03-21 14:33:34','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=10, name=合同管理, url=initContract.do, sort=5)'),
	(544,'2020-03-21 14:33:45','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=57, name=合同管理, url=initContract.do)'),
	(545,'2020-03-21 14:34:04','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=10, name=额定税金, url=initShuijin.do, sort=6)'),
	(546,'2020-03-21 14:34:15','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=58, name=额定税金, url=initShuijin.do)'),
	(547,'2020-03-21 14:34:41','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=10, name=管理费, url=initManagerPay.do, sort=7)'),
	(548,'2020-03-21 14:34:54','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=59, name=管理费, url=initManagerPay.do)'),
	(549,'2020-03-21 14:35:11','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,124,125,126,105,116,120,104,119,121,122,106,109,117,118,107,108,112,113,115,123'),
	(550,'2020-03-21 14:35:16','admin','用户登录'),
	(551,'2020-03-21 16:08:29','admin','用户登录'),
	(552,'2020-03-21 16:10:29','admin','用户登录'),
	(553,'2020-03-21 16:11:08','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=40, name=收入管理, url=initIncome.do, sort=0)'),
	(554,'2020-03-21 16:11:21','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=60, name=收入管理, url=initIncome.do)'),
	(555,'2020-03-21 16:11:43','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,124,125,126,105,116,120,104,119,121,122,106,109,117,118,127,108,113,115,123'),
	(556,'2020-03-21 16:11:49','admin','用户登录'),
	(557,'2020-03-21 16:13:28','admin','新增或修改收入:IncomeDO(id=0, projects=1, projectsName=保定, incomeDay=Sat Aug 01 00:00:00 CST 2020, amount=144, jiafang=null, jiafangInfo=null, remark=444, creater=admin, createTime=Sat Mar 21 16:13:28 CST 2020, updateTime=null)'),
	(558,'2020-03-21 16:18:15','admin','用户登录'),
	(559,'2020-03-21 16:22:00',NULL,'新增或修改收入:IncomeDO(id=3, projects=1, projectsName=保定, incomeDay=Sat Aug 01 00:00:00 CST 2020, amount=144.00, jiafang=aa, jiafangInfo=bb, remark=444, creater=null, createTime=null, updateTime=Sat Mar 21 16:21:59 CST 2020)'),
	(560,'2020-04-04 11:18:54','admin','用户登录'),
	(561,'2020-04-04 11:20:31','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=40, name=运营管理费, url=initYunyingCost.do, sort=5)'),
	(562,'2020-04-04 11:21:00','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=61, name=运营管理费, url=initYunyingCost.do)'),
	(563,'2020-04-04 11:21:16','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,124,125,126,105,116,120,104,119,121,122,106,109,117,118,127,108,128,113,115,123'),
	(564,'2020-04-04 11:21:20','admin','用户登录'),
	(565,'2020-04-04 11:33:24','admin','用户登录'),
	(566,'2020-05-05 21:55:48','admin','用户登录'),
	(567,'2020-05-05 21:56:25','admin','删除菜单，result : true;menuId：61'),
	(568,'2020-05-05 21:56:32','admin','删除菜单，result : true;menuId：44'),
	(569,'2020-05-05 21:56:40','admin','删除菜单，result : true;menuId：43'),
	(570,'2020-05-05 21:56:48','admin','删除菜单，result : true;menuId：33'),
	(571,'2020-05-05 21:57:03','admin','保存菜单，result : true;menu：SysMenu(id=34, parent=40, name=费用管理, url=initCosts.do, sort=4)'),
	(572,'2020-05-05 21:57:19','admin','保存权限记录，result : true;privilege：SysPrivilege(id=108, menuId=34, name=费用管理, url=initCosts.do)'),
	(573,'2020-05-05 21:57:32','admin','用户登录'),
	(574,'2020-05-05 22:41:32','admin','用户登录'),
	(575,'2020-05-07 22:29:26','admin','用户登录'),
	(576,'2020-05-07 22:30:12','admin','删除菜单，result : true;menuId：31'),
	(577,'2020-05-07 22:30:17','admin','删除菜单，result : true;menuId：50'),
	(578,'2020-05-07 22:30:24','admin','用户登录'),
	(579,'2020-05-07 22:31:11','admin','保存菜单，result : true;menu：SysMenu(id=51, parent=30, name=设备设施管理, url=initOtherAssets.do, sort=4)'),
	(580,'2020-05-07 22:31:20','admin','保存权限记录，result : true;privilege：SysPrivilege(id=118, menuId=51, name=设备设施管理, url=initOtherAssets.do)'),
	(581,'2020-05-07 22:31:25','admin','用户登录'),
	(582,'2020-05-07 22:40:21','admin','用户登录'),
	(583,'2020-05-07 22:41:35','admin','新增或修改车辆管理:AssetsDO(id=0, projects=1, name=吊装压缩车, nameId=1, projectsName=保定, number=111, license=123, brand=2, brandStr=中联, carType=2, carTypeStr=中型, buyTime=Thu May 07 00:00:00 CST 2020, inOut=, quantity=1, quoQuantity=0, owner=1, storageLocation=, engineNumber=, engineNumberType=0, price=null, staging=0, purTax=null, tanxiao=null, zhejiu=null, remark=, creater=admin, createTime=Thu May 07 22:41:34 CST 2020, updateTime=null)'),
	(584,'2020-05-07 22:41:59',NULL,'新增或修改车辆管理:AssetsDO(id=8, projects=1, name=封闭转运车, nameId=2, projectsName=保定, number=111232, license=123, brand=2, brandStr=中联, carType=2, carTypeStr=中型, buyTime=Thu May 07 00:00:00 CST 2020, inOut=, quantity=1, quoQuantity=0, owner=1, storageLocation=, engineNumber=, engineNumberType=0, price=0.00, staging=0, purTax=0.00, tanxiao=0.00, zhejiu=0.00, remark=, creater=null, createTime=null, updateTime=Thu May 07 22:41:59 CST 2020)'),
	(585,'2020-05-07 22:43:41','admin','新增或修改设备设施:AssetsOtherDO(id=0, projects=2, type=3, typeStr=保洁车辆, name=人力三轮车, nameId=5, projectsName=阜平, brand=2, brandStr=资产, carType=2, carTypeStr=双桶, buyTime=Tue May 05 00:00:00 CST 2020, inOut=, quantity=0, quoQuantity=0, owner=3, storageLocation=, price=null, staging=0, tanxiao=null, zhejiu=null, remark=, creater=admin, createTime=Thu May 07 22:43:41 CST 2020, updateTime=null)'),
	(586,'2020-05-07 22:43:55',NULL,'新增或修改设备设施:AssetsOtherDO(id=4, projects=2, type=3, typeStr=保洁车辆, name=电动自行车, nameId=6, projectsName=阜平, brand=2, brandStr=资产, carType=2, carTypeStr=双桶, buyTime=Tue May 05 00:00:00 CST 2020, inOut=, quantity=10, quoQuantity=0, owner=3, storageLocation=, price=0.00, staging=0, tanxiao=0.00, zhejiu=0.00, remark=, creater=null, createTime=null, updateTime=Thu May 07 22:43:55 CST 2020)'),
	(587,'2020-05-07 22:46:01','admin','保存菜单，result : true;menu：SysMenu(id=40, parent=0, name=成本管理, url=#, sort=50)'),
	(588,'2020-05-07 22:46:14','admin','用户登录'),
	(589,'2020-05-07 22:48:08','admin','用户登录'),
	(590,'2020-05-07 22:48:38','admin','新增或修改收入:IncomeDO(id=0, projects=2, projectsName=阜平, incomeDay=Wed May 06 00:00:00 CST 2020, incomeAmount=100, contractNumb=11111, contractAmount=1010, jiafang=111, jiafangInfo=111, remark=111, creater=admin, createTime=Thu May 07 22:48:38 CST 2020, updateTime=null)'),
	(591,'2020-05-07 22:52:49','admin','用户登录'),
	(592,'2020-05-07 22:53:17','admin','用户登录'),
	(593,'2020-05-07 22:53:34',NULL,'新增或修改收入:IncomeDO(id=1, projects=2, projectsName=阜平, incomeDay=Tue May 05 00:00:00 CST 2020, incomeAmount=0, contractNumb=11111, contractAmount=1010.00, jiafang=111, jiafangInfo=111, remark=111, creater=null, createTime=null, updateTime=Thu May 07 22:53:34 CST 2020)'),
	(594,'2020-05-07 22:54:40',NULL,'新增或修改收入:IncomeDO(id=1, projects=2, projectsName=阜平, incomeDay=Tue May 05 00:00:00 CST 2020, incomeAmount=300.00, contractNumb=0, contractAmount=0.00, jiafang=111, jiafangInfo=111, remark=111, creater=null, createTime=null, updateTime=Thu May 07 22:54:40 CST 2020)'),
	(595,'2020-05-07 22:59:28','admin','用户登录'),
	(596,'2020-05-07 23:00:18','admin','新增或修改收入:IncomeDO(id=0, projects=3, projectsName=行唐, incomeDay=Thu May 07 00:00:00 CST 2020, incomeAmount=0, contractNumb=, contractAmount=0, jiafang=, jiafangInfo=, remark=, creater=admin, createTime=Thu May 07 23:00:17 CST 2020, updateTime=null)'),
	(597,'2020-05-07 23:00:43','admin','用户登录'),
	(598,'2020-05-07 23:01:15','admin','新增或修改收入:IncomeDO(id=0, projects=3, projectsName=行唐, incomeDay=Thu May 07 00:00:00 CST 2020, incomeAmount=100, contractNumb=, contractAmount=300, jiafang=, jiafangInfo=, remark=, creater=admin, createTime=Thu May 07 23:01:15 CST 2020, updateTime=null)'),
	(599,'2020-05-07 23:01:26',NULL,'新增或修改收入:IncomeDO(id=3, projects=3, projectsName=行唐, incomeDay=Thu May 07 00:00:00 CST 2020, incomeAmount=100.00, contractNumb=, contractAmount=300.01, jiafang=, jiafangInfo=, remark=, creater=null, createTime=null, updateTime=Thu May 07 23:01:26 CST 2020)'),
	(600,'2020-05-07 23:01:44',NULL,'新增或修改收入:IncomeDO(id=3, projects=3, projectsName=行唐, incomeDay=Thu May 07 00:00:00 CST 2020, incomeAmount=100.00, contractNumb=100.00, contractAmount=100.01, jiafang=, jiafangInfo=, remark=, creater=null, createTime=null, updateTime=Thu May 07 23:01:44 CST 2020)'),
	(601,'2020-05-07 23:03:10',NULL,'新增或修改收入:IncomeDO(id=3, projects=3, projectsName=行唐, incomeDay=Thu May 07 00:00:00 CST 2020, incomeAmount=100.02, contractNumb=100.00, contractAmount=100.00, jiafang=, jiafangInfo=, remark=, creater=null, createTime=null, updateTime=Thu May 07 23:02:51 CST 2020)'),
	(602,'2020-05-07 23:03:50',NULL,'新增或修改收入:IncomeDO(id=3, projects=3, projectsName=行唐, incomeDay=Thu May 07 00:00:00 CST 2020, incomeAmount=100.03, contractNumb=100.02, contractAmount=100.02, jiafang=, jiafangInfo=, remark=, creater=null, createTime=null, updateTime=Thu May 07 23:03:50 CST 2020)'),
	(603,'2020-05-07 23:06:14','admin','用户登录'),
	(604,'2020-05-07 23:06:35',NULL,'新增或修改收入:IncomeDO(id=3, projects=3, projectsName=行唐, incomeDay=Thu May 07 00:00:00 CST 2020, incomeAmount=100.03, contractNumb=200.03, contractAmount=300.03, jiafang=, jiafangInfo=, remark=, creater=null, createTime=null, updateTime=Thu May 07 23:06:34 CST 2020)'),
	(605,'2020-05-08 07:28:15','admin','用户登录'),
	(606,'2020-05-08 07:38:31','admin','用户登录'),
	(607,'2020-05-08 07:55:32','admin','用户登录'),
	(608,'2020-05-08 07:56:32','admin','用户登录'),
	(609,'2020-05-08 08:05:39','admin','用户登录'),
	(610,'2020-05-08 08:08:18','admin','用户登录'),
	(611,'2020-05-08 08:08:55','admin','用户登录'),
	(612,'2020-05-08 08:18:22','admin','用户登录'),
	(613,'2020-05-08 08:21:10','admin','用户登录'),
	(614,'2020-05-08 08:25:49','admin','用户登录'),
	(615,'2020-05-08 08:54:43','admin','用户登录'),
	(616,'2020-05-08 08:54:43','admin','用户登录'),
	(617,'2020-05-08 12:55:52','admin','用户登录'),
	(618,'2020-05-08 13:00:05','admin','用户登录'),
	(619,'2020-05-08 13:12:33','admin','用户登录'),
	(620,'2020-05-08 13:17:24','admin','用户登录'),
	(621,'2020-05-08 13:20:05','admin','用户登录'),
	(622,'2020-05-08 13:23:00','admin','用户登录'),
	(623,'2020-05-08 22:15:37','admin','用户登录'),
	(624,'2020-05-08 22:20:14','admin','用户登录'),
	(625,'2020-05-08 22:23:07','admin','用户登录'),
	(626,'2020-05-08 22:26:50','admin','用户登录'),
	(627,'2020-05-08 22:28:43','admin','用户登录'),
	(628,'2020-05-08 22:33:09','admin','用户登录'),
	(629,'2020-05-08 22:41:20','admin','用户登录'),
	(630,'2020-05-08 22:45:57','admin','用户登录'),
	(631,'2020-05-08 22:51:33','admin','用户登录'),
	(632,'2020-05-08 23:05:28','admin','用户登录'),
	(633,'2020-05-08 23:07:34','admin','用户登录'),
	(634,'2020-05-08 23:09:32','admin','用户登录'),
	(635,'2020-05-09 22:21:45','admin','用户登录'),
	(636,'2020-05-09 22:22:14','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=40, name=月度费用, url=initCostsTableMonth.do, sort=5)'),
	(637,'2020-05-09 22:22:27','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=62, name=月度费用, url=initCostsTableMonth.do)'),
	(638,'2020-05-09 22:22:40','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,124,125,126,105,116,120,104,119,121,122,109,118,127,108,129,115,123'),
	(639,'2020-05-09 22:22:43','admin','用户登录'),
	(640,'2020-05-09 22:27:26','admin','保存菜单，result : true;menu：SysMenu(id=0, parent=40, name=项目费用, url=initCostsTable.do, sort=11)'),
	(641,'2020-05-09 22:27:40','admin','保存权限记录，result : true;privilege：SysPrivilege(id=0, menuId=63, name=项目费用, url=initCostsTable.do)'),
	(642,'2020-05-09 22:27:49','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,124,125,126,105,116,120,104,119,121,122,109,118,127,108,129,130,115,123'),
	(643,'2020-05-09 22:27:53','admin','用户登录'),
	(644,'2020-05-09 22:32:48','admin','用户登录'),
	(645,'2020-05-09 22:42:27','admin','用户登录'),
	(646,'2020-05-09 22:56:43','admin','用户登录'),
	(647,'2020-05-09 23:01:57','admin','用户登录'),
	(648,'2020-05-09 23:07:55','admin','用户登录'),
	(649,'2020-05-09 23:12:03','admin','用户登录'),
	(650,'2020-05-09 23:21:59','admin','用户登录'),
	(651,'2020-05-09 23:36:48','admin','用户登录'),
	(652,'2020-05-09 23:38:25','admin','用户登录'),
	(653,'2020-05-09 23:40:23','admin','用户登录'),
	(654,'2020-05-09 23:55:12','admin','用户登录'),
	(655,'2020-05-10 00:04:47','admin','用户登录'),
	(656,'2020-05-10 08:51:37','admin','用户登录'),
	(657,'2020-05-10 08:55:05','admin','用户登录'),
	(658,'2020-05-10 09:00:49','admin','用户登录'),
	(659,'2020-05-10 09:04:13','admin','用户登录'),
	(660,'2020-05-10 09:06:31','admin','用户登录'),
	(661,'2020-05-10 09:08:24','admin','用户登录'),
	(662,'2020-05-10 09:09:21','admin','用户登录'),
	(663,'2020-05-10 09:24:22','admin','用户登录'),
	(664,'2020-05-10 09:31:49','admin','用户登录'),
	(665,'2020-05-10 09:33:38','admin','用户登录'),
	(666,'2020-05-10 09:35:39','admin','用户登录'),
	(667,'2020-05-10 09:38:12','admin','用户登录'),
	(668,'2020-05-10 09:40:31','admin','用户登录'),
	(669,'2020-05-10 09:46:06','admin','用户登录'),
	(670,'2020-05-10 09:51:15','admin','用户登录'),
	(671,'2020-05-10 09:56:09','admin','用户登录'),
	(672,'2020-05-10 10:06:26','admin','用户登录'),
	(673,'2020-05-10 10:23:23','admin','用户登录'),
	(674,'2020-05-10 10:31:01','admin','用户登录'),
	(675,'2020-05-10 10:33:37','admin','用户登录'),
	(676,'2020-05-10 10:37:27','admin','用户登录'),
	(677,'2020-05-10 10:40:00','admin','用户登录'),
	(678,'2020-05-10 10:42:41','admin','用户登录'),
	(679,'2020-05-10 11:39:20','admin','用户登录'),
	(680,'2020-05-10 12:12:29','admin','用户登录'),
	(681,'2020-05-10 12:15:19','admin','用户登录'),
	(682,'2020-05-10 12:17:23','admin','用户登录'),
	(683,'2020-05-10 12:23:52','admin','用户登录'),
	(684,'2020-05-10 12:28:15','admin','用户登录'),
	(685,'2020-05-10 12:31:20','admin','用户登录'),
	(686,'2020-05-10 12:37:57','admin','用户登录'),
	(687,'2020-05-10 12:41:36','admin','用户登录'),
	(688,'2020-05-10 12:48:22','admin','用户登录'),
	(689,'2020-05-10 12:53:29','admin','用户登录'),
	(690,'2020-05-10 12:59:44','admin','用户登录'),
	(691,'2020-05-10 13:21:04','admin','用户登录'),
	(692,'2020-05-10 13:24:53','admin','用户登录'),
	(693,'2020-05-10 13:28:49','admin','用户登录'),
	(694,'2020-05-10 13:36:33','admin','用户登录'),
	(695,'2020-05-10 13:42:23','admin','用户登录'),
	(696,'2020-05-10 14:27:50','admin','用户登录'),
	(697,'2020-05-10 14:34:28','admin','用户登录'),
	(698,'2020-05-10 14:35:48',NULL,'新增或修改收入:IncomeDO(id=3, projects=3, projectsName=行唐, incomeDay=Wed Jul 01 00:00:00 CST 2020, incomeAmount=10000, contractNumb=200.03, contractAmount=300.03, jiafang=, jiafangInfo=, remark=, creater=null, createTime=null, updateTime=Sun May 10 14:35:48 CST 2020)'),
	(699,'2020-05-10 14:36:02',NULL,'新增或修改收入:IncomeDO(id=1, projects=2, projectsName=阜平, incomeDay=Tue May 05 00:00:00 CST 2020, incomeAmount=3000, contractNumb=300.00, contractAmount=300.00, jiafang=111, jiafangInfo=111, remark=111, creater=null, createTime=null, updateTime=Sun May 10 14:36:02 CST 2020)'),
	(700,'2020-05-10 14:36:52',NULL,'新增或修改收入:IncomeDO(id=1, projects=1, projectsName=保定, incomeDay=Tue May 05 00:00:00 CST 2020, incomeAmount=3000.00, contractNumb=300.00, contractAmount=300.00, jiafang=111, jiafangInfo=111, remark=111, creater=null, createTime=null, updateTime=Sun May 10 14:36:52 CST 2020)'),
	(701,'2020-05-10 14:46:42','admin','用户登录'),
	(702,'2020-05-10 14:54:49','admin','用户登录'),
	(703,'2020-05-10 15:00:00','admin','用户登录'),
	(704,'2020-05-10 15:03:04','admin','用户登录'),
	(705,'2020-05-10 15:08:20','admin','用户登录'),
	(706,'2020-05-10 15:11:45','admin','用户登录'),
	(707,'2020-05-10 15:13:28','admin','用户登录'),
	(708,'2020-05-10 15:14:08','admin','用户登录'),
	(709,'2020-05-10 15:15:32','admin','用户登录'),
	(710,'2020-05-10 15:18:27','admin','用户登录'),
	(711,'2020-05-10 15:23:27','admin','用户登录'),
	(712,'2020-05-10 15:25:41','admin','用户登录'),
	(713,'2020-05-10 15:48:10','admin','用户登录'),
	(714,'2020-05-10 15:58:11','admin','用户登录'),
	(715,'2020-05-10 20:41:46','admin','用户登录'),
	(716,'2020-05-11 07:29:05','admin','用户登录'),
	(717,'2020-05-11 13:26:17','admin','用户登录'),
	(718,'2020-05-11 13:31:09','admin','用户登录'),
	(719,'2020-05-11 13:39:41','admin','用户登录'),
	(720,'2020-05-11 13:43:21','admin','用户登录'),
	(721,'2020-05-11 13:49:41','admin','用户登录'),
	(722,'2020-05-11 22:37:56','admin','用户登录'),
	(723,'2020-05-11 22:45:53','admin','用户登录'),
	(724,'2020-05-11 22:49:16','admin','用户登录'),
	(725,'2020-05-11 23:03:35','admin','用户登录'),
	(726,'2020-05-11 23:05:21','admin','用户登录'),
	(727,'2020-05-11 23:09:21','admin','用户登录'),
	(728,'2020-05-11 23:17:27','admin','用户登录'),
	(729,'2020-05-11 23:23:38','admin','用户登录'),
	(730,'2020-05-11 23:27:15','admin','用户登录'),
	(731,'2020-05-12 07:42:02','admin','用户登录'),
	(732,'2020-05-12 08:00:38','admin','用户登录'),
	(733,'2020-05-12 08:01:19','admin','删除菜单，result : true;menuId：56'),
	(734,'2020-05-12 08:01:26','admin','保存菜单，result : true;menu：SysMenu(id=48, parent=47, name=开支汇总, url=initCostCharts.do, sort=1)'),
	(735,'2020-05-12 08:01:35','admin','保存菜单，result : true;menu：SysMenu(id=48, parent=47, name=费用汇总, url=initCostCharts.do, sort=1)'),
	(736,'2020-05-12 08:02:03','admin','保存权限记录，result : true;privilege：SysPrivilege(id=115, menuId=48, name=费用汇总, url=initCostCharts.do)'),
	(737,'2020-05-12 08:02:17','admin','设置角色权限，角色：1;权限：1,2,6,7,8,9,3,10,11,12,4,13,14,15,16,5,101,100,103,102,124,125,126,105,116,120,104,119,121,122,109,118,127,108,129,130,115'),
	(738,'2020-05-12 08:02:21','admin','用户登录'),
	(739,'2020-05-12 08:05:42','admin','用户登录'),
	(740,'2020-05-12 08:17:49','admin','用户登录'),
	(741,'2020-05-12 12:28:17','admin','用户登录'),
	(742,'2020-05-12 12:30:58','admin','用户登录'),
	(743,'2020-05-12 12:35:13','admin','用户登录'),
	(744,'2020-05-12 12:57:58','admin','用户登录'),
	(745,'2020-05-12 13:00:39','admin','用户登录'),
	(746,'2020-05-12 13:02:45','admin','用户登录'),
	(747,'2020-05-12 13:11:00','admin','用户登录'),
	(748,'2020-05-12 13:14:53','admin','用户登录'),
	(749,'2020-05-12 13:16:21','admin','用户登录'),
	(750,'2020-05-12 13:19:10','admin','用户登录'),
	(751,'2020-05-12 13:22:38','admin','用户登录'),
	(752,'2020-05-12 18:49:45','admin','用户登录'),
	(753,'2020-05-12 18:52:54','admin','用户登录'),
	(754,'2020-05-12 19:01:09','admin','用户登录'),
	(755,'2020-05-12 19:03:48','admin','用户登录'),
	(756,'2020-05-12 19:07:11','admin','用户登录'),
	(757,'2020-05-12 22:06:42','admin','用户登录'),
	(758,'2020-05-12 22:10:40','admin','用户登录'),
	(759,'2020-05-12 22:13:56','admin','用户登录'),
	(760,'2020-05-12 22:18:16','admin','用户登录'),
	(761,'2020-05-12 22:32:57','admin','用户登录'),
	(762,'2020-05-12 22:35:41','admin','用户登录'),
	(763,'2020-05-12 22:41:02','admin','用户登录'),
	(764,'2020-05-12 22:44:39','admin','用户登录'),
	(765,'2020-05-12 22:46:37','admin','用户登录'),
	(766,'2020-05-12 22:51:14','admin','用户登录'),
	(767,'2020-05-12 22:54:02','admin','用户登录'),
	(768,'2020-05-12 22:57:26','admin','用户登录'),
	(769,'2020-05-13 08:26:39','admin','用户登录'),
	(770,'2020-05-13 12:31:49','admin','用户登录'),
	(771,'2020-05-13 12:52:47','admin','用户登录'),
	(772,'2020-05-13 12:56:28','admin','用户登录'),
	(773,'2020-05-13 19:23:55','admin','用户登录'),
	(774,'2020-05-13 19:37:09','admin','用户登录'),
	(775,'2020-05-13 19:51:32','admin','用户登录'),
	(776,'2020-05-13 19:56:04','admin','用户登录'),
	(777,'2020-05-13 20:16:25','admin','用户登录'),
	(778,'2020-05-13 20:20:59','admin','用户登录'),
	(779,'2020-05-13 20:31:52','admin','用户登录'),
	(780,'2020-05-13 21:41:37','admin','用户登录'),
	(781,'2020-05-13 21:44:42','admin','用户登录'),
	(782,'2020-05-13 21:47:00','admin','用户登录'),
	(783,'2020-05-13 21:54:36','admin','用户登录'),
	(784,'2020-05-13 21:59:16','admin','用户登录'),
	(785,'2020-05-13 22:00:46','admin','用户登录'),
	(786,'2020-05-13 22:04:52','admin','用户登录'),
	(787,'2020-05-13 22:08:47','admin','用户登录'),
	(788,'2020-05-14 08:11:23','admin','用户登录'),
	(789,'2020-05-14 08:17:36','admin','用户登录'),
	(790,'2020-05-14 08:20:55','admin','用户登录'),
	(791,'2020-05-14 08:39:41','admin','用户登录'),
	(792,'2020-05-14 08:48:08','admin','用户登录'),
	(793,'2020-05-14 08:49:48','admin','用户登录'),
	(794,'2020-05-14 08:52:44','admin','用户登录'),
	(795,'2020-05-14 08:52:44','admin','用户登录'),
	(796,'2020-05-14 18:35:29','admin','用户登录'),
	(797,'2020-05-14 18:43:29','admin','用户登录'),
	(798,'2020-05-14 19:01:46','admin','用户登录'),
	(799,'2020-05-14 19:06:45','admin','用户登录'),
	(800,'2020-05-14 19:11:24','admin','用户登录'),
	(801,'2020-05-14 21:29:15','admin','用户登录'),
	(802,'2020-05-14 21:37:07','admin','用户登录'),
	(803,'2020-05-14 21:45:47','admin','用户登录'),
	(804,'2020-05-14 21:47:32','admin','用户登录'),
	(805,'2020-05-14 21:52:41','admin','用户登录'),
	(806,'2020-05-14 21:57:12','admin','用户登录'),
	(807,'2020-05-14 21:59:28','admin','用户登录'),
	(808,'2020-05-14 22:03:30','admin','用户登录'),
	(809,'2020-05-14 22:09:11','admin','用户登录'),
	(810,'2020-05-14 22:12:11','admin','用户登录'),
	(811,'2020-05-14 22:18:22','admin','用户登录'),
	(812,'2020-05-14 22:24:10','admin','用户登录'),
	(813,'2020-05-14 22:26:57','admin','用户登录'),
	(814,'2020-05-14 22:31:07','admin','用户登录'),
	(815,'2020-05-14 22:34:58','admin','用户登录'),
	(816,'2020-05-14 22:40:19','admin','用户登录'),
	(817,'2020-05-14 22:42:52','admin','用户登录'),
	(818,'2020-05-14 22:46:11','admin','用户登录'),
	(819,'2020-05-15 08:10:45','admin','用户登录'),
	(820,'2020-05-17 16:48:36','admin','用户登录'),
	(821,'2020-05-17 16:49:13',NULL,'新增或修改设备设施:AssetsOtherDO(id=3, projects=2, type=2, typeStr=中转容器, name=压缩箱, nameId=1, projectsName=宝坻城区扫保, brand=3, brandStr=鑫鼎, carType=1, carTypeStr=4桶, buyTime=Sat Feb 01 00:00:00 CST 2020, inOut=, quantity=0, quoQuantity=0, owner=1, storageLocation=, price=0.00, staging=0, tanxiao=0.00, zhejiu=0.00, remark=, creater=null, createTime=null, updateTime=Sun May 17 16:49:12 CST 2020)'),
	(822,'2020-05-17 17:12:39','admin','用户登录'),
	(823,'2020-05-17 17:17:52','admin','用户登录'),
	(824,'2020-05-17 17:28:16','admin','用户登录');

/*!40000 ALTER TABLE `sys_logs` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_member
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_member`;

CREATE TABLE `sys_member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `imgs` varchar(200) DEFAULT NULL COMMENT '头像',
  `valid` tinyint(2) DEFAULT '0' COMMENT '0有效，1禁用',
  `pwd` varchar(32) DEFAULT NULL COMMENT '密码',
  `show_name` varchar(50) DEFAULT NULL COMMENT '显示名',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT 'email',
  `projects_id` int(10) DEFAULT '0' COMMENT '所属项目',
  `area_id` int(10) DEFAULT '0' COMMENT '所属区域',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

LOCK TABLES `sys_member` WRITE;
/*!40000 ALTER TABLE `sys_member` DISABLE KEYS */;

INSERT INTO `sys_member` (`id`, `name`, `imgs`, `valid`, `pwd`, `show_name`, `role_id`, `phone`, `email`, `projects_id`, `area_id`)
VALUES
	(1,'admin','',0,'21232f297a57a5a743894a0e4a801fc3','管理员',1,'123','admin@edu.com.cn',-1,0),
	(2,'zhangsan',NULL,0,'202cb962ac59075b964b07152d234b70','张三',1,'','admin',1,0);

/*!40000 ALTER TABLE `sys_member` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent` int(11) DEFAULT '0' COMMENT '父节点',
  `name` varchar(50) DEFAULT NULL COMMENT '节点名',
  `url` varchar(200) DEFAULT '#' COMMENT '地址',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;

INSERT INTO `sys_menu` (`id`, `parent`, `name`, `url`, `sort`)
VALUES
	(1,0,'系统管理','#',10),
	(2,1,'菜单管理','initMenu.do',1),
	(3,1,'角色管理','initRole.do',2),
	(4,1,'用户管理','initMemberManager.do',3),
	(5,1,'日志管理','initLog.do',4),
	(10,0,'基础信息','#',20),
	(11,10,'新增区域','initAreaEdit.do',1),
	(12,10,'区域管理','initArea.do',2),
	(13,10,'新增项目','initProjectsEdit.do',3),
	(14,10,'项目管理','initProjects.do',4),
	(20,0,'人事管理','#',30),
	(21,20,'新增人员','initEmployeeEdit.do',0),
	(22,20,'员工管理','initEmployee.do',3),
	(30,0,'资产管理','#',40),
	(32,30,'车辆管理','initAssets.do',2),
	(34,40,'费用管理','initCosts.do',4),
	(40,0,'成本管理','#',50),
	(47,0,'汇总管理','#',100),
	(48,47,'费用汇总','initCostCharts.do',1),
	(49,20,'人事申请','initEmpShenqing.do',1),
	(51,30,'设备设施管理','initOtherAssets.do',4),
	(52,20,'离职员工','initEmpLizhi.do',4),
	(53,20,'人事审核','initEmpShengHe.do',2),
	(54,20,'工资单管理','initPay.do',12),
	(55,20,'创建工资单','initPayEdit.do',11),
	(57,10,'合同管理','initContract.do',5),
	(58,10,'额定税金','initShuijin.do',6),
	(59,10,'管理费','initManagerPay.do',7),
	(60,40,'收入管理','initIncome.do',0),
	(62,40,'月度费用','initCostsTableMonth.do',5),
	(63,40,'项目费用','initCostsTable.do',11);

/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_privilege
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_privilege`;

CREATE TABLE `sys_privilege` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT '0',
  `name` varchar(50) DEFAULT NULL COMMENT '权限名',
  `url` varchar(50) DEFAULT NULL COMMENT '权限地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

LOCK TABLES `sys_privilege` WRITE;
/*!40000 ALTER TABLE `sys_privilege` DISABLE KEYS */;

INSERT INTO `sys_privilege` (`id`, `menu_id`, `name`, `url`)
VALUES
	(1,2,'访问菜单管理页','initMenu.do'),
	(2,2,'访问菜单权限页','initPrivileges.do'),
	(3,3,'访问角色管理页','initRole.do'),
	(4,4,'访问用户管理页','initMemberManager.do'),
	(5,5,'访问日志管理页','initLog.do'),
	(6,2,'新增编辑菜单功能','saveMenu.do'),
	(7,2,'删除菜单功能','deleteMenu.do'),
	(8,2,'编辑菜单下的权限','savePrivilege.do'),
	(9,2,'删除菜单下的权限','deletePrivilege.do'),
	(10,3,'编辑角色信息','saveRole.do'),
	(11,3,'删除角色','deleteRole.do'),
	(12,3,'为角色分配权限','saveRolePrivileges.do'),
	(13,4,'新增编辑用户','saveMember.do'),
	(14,4,'修改用户密码','saveMemberPwd.do'),
	(15,4,'启用或禁用用户','initEditMemberValid.do'),
	(16,4,'修改用户头像','saveMemberImgs.do'),
	(100,12,'访问区域管理页','initArea.do'),
	(101,11,'编辑区域管理页','saveArea.do'),
	(102,14,'访问项目管理页','initProjects.do'),
	(103,13,'编辑项目管理页','saveProjects.do'),
	(104,22,'访问员工管理页','initEmployee.do'),
	(105,21,'编辑员工信息页','initEmployeeEdit.do'),
	(108,34,'费用管理','initCosts.do'),
	(109,32,'资产信息','initAssets.do'),
	(115,48,'费用汇总','initCostCharts.do'),
	(116,49,'人事申请','initEmpShenqing.do'),
	(118,51,'设备设施管理','initOtherAssets.do'),
	(119,52,'离职员工','initEmpLizhi.do'),
	(120,53,'人事审核','initEmpShengHe.do'),
	(121,55,'编辑工资单','initPayEdit.do'),
	(122,54,'工资单管理','initPay.do'),
	(124,57,'合同管理','initContract.do'),
	(125,58,'额定税金','initShuijin.do'),
	(126,59,'管理费','initManagerPay.do'),
	(127,60,'收入管理','initIncome.do'),
	(129,62,'月度费用','initCostsTableMonth.do'),
	(130,63,'项目费用','initCostsTable.do');

/*!40000 ALTER TABLE `sys_privilege` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '角色名',
  `intro` varchar(300) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;

INSERT INTO `sys_role` (`id`, `name`, `intro`)
VALUES
	(1,'系统管理员','系统最高权限');

/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role_privilege
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role_privilege`;

CREATE TABLE `sys_role_privilege` (
  `role_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '角色',
  `privilege_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '权限',
  KEY `idx_member` (`role_id`),
  KEY `idx_privilege` (`privilege_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限表';

LOCK TABLES `sys_role_privilege` WRITE;
/*!40000 ALTER TABLE `sys_role_privilege` DISABLE KEYS */;

INSERT INTO `sys_role_privilege` (`role_id`, `privilege_id`)
VALUES
	(2,1),
	(2,2),
	(2,6),
	(2,7),
	(2,8),
	(2,9),
	(2,3),
	(2,10),
	(2,11),
	(2,12),
	(2,4),
	(2,13),
	(2,14),
	(2,15),
	(2,16),
	(1,1),
	(1,2),
	(1,6),
	(1,7),
	(1,8),
	(1,9),
	(1,3),
	(1,10),
	(1,11),
	(1,12),
	(1,4),
	(1,13),
	(1,14),
	(1,15),
	(1,16),
	(1,5),
	(1,101),
	(1,100),
	(1,103),
	(1,102),
	(1,124),
	(1,125),
	(1,126),
	(1,105),
	(1,116),
	(1,120),
	(1,104),
	(1,119),
	(1,121),
	(1,122),
	(1,109),
	(1,118),
	(1,127),
	(1,108),
	(1,129),
	(1,130),
	(1,115);

/*!40000 ALTER TABLE `sys_role_privilege` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
