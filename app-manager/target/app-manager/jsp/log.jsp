<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../import.jsp"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
</title>
	<script type="text/javascript">
	Ext.onReady(function(){
		Ext.QuickTips.init();
		
		this.searchFunc=function(){
			gridStore.setBaseParam("name", document.getElementById("name").value);
			gridStore.load();
		};
		
		//列表数据
		var gridStore = new Ext.data.JsonStore({
		    url : 'searchLogs.do',
		    root : 'resultList',
		    remoteSort: false, 
		    totalProperty:'total',
		    fields: [
		       {name:'id'},
		       {name:'operatorTime'},
		       {name:'operator'},
		       {name:'description'}
	        ],
	        baseParams : {
        		limit : 50,
        		name :''
			}
	    });
		
		var grid = new com.custom.GridPanel({
			store : gridStore,
			region : 'center',
			frame : false,
			border : false,
			autoHeight:true,
			columns: [ 
					{width:1,header:'ID', align:'center',sortable:false, dataIndex:'id'},
					{width:1,header:'操作人', align:'left',sortable:false, dataIndex:'operator'},
					{width:2,header:'操作时间', align:'center',sortable:false, dataIndex:'operatorTime',renderer:function(val){
						return Ext.util.Format.date(val,'Y-m-d H:i:s');
					}},
					{width:10,header:'描述', align:'left',sortable:false, dataIndex:'description',renderer:function(val, meta){
						meta.attr = 'style="white-space:normal;"';
						return "<div class='columnCSS'>"+val+"</div>";
					}}
			]
		}); 
		
		new Ext.Viewport({
			layout : 'border',
			items : [{
				region : 'north',
				title :  "操作日志",
				border : false,
				height : 75,
				keys : {
					key : Ext.EventObject.ENTER,
					fn : function(btn,e){
						searchFunc();
					}
				},
				contentEl : 'search_div_id'
			},{
		        region : 'center',
				frame : false,
				border : true,
				autoScroll : true,
				items : [grid],
				tbar : ['->',new Ext.PagingToolbar({
				            pageSize: 50,
				            store: gridStore,
				            style : {'border' : 0},
				            displayInfo: true
				        })]
			}]
		});
		
		searchFunc();
	});
	</script>
</head>
<body>
		<div id="search_div_id">
			<table class="Mytable">
				<tr>
					<td class="Myfont">
						关键词：
						<input type="text" name="name" id="name">
						<input type="button" value="搜索" class="Mybotton" onclick="searchFunc()">
					</td>
				</tr>
			</table>
		</div>
</body>
</html>