/**
 * 注册用的js
 */
var iscodecheck = false;
function changeImage() {
	// 改变验证码图片中的文字
	document.getElementById("img").src = "/day01/imageCode?time="
			+ new Date().getTime();
}

// 验证码失去焦点
function inputOnBlur() {
	var codetext = document.getElementById("codetext").value;
	var errorMes = document.getElementById("codeErrorMessage");
	if (codetext.length != 4) {
		errorMes.className = "error";
		errorMes.innerHTML = " 请输入4位验证码";
		return;
	}
	var o;
	$.ajaxSetup({
		async : false
	});
	$.post("/day01/checkCode", {
		name : codetext,
	}, function(data) {
		o = JSON.parse(data);
	}), "json";
	// alert(o[0].statuCode);
	if (o[0].statuCode === "1") {
		// alert("验证成功");
		errorMes.className = "error";
		errorMes.innerHTML = " OK";
		iscodecheck = true;
	} else {
		errorMes.className = "error";
		errorMes.innerHTML = " 验证码输入错误";
	}
}
window.onload = function () { 
	var username = document.getElementById("username");
	username.onblur = function(){
		alert("666");
	}
	
};
function check() {
	var ischeckform = iscodecheck; // 验证码
	var oName_state = false;
	var psw_state = false;
	var psw2_state = false;
	var userNameMes = document.getElementById("userNameMessage");
	
	var username = document.getElementById("username");
	if (username.value.length < 6) {
		userNameMes.className = "error";
		userNameMes.innerHTML = "用户名至少为6位";
		return false;
	}

	// 验证码
	if (!ischeckform) {
		var errorMes = document.getElementById("codeErrorMessage");
		errorMes.className = "error";
		errorMes.innerHTML = " 请输入正确的验证码";
	}
	if (ischeckform && oName_state && psw_state && psw2_state) {
		return true;
	} else {
		return false;
	}
}