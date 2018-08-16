<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sign in</title>
    <script type="text/javascript" src="resources/js/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#signIn").on("click", function() {
                var params = {
                    userId : $("#userId").val()
                    , password : $("#password").val()
                }

                $.post("/signIn", params, signInCallback)
            })
        })

        function signInCallback(result) {
            alert(result.message)

            if (result.message == "success") {
                location.href = "/board"
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
</body>
</html>
