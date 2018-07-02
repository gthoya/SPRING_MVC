<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>board</title>
    <script type="text/javascript" src="resources/js/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var param = {}

            $.get("/contentsList", param, searchCallback)
        });

        function searchCallback(result) {
            $("#contentsList").html(result)
        }
    </script>
</head>
<body>
    <div style="width: 100%;">
        <div style="float: left; width: 5%;">no</div>
        <div style="float: left; width: 65%;">제목</div>
        <div style="float: left; width: 15%;">작성일시</div>
        <div style="float: left; width: 15%;">작성자</div>
    </div>
    <div id="contentsList" />
</body>