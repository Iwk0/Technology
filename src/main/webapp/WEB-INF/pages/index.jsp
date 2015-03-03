<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <title>Title</title>
        <script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
        <script src="<c:url value='/resources/js/sockjs-0.3.4.js' />"></script>
        <script src="<c:url value='/resources/js/stomp.js' />"></script>
        <meta name="_csrf" content="${_csrf.token}" />
        <meta name="_csrf_header" content="${_csrf.headerName}" />
        <script>
            $(function() {
                var socket = new SockJS("/hello");
                var stompClient = Stomp.over(socket);
                stompClient.connect({}, function(frame) {});

                $("#upload").on("click", function() {
                    var oMyForm = new FormData(document.getElementById('form'));
                    var token = $("meta[name='_csrf']").attr("content");
                    var header = $("meta[name='_csrf_header']").attr("content");

                    $.ajax({
                        url: '<c:url value="/upload" />',
                        data: oMyForm,
                        dataType: 'text',
                        processData: false,
                        contentType: false,
                        type: 'POST',
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader(header, token);
                        },
                        complete: function(msg) {
                            if (msg.responseText === "SUCCESS") {
                                stompClient.send("/app/hello", {}, JSON.stringify({'name': msg.responseText}));
                                $("#error").text("");
                            } else if (msg.responseText === "FORMAT") {
                                $("#error").text("Wrong file format");
                            } else {
                                $("#error").text("ERROR");
                            }
                        }
                    });
                });
            });
        </script>
    </head>
    <body>
        <h2>Hello <security:authentication property="principal.username" /></h2>

        <c:forEach items="${users}" varStatus="i" var="user">
            <div>${user.id} ${user.username} ${user.status} <spring:message code="ACTIVE" /> ${i.index}</div>
        </c:forEach>

        <form style="margin-top: 20px; margin-bottom: 10px" id="form" enctype="multipart/form-data">
            <label> File name:
                <input type="text" name="name" />
            </label><br />
            <input style="margin-top: 10px;" name="file" type="file" />
        </form>
        <button id="upload">Upload</button>
        <label style="color: #ff0000" id="error"></label><br />

        <form action="<c:url value="/logout" />" id="logoutForm" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        <script>
            function logout() {
                document.getElementById("logoutForm").submit();
            }
        </script>
        <a href="javascript:logout();">Logout</a>

        <a href="<c:url value='/user/new' />">New user</a>
        <a href="<c:url value='/controlPanel' />">Control panel</a>
    </body>
</html>