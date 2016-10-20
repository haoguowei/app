Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	//-----------------权限相关 start-----------
	var urlSaveRole = "saveRole.do";
	var urlDelRole = "deleteRole.do";
	var urlSaveRolePrivileges = "saveRolePrivileges.do";
	
	//是否有权限
	var isPRISaveRole = isHavePRI(urlSaveRole);//编辑角色信息的权限
	var isPRIDelRole = isHavePRI(urlDelRole); //删除角色的权限
	var isPRISaveRolePrivileges = isHavePRI(urlSaveRolePrivileges); //角色分配权限的权限

	//-----------------权限相关 end-----------
	
	//编辑窗口
	var pWindow = new com.custom.Window({
		width : 460,
		height : 220,
		contentEl : 'pWindow',
		buttons : [{
			text : '保存',
			handler : function(){
				var rName = document.getElementById("rName").value;
				var rIntro = document.getElementById("rIntro").value;
				
				if (_isNull(rName)) {
					alert("请填写角色名！");
					document.getElementById("rName").focus();
					return false;
				}
				if (!regexVerify('chinese2',rName)) {
					alert("角色名含有非法字符！");
					document.getElementById("rName").focus();
					return false;
				}
				
				var url = urlSaveRole + "?rId=" + document.getElementById("hidRoleIdEdit").value
					+"&rName=" + rName
					+"&rIntro=" + rIntro;
					
				Ext.Ajax.request( {
					url : url,
					success : function(response){
						var resp = Ext.util.JSON.decode(response.responseText);
						if(resp.success){
							alert("操作成功！");
							pWindow.hide();
							searchFunc();
						}else{
							alert("操作失败：" + resp.resultTipMsg);
						}
					}
				});
			}
		},{
			text : '关闭',
			handler : function(){
				pWindow.hide();
			}
		}]
	});
	
	this.reset = function(){
		document.getElementById("hidRoleIdEdit").value = 0;
		document.getElementById("rName").value = '';
		document.getElementById("rIntro").value = '';
	};
	
	//新增
	this.addF = function(bt) {
		reset();
		pWindow.setTitle("新增角色");
		pWindow.show(bt);
	};
	
	//更新
	this.updateF = function(bt,roleId) {
		Ext.Ajax.request( {
			url : 'getRoleById.do?roleId=' + roleId,
			success : function(response){
				var resp = Ext.util.JSON.decode(response.responseText);
				reset();
				
				document.getElementById("hidRoleIdEdit").value = resp.id;
				document.getElementById("rName").value = resp.name;
				document.getElementById("rIntro").value = resp.intro;
				
				pWindow.setTitle("编辑角色:" + resp.id + "-" + resp.name);
				pWindow.show(bt);
			}
		});
	};
	
	//删除
	this.deleteF = function(bt, roleId) {
		if (confirm("确定要删除该角色吗？")) {
			Ext.Ajax.request({
				url : urlDelRole + '?roleId=' + roleId,
				success : function(response) {
					var resp = Ext.util.JSON.decode(response.responseText);
					if (resp.success) {
						alert("操作成功！");
						searchFunc();
					} else {
						alert("操作失败：" + resp.resultTipMsg);
					}
				}
			});
		}
	};

	//查询
	this.searchFunc = function() {
		gridStore.load();
	};
	
	//定义分配权限窗口
	var win = new WindowPrivileges("treeDiv", function(priIds){
		if (_isNull(document.getElementById("hidRoleId").value)) {
			alert("获取不到当前角色！");
			return false;
		}
		
		//保存角色权限
		var url = urlSaveRolePrivileges + "?roleId=" + document.getElementById("hidRoleId").value + "&priIds=" + priIds;
		Ext.Ajax.request( {
			url : url,
			success : function(response){
				var resp = Ext.util.JSON.decode(response.responseText);
				if(resp.success){
					alert("操作成功！");
					win.hide();
				}else{
					alert("操作失败：" + resp.resultTipMsg);
				}
			}
		});
	});
	
	//分配权限
	this.setRolePrivileges = function(bt,roleId) {
		document.getElementById("hidRoleId").value = roleId;
		win.loadTree(roleId);
		win.show(bt);
	};
	
	
	//列表数据
	var gridStore = new Ext.data.JsonStore({
	    url : 'searchRoles.do',
	    root : 'resultList',
	    remoteSort: false, 
	    totalProperty:'total',
	    fields: [
	       {name:'id'},
	       {name:'name'},
	       {name:'intro'}
        ]
    });
	
	var grid = new com.custom.GridPanel({
		store : gridStore,
		region : 'center',
		frame : false,
		border : false,
		autoHeight:true,
		columns: [ 
				{width:1,header:'ID', align:'center',sortable:false, dataIndex:'id'},
				{width:1,header:'角色', align:'center',sortable:false, dataIndex:'name'},
				{width:3,header:'角色描述', align:'left',sortable:false, dataIndex:'intro'},
				{width:2,header:'操作', align:'center',sortable:false, dataIndex:'id',renderer:function(val,cell,record){
					var str = '';
					if(isPRISaveRole){ //权限
						str += genButton("修改","updateF(this,"+val+")");
					}
					if(isPRIDelRole){ //权限
						str += genButton("删除","deleteF(this,"+val+")");
					}
					if(isPRISaveRolePrivileges){ //权限
						str += genButton("分配权限", 'setRolePrivileges(this,'+val+')');
					}
					return str;
				}}
		]
	}); 
	
	new Ext.Viewport({
		layout : 'border',
		items : [{
			title:'角色管理',
	        region : 'center',
			frame : false,
			border : true,
			autoScroll : true,
			items : [grid],
			tbar : [ {
				text : '新增角色',
				id:'bt_add',
				handler : function(b, e) {
					addF(this);
				}
			} ]
		}]
	});
	
	if(!isPRISaveRole){//权限
		Ext.getCmp("bt_add").hide();  
	}
	
	searchFunc();
});