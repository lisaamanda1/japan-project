$(function() {
	app.getUser();
	loadHeader();
	app.initModule();
});

var app = function() {
	var user;
	var tabs;
	var gamenId;
	var userList;
	var searchForm;
	var sex = {1:'男', 0:'女'};
	var SYOZOKU_KAISYA = {1:'株式会社ブライトスター', 2:'株式会社トップクラウド'};
	var SYOKUGYO_KIND = {1:'役員', 2:'総務', 3:'IT営業', 4:'ITエンジニア', 5:'不動産スタッフ', 6:'個人事業主'};
	function initModule() {
		userList = $("#userList");
		searchForm = $("#searchForm");
//		getUser();
		getLocalTime();
		tabs = $("#tabs");
		
		userList.datagrid({
			url:null,
			method:'get',
//		    fit:true,
			height:'70%',
		    showFooter:true,
		    striped:true,
		    fitColumns:true,
		    singleSelect:true,
		    checkOnSelect:false,
		    selectOnCheck:false,
		    autoRowHeight:false,
			rownumbers:true,
			pagination:true,
			remoteSort:false,
			nowrap:false,
		    idField:'SYANI_ID',
		    columns:[[
                {field:'SYOZOKU_KAISYA',title:'所属会社',width:130,align:'center',formatter:function(val,row,index){
                	return SYOZOKU_KAISYA[val];
                }},
                {field:'NAME',title:'社員名',width:100,align:'center',formatter:function(val,row,index){
                	return row.FIRST_NAME_KANJI + row.LAST_NAME_KANJI;
                }},
                {field:'SEIBETU',title:'性別',width:60,align:'center',formatter:function(val,row,index){
                	return sex[val];
                }},
                {field:'SYOKUGYO_KIND',title:'職業種類',width:60,align:'center',formatter:function(val,row,index){
                	return SYOKUGYO_KIND[val];
                }},
                {field:'NYUUSYA_DATE',title:'入社日',width:60,align:'center',formatter:function(val,row,index){
                	return new Date(val).toLocaleString();
                }},
                {field:'TAISYA_DATE',title:'退社日',width:80,align:'center',formatter:function(val,row,index){
                	if(!val) {
                		return '';
                	}
                	return new Date(val).toLocaleString();
                }},
                {field:'OPT',title:'編集',width:80,align:'center',formatter:function(val,row,index){
                	var result = '<a href="javascript:;" style="text-decoration:none" onClick="app.updateStaff({0})">更新</a>'.format(index);
                	if(user.userRole == 'S') {
                		result += '<br />';
                    	result += '<a href="javascript:;" class="admin" name="del" style="text-decoration:none" onClick="app.deleteStaff({0},\'{1}\')">削除</a>'.format(row.SYANI_ID, row.FIRST_NAME_KANJI + row.LAST_NAME_KANJI);
    				}
                	return result;
                }},
			]],
			toolbar:[{
                text:'新規登録',
                iconCls:'icon-add',
                handler:function(){
                	//$.messager.alert('info', '社員登録画面へ遷移。。（開発中）', 'info');
                	edit.openEditDlg();
            	}
            }],
            onLoadSuccess:function(data){
            	$(this).datagrid('clearSelections');
            	$(this).datagrid('clearChecked');
            }
		});
		userList.datagrid('options').url = "/user/getUserList";
		var pager = userList.datagrid('getPager');
		pager.pagination({
			showRefresh:false
		});
		
		searchForm.find("#searchBtn").click(function(){
			var params = getParams();
			if(params) {
				userList.datagrid('load', getParams());
			}
        });
		
		tabs.tabs({
			onSelect: function(title, index) {
				if(title == '作業催促') {
					gamenId = "TG0100";
				} else if(title == '受注管理') {
					gamenId = "TG0200";
				} else if(title == '外注管理') {
					gamenId = "TG0300";
				} else if(title == '請求管理') {
					gamenId = "TG0400";
				} else if(title == '見積作成') {
					gamenId = "TG0500";
				} else if(title == '各書類作成') {
					gamenId = "TG0600";
				} else if(title == '取引先管理') {
					gamenId = "TG0700";
				} else if(title == '社員管理') {
					gamenId = "TG0800";
				} else if(title == 'システム設定') {
					gamenId = "TG0900";
				} else if(title == 'ユーザ管理') {
					gamenId = "TG1000";
				}
				$.get(
	    			'/user/saveAccessLog',
	    			{gamenId: gamenId}
	    		);
			}
		});
		//tabs.tabs('select', '作業催促');
		tabs.tabs('select', '社員管理');
		
		edit.initModule();
	}
	
	function updateStaff(index) {
		row = userList.datagrid("getRows")[index];
		edit.openEditDlg(row);
		//row.SYANI_ID
	}
	
	function deleteStaff(id, name) {
		$.messager.confirm({
			title:'削除',
			msg:'以下の社員情報を削除しますか？社員名：' + name,
			ok: '削除',
			fn: function(r) {
				if(r && id) {
					$.get(
						'/user/deleteUser',
						{id:id},
						function(data) {
							userList.datagrid('reload');
						}
					);
				}
			}
		});
	}
	
	function getParams() {
		var params = {
			SYOZOKU_KAISYA: searchForm.find('#SYOZOKU_KAISYA').combobox('getValue'),
			name: searchForm.find('#name').textbox('getValue'),
			SYOKUGYO_KIND: searchForm.find('#SYOKUGYO_KIND').combobox('getValue'),
			ZAISEKI: searchForm.find("#ZAISEKI").checkbox('options').checked,
			HIZAISEKI: searchForm.find("#HIZAISEKI").checkbox('options').checked
		};
		if(!params.ZAISEKI && !params.HIZAISEKI) {
			$.messager.alert('warning', '在籍と非在籍がいずれにしても、１つのチェックが必須です。', 'warning');
			return;
		}
		return params;
	}
	
	function getUser() {
		$.get(
			'/getUser',
			{},
			function(data) {
				user = data;
				if(!user.userName) {
					window.location.reload();
				}
				$('#userName').html(user.userName);
				$('#userRole').html(user.userRole);
				if(user.userRole == 'A') {
					tabs.tabs('close', 'システム設定');
					tabs.tabs('close', 'ユーザ管理');
				} else if(user.userRole == 'B') {
					tabs.tabs('close', '請求管理');
					tabs.tabs('close', '見積作成');
					tabs.tabs('close', '取引先管理');
					tabs.tabs('close', '社員管理');
					tabs.tabs('close', 'システム設定');
					tabs.tabs('close', 'ユーザ管理');
				} else if(user.userRole == 'C') {
					tabs.tabs('close', '受注管理');
					tabs.tabs('close', '外注管理');
					tabs.tabs('close', '取引先管理');
					tabs.tabs('close', '社員管理');
					tabs.tabs('close', 'システム設定');
					tabs.tabs('close', 'ユーザ管理');
				} else if(user.userRole == 'D') {
					tabs.tabs('close', '受注管理');
					tabs.tabs('close', '外注管理');
					tabs.tabs('close', '請求管理');
					tabs.tabs('close', '見積作成');
					tabs.tabs('close', 'システム設定');
					tabs.tabs('close', 'ユーザ管理');
				}
			}
		);
	}
	
	function getLocalTime() {
		$.get(
			'/getLocalTime',
			{},
			function(data) {
				$('#localTime').html(data);
			}
		);
	}
	
	return {
		initModule : function() {
            initModule();
        },
        deleteStaff: function(id, name) {
        	deleteStaff(id, name);
        },
        updateStaff: function(index) {
        	//$.messager.alert('info', '該当社員情報の「ID:{0}」を持ち、社員更新画面へ遷移する。。（開発中）'.format(id), 'info');
        	updateStaff(index);
        },
        logout: function() {
        	$.get(
    			'/logout',
    			{},
    			function(data) {
    				window.location.href = '/login';
    			}
    		);
        },
        getUser: function() {
        	getUser();
        }
    }
}();