<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<div class="page-header">
    <h1>用户管理</h1>
</div>
<script>
    $(function(){
        $("#userTable").jqGrid({
            styleUI:"Bootstrap",
            url:"${pageContext.request.contextPath}/user/queryAll",
            datatype:"json",
            colNames:[  '用户id', '手机号', '密码','盐' ,'上师名称', '省份','性别', '个性签名' , '头像路径', '状态', '注册时间',"操作"],
            colModel:[
                {name : 'id'},
                {name : 'phone',editable:true},
                {name : 'password',editable:true},
                {name : 'salt'},
                {name : 'dharmaName',editable:true},
                {name : 'province',editable:true},
                {name : 'gender',editable:true},
                {name : 'personalSign',editable:true},
                {name : 'profile',
                    editable:true,
                    edittype:"file",
                    formatter:function (cellvalue, options, rowObject) {
                        return "<img style='width:50px;height:50px' src='${pageContext.request.contextPath}/carouselPic/"+cellvalue+"'/>";
                    }
                },
                {name : 'status',editable:true},
                {name : 'registTime',edittype:"date"},
            {
                formatter:function (cellvalue, row, jsonRow) {
                    if(jsonRow.status=="冻结"){
                        return "<button class='btn btn-primary btn-sm' onclick='update(\""+jsonRow.id+"\",\""+jsonRow.status+"\")'>解除冻结</button>"
                    }else if(jsonRow.status=="正常"){
                        return " <button class='btn btn-primary btn-sm' onclick='update(\""+jsonRow.id+"\",\""+jsonRow.status+"\")'>点击冻结</button>"
                    }
                }
            }
                ],
            pager:"userPager",
            rowNum:3,
            rowList:[3,5,7],
            viewrecords:true,
            autowidth:true,
            editurl:"${pageContext.request.contextPath}/user/edit",
            height:"100%",
            multiselect:true,
            rownumbers:true,
            subGrid : true,
            caption : "Grid as Subgrid",
            subGridRowExpanded : function(subgrid_id, row_id) {
                alert(row_id)
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
                        url : "/SubGrid?q=2&id=" + row_id,
                        datatype : "json",
                        colNames : [ 'No', 'Item', 'Qty', 'Unit','Line Total' ],
                        colModel : [
                            {name : "num",  index : "num",width : 80,key : true},
                            {name : "item",index : "item",  width : 130},
                            {name : "qty",index : "qty",width : 70,align : "right"},
                            {name : "unit",index : "unit",width : 70,align : "right"},
                            {name : "total",index : "total",width : 70,align : "right",sortable : false}
                        ],
                        rowNum : 20,
                        pager : pager_id,
                        sortname : 'num',
                        sortorder : "asc",
                        height : '100%'
                    });
                jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                    "#" + pager_id, {
                        edit : false,
                        add : false,
                        del : false
                    });
            },
        }).jqGrid("navGrid","#userPager",{},{
            //修改的部分
            closeAfterEdit:true,
            afterSubmit:function(response){
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/user/upload",
                    fileElementId:"profile",
                    data:{"id":response.responseText},
                    type:"post",
                    success:function(){
                        $("#userTable").trigger("reloadGrid");
                    }
                })
                return "[true]";
            }
        },{
            //添加的部分`
            closeAfterAdd:true,
            afterSubmit:function(response){
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/user/upload",
                    fileElementId:"profile",
                    data:{"id":response.responseText},
                    type:"post",
                    success:function(){
                        $("#userTable").trigger("reloadGrid");
                    }
                })
                return "[true]";
            }
        })
    })

    function update(id,status) {
        $.ajax({
            url:"${pageContext.request.contextPath}/user/modifyStatus",
            type:"post",
            datatype:"json",
            data:{status:status,id:id},
            success:function(data){
                $("#userTable").trigger("reloadGrid");
            }
        })
    }
</script>
<table id="userTable"></table>
<div id="userPager"></div>
