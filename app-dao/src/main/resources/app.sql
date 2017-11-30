/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.13-log : Database - app
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`app` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `app`;

/*Table structure for table `sys_logs` */

DROP TABLE IF EXISTS `sys_logs`;

CREATE TABLE `sys_logs` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `operator_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operator` varchar(50) DEFAULT NULL COMMENT '操作人',
  `description` varchar(500) DEFAULT NULL COMMENT '操作内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='操作日志';

/*Table structure for table `sys_member` */

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

/*Data for the table `sys_member` */

insert  into `sys_member`(`id`,`name`,`imgs`,`valid`,`pwd`,`show_name`,`role_id`,`phone`,`email`) values (1,'admin','',0,'21232f297a57a5a743894a0e4a801fc3','管理员',1,'123','admin@edu.com.cn');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent` int(11) DEFAULT '0' COMMENT '父节点',
  `name` varchar(50) DEFAULT NULL COMMENT '节点名',
  `url` varchar(200) DEFAULT '#' COMMENT '地址',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`parent`,`name`,`url`,`sort`) values (1,0,'系统管理','#',10),(2,1,'菜单管理','initMenu.do',1),(3,1,'角色管理','initRole.do',2),(4,1,'用户管理','initMemberManager.do',3),(5,1,'日志管理','initLog.do',4);

/*Table structure for table `sys_privilege` */

DROP TABLE IF EXISTS `sys_privilege`;

CREATE TABLE `sys_privilege` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT '0',
  `name` varchar(50) DEFAULT NULL COMMENT '权限名',
  `url` varchar(50) DEFAULT NULL COMMENT '权限地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Data for the table `sys_privilege` */

insert  into `sys_privilege`(`id`,`menu_id`,`name`,`url`) values (1,2,'访问菜单管理页','initMenu.do'),(2,2,'访问菜单权限页','initPrivileges.do'),(3,3,'访问角色管理页','initRole.do'),(4,4,'访问用户管理页','initMemberManager.do'),(5,5,'访问日志管理页','initLog.do'),(6,2,'新增编辑菜单功能','saveMenu.do'),(7,2,'删除菜单功能','deleteMenu.do'),(8,2,'编辑菜单下的权限','savePrivilege.do'),(9,2,'删除菜单下的权限','deletePrivilege.do'),(10,3,'编辑角色信息','saveRole.do'),(11,3,'删除角色','deleteRole.do'),(12,3,'为角色分配权限','saveRolePrivileges.do'),(13,4,'新增编辑用户','saveMember.do'),(14,4,'修改用户密码','saveMemberPwd.do'),(15,4,'启用或禁用用户','initEditMemberValid.do'),(16,4,'修改用户头像','saveMemberImgs.do');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '角色名',
  `intro` varchar(300) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`intro`) values (1,'系统管理员','系统最高权限');

/*Table structure for table `sys_role_privilege` */

DROP TABLE IF EXISTS `sys_role_privilege`;

CREATE TABLE `sys_role_privilege` (
  `role_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '角色',
  `privilege_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '权限',
  KEY `idx_member` (`role_id`),
  KEY `idx_privilege` (`privilege_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限表';

/*Data for the table `sys_role_privilege` */

insert  into `sys_role_privilege`(`role_id`,`privilege_id`) values (1,1),(1,2),(1,6),(1,7),(1,8),(1,9),(1,3),(1,10),(1,11),(1,12),(1,4),(1,13),(1,14),(1,15),(1,16),(1,5);


DROP TABLE IF EXISTS `tb_contract`;
CREATE TABLE `tb_contract` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `contract_no` VARCHAR(50) DEFAULT NULL COMMENT '总合同号',
  `delivery_date` VARCHAR(50) DEFAULT NULL COMMENT '交货日期',
  `tabulation_date` VARCHAR(50) DEFAULT NULL COMMENT '制表日期',
  `file_name` VARCHAR(50) DEFAULT NULL COMMENT '交货日期',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注说明',
  `user_name` VARCHAR(50) DEFAULT NULL COMMENT '系统登记人',
  `create_time` DATETIME DEFAULT  CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='合同表';

CREATE TABLE `tb_contract_product` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `contract_no` VARCHAR(50) DEFAULT NULL COMMENT '总合同号',
  `pro_no` VARCHAR(50) DEFAULT NULL COMMENT '产品编号(公司货号)',
  `pro_name` VARCHAR(100) DEFAULT NULL  COMMENT '配件名称',
  `pro_img` VARCHAR(200) DEFAULT NULL COMMENT '配件图片',
  `pro_units` VARCHAR(50) DEFAULT NULL COMMENT '单位（个、盆等）',
  `pro_gross` double(16,2)  DEFAULT 0 COMMENT '合同生产量',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注说明',
  `user_name` VARCHAR(50) DEFAULT NULL COMMENT '系统登记人',
  `create_time` DATETIME DEFAULT  CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='合同的产品表';

CREATE TABLE `tb_product_parts` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `contract_no` VARCHAR(50) DEFAULT NULL COMMENT '总合同号',
  `pro_no` VARCHAR(50) DEFAULT NULL COMMENT '产品编号(公司货号)',
  `parts_no` VARCHAR(50) DEFAULT NULL COMMENT '配件编号（系统生成）',
  `parts_name` VARCHAR(50) DEFAULT NULL  COMMENT '配件名称',
  `parts_fmt` VARCHAR(50) DEFAULT NULL  COMMENT '配件规格',
  `parts_img` VARCHAR(200) DEFAULT NULL COMMENT '配件图片',
  `units` VARCHAR(50) DEFAULT NULL COMMENT '单位（个、斤、枝、条等等）',
  `hetong_num` double(16,2)  DEFAULT 0 COMMENT '本合同量',
  `kucun_num` double(16,2) DEFAULT 0 COMMENT '库存存量',
  `buy_num` double(16,2)  DEFAULT 0 COMMENT '购买数量',
  `is_receive` INT(11) NOT NULL DEFAULT 0 COMMENT '是否收货收齐，0否，1是',
  `is_sendout` INT(11) NOT NULL DEFAULT 0 COMMENT '是否外发发完，0否，1是',
  `receive_num` double(16,2) DEFAULT 0 COMMENT '已经收货的量',
  `sendout_num` double(16,2) DEFAULT 0 COMMENT '已经外发的量',
  `sk_sumkg` double(16,2) DEFAULT NULL COMMENT '水口料回收总重量（斤）',
  `sk_hszb` double(16,2) DEFAULT NULL COMMENT '水口料可回收占比',
  `sk_hskg` double(16,2) DEFAULT NULL COMMENT '水口料回收重量（斤）',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注说明',
  `user_name` VARCHAR(50) DEFAULT NULL COMMENT '系统登记人',
  `create_time` DATETIME DEFAULT  CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='产品的配件表';



DROP TABLE IF EXISTS `tb_parts_receive_sendout`;
CREATE TABLE `tb_parts_receive_sendout` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `contract_no` VARCHAR(50) DEFAULT NULL COMMENT '合同编号',
  `pro_no` VARCHAR(50) DEFAULT NULL COMMENT '产品编号(公司货号)',
  `parts_no` VARCHAR(50) DEFAULT NULL COMMENT '配件编号',
  `handling_man` VARCHAR(50) DEFAULT NULL COMMENT '经手人',
  `date` VARCHAR(50) DEFAULT NULL COMMENT '日期',
  `TYPE` INT(11) NOT NULL DEFAULT 0 COMMENT '0收货，1外发，2外发收货',
  `num`  double(16,2) DEFAULT 0 COMMENT '数量',
  `process_man` VARCHAR(50) DEFAULT NULL COMMENT '加工户',
  `times` INT(11) NOT NULL DEFAULT 1 COMMENT '次数',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注说明',
  `user_name` VARCHAR(50) DEFAULT NULL COMMENT '系统登记人',
  `user_id` INT(11) DEFAULT 0 COMMENT '系统登记人',
 `create_time` DATETIME DEFAULT  CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='配件的收货外发信息表';
