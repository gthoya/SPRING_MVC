<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="/resources/js/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#btnCreate").on("click", function() {
                getContents(-1)
            })


            var param = {}

            $.get("/contentsList", param, searchCallback)
        });

        function searchCallback(result) {
            $("#contentsList").html(result)
        }

        function getContents(id) {
            location.href = "/contents/" + id
        }
    </script>
</head>
<body>
    <div style="text-align: right; width: 100%;">
        <button id="btnCreate">등록</button>
    </div>
    <div style="width: 100%;">
        <div style="float: left; border: 1px solid black; width: 5%;">no</div>
        <div style="float: left; border: 1px solid black; border-left: 0px; width: 60%;">제목</div>
        <div style="float: left; border: 1px solid black; border-left: 0px; width: 15%;">작성일시</div>
        <div style="float: left; border: 1px solid black; border-left: 0px; width: 15%;">작성자</div>
    </div>
    <div id="contentsList" />
</body>