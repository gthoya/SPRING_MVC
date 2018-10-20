<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>File Upload Temp</title>
</head>
<body>
    <div style="width: 100%">
        <form id="uploadForm" method="POST" action="/uploadFile" enctype="multipart/form-data">
            <input type="file" id="attachFile" name="attachFile">
            <input type="submit" value="upload">
        </form>
    </div>
</body>
</html>
