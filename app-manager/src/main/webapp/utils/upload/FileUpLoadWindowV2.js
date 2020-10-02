/**
 * 文件上传控件
 */
var FileUpLoadWindowV2 = function (callback) {
    var self = this;
    this.uploadForm = new Ext.form.FormPanel({
        fileUpload: true,
        frame: false,
        bodyStyle: 'padding: 20px 10px 0 10px;',
        height: 300,
        width: 400,
        defaults: {
            anchor: '95%',
            allowBlank: false
        },
        items: [{
            xtype: 'fileuploadfield',
            id: 'form-file',
            emptyText: '选择文件...',
            fieldLabel: '选择文件',
            name: 'file',
            buttonText: '浏览...'
        }],
        buttons: [{
            text: '上传',
            handler: function () {
                var fileForm = self.uploadForm.getForm();
                if (fileForm.isValid()) {
                    fileForm.submit({
                        url: 'fileUploadV2.do',
                        waitMsg: '正在上传，请等待...',
                        success: function (fp, action) {
                            callback(fp, action);
                        },
                        failure: function (form, action) {
                            var val = action.result.msg;
                            if (val == null || val == 'undefined' || val == 'null' || val == '') {
                                alert("操作失败，请联系管理员！");
                            } else {
                                alert(action.result.msg);
                            }
                        }
                    });
                }
            }
        }, {
            text: '取消',
            handler: function () {
                self.hide();
            }
        }]
    });

    FileUpLoadWindowV2.superclass.constructor.call(this, {
        title: '文件上传',
        width: 420,
        height: 150,
        items: [self.uploadForm]
    });
};

/**
 * 文件上传
 * 该控件依赖ext_basex.js，custom.js
 *
 * var pic = new FileUpLoadWindowV2(function(fp, response){
 * 		if(response.result.success){
 * 			alert("成功");
 * 		}
 * })
 */
Ext.extend(FileUpLoadWindowV2, com.custom.Window, {});