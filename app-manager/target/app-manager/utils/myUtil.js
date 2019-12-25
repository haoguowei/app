/**
 * 得到 选择grid的一条记录
 * @param grid
 * @returns
 */
function _getGridSelectedOne(grid) {
	if (grid.getSelectionModel().getCount() == 0) {
		alert('您至少需要选择一个目标！');
		return null;
	}
	if (grid.getSelectionModel().getCount() > 1) {
		alert('您只能选择一个目标！');
		return null;
	}
	return grid.getSelectionModel().getSelections()[0];
}

//页面返回
function _back() {
	var ua = navigator.userAgent.toLowerCase();
	//ie
	if (ua.indexOf("msie") > -1) {
		window.history.go(-1);
		window.location.reload();
	} else {
		var url = document.referrer;
		if (!_isNull(url)) {
			location.replace(url);
		}
		;
	}
}

/**
 * 得到多选选择的grid记录的id数组
 * 
 * @param grid
 * @returns {Array}
 */
function _getGridSelectedIds(grid) {
	if (grid.getSelectionModel().getCount() == 0) {
		alert('您至少需要选择一个目标！');
		return null;
	}
	var res = new Array();
	var arr = grid.getSelectionModel().getSelections();
	for (var i = 0; i < arr.length; i++) {
		res.push(arr[i].data.id);
	}
	return res;
}

/**
 * grid刷新后去掉grid上的全选选中状态
 * 用法：
 * var gridStore = new Ext.data.JsonStore({
 * 	 listeners : {
 * 	    	'load':function(){
 * 	    		utilClearHeadCheckBox(grid);
 * 	    	}
 * 	    }
 *  })
 * @param grid
 */
function _clearHeadCheckBox(grid) {
	var hd_checker = grid.getEl().select('div.x-grid3-hd-checker');
	var hd = hd_checker.first();
	if (hd.hasClass('x-grid3-hd-checker-on')) {
		hd.removeClass('x-grid3-hd-checker-on');
	}
}

/**
 * Ext得到radio值
 * 参数为Ext.form.RadioGroup的id
 */
function _getRadioValue(id) {
	var val = 0;
	Ext.getCmp(id).eachItem(function(item) {
		if (item.checked == true) {
			val = item.inputValue;
		}
	});
	return val;
};

/**
 * js得到redio的值
 * @param RadioName
 * @returns
 */
function _getRadioValueJS(RadioName) {
	var obj = document.getElementsByName(RadioName);
	if (obj != null) {
		for (var i = 0; i < obj.length; i++) {
			if (obj[i].checked) {
				return obj[i].value;
			}
		}
	}
	return null;
}

/**
 * 判断是否为空
 */
function _isNull(val) {
	if (val == null || val == 'undefined' || val == 'null' || val == '') {
		return true;
	} else {
		return false;
	}
};

/**
 * 日期格式化并tip显示
 */
var _showFmtDate = function(value) {
	if (value == null) {
		value = "";
	}
	value = Ext.util.Format.date(value, 'Y-m-d H:i:s');
	return '<span style="display:table;width:100%;" qtip="' + value + '">'
			+ value + '</span>';
};

/**
 * Ext的ajax同步请求
 * @param url
 * @returns
 */
function _ajaxSyncRequest(url) {
	var request = Ext.lib.Ajax.getConnectionObject().conn;
	request.open('POST', url, false);
	request.send(null);
	return request.responseText;
}

/**
 * 清除select框中的options
 * @param selectObj
 */
function _cleanSelect(selectObj) {
	var len = selectObj.options.length;
	for (var i = 0; i < len; i++) {
		selectObj.options.remove(selectObj.options[i]);
	}
}

/**
 * 全选
 * @param obj
 * @param name
 */
function selectAll(obj, name) {
	var names = document.getElementsByName(name);
	var arr = new Array();
	for (var i = 0; i < names.length; i++) {
		names[i].checked = obj.checked;
	}
};

/**
 * 根据name得到checkbox的所有要提交的值
 */
function _getSelectValues(name) {
	var names = document.getElementsByName(name);
	var arr = new Array();
	for (var i = 0; i < names.length; i++) {
		if (names[i].checked) {
			arr.push(names[i].value);
		}
	}
	return arr;
}
