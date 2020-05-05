DROP TABLE yunying_cost;
DROP TABLE other_cost;
DROP TABLE yy_cost;


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