<%--
  Created by IntelliJ IDEA.
  User: gthoya
  Date: 2018-05-08
  Time: 오후 8:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>sign in</title>
    <script type="text/javascript" src="resources/js/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#signIn").on("click", function() {
                var param = {
                    userId : $("#userId").val()
                    , password : $("#password").val()
                }

                $.post("/signIn", param, signInCallback);
            })
        })

        function signInCallback(result) {
            alert(result.message);

            if (result.message == "success") {
                $("#user").text(result.userId + " 님 로그인 하셨습니다.");
                $("#userDiv").visible(true);
            } else {
                $("#user").text("");
                $("#userDiv").visible(false);
            }
        }
    </script>
</head>
<body>
    <div style="text-align: center">
        <div>
            <label>ID</label>
            <input type="text" id="userId" style="width: 120px"/>
        </div>
        <div>
            <label>Password</label>
            <input type="password" id="password" style="width: 120px"/>
        </div>
        <div>
            <input type="button" id="signIn" name="signIn" value="로그인">
        </div>
    </div>
    <div id="userDiv" style="text-align: center" visible="false">
        <label id="user"></label>
    </div>
</body>
</html>
