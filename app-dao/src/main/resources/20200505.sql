DROP TABLE yunying_cost;
DROP TABLE other_cost;
DROP TABLE yy_cost;
DROP TABLE income;

ALTER TABLE `assets` ADD `name_id` INT  NULL  DEFAULT NULL COMMENT '名称id' AFTER `name`;
ALTER TABLE `assets_other` ADD `name_id` INT  NULL  DEFAULT NULL COMMENT '名称id' AFTER `name`;


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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='收入管理';


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
  `use` varchar(200) NOT NULL DEFAULT '' COMMENT '费用用途',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='费用';

CREATE TABLE `costs_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT 'name',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='费用类型';

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