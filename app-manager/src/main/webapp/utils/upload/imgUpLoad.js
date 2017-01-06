	/**
	 * 图片上传控件
	 * type : 允许上传的图片类型
	 * size : 允许上传的图片大小（kb）
	 */
	var ImgUpLoadWindow = function(callback){
		var self = this;
		this.uploadForm = new Ext.form.FormPanel({
			fileUpload : true,
			frame : false,
			bodyStyle : 'padding: 20px 10px 0 10px;',
			height : 300,
			width : 400,
			defaults : {
				anchor : '95%',
				allowBlank : false
			},
			items : [ {
				xtype : 'fileuploadfield',
				id : 'form-file',
				emptyText : '选择文件...',
				fieldLabel : '选择文件',
				name : 'file',
				buttonText : '浏览...'
			} ],
			buttons : [ {
				text : '上传',
				handler : function() {
					var fileForm = self.uploadForm.getForm();
					if (fileForm.isValid()) {
						fileForm.submit({
							url : 'fileUpload.do',
							waitMsg : '正在上传，请等待...',
							success : function(fp, action) {
								callback(fp,action);
							},
							failure : function(form, action) {
								var val = action.result.msg;
								if (val == null || val == 'undefined' || val == 'null' || val == '') {
									alert("操作失败，可能原因：\n\t1.图片格式错误，请上传“jpg”或“png”格式的图片！\n\t2.图片太大！请上传小于8M的图片！\n\t3.联系管理员！");
								} else {
									alert(action.result.msg);
								}
						    }
						});
					}
				}
			}, {
				text : '取消',
				handler : function() {
					self.hide();
				}
			} ]
		});
		ImgUpLoadWindow.superclass.constructor.call(this, {
			title : '上传文件',
			width : 400,
			height : 150,
			items : [self.uploadForm]
		});
	};
	
	/**
	 * 图片上传
	 * 该控件依赖ext_basex.js，custom.js
	 * 用法：
	 * type : 允许上传的图片类型
	 * size : 允许上传的图片大小（kb）
	 * 
	 * var picWindow = new ImgUpLoadWindow(type, size,function(fp, response){
	 * 		if(response.result.success){
	 * 			alert("成功");
	 * 		}
	 * })
	 */
	Ext.extend(ImgUpLoadWindow, com.custom.Window, {});