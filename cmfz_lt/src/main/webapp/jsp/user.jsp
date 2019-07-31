<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%--文件上传添加cnctype--%>
<form method="post" action="${pageContext.request.contextPath}/user/importFile" enctype="multipart/form-data ">
    <input type="file" name="file">
    <input type="submit">
</form>