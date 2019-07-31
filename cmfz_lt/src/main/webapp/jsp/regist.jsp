<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8" %>

<html lang="en">

<head>
    <script src="../boot/js/jquery-3.3.1.min.js"></script>
    <script src="../boot/js/script.js"></script>
    <script>
        function formSubmit() {
            $.ajax({
                url:"${pageContext.request.contextPath}/user/regist",
                datatype:"json",
                type:"post",
                data:$("#registForm").serialize(),
                success:function (data) {
                    if(data.error!=-400){
                        location.href="${pageContext.request.contextPath}/jsp/ok.jsp"
                    }else{
                        location.href="${pageContext.request.contextPath}/jsp/error.jsp"
                    }
                }
            })
        }
    </script>
</head>
<body>
    <h1>用户注册</h1>
<%--此处去掉ajax了--%>
    <form id="registForm"  method="post" action="${pageContext.request.contextPath}/user/regist">
            电话<input name="phone" type="text"><br>
            密码<input name="password" type="text"><br>
            上师<input name="dharmaName" type="text"><br>
            省份<input name="province" type="text"><br>
            城市<input name="city" type="text"><br>
            性别<input name="gender" type="text"><br>
            签名<input name="personalSign" type="text"><br>
            头像<input name="profile" type="text"><br>
                <input type="submit" value="提交" >
    </form>

</body>
</html>