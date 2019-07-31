<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>

<script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="../kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.ready(function(K) {
        window.editor = K.create('#editor_id', {
                width : '700px',
            uploadJson:'${pageContext.request.contextPath}/article/upload',
            //展示上传空间所有图片
            fileManagerJson:'${pageContext.request.contextPath}/article/showAll',
            allowFileManager:true,
            //默认值是imgFile,可修改
            filePostName:"file"
            }
        );
    });
</script>

<textarea id="editor_id" name="content" style="width:700px;height:300px;">
&lt;strong&gt;HTML内容&lt;/strong&gt;
</textarea>