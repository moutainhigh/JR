// jqgrid的通用配置

function getJqgridCategory(type,tableDom,pagerDom) {
	return config = {
		url : 'sysGoodsCategory/getGoodsCategoryData.do?firstType='+type,
		datatype : "json",
		colNames : [ '操作', '系统编码', '助记码', '商品名称', '商品小类', '销售方式', '兑换', '金料', '石料', '品名', '统计大类', '统计中类', '统计小类', '标签模板', '状态' ],
		colModel : [ {
			name : 'opera',
			sortable : false,formatter : function(cellValue, options, rowObject) {
				var html="<button class='btn btn-xs btn-default' data-original-title='Edit Row' onclick=\"editCategory('" + rowObject.id + "');\"><i class='fa fa-pencil'></i></button>";
				html += "<button class='btn btn-xs btn-default' data-original-title='Cancel' onclick=\"deleteCategory('" + rowObject.id + "');\"><i class='fa fa-times'></i></button>";
				return html;
			}
		}, {
			name : 'systemCode'
		}, {
			name : 'assistCode'
		}, {
			name : 'productName'
		}, {
			name : 'secondTypeName'
		}, {
			name : 'saleType',
			formatter : function(cellValue, options, rowObject) {
				if (cellValue == 1) {
					return "折扣";
				} else if (cellValue == 2) {
					return "金价";
				} else if (cellValue == 3) {
					return "折扣+金价";
				} else {
					return "未知";
				}
			}
		}, {
			name : 'canBarter',
			formatter : function(cellValue, options, rowObject) {
				if (cellValue == 1) {
					return "<span style='color:green'>可兑换</span>";
				} else {
					return "<span style='color:red'>不可兑换</span>";
				}
			}
		}, {
			name : 'goldName'
		}, {
			name : 'jewelName'
		}, {
			name : 'categoryName'
		}, {
			name : 'firstClassify'
		}, {
			name : 'secondClassify'
		}, {
			name : 'thirdClassify'
		}, {
			name : 'grfName'
		}, {
			name : 'status',
			formatter : function(cellValue, options, rowObject) {
				if (cellValue == 1) {
					return "<span style='color:green'>在用</span>";
				} else {
					return "<span style='color:red'>禁用</span>";
				}
			}
		} ],
		caption : "商品分类详情",
		rowNum : -1,
		multiselect : true,
		pager : '#'+pagerDom,
		viewrecords : true,
		loadonce : true,
		autowidth : true,
		pgbuttons : false,
		pgtext : false,
		rownumbers : false,
		ondblClickRow : function(rowid) { // 双击行
			editCategory(rowid);
		},
		recordtext : "共{1}记录"
	}
}

function getBaseType(type,tableDom,pagerDom) {
	return config = {
		url : 'baseType/getBaseTypeData.do?typeName='+type,
		datatype : "json",
		colNames : [ '操作', '名称','描述' ],
		colModel : [ {
			name : 'opera',
			width: 20,
			sortable : false,formatter:function(cellValue, options, rowObject){
				if(rowObject.isSysDef == 1){
					return "";
				}else{
					return "<button class='btn btn-xs btn-default' data-original-title='Edit Row' data-original-title='Edit Row' onclick=\"edit('"+ rowObject.id + "');\"><i class='fa fa-pencil'></i></button><button class='btn btn-xs btn-default' data-original-title='Cancel' onclick=\"deleteRows('"+ rowObject.id + "');\"><i class='fa fa-times'></i></button>";
				}
			}
		}, {
			name : 'name',formatter : function(cellValue, options, rowObject) {
				if(rowObject.isSysDef == 1){
					return "<span><i class='glyphicon glyphicon-pushpin  text-danger' title='系统默认，禁止修改'></i>    "+cellValue+"</span>";
				}else{
					return cellValue;
				}
			}
		}, {
			name : 'remarks'
		} ],
		caption : "",
		rowNum : -1,
		multiselect : true,
		pager : '#'+pagerDom,
		viewrecords : true,
		loadonce : true,
		autowidth : true,
		pgbuttons : false,
		pgtext : false,
		rownumbers : false,
		ondblClickRow : function(rowid) { // 双击行
			editCategory(rowid);
		},
		recordtext : "共{1}记录"
	}
}
function getJqgridGoodsGroup(type,tableDom,pagerDom) {
	return config = {
		url : 'goodsGroup/getGoodsGroupData.do?firstType='+type,
		datatype : "json",
		colNames : [ '操作', '大类名称', '小类名称', '销售方式', '兑换', '状态','描述' ],
		colModel : [ {
			name : 'opera',
			width: '80px',
			sortable : false,formatter:function(cellValue, options, rowObject){
				if(rowObject.isSysDef == 1){
					return "<span><i class='glyphicon glyphicon-pushpin  text-danger' title='系统默认，禁止修改'></i></span>";
				}else{
					return "<button class='btn btn-xs btn-default' data-original-title='Edit Row' data-original-title='Edit Row' onclick=\"editClassify('"+ rowObject.id + "');\"><i class='fa fa-pencil'></i></button><button class='btn btn-xs btn-default' data-original-title='Cancel' onclick=\"deleteRows('"+ rowObject.id + "');\"><i class='fa fa-times'></i></button>";
				}
			}
		}, {
			name : 'firstTypeName'
		}, {
			name : 'secondTypeName'
		}, {
			name : 'saleType',
			formatter : function(cellValue, options, rowObject) {
				if (cellValue == 1) {
					return "折扣";
				} else if (cellValue == 2) {
					return "金价";
				} else if (cellValue == 3) {
					return "折扣+金价";
				} else {
					return "未知";
				}
			}
		}, {
			name : 'canBarter',
			formatter : function(cellValue, options, rowObject) {
				if (cellValue == 1) {
					return "<span style='color:green'>可兑换</span>";
				} else {
					return "<span style='color:red'>不可兑换</span>";
				}
			}
		}, {
			name : 'status',
			formatter : function(cellValue, options, rowObject) {
				if (cellValue == 1) {
					return "<span style='color:green'>在用</span>";
				} else {
					return "<span style='color:red'>禁用</span>";
				}
			}
		}, {
			name : 'remarks'
		} ],
		caption : "素金",
		rowNum : -1,
		multiselect : true,
		pager : '#'+pagerDom,
		viewrecords : true,
		loadonce : true,
		autowidth : true,
		pgbuttons : false,
		pgtext : false,
		rownumbers : false,
		ondblClickRow : function(rowid) { // 双击行
			editCategory(rowid);
		},
		recordtext : "共{1}记录"
	}
}

function getStoreList(tableDom,pagerDom) {
	return config = {
		url : 'sysStore/getStoreListData.do',
		datatype : "json",
		colNames : [ '操作', '门店名称', '联系电话', '地址', '区域', '门店类型','状态', '备注' ],
		colModel : [ {
			name : 'opera',
			width:50,
			sortable : false,formatter : function(cellValue, options, rowObject) {
				var html="<button class='btn btn-xs btn-default' data-original-title='Edit Row' onclick=\"addStore('" + rowObject.id + "');\"><i class='fa fa-pencil'></i></button>";
				return html;
			}
		}, {
			name : 'storeName'
		}, {
			name : 'mobile'
		}, {
			name : 'address'
		}, {
			name : 'regionName'
		}, {
			name : 'status',
			formatter : function(cellValue, options, rowObject) {
				if (cellValue == 1) {
					return "在用";
				} else {
					return "停用";
				}
			}
		},{
			name : 'storeType',
			formatter : function(cellValue, options, rowObject) {
				if (cellValue == 0) {
					return "直营店";
				} else {
					return "加盟店";
				}
			}
		}, {
			name : 'remarks'
		} ],
		caption : "门店列表详情",
		rowNum : -1,
		multiselect : true,
		pager : '#'+pagerDom,
		viewrecords : true,
		loadonce : true,
		autowidth : true,
		pgbuttons : false,
		pgtext : false,
		rownumbers : false,
		ondblClickRow : function(rowid) { // 双击行
		},
		recordtext : "共{1}记录"
	}
}

function getCounterList(tableDom,pagerDom) {
	return config = {
		url : 'sysCounter/getCounterListData.do',
		datatype : "json",
		colNames : [ '操作', '柜台名称', '柜台类型','状态', '备注' ],
		colModel : [ {
			name : 'opera',
			width:50,
			sortable : false,formatter : function(cellValue, options, rowObject) {
				var html="<button class='btn btn-xs btn-default' data-original-title='Edit Row' onclick=\"editCounter('" + rowObject.id + "');\"><i class='fa fa-pencil'></i></button>";
				return html;
			}
		}, {
			name : 'counterName'
		}, {
			name : 'counterType',
			formatter : function(cellValue, options, rowObject) {
				if (cellValue == 0) {
					return "成品柜台";
				} else if(cellValue ==1){
					return "旧料柜台";
				}else {
					return "在途柜台";
				}
			}
		}, {
			name : 'status',
			formatter : function(cellValue, options, rowObject) {
				if (cellValue == 1) {
					return "在用";
				} else {
					return "停用";
				}
			}
		}, {
			name : 'remarks'
		} ],
		caption : "柜台列表详情",
		rowNum : -1,
		multiselect : true,
		pager : '#'+pagerDom,
		viewrecords : true,
		loadonce : true,
		autowidth : true,
		pgbuttons : false,
		pgtext : false,
		rownumbers : false,
		ondblClickRow : function(rowid) { // 双击行
		},
		recordtext : "共{1}记录"
	}
}