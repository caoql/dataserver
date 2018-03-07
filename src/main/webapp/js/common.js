var obj = {
	// 服务名称
	appId : '/dataserver',
	// 解析模板方法
	explain : function (selector, list) {
		var reg = /:[a-zA-Z]+/g;
		var result = reg.exec($(selector).val());
		// 临时存放，用来记录新模板的值
		var temp = [];
		while (result != null) {
			var exists = false;
			for (var i in list) {
				if (list[i].pname == result[0]) {
					exists = true;
					break;
				}
			}
			if (!exists) {
				list.push({
					pname : result[0],
					ptype : "",
					pdirection : ""
				});
			}
			temp.push(result[0]);
			result = reg.exec($(selector).val());
		}
		// 判断值是否改变
		var replacePlist = [];
		for (var i in list) {
			if (temp.includes(list[i].pname)) {
				replacePlist.push(list[i]);
			}
		}
		return replacePlist;
	},
	/**
	 * 获取表单数据
	 * @param selector 表单选择器,如'#formTemple'
	 * @param notEmptyField 是否去空 true表示去空
	 * @return 返回数据对象
	 */
	serializeToJson : function (selector, notEmptyField) {
		var obj = {};
	    $.each($(selector).serializeArray(), function(i, o) {
	        var n = o.name, v = isNaN(o.value) === true ? o.value: $.trim(o.value);
	        if ( !(notEmptyField && '' == v)) {
	        	obj[n] = (obj[n] === undefined) ? v: $.isArray(obj[n]) ? obj[n].concat(v): [obj[n], v];
	        }
	    });
	    return obj;
	},
	/**
	 * 向select选项中 加入一个Item 
	 * @param objSelect 如: document.getElementById("operType")
	 * @param objItemText 如:"SQL语句"
	 * @param objItemValue 如: "A"
	 */
	addItemToSelect : function (objSelect, objItemText, objItemValue) { 
		var varItem = new Option(objItemText, objItemValue); 
		objSelect.options.add(varItem); 
	} 
}
var $$ = obj;
