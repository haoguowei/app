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
		
		loader.on('beforeload',function(node){
			loader.dataUrl = "getPrivilegeTree.do?roleId=" + roleId;
		});
		
		//如果已经加载过了，需要重新加载，如果是第一次加载，则渲染tree的时候自动加载，此次补在重复加载
		if(self.tree.getRootNode().childNodes.length > 0){
			loader.load(self.tree.root, function(){
				self.tree.getRootNode().expand();
			});
		}else{
			self.tree.getRootNode().expand();
		}
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