function login(){
	var username=$("#inputUsername").val().trim();
	var password=$("#inputPassword").val().trim();
	
	$("#promptUn").remove();
	$("#promptPw").remove();
	
	if(!username || username==""){
		$("#inputUsername").val("");
		$("#inputUsername").after("<p id='promptUn'>请输入账号！</p>")
		return;
	}
	if(!password || password==""){
		$("#inputPassword").val("");
		$("#inputPassword").after("<p id='promptPw'>请输入密码！</p>");
		return;
	}
	$("#loginButton").hide();
	$("#loadingImg").show();
	var url="/admin/user/login";
	$.ajax({
		url:url,
		type:"POST",
		data:$("#loginForm").serialize(),
		dataType:"html",
		success:function(data,textStatus){
			console.info(data);
			console.info(textStatus);
//			console.info(xhr);
			if(textStatus == 'success'){
				window.location.href = "/admin/admin-index.html";
			}
		},
		error:function(XMLHttpRequest){
			$("#loginButton").show();
			$("#loadingImg").hide();
			if (XMLHttpRequest.status == 500) {
				alert("登录失败，服务器错误！");
			}
			if (XMLHttpRequest.status == 404) {
				alert("用户不存在或密码错误！");
			}
		}
	});
}


function zc(){
	
	var sfz=$("#sfz").val().trim();
	var zz=/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;  //定义正则表达式
	var pw=/sdsd/;
	
	if(zz.test(sfz)){
		$.ajax({
			
		});
	}
	$("#sfzYz").val("请输入正确的身份证");
	
}