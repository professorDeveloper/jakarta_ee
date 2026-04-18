<%--
  Created by IntelliJ IDEA.
  User: azamo
  Date: 4/17/2026
  Time: 8:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>

<h1>Main Page </h1>
<h3>Hi <%=request.getSession().getAttribute("username")%>
</h3>
</body>
</html>
