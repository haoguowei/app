/**
 * 选择角色权限窗
 * @param treeDiv tree的div
 * @returns
 */
WindowPrivileges = function(treeDiv){
	
	var self = this;
	
	var HEIGHT = 600;
	var WIDTH = 400;
	
	//菜单树
	this.tree = new com.custom.TreePanel({
		el :  treeDiv,
		width : WIDTH,
		height: HEIGHT,
		root : {
			id : 0,
			text : '系统权限',
			leaf : false,
			checked : null
		},
		loader : new Ext.tree.TreeLoader( {
			dataUrl : "getPrivilegeTree.do?roleId=" + 1
		})
	});
	
	this.loadTree = function(){
		//根节点展开
		self.tree.getRootNode().expand();
		//页面渲染
		self.tree.render();
	};
	
	WindowPrivileges.superclass.constructor.call(this, {
		title : '角色权限',
		width : WIDTH,
		height : HEIGHT,
		layout : 'fit',
		items : [self.tree],
		buttons : [{
			text : '保存',
			handler : function(){
				
			}
		},{
			text : '关闭',
			handler : function(){
				self.hide();
			}
		}]
	});
	
	this.on('beforeshow',function(){
		self.loadTree();
	});
	
};

Ext.extend(WindowPrivileges, com.custom.Window, {});