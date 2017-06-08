var commonPost = "http://192.168.0.110/Arena_Back/";

var commonUploadFilePost = "http://192.168.0.41:8080/UploadFile_WebService/";
//var commonUploadFilePost = "http://upload.mytv365.com/UploadFileProject/";
/**
 * 填充表单数据
 * 
 * @author zhy
 * @param formId 表单ID
 * @param obj json数据
 * @returns
 */
function loadDataMany(formId, obj) {
	var key, value, tagName, type, arr, key2, value2;
	for(x in obj) {
		key = x;
		value = obj[x];
		for(x2 in value) {
			key2 = "." + x2;
			value2 = value[x2];
			var valueObj = $("#" + formId + " [name='" + key + key2 + "']");
			tagName = valueObj.prop("tagName");
			type = valueObj.attr('type');
			if(tagName == 'INPUT') {
				if(type == 'radio') {
					$.each(valueObj, function(i, n) {
						n.checked = (n.value == value2);
					})
				} else if(type == 'checkbox') {
					arr = value.split(',');
					for(var i = 0; i < arr.length; i++) {
						if(valueObj.val() == arr[i]) {
							valueObj.attr('checked', true);
							break;
						}
					}
				} else {
					valueObj.val(value2);
				}
			} else if(tagName == 'SELECT' || tagName == 'TEXTAREA') {
				valueObj.val(value2);
			} else if(tagName == 'IMG') {
				valueObj.attr("src", value);
			} else {
				valueObj.html(value);
			}
		}
	}
}

/**
 * 填充表单数据
 * 
 * @author zhy
 * @param formId 表单ID
 * @param obj json数据
 * @returns
 */
function loadDataOne(formId, obj) {
	var key, value, tagName, type, arr;
	for(x in obj) {
		key = x;
		value = obj[x];
		var valueObj = $("#" + formId + " [name='" + key + "']");
		tagName = valueObj.prop("tagName");
		type = valueObj.attr('type');
		if(tagName == 'INPUT') {
			if(type == 'radio') {
				$.each(valueObj, function(i, n) {
					n.checked = (n.value == value);
				})
			} else if(type == 'checkbox') {
				arr = value.split(',');
				for(var i = 0; i < arr.length; i++) {
					if(valueObj.val() == arr[i]) {
						valueObj.attr('checked', true);
						break;
					}
				}
			} else {
				valueObj.val(value);
			}
		} else if(tagName == 'SELECT' || tagName == 'TEXTAREA') {
			valueObj.val(value);
		} else if(tagName == 'IMG') {
			valueObj.attr("src", value);
		} else {
			valueObj.html(value);
		}
	}
}

/**
 * 填充表单数据
 * 
 * @author zhy
 * @param formId 表单ID
 * @param obj json数据
 * @returns
 */
function loadDataOneByObj(formObj, obj) {
	var key, value, tagName, type, arr;
	for(x in obj) {
		key = x;
		value = obj[x];
		var valueObj = $("[name='" + key + "']", formObj);
		tagName = valueObj.prop("tagName");
		type = valueObj.attr('type');
		if(tagName == 'INPUT') {
			if(type == 'radio') {
				$.each(valueObj, function(i, n) {
					n.checked = (n.value == value);
				})
			} else if(type == 'checkbox') {
				arr = value.split(',');
				for(var i = 0; i < arr.length; i++) {
					if(valueObj.val() == arr[i]) {
						valueObj.attr('checked', true);
						break;
					}
				}
			} else {
				valueObj.val(value);
			}
		} else if(tagName == 'SELECT' || tagName == 'TEXTAREA') {
			valueObj.val(value);
		} else if(tagName == 'IMG') {
			valueObj.attr("src", value);
		} else {
			valueObj.html(value);
		}
	}
}

/**
 * 自定义receiveName属性填充表单数据
 * 
 * @author zhy
 * @param formId 表单ID
 * @param obj json数据
 * @returns
 */
function loadDataOneByMyAttr(formId, obj) {
	var key, value, tagName, type, arr;
	for(x in obj) {
		key = x;
		value = obj[x];
		var valueObj = $("#" + formId + " [receiveName='" + key + "']");
		tagName = valueObj.prop("tagName");
		type = valueObj.attr('type');
		if(tagName == 'INPUT') {
			if(type == 'radio') {
				$.each(valueObj, function(i, n) {
					n.checked = (n.value == value);
				})
			} else if(type == 'checkbox') {
				arr = value.split(',');
				for(var i = 0; i < arr.length; i++) {
					if(valueObj.val() == arr[i]) {
						valueObj.attr('checked', true);
						break;
					}
				}
			} else {
				valueObj.val(value);
			}
		} else if(tagName == 'SELECT' || tagName == 'TEXTAREA') {
			valueObj.val(value);
		} else if(tagName == 'IMG') {
			valueObj.attr("src", value);
		} else {
			valueObj.html(value);
		}
	}
}

/**
 * 自定义receiveName属性填充表单数据
 * 
 * @author zhy
 * @param formId 表单ID
 * @param obj json数据
 * @returns
 */
function loadDataOneByMyAttrObj(formObj, obj) {
	var key, value, tagName, type, arr;
	for(x in obj) {
		key = x;
		value = obj[x];
		var valueObj = $(" [receiveName='" + key + "']", formObj);
		tagName = valueObj.prop("tagName");
		type = valueObj.attr('type');
		if(tagName == 'INPUT') {
			if(type == 'radio') {
				$.each(valueObj, function(i, n) {
					n.checked = (n.value == value);
				})
			} else if(type == 'checkbox') {
				arr = value.split(',');
				for(var i = 0; i < arr.length; i++) {
					if(valueObj.val() == arr[i]) {
						valueObj.attr('checked', true);
						break;
					}
				}
			} else {
				valueObj.val(value);
			}
		} else if(tagName == 'SELECT' || tagName == 'TEXTAREA') {
			valueObj.val(value);
		} else if(tagName == 'IMG') {
			valueObj.attr("src", value);
		} else {
			valueObj.html(value);
		}
	}
}

/**
 * 时间转换
 * 
 * @author zhy
 * @param date传入的日期
 * @returns {String} 返回字符串格式日期
 */
function formatDate(date) {
	var now = new Date(date);

	var year = now.getFullYear();

	var month = now.getMonth() + 1;
	if(month < 10) {
		month = "0" + month;
	}

	var day = now.getDate();

	if(day < 10) {
		day = "0" + day;
	}

	var hour = now.getHours();

	if(hour < 10) {
		hour = "0" + hour;
	}

	var minute = now.getMinutes();

	if(minute < 10) {
		minute = "0" + minute;
	}

	var second = now.getSeconds();

	if(second < 10) {
		second = "0" + second;
	}

	return year + "-" + month + "-" + day + "   " + hour + ":" + minute + ":" + second;
}

/**
 * 打开遮罩
 * 
 * @param shadeId 要添加的遮罩ID
 */
function openShade(shadeId) {
	$("#top", parent.document).css("display", "block");
	$("#left", parent.document).css("display", "block");
	if(shadeId) {
		$("#" + shadeId).css("display", "block");
	}
}

/**
 * 关闭遮罩
 * 
 * @param shadeId 要添加的遮罩ID
 */
function closeShade(shadeId) {
	$("#top", parent.document).css("display", "none");
	$("#left", parent.document).css("display", "none");
	if(shadeId) {
		$("#" + shadeId).css("display", "none");
	}
}

var clickPowerId = getCookie("clickPowerId");
var checkedPower = {};
/**
 * 获取表格宽度
 * 
 * @author 周化益
 * @returns {Number}
 */
function getPageWidth(num) {
	var width = document.body.clientWidth;
	return(width * 0.956) * num;
}

/**
 * 获取页面宽度
 * 
 * @author 周化益
 * @returns
 */
function getpageHeight() {
	return document.body.clientHeight;
}

var actionPower = {};

/**
 * 单机复选框事件
 * 
 * @author 周化益
 * @param e
 */
function child(e) {
	var pId = e.id;
	var ck = e.checked;
	var p = $("#" + pId + "").attr("pId");

	if(p == "t0") {
		var children = $("input[pId='" + pId + "']");
		children.each(function() {
			this.checked = ck;
			if(ck == false) {
				delete checkedPower[this.id.substring(1, this.id.length)];
			} else {
				checkedPower[this.id.substring(1, this.id.length)] = "1,2,3,4";
			}
		});
		if(ck == false) {
			delete checkedPower[pId.substring(1, pId.length)];
		} else {
			checkedPower[pId.substring(1, pId.length)] = "1,2,3,4";
		}
	} else {
		if(ck == false) {
			delete checkedPower[pId.substring(1, pId.length)];
		} else {
			if(pId == 't8') {
				checkedPower[pId.substring(1, pId.length)] = "1,2,3,4,5";
			} else {
				checkedPower[pId.substring(1, pId.length)] = "1,2,3,4";
			}
			checkedPower[p.substring(1, p.length)] = "1,2,3,4";
		}
		var checkLevelLengh = $("input[pId='" + p + "']:checked").length;
		if(checkLevelLengh > 0) {
			document.getElementById(p).checked = true;
		} else {
			document.getElementById(p).checked = false;
		}
	}
}

/**
 * 成功消息提示
 * 
 * @author 周化益
 * @param msg 提示的消息
 */
function successMsg(msg) {
	$.messager.show({
		title: '提示信息',
		msg: '<span style="color:green">' + msg + '</span>',
		showType: 'show'
	});
}

/**
 * 失败消息提示
 * 
 * @author 周化益
 * @param msg 提示的消息
 */
function failMsg(msg) {
	$.messager.show({
		title: '提示信息',
		msg: '<span style="color:red">' + msg + '</span>',
		showType: 'show'
	});
}

/**
 * 通用的上传方法
 * 
 * @author 周化益
 * @param file
 * @param entityId
 * @param suffixName
 * @param folderName
 */
function commonUploadFile(file, entityId, suffixName, folderName) {
	var xhr = new XMLHttpRequest();
	var formData = new FormData();
	formData.append("fileData", file);
	formData.append("entityId", entityId);
	formData.append("suffixName", suffixName);
	formData.append("folderName", folderName);
	//xhr.upload.addEventListener("progress", uploadProgress, false);
	xhr.addEventListener("load", uploadComplete, true);
	xhr.open("post", filePath() + "/fileUpload.do");
	xhr.send(formData);
}

/**
 * 赋值Cookie中
 * 
 * @author 周化益
 * @param cookName Cook名
 * @param cookValue Cook值
 */
function SetCookie(cookName, cookValue) {
	date = new Date();
	date.setDate(date.getDate() + 30);
	document.cookie = cookName + '=' + escape(cookValue) +
		'; expires=' + date.toGMTString();
}

/**
 * 从Cook中取值
 * 
 * @author 周化益
 * @param cookName cook名
 * @returns
 */
function getCookie(cookName) {
	// 获取cookie字符串
	var strCookie = document.cookie;
	// 将多cookie切割为多个名/值对
	var arrCookie = strCookie.split("; ");
	// 遍历cookie数组，处理每个cookie对
	for(var i = 0; i < arrCookie.length; i++) {
		var arr = arrCookie[i].split("=");
		// 找到名称为userId的cookie，并返回它的值
		if(cookName == arr[0]) {
			return userId = arr[1];
		}
	}
	return null;
}

/**
 * 获取时间毫秒数
 * 
 * @returns
 */
function getTimeStr() {
	return new Date().getTime();
}
/**
 * 等待操作
 */
var MaskUtil = (function() {

	var $mask, $maskMsg;

	var defMsg = '正在处理，请稍待。。。';

	function init() {
		if(!$mask) {
			$mask = $("<div class=\"datagrid-mask mymask\"></div>").appendTo("body");
		}
		if(!$maskMsg) {
			$maskMsg = $("<div class=\"datagrid-mask-msg mymask\">" + defMsg + "</div>")
				.appendTo("body").css({
					'font-size': '12px'
				});
		}

		$mask.css({
			width: "100%",
			height: $(document).height()
		});

		var scrollTop = $(document.body).scrollTop();

		$maskMsg.css({
			left: ($(document.body).outerWidth(true) - 190) / 2,
			top: (($(window).height() - 45) / 2) + scrollTop
		});

	}

	return {
		mask: function(msg) {
			init();
			$mask.show();
			$maskMsg.html(msg || defMsg).show();
		},
		unmask: function() {
			$mask.hide();
			$maskMsg.hide();
		}
	}

}());

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}

/**
 * 矩形统计图
 * 
 * @author zhy
 * @param {Object} columns
 * @param {Object} xColumns
 * @param {Object} values
 * @param {Object} contentId
 */
function eCharts(columns, xColumns, values, contentId) {
	var dom = document.getElementById(contentId);
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	app.title = '堆叠柱状图';

	var seriesArray = [];

	$.each(values, function(i, n) {
		var seriesParam = {};
		seriesParam["name"] = n.name;
		seriesParam["type"] = "bar";
		seriesParam["data"] = n.data;
		seriesArray[i] = seriesParam;
	})

	option = {
		tooltip: {
			trigger: 'axis',
			axisPointer: { // 坐标轴指示器，坐标轴触发有效
				type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend: {
			data: columns
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		xAxis: [{
			type: 'category',
			data: xColumns
		}],
		yAxis: [{
			type: 'value'
		}],
		series: seriesArray
	};
	if(option && typeof option === "object") {
		myChart.setOption(option, true);
	}
}

/**
 * 回车事件
 * 
 * @author zhy
 * @param {Object} callback
 */
function keyUpEnter(callback) {
	if(event.keyCode == 13) {
		callback();
	} else {
		return;
	}
}

/**
 * 回车事件
 * 
 * @author zhy
 * @param {Object} callback
 */
function keyUpEnterByParam(callback, th) {
	if(event.keyCode == 13) {
		callback(th.value);
	}
}

Array.prototype.del = function(n) {
	if(n < 0) {
		return this;
	} else {
		return this.slice(0, n).concat(this.slice(n + 1, this.length));
	}
}

//上传控件初始化
function initFileInput(ctrlName, uploadUrl) {
	var control = $('#' + ctrlName);

	control.fileinput({
		language: 'zh', //设置语言
		uploadUrl: uploadUrl, //上传的地址
		allowedFileExtensions: ['jpg', 'png', 'gif'], //接收的文件后缀
		showUpload: turn, //是否显示上传按钮
		showCaption: false, //是否显示标题
		browseClass: "btn btn-primary", //按钮样式             
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
	});
}