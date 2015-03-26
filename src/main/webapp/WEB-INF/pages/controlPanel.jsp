<jsp:useBean id="users" scope="request" type="org.springframework.data.domain.Page"/>
<%--
  Created by IntelliJ IDEA.
  User: imishev
  Date: 15-2-24
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title></title>
        <link type="text/css" href="http://jqueryrock.googlecode.com/svn/trunk/css/jquery-ui-1.9.2.custom.css" rel="stylesheet" />
        <link type="text/css" href="http://jqueryrock.googlecode.com/svn/trunk/jqgrid/css/ui.jqgrid.css" rel="stylesheet" />
        <script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
        <script type="text/javascript" src="http://jqueryrock.googlecode.com/svn/trunk/js/jquery-ui-1.9.2.custom.js"></script>
        <script src="http://jqueryrock.googlecode.com/svn/trunk/jqgrid/js/grid.locale-en.js" type="text/javascript"></script>
        <script src="http://jqueryrock.googlecode.com/svn/trunk/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
        <script>
            $(function () {
                /*$("#list").jqGrid({
                    url: '<c:url value="/users/list" />',
                    datatype: "json",
                    mtype: "GET",
                    colNames: [ "Username", "Password", "Status" ],
                    colModel: [
                        { name: "username",sortable: true },
                        { name: "password", sortable: false},
                        { name: "status", align: "right",sortable: false }
                    ],
                    pager: "#pager",
                    rowNum: 5,
                    rownumbers: true,
                    rowList: [5, 10, 15],
                    height: '500',
                    width: 'auto',
                    loadonce: true,
                    caption: "Simple first grid"
                });*/

                /*$.ajax({
                    url: '<c:url value="/users/list" />',
                    type: 'GET',
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    async: false,
                    success: function(msg) {
                        console.log(JSON.stringify(msg));
                    }
                });*/
            });
        </script>
    </head>
    <body>
        <%--<jsp:include page="your jsp" />--%>
<%--        <h1>Control panel</h1>
        <security:authorize access="hasAnyRole('ADMIN', 'USER')">
            <label>Hidden</label>
        </security:authorize>--%>
        <div>${users.totalPages}</div>
        <c:set value="${users.number}" var="page" />
        <c:if test="${page - 1 >= 0}">
            <a href="<c:url value="/controlPanel?size=${page}" />">Previous</a>
        </c:if>
        <c:if test="${page + 2 <= users.totalPages}">
            <a href="<c:url value="/controlPanel?size=${page + 2}" />">Next</a>
        </c:if>
        <c:forEach items="${users.content}" var="user">
            <div>${user.username}</div>
        </c:forEach>
        <table id="list">
            <tr>
                <td></td>
            </tr>
        </table>
        <div id="pager"></div>
        <a href="<c:url value='/' />">Home page</a>
    </body>
</html>