<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Register</title>
</head>
<body>
    <form method="post" action="register">
        <h1>注册账户</h1>
        <fieldset>
            <p>
                <label for="get_loginname">登录名：</label>
                <input type="text" id="get_loginname" name="loginname"/>
            </p>
            <p>
                <label for="get_username">用户名：</label>
                <input type="text" id="get_username" name="username"/>
            </p>
            <p>
                <label for="get_email">邮箱：</label>
                <input type="email" id="get_email" name="email"/>
            </p>
            <p>
                <label for="get_password">密码：</label>
                <input type="password" id="get_password" name="password"/>
            </p>
        </fieldset>
        <input type="submit" value="注册">
    </form>
</body>
</html>