var p = window.parent;

//新增或修改弹框
var edit = function () {
    var editDlg = undefined;
    var editForm = undefined;
    var dataList = undefined;

    function initModule() {
        editDlg = $('#editDlg');
        editForm = $("#editForm");
        dataList = $('#dataList');
        editDlg.dialog({
            //iconCls:'Applicationformedit',
            closed:true,
            modal:true,
            buttons:[{
                text: '提交',
                iconCls: 'Accept',
                handler: function () {
                    save();
                }
            },{
                text: '关闭',
                iconCls: 'Cancel',
                handler: function () {
                    editDlg.dialog('close');
                }
            }]
        });
    }

    //新增或修改
    function save() {
        //验证表单
        if(!editForm.form('validate')){
            return;
        }
        var params = {
			SYAIN_ID: editDlg.find('#SYAIN_ID').val(),
        	SYOZOKU_KAISYA: editDlg.find('#SYOZOKU_KAISYA').combobox('getValue'),
        	FIRST_NAME: editDlg.find('#FIRST_NAME_KANJI').textbox('getValue'),
        	LAST_NAME: editDlg.find('#LAST_NAME_KANJI').textbox('getValue'),
        	SEIBETU: editDlg.find('#SEIBETU').combobox('getValue'),
        	SYOKUGYO_KIND: editDlg.find('#SYOKUGYO_KIND').combobox('getValue'),
        	NYUUSYA_DATE: editDlg.find('#NYUUSYA_DATE').datetimebox('getValue')
        };
        $.post(
        	'/user/saveUser',
        	params,
        	function(data) {
        		alert('ok');
        	}
        );
    }

    //修改时加载数据
    function loadData(row) {
//        row.start_time = Common.dateTimeFormat(row.start_time);
//        row.end_time = Common.dateTimeFormat(row.end_time);
    	//alert(new Date(row.NYUUSYA_DATE).format('MM/dd/yyyy HH:mm:ss'));
    	row.NYUUSYA_DATE = new Date(row.NYUUSYA_DATE).format('MM/dd/yyyy HH:mm:ss')
        editForm.form('load', row);
    }

    return {
        initModule: function () {
            initModule();
        },
        openEditDlg: function (row) {
            editForm.form('reset');
            editDlg.find('#id').val('');
//            editDlg.find("#start_time").datetimebox("setValue",p.Tools.dateAdd(new Date(), 0));
//            editDlg.find("#end_time").datetimebox("setValue",p.Tools.dateAdd(new Date(), 7));
            if(row) {
                loadData(row);
            }
            editDlg.dialog('open');
        }
    }
}();