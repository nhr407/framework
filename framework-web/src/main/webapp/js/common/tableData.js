var URL; //请求地址
var PARAMS; //请求条件参数
var PAGENUM; //起始页
var PAGESIZE; //每页显示的条数
var TABLEID; //表格ID
var COLUMNS; //显示列所对应的属性字段名
var TOTAL; //查询的总条数
var PAGETOTAL; //总页数
var COLUMNFORMAT; //显示的格式
var ROWSDATA; //列表数据
var IDNAME; //主键名称
var SORT; //排序字段
var ORDER; //排序方式

/**
 * 分页列表
 * 
 * @author 周化益
 * @param url 请求地址
 * @param params 请求条件参数
 * @param pageNum 起始页
 * @param pageSize 每页显示的条数
 * @param tableId 表格ID
 * @param columns 显示列所对应的属性字段名
 * @param columnFormat 字段转换函数
 * @param IdName 主键ID
 * @param sort 排序字段
 * @param order 排序方式
 */
function tableData(url, params, pageNum, pageSize, tableId, columns, columnFormat, IdName, sort, order) {
	if(url.indexOf("?") > 0) {
		if(url.indexOf("&") == -1) {
			url = url + "&";
		}
	} else {
		url = url + "?";
	}
	
	URL = url;
	PARAMS = params;
	PAGENUM = pageNum;
	PAGESIZE = pageSize;
	TABLEID = tableId;
	COLUMNS = columns;
	IDNAME = IdName;
	COLUMNFORMAT = columnFormat;
	SORT = sort;
	ORDER = order;
	if(sort) {
		params['sort'] = sort;
		if(order) {
			params['order'] = order;
		}
	} else {
		params['sort'] = "id";
		params['order'] = "desc";
	}

	$.getJSON(url + 'page=' + pageNum + '&rows=' + pageSize, params, function(data) {
		var rowStr = '';
		var checkbox = $("#" + tableId + " thead td input");
		var ck = checkbox.attr("type") == "checkbox";
		if(!data) {
			ROWSDATA = [];
			TOTAL = 0;
		} else {
			ROWSDATA = data.rows ? data.rows : [];
			TOTAL = data.total ? data.total : 0;
		}

		var dataLength = ROWSDATA.length;
		if(dataLength == TOTAL) {
			var rowsTemp = ((pageNum - 1) * pageSize);
			var num = TOTAL - rowsTemp;
			if(num == TOTAL) {
				if(num > pageSize) {
					num = pageSize;
				}
			}
			var dataTemp = [];
			for(var i = 0; i < num; i++) {
				dataTemp[i] = ROWSDATA[rowsTemp + i]
			}
			ROWSDATA = dataTemp;
		}

		PAGETOTAL = parseInt(TOTAL / pageSize);
		if(TOTAL % pageSize > 0) {
			PAGETOTAL++;
		}

		$.each(ROWSDATA, function(i, n) {
			if(i % 2 == 0) {
				rowStr += '<tr class="altroww">';
			} else {
				rowStr += '<tr>';
			}

			rowStr += "<td>" + ((PAGENUM - 1) * PAGESIZE + i + 1) + "</td>";

			if(ck) {
				rowStr += "<td><input type='checkbox' name='ck' value=" + n[IdName] + "></td>";
			}

			$.each(columns, function(j, m) {
				if(columnFormat[m]) {
					rowStr += '<td>' + columnFormat[m](n[m], i, n) + '</td>';
				} else {
					if(n[m] || n[m] == 0) {
						rowStr += '<td>' + n[m] + '</td>';
					} else {
						rowStr += '<td></td>';
					}
				}
			});
			rowStr += '</tr>';
		});

		if(ck) {
			checkbox.click(function() {
				var that = this;
				$(this).closest('table').find('tr > td input:checkbox').each(function() {
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
			});
		}
		$('#' + tableId + ' tbody').html(rowStr);

		$("#padding input[padding-attr='num']").val(pageNum);
		$("#padding span[padding-attr='pageTotal']").html(PAGETOTAL);
		$("#padding span[padding-attr='start']").html((pageNum - 1) * pageSize + 1);
		var end = pageNum * pageSize;
		if(end > TOTAL) {
			end = TOTAL;
		}
		$("#padding span[padding-attr='end']").html(end);
		$("#padding span[padding-attr='total']").html(TOTAL);
	})
}

/**
 * 下一页
 */
function nextPage() {
	if(PAGENUM >= PAGETOTAL) {
		PAGENUM = PAGETOTAL;
		return;
	} else {
		PAGENUM = parseInt(PAGENUM) + 1;
		tableData(URL, PARAMS, PAGENUM, PAGESIZE, TABLEID, COLUMNS, COLUMNFORMAT, IDNAME, SORT, ORDER);
	}
}

/**
 * 首页
 */
function firstPage() {
	tableData(URL, PARAMS, 1, PAGESIZE, TABLEID, COLUMNS, COLUMNFORMAT, IDNAME, SORT, ORDER);
}

/**
 * 上一页
 */
function upPage() {
	if(PAGENUM <= 1) {
		PAGENUM = 1;
		return;
	} else {
		PAGENUM = PAGENUM - 1;
		tableData(URL, PARAMS, PAGENUM, PAGESIZE, TABLEID, COLUMNS, COLUMNFORMAT, IDNAME, SORT, ORDER);
	}
}

/**
 * 单击的页数
 * @param pageNum 点击的页数
 */
function clickPadding(pageNum) {
	if(pageNum == PAGENUM) {
		return;
	}
	if(pageNum > PAGETOTAL) {
		pageNum = PAGETOTAL;
	} else if(pageNum < 1) {
		pageNum = 1;
	}
	tableData(URL, PARAMS, pageNum, PAGESIZE, TABLEID, COLUMNS, COLUMNFORMAT, IDNAME, SORT, ORDER);
}

/**
 * 刷新表格
 */
function CommonRefreshTable() {
	tableData(URL, PARAMS, PAGENUM, PAGESIZE, TABLEID, COLUMNS, COLUMNFORMAT, IDNAME, SORT, ORDER);
}

/**
 * 获取列表数据
 * @author 周化益
 * @param rowsData 
 * @param index
 * @returns 列表数据
 */
function getRowData(index) {
	if(index || index == 0) {
		return ROWSDATA[index];
	} else {
		return ROWSDATA;
	}
}

/**
 * 末页
 */
function lastPage() {
	tableData(URL, PARAMS, PAGETOTAL, PAGESIZE, TABLEID, COLUMNS, COLUMNFORMAT, IDNAME, SORT, ORDER);
}

/**
 * 排序查询
 * 
 * @author zhy
 * @param sortColum 排序字段
 * @param orderMethod 排序方式
 */
function orderTable(sortColum, orderMethod) {
	tableData(URL, PARAMS, PAGENUM, PAGESIZE, TABLEID, COLUMNS, COLUMNFORMAT, IDNAME, sortColum, orderMethod);
}

/**
 * 排序函数
 * 
 * @author zhy
 * @param {Object} th 点击的对象
 * @param {Object} columnName 排序字段名
 */
function orderFun(th, columnName) {
	var self = $(th);
	$("#"+TABLEID + " thead .fa-angle-up").removeClass("fa-angle-up").addClass("fa-sort");
	$("#"+TABLEID + " thead .fa-angle-down").removeClass("fa-angle-down").addClass("fa-sort");
	
	if(self.attr("order-status") == "") {
		self.attr("order-status", "desc").removeClass("fa-angle-up").addClass("fa-angle-down");
		orderTable(columnName, "desc");
	} else if(self.attr("order-status") == "asc") {
		self.attr("order-status", "desc").removeClass("fa-angle-up").addClass("fa-angle-down");
		orderTable(columnName, "desc");
	} else {
		self.attr("order-status", "asc").removeClass("fa-angle-down").addClass("fa-angle-up");
		orderTable(columnName, "asc");
	}
}