$(function() {
	loadHeader();
	app.initModule();
});

var app = function() {
	var loginBtn;
	var username;
	var password;
	var errMsg;
	
	function initModule() {
		loginBtn = $('#login');
		username = $('#username');
		password = $('#password');
		errMsg = $('#errMsg');
		getUserNameFromCookie();
		getVersion();
		loginBtn.click(function() {
			login();
		});
	}
	
	function getVersion() {
		$.get(
			'/getVersion',
			{},
			function(data) {
				$('#version').html(data.version);
				$('#function').html(data.function);
			}
		);
	}
	
	function getUserNameFromCookie() {
		var cookies = document.cookie.split(';');
        for(var i in cookies) {
            var cookie = cookies[i].split('=');
            if(cookie.length > 1) {
                var key = cookie[0].trim();
                var value = cookie[1].trim();
                if(key == 'userName') {
                	username.textbox('setValue', value);
                }
            }
        }
	}
	
	function login() {
		var userNameStr = username.textbox('getValue');
		var passwordStr = password.passwordbox('getValue');
		if(userNameStr == '') {
			$.messager.alert('warning','ユーザIDを入力してください。','warning');
			return;
		}
		if(passwordStr == '') {
			$.messager.alert('warning','パスワードを入力してください。','warning');
			return;
		}
		var userNameReg = new RegExp(/^[0-9A-Za-z_-]+$/);
		var passwordReg = new RegExp(/^[0-9A-Za-z]+$/);
		if(!userNameReg.test(userNameStr)) {
			$.messager.alert('warning','ユーザIDは[a-z]、[A-z]、[0-9]、「_」、「-」で入力してください。','warning');
			return;
		}
		if(!passwordReg.test(passwordStr)) {
			$.messager.alert('warning','パスワードは[a-z]、[A-z]、[0-9]で入力してください。','warning');
			return;
		}
		if(userNameStr.length < 4 || userNameStr.length > 20) {
			$.messager.alert('warning','ユーザIDは4～20文字を入力してください。','warning');
			return;
		}
		if(passwordStr.length < 4 || passwordStr.length > 15) {
			$.messager.alert('warning','パスワードは4～15文字を入力してください。','warning');
			return;
		}
		var params = {
			userName:userNameStr,
			password:passwordStr
		};
		$.get(
			"/loginAuth",
			params,
			function(data) {
				if(data == 'success') {
					var d = new Date();
			        d.setTime(d.getTime() + 7*24*60*60*1000);
			        var expires = ";expires=" + d.toGMTString();
					document.cookie = "userName=" + params.userName + expires;
					window.location.href = '/index';
					$.get(
		    			'/user/saveAccessLog',
		    			{gamenId: 'TG0100'}
		    		);
				} else {
					errMsg.html(data);
					username.textbox('clear');
					password.passwordbox('clear');
				}
				
			}
		);
	}
	
	return {
        initModule: function () {
            initModule();
        }
    }
}();