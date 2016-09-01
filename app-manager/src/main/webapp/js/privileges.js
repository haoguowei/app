Ext.onReady(function(){
	Ext.QuickTips.init();
	var menuId = $("menuId").value;
	var title = '《' + $("menuName").value + '》的权限';
	
	//编辑权限窗口
	var pWindow = new com.custom.Window({
		width : 460,
		height : 250,
		contentEl : 'pWindow',
		buttons : [{
			text : '保存',
			handler : function(){
				var pName = $("pName").value;
				var pUrl = $("pUrl").value;
				
				if (_isNull(pName)) {
					alert("请填写权限名！");
					$("pName").focus();
					return false;
				}
				if (!regexVerify('chinese2',pName)) {
					alert("权限名含有非法字符！");
					$("pName").focus();
					return false;
				}
				
				if (_isNull(pUrl)) {
					alert("请填写权限URL！");
					$("pUrl").focus();
					return false;
				}
				
				if (!regexVerify('letter2',pUrl)) {
					alert("权限URL含有非法字符！");
					$("pUrl").focus();
					return false;
				}
				
				
				var url = "savePrivilege.do?pId=" + $("hidPriId").value
					+"&menuId=" + menuId
					+"&pName=" + pName
					+"&pUrl=" + pUrl;
					
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
		$("hidPriId").value = 0;
		$("pName").value = '';
		$("pUrl").value = '';
	};
		
	//查询
	this.searchFunc = function() {
		gridStore.load();
	};
	
	//新增
	this.addF = function(bt, privilegeId) {
		reset();
		pWindow.setTitle("新增权限");
		pWindow.show(bt);
	};
	
	//更新
	this.updateF = function(bt,privilegeId) {
		Ext.Ajax.request( {
			url : 'getPrivilegeById.do?privilegeId=' + privilegeId,
			success : function(response){
				var resp = Ext.util.JSON.decode(response.responseText);
				reset();
				
				$("hidPriId").value = resp.id;
				$("pName").value = resp.name;
				$("pUrl").value = resp.url;
				
				pWindow.setTitle("编辑权限:" + resp.id + "-" + resp.name);
				pWindow.show(bt);
			}
		});
	};
	
	//删除
	this.deleteF = function(bt, privilegeId) {
		if (confirm("确定要删除该权限吗？")) {
			Ext.Ajax.request({
				url : 'deletePrivilege.do?privilegeId=' + privilegeId,
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

	// 列表数据
	var gridStore = new Ext.data.JsonStore({
		url : 'searchPrivileges.do?menuId=' +( _isNull(menuId) ? 0 : menuId),
		root : 'resultList',
		remoteSort : false,
		totalProperty : 'total',
		fields : [ {
			name : 'id'
		}, {
			name : 'name'
		}, {
			name : 'url'
		} ]
	});
	
	var grid = new com.custom.GridPanel({
		store : gridStore,
		region : 'center',
		frame : false,
		border : false,
		autoHeight:true,
		columns: [ 
				{header:'ID', align:'center',sortable:false, dataIndex:'id'},
				{header:'权限名称', align:'left',sortable:false, dataIndex:'name'},
				{header:'权限URL', align:'left',sortable:false, dataIndex:'url'},
				{header:'操作', align:'left',sortable:false, dataIndex:'id',renderer:function(val,cell,record){
					var str = genButton("修改","updateF(this,"+val+")");
					str += genButton("删除","deleteF(this,"+val+")");
					return str;
				}}
				]
	}); 


		
	new Ext.Viewport({
		layout : 'border',
		items : [ {
			title : title,
			region : 'center',
			frame : false,
			border : true,
			autoScroll : true,
			items : [ grid ],
			tbar : [ {
				text : '新增权限',
				handler : function(b, e) {
					addF(this);
				}
			} ]
		} ]
	});

	searchFunc();
});