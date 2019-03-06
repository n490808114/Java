<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
<h1>${requestScope.message}</h1>
<h1>${requestScope.user.getLoginName()}</h1>
<h1>${requestScope.user.getUserName()}</h1>
<h1>${requestScope.user.getPassword()}</h1>
</body>
</html>


