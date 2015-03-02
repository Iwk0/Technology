<%--
  Created by IntelliJ IDEA.
  User: imishev
  Date: 15-2-24
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title></title>
    </head>
    <body onload="document.login.username.focus();">
        <h1><spring:message code="label.title" /></h1>
        <form name="login" action="<c:url value='/j_spring_security_check' />" method="POST">
            <div>
                <label>
                    Username: <input type="text" name="username"/>
                </label>
            </div>
            <div>
                <label>Password: <input type="password" name="password"/></label>
            </div>
            <c:if test="${not empty error}">
                <br /><div style="color: red">Incorrect password</div>
            </c:if>

            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}" />

            <input type="submit" value="Login" class="submit"/>
        </form>
    </body>
</html>