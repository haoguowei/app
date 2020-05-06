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