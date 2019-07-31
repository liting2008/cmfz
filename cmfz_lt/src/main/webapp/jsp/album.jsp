<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%--页头--%>
<div class="page-header">
    <h1>专辑管理</h1>
</div>

<script>
<%-- jqGrid高级-->多级表格2--%>
    $(function(){
        $("#albumTable").jqGrid({
            styleUI:"Bootstrap",
            url:"${pageContext.request.contextPath}/album/queryAll",
            datatype:"json",
            colNames:["编号","名称","封面","数量","得分","作者","播音员","简介","上传时间"],
            colModel:[
                {name:"id"},
                {name:"title",editable:true},
                {name:"cover",editable:true,edittype:"file",formatter:function(cellvalue, options, rowObject){
                    return "<img style='width:50px;height:50px' src='${pageContext.request.contextPath}/albumPic/"+cellvalue+"'/>";
                    }},
                {name:"count",editable:true},
                {name:"score",editable:true},
                {name:"author",editable:true},
                {name:"broadcast",editable:true},
                {name:"brief",editable:true},
                {name:"publishTime",editable:true,edittype:"date"}],
            pager:"albumPager",
            rowNum:3,
            rowList:[3,5,7],
            viewrecords:true,
            autowidth:true,
            editurl:"${pageContext.request.contextPath}/album/edit",
            height:"100%",
            multiselect:true,
            rownumbers:true,
            subGrid : true,
            caption : "Grid as Subgrid",
            subGridRowExpanded : function(subgrid_id, row_id) {
                // we pass two parameters
                // subgrid_id is a id of the div tag created whitin a table data
                // the id of this elemenet is a combination of the "sg_" + id of the row
                // the row_id is the id of the row
                // If we wan to pass additinal parameters to the url we can use
                // a method getRowData(row_id) - which returns associative array in type name-value
                // here we can easy construct the flowing
                var subgrid_table_id, pager_id;
                subgrid_table_id = subgrid_id + "_t";
                pager_id = "p_" + subgrid_table_id;
                $("#" + subgrid_id).html(
                    "<table id='" + subgrid_table_id
                    + "' class='scroll'></table><div id='"
                    + pager_id + "' class='scroll'></div>");
                jQuery("#" + subgrid_table_id).jqGrid(
                    {
                        url : "${pageContext.request.contextPath}/chapter/queryAll?albumId=" + row_id,
                        datatype : "json",
                        styleUI:"Bootstrap",
                        autowidth:true,
                        colNames : [ '编号', '专辑编号', '名称', '大小','下载路径','上传时间','操作' ],
                        colModel : [
                            {name : "id"},
                            {name : "albumId"},
                            {name : "title"},
                            {name : "size"},
                            {name : "downPath"},
                            {name : "uploadTime"},
                            {name : "downPath",formatter:function(cellvalue, options, rowObject){
                             return "<a onclick=\"\" class=\"btn btn-primary\"href=\"${pageContext.request.contextPath}/chapter/download?downPath="+cellvalue+"\" role=\"button\">下载</a>"
                }},
                        ],
                        rowNum : 20,
                        pager : pager_id,
                        sortname : 'num',
                        sortorder : "asc",
                        height : '100%'
                    });
                jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                    "#" + pager_id, {
                        edit : true,
                        add : true,
                        del : true
                    });
            },
        }).jqGrid("navGrid","#albumPager",{},{
            //修改的部分
            closeAfterEdit:true,
            afterSubmit:function(response){
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/album/upload",
                    fileElementId:"cover",
                    data:{"id":response.responseText},
                    type:"post",
                    success:function(){
                        $("#albumTable").trigger("reloadGrid");
                    }
                })
                return "[true]";
            }
        },{
            //添加的部分
            closeAfterAdd:true,
            afterSubmit:function(response){
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/album/upload",
                    fileElementId:"cover",
                    data:{"id":response.responseText},
                    type:"post",
                    success:function(){
                        $("#albumTable").trigger("reloadGrid");
                    }
                })
                return "[true]";
            }
        })
    })
</script>
<table id="albumTable"></table>
<div id="albumPager"></div>