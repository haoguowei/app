/**
 * 选择角色权限窗
 * @param treeDiv tree的div
 * @param callback 回调保存
 * @returns
 */
WindowPrivileges = function(treeDiv, callback){
	
	var self = this;
	
	var HEIGHT = 600;
	var WIDTH = 400;
	
	//菜单树
	this.tree = new com.custom.TreePanel({
		el :  treeDiv,
		width : WIDTH,
		height: HEIGHT,
		root : new Ext.tree.AsyncTreeNode({
			id : 0,
			text : '系统权限',
			leaf : false,
			checked : null
		}),
		loader : new Ext.tree.TreeLoader( {
			dataUrl : "#"
		})
	});
	
	//加载树
	this.loadTree = function(roleId) {
		var loader = self.tree.getLoader();
		loader.dataUrl = "getPrivilegeTree.do?roleId=" + roleId;
		self.tree.getRootNode().reload();
	};

	//得到选择的权限节点id
	this.getSelectedNodes = function() {
		var nodes = self.tree.getChecked();
		if (nodes == null || nodes.length == 0) {
			return null;
		}

		var ids = new Array();
		for (var i = 0; i < nodes.length; i++) {
			if(nodes[i].isLeaf()){
				ids.push(nodes[i].id);
			}
		}
		return ids;
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
				callback(self.getSelectedNodes());
			}
		},{
			text : '关闭',
			handler : function(){
				self.hide();
			}
		}]
	});
	
	this.on('beforeshow',function(){
	});
	
};

Ext.extend(WindowPrivileges, com.custom.Window, {});