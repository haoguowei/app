	/**
	 * 命名空间
	 */
	Ext.namespace("com.custom");
	
	/**
	 * 日期控件只显示年月的插件
	 * @memberOf {TypeName} 
	 * @return {TypeName} 
	 */
	Ext.ux.MonthPickerPlugin = function() {
		var picker;
		var oldDateDefaults;
	
		this.init = function(pk) {
			picker = pk;
			picker.onTriggerClick = picker.onTriggerClick.createSequence(onClick);
			picker.getValue = picker.getValue.createInterceptor(setDefaultMonthDay)
					.createSequence(restoreDefaultMonthDay);
			picker.beforeBlur = picker.beforeBlur.createInterceptor(
					setDefaultMonthDay).createSequence(restoreDefaultMonthDay);
		};
	
		function setDefaultMonthDay() {
			oldDateDefaults = Date.defaults.d;
			Date.defaults.d = 1;
			return true;
		}
	
		function restoreDefaultMonthDay(ret) {
			Date.defaults.d = oldDateDefaults;
			return ret;
		}
	
		function onClick(e, el, opt) {
			var p = picker.menu.picker;
			p.activeDate = p.activeDate.getFirstDateOfMonth();
			if (p.value) {
				p.value = p.value.getFirstDateOfMonth();
			}
	
			p.showMonthPicker();
	
			if (!p.disabled) {
				p.monthPicker.stopFx();
				p.monthPicker.show();
				// if you want to click,you can the dblclick event change click  
				p.mun(p.monthPicker, 'click', p.onMonthClick, p);
				p.mun(p.monthPicker, 'click', p.onMonthDblClick, p);
				p.onMonthClick = p.onMonthClick.createSequence(pickerClick);
				p.onMonthDblClick = p.onMonthDblClick
						.createSequence(pickerDblclick);
				p.mon(p.monthPicker, 'click', p.onMonthClick, p);
				p.mon(p.monthPicker, 'click', p.onMonthDblClick, p);
			}
		}
	
		function pickerClick(e, t) {
			var el = new Ext.Element(t);
			if (el.is('button.x-date-mp-cancel')) {
				picker.menu.hide();
			} else if (el.is('button.x-date-mp-ok')) {
				var p = picker.menu.picker;
				p.setValue(p.activeDate);
				p.fireEvent('select', p, p.value);
			}
		}
	
		function pickerDblclick(e, t) {
			var el = new Ext.Element(t);
			if (el.parent()
					&& (el.parent().is('td.x-date-mp-month') || el.parent().is(
							'td.x-date-mp-year'))) {
	
				var p = picker.menu.picker;
				p.setValue(p.activeDate);
				p.fireEvent('select', p, p.value);
			}
		}
	};
	//注册插件
	Ext.preg('monthPickerPlugin', Ext.ux.MonthPickerPlugin);
	
	/**
	 * 自定义控件 com.custom.GridPanel
	 * 继承 Ext.grid.GridPanel
	 */
	com.custom.GridPanel = Ext.extend(Ext.grid.GridPanel, {
		stripeRows : true,//隔行换色
		columnLines : true,//显示列线条
		border : true,
		frame : false,
		buttonAlign : 'center',
		trackMouseOver : true,
		disableSelection : false,
		viewConfig : {
			forceFit: true //宽度自适应
		},
		loadMask : {
			msg : '正在加载数据，请稍侯...'
		},//加载完成前显示信息，设置为true则显示Loading
		selModel : new Ext.grid.RowSelectionModel({
			singleSelect : false
		})//设置单行选中模式(true单行，false多行)
	});
	
	/**
	 * 自定义控件 com.custom.Window
	 * 继承 Ext.Window
	 */
	com.custom.Window = Ext.extend(Ext.Window, {
		//		 x: 100,	//窗口的X坐标值
		//       y: 100,	//窗口的Y坐标值
		//       animateTarget: 'btn_id',  //按钮的id，可出现淡入淡出效果,或者在调用show方法中加入按钮id，如window.show("btn_id");
		layout : 'fit', // 合适 布局
		closable : true, //是否允许关闭窗口，默认为true。
		plain : true, //主体背景是否透明，默认为false。
		modal : true, //是否为模式窗口（后面的内容是否可操作），默认为false。
		draggable : true, //是否可拖曳，默认为true。
		resizable : true, //是否可改变窗口的大小，默认为true。
		closable : true, // （是否显示关闭，默认有关闭）
		closeAction : 'hide', //关闭(close)或隐藏(hide)
		collapsible : false, // 右上角出现收缩按钮
		animCollapse : true,
		buttonAlign : 'center'
	});
	
	
	/**
	 * 自定义控件 com.custom.DateField
	 * 继承 Ext.form.DateField
	 * 可以根据日期的格式format，自动切换是否只选择年月
	 * @param {Object} cfg
	 * @memberOf {TypeName} 
	 */
	com.custom.DateField = function(cfg) {
		var self = this;
		if (Ext.isDefined(cfg)) {
			if (!Ext.isDefined(cfg.format)) {
				cfg.format = 'Y-m-d';
			}
			//如果日期格式没有d,只选择年月(使用插件)
			if (cfg.format.indexOf('d') == -1) {
				cfg.plugins = 'monthPickerPlugin';
			}
		}
		com.custom.DateField.superclass.constructor.call(this, cfg);
	};
	Ext.extend(com.custom.DateField, Ext.form.DateField, {
		//此处可定义默认属性,如果新创建的对象里也有此属性，则会覆盖此处
		editable : true,
		width : 180
	});
	
	
	
	/**
	 * 自定义控件 com.custom.TreePanel
	 * 继承 Ext.tree.TreePanel
	 * @param {Object} cfg
	 * @memberOf {TypeName} 
	 */
	com.custom.TreePanel = function(cfg) {
		var self = this;
		com.custom.TreePanel.superclass.constructor.call(this, cfg);
		
		//checkbox的选择事件
		this.on("checkchange",function(node, flag){
			node.expand();
			//node.cascade从当前节点开始向下迭代调用指定函数，如果指定函数返回false则将终止迭代。
			node.cascade(function(child){
			   child.expand();
			   child.attributes.checked = flag;   
			   child.ui.checkbox.checked = flag;   
			   return true;   
			});   
		   //node.bubble从当前节点开始向上迭代调用指定函数，如果指定函数返回false则将终止迭代。
			node.bubble(function(parent){
			   if(parent.parentNode != null){
				   	var parentNode = parent.parentNode;
				   	var childs = parentNode.childNodes;
					var flag = false;
					for(var i = 0; i < childs.length; i++){
						if(childs[i].attributes.checked){
							flag = true;
							break;
						}
					}
					parentNode.attributes.checked = flag;   
					parentNode.ui.checkbox.checked = flag;
			   }
			   return true;   
			}); 
		});
	};
	Ext.extend(com.custom.TreePanel,Ext.tree.TreePanel, {
		lines : true, // 出现节点间虚线
		animate : true,// 是否动画
		enableDD : false,// 是否支持拖放
		collapsible : true, // 是否可折叠
		autoScroll : true,// 自动出现滚动条
		trackMouseOver : true,//mouseover效果
		border : true,
		header : false,
		rootVisible : true, //是否显示根节点，默认为true。
		requestMethod : 'post' //请求方法，可选值有POST、GET。
	});
	
	/**
	 * 自定义控件 com.custom.Button
	 * 继承 Ext.Button
	 */
	com.custom.Button = Ext.extend(Ext.Button, {
		width : 80
	});
	
	/**
	 * 自定义控件 com.custom.Search
	 * 继承 Ext.form.ComboBox
	 */
	com.custom.Search = Ext.extend(Ext.form.ComboBox, {
		mode : 'remote', //local本地，remote远程.远程方式时，默认输入4个字符后自动向后台提交参数：query
		minChars : 0, //输入n个字符后发送查询参数，默认为4
		typeAhead : false,//联想 
		loadingText : 'Searching...',
		hideTrigger : true, //隐藏下拉按钮
		editable : true,
		triggerAction : 'all',
		selectOnFocus : true
	});
	
	/**
	 * 自定义控件 com.custom.ComboBox
	 * 继承 Ext.form.ComboBox
	 */
	com.custom.ComboBox = Ext.extend(Ext.form.ComboBox, {
		//emptyText:'请选择...',
		mode:'local', //local本地，remote远程
		typeAhead : true,//联想 
		hideTrigger : false, //隐藏下拉按钮
		editable : false,
		triggerAction : 'all',
		selectOnFocus : true
	});
	
	/**
	 * 重写Ext.form.Action.Submit里的processResponse方法，
	 * 处理在火狐下json串中多<pre >
	 * 导致数据不能解析的问题
	 * 此方法主要解决本系统中在火狐及chrome浏览器下文件上传的bug
	 * 
	 */
	Ext.override(Ext.form.Action.Submit, {
		processResponse : function(a) {
			this.response = a;
	
			// 增加下面几句代码就OK啦 -------------------------
			var result = /<pre.*>(.*)<\/pre>/.exec(a.responseText);
			if (result != null) {
				a.responseText = result[1];
				this.response = Ext.util.JSON.decode(a.responseText);
			}
			// 下面是ext的源代码-------------------------
			if (!a.responseText && !a.responseXML) {
				return true;
			}
			this.result = this.handleResponse(a);
			return this.result;
		}
	});
	
	/**
	 * 注册控件
	 * 第一个参数为自定义控件的xtype
	 * 用法：
	 * var grid = new com.custom.GridPanel({
	 * 		id : 'station_grid',
	 * 		border : false,
	 * 			...
	 * });
	 */
	Ext.reg("customgridpanel", com.custom.GridPanel);
	Ext.reg("customwindow", com.custom.Window);
	Ext.reg("customdatefield", com.custom.DateField);
	Ext.reg("customtreepanel", com.custom.TreePanel);
	Ext.reg("customButton", com.custom.Button);
	Ext.reg("customSearch", com.custom.Search);//联想搜索， 继承 Ext.form.ComboBox
	Ext.reg("customComboBox", com.custom.ComboBox);//下拉选择框  继承 Ext.form.ComboBox