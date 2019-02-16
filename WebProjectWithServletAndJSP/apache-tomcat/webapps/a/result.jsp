<html>
<head>
    <meta charset="UTF-8">
    <title>RESULT</title>
</head>
<body>
    <h1 align="center">RESULT:</h1>
    <%
    String result = (String) request.getAttribute("result");
    out.print(result);
    %>
</body>   

</html>