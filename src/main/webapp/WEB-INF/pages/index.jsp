<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title>Title</title>
        <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    </head>
    <body>
        <h2>Hello <security:authentication property="principal.username" /></h2>

        <%--<jsp:useBean id="users" scope="request" type="java.util.List" />--%>
        <c:forEach items="${users}" varStatus="i" var="user">
            <div>${user.id} ${user.username} ${user.status} <spring:message code="ACTIVE" /> ${i.index}</div>
        </c:forEach>
        <h1>Wide</h1>
        <a href="<c:url value='/logout' />">Logout</a>
        <a href="<c:url value='/controlPanel' />">Control panel</a>
        <a href="<c:url value='/user/new' />">New user</a>

        <script>
            $(function() {
                var arr = { username: "dasdsa", password: "password", id: 1, amount : "4545345344354354353454332" };
                $.ajax({
                    url: '<c:url value="/description" />',
                    type: 'POST',
                    data: JSON.stringify(arr),
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    async: false,
                    success: function(msg) {
                        if ($.isEmptyObject(msg)) {
                            console.log("OK");
                        } else {
                            console.log("NOT");
                        }
                    }
                });
            });
        </script>
    </body>
</html>