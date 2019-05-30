Ext.define('ptl.common.UEditor', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.ueditor',
	constructor : function(config) {
		// 只能设置一次
		if (Ext.isEmpty(ptl.common.UEditor.scriptLoad)) {
			var cfgPath = "ueditor/ueditor.config.js";
			var ctrPath = "ueditor/ueditor.all.js";
			var lgPath = "ueditor/lang/zh-cn/zh-cn.js";
			Ext.Loader.loadScriptsSync([ cfgPath, ctrPath, lgPath ]);
			ptl.common.UEditor.scriptLoad = true;
		}

		var config = config || {};
		this.ue;
		this.value = config.value || "";
		this.editorId = 'uEditor' + Ext.id();
		var html = '<div id="' + this.editorId + '" style="width:100%;height:100%;"></div>';
		this.ueZIndex = config.ueZIndex || 20000;
		ptl.common.UEditor.superclass.constructor.call(this, Ext.apply({
			layout : 'fit',
			border : false,
			header : false,
			scrollable : false,
			style : "line-height:normal;",
			html : html,
			listeners : {
				afterrender : this.formatEditor,
				scope : this
			}
		}, config));
	},

	formatEditor : function(cmp, eOpts) {
		var me = this;
		me.ue = UE.getEditor(this.editorId, {
			zIndex : me.ueZIndex,
			autoHeightEnabled : false,
			autoFloatEnabled : false,
			allowDivTransToP : false,
			emotionLocalization : true,
			// 下面两个配置要加上去，因为如果采用按需要加载方式，这个路径不配置会计算错误
			UEDITOR_HOME_URL : "ueditor/",
			themePath : "ueditor/themes/",
			fullscreen : false,
			fontsize : [ 9, 10, 11, 12, 14, 16, 18, 20, 24, 30, 36, 42, 48, 54 ],
			sourceEditor : 'textarea',
			serverUrl : 'ueditor/config',
			lineheight : [ '0.5', '0.8', '1', '1.2', '1.5', '1.75', '2', '4', '5' ]
		});

		me.ue.ready(function() {
			// 设置编辑器的内容
			if (!Ext.isEmpty(me.value)) {
				me.ue.setContent(me.value);
			}
		});
	},

	setValue : function(value) {
		if (this.ue != undefined) {
			this.ue.execCommand('insertHtml', value);
		}
	},

	getValue : function() {
		if (this.ue != undefined) {
			return this.ue.getContent();
		}
	}
});