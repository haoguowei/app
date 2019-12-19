
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户表';



DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent` int(11) DEFAULT '0' COMMENT '父节点',
  `name` varchar(50) DEFAULT NULL COMMENT '节点名',
  `url` varchar(200) DEFAULT '#' COMMENT '地址',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单表';


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
	(21,20,'新增员工','initEmployeeEdit.do',1),
	(22,20,'员工管理','initEmployee.do',2),
	(30,0,'资产管理','#',40),
	(31,30,'录入资产','initAssetsEdit.do',1),
	(32,30,'资产信息','initAssets.do',2),
	(33,30,'录入消费','initYYCostEdit.do',3),
	(34,30,'消费汇总','initYYCost.do',4),
	(40,0,'财务管理','#',50),
	(41,40,'录入收支','initFinanceEdit.do',1),
	(42,40,'收支清单','initFinance.do',2);


DROP TABLE IF EXISTS `sys_privilege`;

CREATE TABLE `sys_privilege` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT '0',
  `name` varchar(50) DEFAULT NULL COMMENT '权限名',
  `url` varchar(50) DEFAULT NULL COMMENT '权限地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';


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
	(105,21,'编辑员工信息页','saveEmployee.do');
