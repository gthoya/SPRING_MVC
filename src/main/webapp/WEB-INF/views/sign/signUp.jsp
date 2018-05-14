<%--
  Created by IntelliJ IDEA.
  User: gthoya
  Date: 2018-05-08
  Time: 오후 8:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sign up</title>
    <script type="text/javascript" src="resources/js/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#signIn").on("click", function() {
                var param = {
                    userId : $("#userId").val()
                    , password : $("#password").val()
                }

                $.post("/signUp", param, signUpCallback);
            })
        })

        function signUpCallback(result) {
            alert(result.message);
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
            <input type="button" id="signIn" value="회원가입">
        </div>
    </div>
</body>
</html>