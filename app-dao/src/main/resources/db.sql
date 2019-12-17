CREATE TABLE `finance` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `income_amount` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '收入金额',
  `payout_amount` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '支出金额',
  `year` int(10) NOT NULL DEFAULT 0 COMMENT '年份',
  `month` int(10) NOT NULL DEFAULT 0 COMMENT '月份',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='金额';

CREATE TABLE `employee` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `status` int(10) NOT NULL DEFAULT 0 COMMENT '状态；0-正常；1-离职',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `phone` varchar(50) NOT NULL DEFAULT '' COMMENT '手机号',
  `id_card` varchar(50) NOT NULL DEFAULT '' COMMENT '身份证号',
  `projects` int(10) NOT NULL DEFAULT 0 COMMENT '所属项目',
  `job_type` int(10) NOT NULL DEFAULT 0 COMMENT '经理/主管/文员/助理/队长/司机/装卸工/保洁员',
  `gender` int(10) NOT NULL DEFAULT 0 COMMENT '性别；0-男；1-女',
  `ethnic` int(10) NOT NULL DEFAULT 0 COMMENT '民族',
  `age` int(10) NOT NULL DEFAULT 0 COMMENT '年龄',
  `birth_date` date NOT NULL DEFAULT '0000-01-01' COMMENT '出生日期',
  `entry_date` date NOT NULL DEFAULT '0000-01-01' COMMENT '入职日期',
  `leave_date` date NOT NULL DEFAULT '0000-01-01' COMMENT '离职日期',
  `edu_type` int(10) NOT NULL DEFAULT 0 COMMENT '学历？',
  `hukou_type` int(10) NOT NULL DEFAULT 0 COMMENT '0-农村；1-城镇',
  `huji_address` varchar(200) NOT NULL DEFAULT '' COMMENT '户籍地址',
  `address` varchar(200) NOT NULL DEFAULT '' COMMENT '现住地址',
  `emergency_contact` varchar(50) NOT NULL DEFAULT '' COMMENT '紧急联系人',
  `emergency_contact_phone` varchar(50) NOT NULL DEFAULT '' COMMENT '紧急联系人手机号',
  `safe_type` varchar(50) NOT NULL DEFAULT '' COMMENT '保险类型',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='人事';

CREATE TABLE `assets` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `type` int(10) NOT NULL DEFAULT 0 COMMENT '资产类型-车辆/电动人力/箱/桶/辅助类',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `number` varchar(50) NOT NULL DEFAULT '' COMMENT '资产编号',
  `license` varchar(50) NOT NULL DEFAULT '' COMMENT '牌照号',
  `brand` varchar(50) NOT NULL DEFAULT '' COMMENT '品牌',
  `car_type` varchar(50) NOT NULL DEFAULT '' COMMENT '规格型号（车型）',
  `buy_time` date NOT NULL DEFAULT '0000-01-01' COMMENT '采购时间',
  `in_out` varchar(200) NOT NULL DEFAULT '' COMMENT '转入/借用',
  `quantity` int(10) NOT NULL DEFAULT 0 COMMENT '数量',
  `quo_quantity` int(10) NOT NULL DEFAULT 0 COMMENT '现况数量',
  `owner` int(10) NOT NULL DEFAULT 0 COMMENT '责任人',
  `storage_location` varchar(200) NOT NULL DEFAULT '' COMMENT '存放地点',
  `engine_number` varchar(50) NOT NULL DEFAULT '' COMMENT '发动机号',
  `engine_number_type` int(10) NOT NULL DEFAULT 0 COMMENT '号牌种类',
  `price` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '单价',
  `staging` int(11) NOT NULL DEFAULT 0 COMMENT '分期月数？',
  `pur_tax` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '购置税',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='资产';

CREATE TABLE `projects` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父id',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='项目';


CREATE TABLE `yy_cost` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `enter_date` date NOT NULL DEFAULT '0000-01-01' COMMENT '录入日期',
  `license` varchar(50) NOT NULL DEFAULT '' COMMENT '牌照号',
  `status` int(10) NOT NULL DEFAULT 0 COMMENT '状态；0-正常；1-上报',
  `projects` int(10) NOT NULL DEFAULT 0 COMMENT '所属项目',
  `start_mileage` int(10) NOT NULL DEFAULT 0 COMMENT '日初里程数',
  `end_mileage` int(10) NOT NULL DEFAULT 0 COMMENT '日末里程数',
  `workload` int(10) NOT NULL DEFAULT 0 COMMENT '作业量',
  `fuel` int(10) NOT NULL DEFAULT 0 COMMENT '加油量',
  `fuel_amount` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '加油金额',
  `baoyang_amount` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '保养费用',
  `fix_amount` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '维修费用',
  `fix_factory` varchar(200) NOT NULL DEFAULT '' COMMENT '维修厂',
  `shigu_times` int(10) NOT NULL DEFAULT 0 COMMENT '事故次数',
  `shigu_amount` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '事故金额',
  `shigu_out_amount` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '事故保险外赔偿金额',
  `baoxian_amount` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '保险费用',
  `year_check_amount` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '年检费用',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `creater` varchar(200) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='运营费用';