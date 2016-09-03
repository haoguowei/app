DROP TABLE IF EXISTS `sys_logs`;
CREATE TABLE `sys_logs` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `operator_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operator` varchar(50) DEFAULT NULL COMMENT '操作人',
  `description` varchar(500) DEFAULT NULL COMMENT '操作内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='操作日志';

DROP TABLE IF EXISTS `sys_member`;
CREATE TABLE `sys_member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `valid` tinyint(2) DEFAULT '0' COMMENT '0有效，1禁用',
  `pwd` varchar(32) DEFAULT NULL COMMENT '密码',
  `show_name` varchar(50) DEFAULT NULL COMMENT '显示名',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT 'email',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

LOCK TABLES `sys_member` WRITE;
INSERT INTO `sys_member` VALUES (1,'admin',0,'21232f297a57a5a743894a0e4a801fc3','管理员',1,'123','admin@edu.com.cn');
UNLOCK TABLES;

DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent` int(11) DEFAULT '0' COMMENT '父节点',
  `name` varchar(50) DEFAULT NULL COMMENT '节点名',
  `url` varchar(200) DEFAULT '#' COMMENT '地址',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

LOCK TABLES `sys_menu` WRITE;
INSERT INTO `sys_menu` VALUES (1,0,'系统管理','#',10),(2,1,'菜单管理','initMenu.do',1),(3,1,'角色管理','initRole.do',2),(4,1,'用户管理','initMemberManager.do',3),(5,1,'日志管理','initLog.do',4);
UNLOCK TABLES;

DROP TABLE IF EXISTS `sys_privilege`;
CREATE TABLE `sys_privilege` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT '0',
  `name` varchar(50) DEFAULT NULL COMMENT '权限名',
  `url` varchar(50) DEFAULT NULL COMMENT '权限地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='权限表';

LOCK TABLES `sys_privilege` WRITE;
INSERT INTO `sys_privilege` VALUES (1,2,'访问菜单管理页','initMenu.do'),(2,2,'访问菜单权限页','initPrivileges.do'),(3,3,'访问角色管理页','initRole.do'),(4,4,'访问用户管理页','initMemberManager.do'),(5,5,'访问日志管理页','initLog.do'),(6,2,'新增编辑菜单功能','saveMenu.do'),(7,2,'删除菜单功能','deleteMenu.do'),(8,2,'编辑菜单下的权限','savePrivilege.do'),(9,2,'删除菜单下的权限','deletePrivilege.do'),(10,3,'编辑角色信息','saveRole.do'),(11,3,'删除角色','deleteRole.do'),(12,3,'为角色分配权限','saveRolePrivileges.do'),(13,4,'新增编辑用户','saveMember.do'),(14,4,'修改用户密码','saveMemberPwd.do'),(15,4,'启用或禁用用户','initEditMemberValid.do');
UNLOCK TABLES;


DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '角色名',
  `intro` varchar(300) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

LOCK TABLES `sys_role` WRITE;
INSERT INTO `sys_role` VALUES (1,'系统管理员','系统最高权限');
UNLOCK TABLES;

DROP TABLE IF EXISTS `sys_role_privilege`;
CREATE TABLE `sys_role_privilege` (
  `role_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '角色',
  `privilege_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '权限',
  KEY `idx_member` (`role_id`),
  KEY `idx_privilege` (`privilege_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限表';

LOCK TABLES `sys_role_privilege` WRITE;
INSERT INTO `sys_role_privilege` VALUES (1,1),(1,2),(1,6),(1,7),(1,8),(1,9),(1,3),(1,10),(1,11),(1,12),(1,4),(1,13),(1,14),(1,15),(1,5);
UNLOCK TABLES;
