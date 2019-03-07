<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html charset=UTF-8">
	<title>测试接收JSON文件</title>
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/json2.js"></script>
	<script>
	$(document).ready(function(){
		testRequestBody();
	});
	function testRequestBody()
	{
		$.ajax(
		{
		url:"${pageContent.request.contextPath}/json/testRequestBody",
		dataType:"json",
		type:"POST",
		contentType:"application/json",
		data:JSON.stringify({loginName:"admin",password:"12345"}),
		async:true,
            success:function(data){
                consola.log(data);
                $("#loginName").html(data.loginName);
                $("#password").html(data.password);
                $("#userName").html(data.userName);
            },
            error:function(){
            alert("数据发送失败");
            }
		});
	}
	</script>
</head>
<body>
	登录名：<span id="loginName"></span><br />
	密码：<span id="password"></span><br />
	用户名：<span id="userName"></span><br />
</body>
</html>