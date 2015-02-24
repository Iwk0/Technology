<%--
  Created by IntelliJ IDEA.
  User: imishev
  Date: 15-2-24
  Time: 13:41
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title></title>
    </head>
    <body>
        <form:form modelAttribute="user">
            <label><spring:message code="label.username" /><form:input path="username" /></label><br />
            <label><spring:message code="label.password" /><form:password path="password" /></label><br />
            <label><spring:message code="label.role" /><br />
                <form:select path="role">
                    <c:forEach items="${roles}" var="item">
                        <form:option value="${item}"><spring:message code='${item}' /></form:option>
                    </c:forEach>
                </form:select>
            </label>
            <input type="submit" value="<spring:message code='button.registration' />" />
        </form:form>
    </body>
</html>