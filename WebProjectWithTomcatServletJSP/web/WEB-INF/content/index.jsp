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
		testReadXml();
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
	                    var simgle_data = data[i];
                        $("<td/>").html(simgle_data.id).appendTo(tr);
                        $("<td/>").html(simgle_data.userName).appendTo(tr);
                        $("<td/>").html(simgle_data.loginName).appendTo(tr);
                        $("<td/>").html(simgle_data.password).appendTo(tr);
                        $("<td/>").html(simgle_data.createDate).appendTo(tr);
                        $("<td/>").html(simgle_data.email).appendTo(tr);
                        $("#UserTable").append(tr);
                        tr = null;
	                }
	            },
	            error:function(){
	                alert("数据获取失败");
	            }
	    }
	    )
	}
	function testReadXml(){
	    var xmlData = "
	    <?xml version="1.0" encoding="utf-8" standalone="yes"?>
	    <User>
	        <id>1</id>
	        <userName>testUserName</userName>
	        <password>testPassword</password>
	    </User>
	    "
	    $.ajax({
	        url : "${pageContent.request.contextPath}/web/xml/readxml",
	        type:"post",
	        contentType:"application/xml",
	        data:xmlData,
	        async:true,
	    })
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