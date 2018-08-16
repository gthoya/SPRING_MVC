<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="/resources/js/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#btnSearch").on("click", function() {
                if ($("#searchText").val() == "") {
                    alert("검색어를 입력해")
                    return
                }

                getContentsList()
            })

            $("#btnCreate").on("click", function() {
                getContents(-1)
            })

            $("#btnSignUp").on("click", function() {
                location.href = "/signUp"
            })

            $("#btnSignIn").on("click", function() {
                location.href = "/signIn"
            })

            $("#btnSignOut").on("click", function() {
                if (confirm("로그아웃 할거야?")) {
                    $.get("/signOut", null, location.reload())
                }
            })

            getContentsList();
        });

        function searchCallback(result) {
            $("#contentsList").html(result)
        }

        function getContents(id) {
            location.href = "/contents/" + id
        }

        function getContentsList() {
            var params = {}

            if ($("#searchOption").val() == "title") {
                params.title = $("#searchText").val()
            } else if ($("#searchOption").val() == "contentsBody") {
                params.contentsBody = $("#searchText").val()
            }

            $.get("/contentsList", params, searchCallback)
        }
    </script>
</head>
<body>
    <div style="text-align: right; width: 100%;">
        <c:if test="${not empty userId}">
            <button id="btnCreate">등록</button>
            <button id="btnSignOut">로그아웃</button>
        </c:if>
        <c:if test="${empty userId}">
            <button id="btnSignIn">로그인</button>
            <button id="btnSignUp">회원가입</button>
        </c:if>
    </div>
    <div style="width: 100%; text-align: center;">
        <select id="searchOption">
            <option value="title">제목</option>
            <option value="contentsBody">내용</option>
        </select>
        <input type="text" id="searchText"/>
        <button id="btnSearch">검색</button>
    </div>
    <div style="width: 100%;">
        <div style="float: left; border: 1px solid black; width: 5%;">no</div>
        <div style="float: left; border: 1px solid black; border-left: 0px; width: 60%;">제목</div>
        <div style="float: left; border: 1px solid black; border-left: 0px; width: 15%;">작성일시</div>
        <div style="float: left; border: 1px solid black; border-left: 0px; width: 15%;">작성자</div>
    </div>
    <div id="contentsList" />
</body>