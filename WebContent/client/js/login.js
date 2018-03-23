/**
 * my_click和my_blur均是用于前台页面搜索框的函数
 */
// 鼠标点击搜索框时执行
function my_click(obj, myid) {
	// 点击时，如果取得的值和搜索框默认value值相同，则将搜索框清空
	if (document.getElementById(myid).value == document.getElementById(myid).defaultValue) {
		document.getElementById(myid).value = '';
		obj.style.color = '#000';
	}
}
// 鼠标不聚焦在搜索框时执行
function my_blur(obj, myid) {
	// 鼠标失焦时，如果搜索框没有输入值，则用搜索框的默认value值填充
	if (document.getElementById(myid).value == '') {
		document.getElementById(myid).value = document.getElementById(myid).defaultValue;
		obj.style.color = '#999';
	}
}

/**
 * 点击搜索按钮执行的函数
 */
function search() {
	document.getElementById("searchform").submit();
}
function login(){
//	alert("666");
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	
	$.ajaxSetup({
		async : false
	});
	$.post("/day01/login",
			{	username:username,
				password:password
			},function(data){
				var result = JSON.parse(data);
				if(result[0].statuCode === "1"){
					window.location.href='myaccount.html';
				}else{
					var message = document.getElementById('loginErrMes');
					message.innerHTML = '用户名或密码不正确';
				}
//				alert(result[0].statuCode);
			}), "json";
}