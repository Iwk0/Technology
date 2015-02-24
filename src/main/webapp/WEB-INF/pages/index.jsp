<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.2.min.js"></script>
    </head>
    <body>
        <h2>Hello <security:authentication property="principal.username" /></h2>

        <jsp:useBean id="users" scope="request" type="java.util.List" />
        <c:forEach items="${users}" var="user">
            <div>${user.id} ${user.username} ${user.status}</div>
        </c:forEach>

        <a href="${pageContext.request.contextPath}/logout">Logout</a>
        <a href="${pageContext.request.contextPath}/controlPanel">Control panel</a>
    </body>
</html>