<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>My Main Page</title>
</head>
<body>
<h1>${requestScope.loginname}</h1>
<h1>${requestScope.username}</h1>
<h1>${requestScope.password}</h1>
<h1>${requestScope.email}</h1>
</body>
</html>