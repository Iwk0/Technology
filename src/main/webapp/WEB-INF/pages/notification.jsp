<%--
  Created by IntelliJ IDEA.
  User: Iwk0
  Date: 02/03/2015
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Hello WebSocket</title>
        <script src="<c:url value='/resources/js/sockjs-0.3.4.js' />"></script>
        <script src="<c:url value='/resources/js/stomp.js' />"></script>
        <script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
        <script type="text/javascript">
            $(function() {
                var socket = new SockJS('/hello');
                var stompClient = Stomp.over(socket);

                stompClient.connect({}, function(frame) {
                    stompClient.subscribe('/topic/greetings', function(greeting) {
                        showGreeting(JSON.parse(greeting.body).content);
                    });
                });

                function showGreeting(message) {
                    $("#response").text(message);
                }
            });
        </script>
    </head>
    <body>
        <div>
            <p id="response"></p>
        </div>
    </body>
</html>