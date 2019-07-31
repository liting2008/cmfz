<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%-- 在该HTML页面添加以下脚本 (文本编辑器)--%>
<script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="../kindeditor/lang/zh-CN.js"></script>
<script>
    $(function() {
            KindEditor.create('#editor_id', {
                    //通过K.create函数的第二个参数，可以对编辑器进行配置，具体参数请参考 编辑器初始化参数 。
                    width : '700px',
                uploadJson:'${pageContext.request.contextPath}/article/upload',
                //展示上传空间所有图片
                fileManagerJson:'${pageContext.request.contextPath}/article/showAll',
                allowFileManager:true,
                    //默认值是imgFile,可修改(此处改为file)
                filePostName:'file',
                //失去焦点属性
                    afterBlur:function(){
                        this.sync();
                    }
            });
    })

    function addArticle(){
        $.ajax({
            url:"${pageContext.request.contextPath}/article/addArticle",
            type:"post",
            datatype:"json",
            data:$("#articleForm").serialize(),
            success:function(){

            }
        })
    }
</script>
<%--动态模态框--%>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    文章上传
</button>

<!-- Modal -->
<%--tabindex="-1"   删除掉，否则永远获取不到焦点--%>
<div class="modal fade" id="myModal"  role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
          <%-- 容器改成800px与编辑器700px搭配--%>
        <div class="modal-content" style="width:800px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <form action="javascript:void(0)" id="articleForm">
            <div class="modal-body">

                <input type="text" name="title">
                 <%-- 在需要显示编辑器的位置添加textarea输入框。(文本编辑器) --%>
                <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                &lt;strong&gt;HTML内容&lt;/strong&gt;
                </textarea>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="addArticle()">Save changes</button>
            </div>
            </form>
        </div>
    </div>
</div>
