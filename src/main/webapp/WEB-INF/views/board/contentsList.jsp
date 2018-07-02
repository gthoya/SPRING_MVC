<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${contentsList}" var="contents">
<div style="width: 100%;">
    <div style="float: left; width: 5%;">${contents.id}</div>
    <div style="float: left; width: 65%;">${contents.title}</div>
    <div style="float: left; width: 15%;">${contents.createDateTime}</div>
    <div style="float: left; width: 15%;">${contents.createUser}</div>
</div>
</c:forEach>