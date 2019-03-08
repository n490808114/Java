<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html charset=UTF-8">
	<title>测试接收JSON文件</title>
	<script type="text/javascript" src="resource/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="resource/js/json2.js"></script>
	<script>
	$(document).ready(function(){
		testRequestBody();
		testResponseBody();
	});
	function testRequestBody()
	{
		$.ajax
		(
                {
                    url:"${pageContent.request.contextPath}/web/json/testRequestBody",
                    dataType:"json",
                    type:"POST",
                    contentType:"application/json",
                    data:JSON.stringify({"loginName":"admin","password":"12345"}),
                    async:true,
                    success:function(data){
                        $("#loginName").html(data.loginName);
                        $("#password").html(data.password);
                        $("#userName").html(data.userName);
                    },
                    error:function(){
                    alert("数据发送失败");
                    }
                }
		);
	}
	function testResponseBody(){
	    $.ajax
	    (
	    {
	        url:"${pageContent.request.contextPath}/web/json/testResponseBody",
	        type:"get",
	        contentType:"application/json",
	        async:"true",
	            success:function(data){
	                for(var i=0;i<data.length;i++){
	                    var tr = $("#UserMessage");
                        $("<td/>").html(this.id).appendTo(tr);
                        $("<td/>").html(this.userName).appendTo(tr);
                        $("<td/>").html(this.loginName).appendTo(tr);
                        $("<td/>").html(this.password).appendTo(tr);
                        $("<td/>").html(this.createDate).appendTo(tr);
                        $("<td/>").html(this.email).appendTo(tr);
                        $("#UserTable").append(tr);
	                }
	            },
	            error:function(){
	                alert("数据获取失败");
	            }
	    }
	    )
	}
	</script>
</head>
<body>
	登录名：<span id="loginName"></span><br />
	密码：<span id="password"></span><br />
	用户名：<span id="userName"></span><br />

    <table id="UserTable">
        <tr id="UserMessage">
            <th>id</th>
            <th>UserName</th>
            <th>LoginName</th>
            <th>Password</th>
            <th>Status</th>
            <th>CreateDate</th>
            <th>Email</th>
        </tr>
    </table>
</body>
</html>