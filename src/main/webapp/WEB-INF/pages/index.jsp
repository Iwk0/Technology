<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h2>Hello <security:authentication property="principal.username" /></h2>

        <jsp:useBean id="users" scope="request" type="java.util.List" />
        <c:forEach items="${users}" var="user">
            <div>${user.id} ${user.username} ${user.status} <spring:message code="ACTIVE" /></div>
        </c:forEach>

        <a href="${pageContext.request.contextPath}/logout">Logout</a>
        <a href="${pageContext.request.contextPath}/controlPanel">Control panel</a>
        <a href="${pageContext.request.contextPath}/user/new">New user</a>
    </body>
</html>