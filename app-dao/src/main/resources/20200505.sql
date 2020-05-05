DROP TABLE yunying_cost;
DROP TABLE other_cost;
DROP TABLE yy_cost;

CREATE TABLE `costs` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `enter_date` DATE NOT NULL DEFAULT '0000-01-01' COMMENT '录入日期',
  `employee_id` INT(11) DEFAULT NULL COMMENT '员工',
  `employee_name` VARCHAR(50) DEFAULT NULL COMMENT '员工',
  `status` INT(10) NOT NULL DEFAULT '0' COMMENT '状态；0-正常；1-上报',
  `projects` INT(10) NOT NULL DEFAULT '0' COMMENT '所属项目',
  `start_mileage` INT(10) NOT NULL DEFAULT '0' COMMENT '日初里程数',
  `end_mileage` INT(10) NOT NULL DEFAULT '0' COMMENT '日末里程数',
  `workload` INT(10) NOT NULL DEFAULT '0' COMMENT '作业量',
  `fuel` INT(10) NOT NULL DEFAULT '0' COMMENT '加油量',
  `fuel_amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '加油金额',
  `baoyang_amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '保养费用',
  `fix_amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '维修费用',
  `fix_factory` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '维修厂',
  `shigu_times` INT(10) NOT NULL DEFAULT '0' COMMENT '事故次数',
  `shigu_amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '事故金额',
  `shigu_out_amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '事故保险外赔偿金额',
  `baoxian_amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '保险费用',
  `year_check_amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '年检费用',
  `remark` VARCHAR(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `projects_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '备注',
  `asset_id` INT(11) DEFAULT NULL COMMENT '关联资产',
  `total_amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '总费用',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='运营费用';