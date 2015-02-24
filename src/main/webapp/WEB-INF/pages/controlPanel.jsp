<%--
  Created by IntelliJ IDEA.
  User: imishev
  Date: 15-2-24
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <title></title>
    </head>
    <body>
        <h1>Control panel</h1>
        <security:authorize access="hasRole('ADMIN')" var="role">
            <label>Hidden</label>
        </security:authorize>
    </body>
</html>