<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="/resources/js/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#btnCreate").on("click", function() {
                if (window.confirm("등록 할거야?")) {
                    createContents()
                }
            })

            $("#btnModify").on("click", function() {
                if (window.confirm("수정 할거야?")) {
                    modifyContents()
                }
            })

            $("#btnDelete").on("click", function() {
                if (window.confirm("삭제 할거야?")) {
                    deleteContents()
                }
            })
        });

        function createContents() {
            if (!validateContents()) {
                return
            }

            var params = {
                title : $("#contentsTitle").val()
                , contentsBody : $("#contentsBody").val()
            }

            $.post("/contents/create", params, callback)
        }

        function modifyContents() {
            if (!validateContents()) {
                return
            }

            var params = {
                id : $("#contentsId").val()
                , title : $("#contentsTitle").val()
                , contentsBody : $("#contentsBody").val()
            }

            $.post("/contents/modify", params, callback)
        }

        function deleteContents() {
            $.post("/contents/unused/" + $("#contentsId").val(), null, callback)
        }

        function callback(result) {
            if (result == "success") {
                alert("성공~")
                location.href = "/board"
            } else {
                alert("실패~")
            }
        }

        function validateContents() {
            if ($("#contentsTitle").val() == "") {
                alert("제목을 입력해")
                return false
            } else if ($("#contentsBody").val() == "") {
                alert("내용을 입력해")
                return false
            }

            return true
        }
    </script>
</head>
<body>
<div>
    <div style="text-align: right; width: 100%;">
        <c:if test="${contents.id eq 0}">
            <button id="btnCreate">등록</button>
        </c:if>
        <c:if test="${contents.id ne 0 && userId eq contents.createUser}">
            <button id="btnModify">수정</button>
            <button id="btnDelete">삭제</button>
        </c:if>
    </div>
    <div style="width: 100%">
        <div style="float: left; border: 1px solid black; width: 5%">
            <input type="text" id="contentsId" value="${contents.id}" readonly>
        </div>
        <div style="float: left; border: 1px solid black; border-left: 0px; width: 60%;">
            <input type="text" id="contentsTitle" value="${contents.title}">
        </div>
        <div style="float: left; border: 1px solid black; border-left: 0px; width: 15%;">${contents.createDateTime}</div>
        <div style="float: left; border: 1px solid black; border-left: 0px; width: 15%;">${contents.createUserName}</div>
    </div>
    <div style="width: 100%; height: 50%;">
        <textarea id="contentsBody" style="width: 100%; height: 100%;">${contents.contentsBody}</textarea>
    </div>
</div>
</body>
</html>