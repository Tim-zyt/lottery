/**
 * Created by 01170626 on 2016/12/7.
 */
$(document).ready(function () {
    refreshOperaPage();
});

var operas = "";

function refreshOperaPage(){
    $.ajax({
        type: "post",
        url : getContextPath() + "/opera/getAllOperas",
        dataType:'json',
        data: {
        },
        success: function(data){
            operas = data.data;
            var preOperaId = data.message;
            var operaHtml = "";
            var iLen = operas.length;
            for(var i = iLen - 1 ; i >=0  ; i--){
                if(operas[i].id==preOperaId){
                    operaHtml+="<tr><td><span style='cursor:pointer;' id='opera"+i+"' onclick='selectOpera("+i+","+iLen+","+preOperaId+")' class='label label-success'>";
                }else{
                    operaHtml+="<tr><td><span style='cursor:pointer;' id='opera"+i+"' onclick='selectOpera("+i+","+iLen+","+preOperaId+")' class='label label-default'>";
                }
                operaHtml+="选中"+"</span></td><td>"+operas[i].opName+"</td><td>"+operas[i].opActor+"</td><td>"+
                    operas[i].opDepartment+"</td><td>"+operas[i].opSort+"</td><td><span style='cursor:pointer;' class='label label-info' onclick='confirmUpdateOpera("+i+")'>"+"编辑"+"</span></td><td>" +
                    "<span class='label label-danger' style='cursor:pointer;' onclick='comfirmDeleteOpera("+operas[i].id+")'>"+"删除"+"</span></td></tr>";
            }
            $("#operas").html(operaHtml);
        }
    });
}

function selectOpera(i,iLen,preOperaId){
    if(preOperaId!=operas[i].id){
        $.ajax({
            type: "post",
            url : getContextPath() + "/config/setCurrentOpera",
            dataType:'json',
            data: {
                "operaId":operas[i].id,
                "opName":operas[i].opName,
                "preOperaId":preOperaId
            },
            success: function(data){
                var selectSuccess = data.data;
                if(selectSuccess){
                    refreshOperaPage();
                }else{
                    layer.msg('选中失败', {
                        time: 500, //20s后自动关闭
                    });
                }
            }
        });
    }
}

function comfirmDeleteOpera(operaId) {
    layer.confirm('确定要删除该节目吗？', {
        btn: ['删除','取消'] //按钮
    }, function(){
        deleteOpera(operaId);
    }, function(){
        layer.msg('取消删除', {
            time: 500, //20s后自动关闭
        });
    });
}


function deleteOpera(operaId) {
    $.ajax({
        type: "post",
        url : getContextPath() + "/opera/deleteOpera?operaId="+operaId,
        dataType:'json',
        data: {
        },
        success: function(data){
            var deleteSuccess = data.data;
            if(deleteSuccess){
                layer.msg('删除成功', {
                    time: 500, //20s后自动关闭
                    // btn: ['明白了', '知道了']
                });
                refreshOperaPage();
            }else{
                layer.msg('删除失败', {
                    time: 500, //20s后自动关闭
                    // btn: ['明白了', '知道了']
                });
            }
        }
    });
}

function confirmUpdateOpera(i){
    layer.open({
        type: 1,
        closeBtn: false,
        shift: 7,
        shadeClose: true,
        content: "<div style='width:350px;'><div style='width:320px;margin-left: 3%;' class='form-group has-feedback'><p>请输入节目名称</p><input id='opName' class='form-control' type='text' name='opName' value='"+operas[i].opName+"'/></div>" +
        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'><p>请输入节目演员</p><input id='opActor' class='form-control' type='text' name='opActor' value='"+operas[i].opActor+"'/></div>"+
        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'><p>请输入节目部门</p><input id='opDepartment' class='form-control' type='text' name='opDepartment' value='"+operas[i].opDepartment+"'/></div>"+
        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'><p>请输入节目顺序</p><input id='opSort' class='form-control' type='number' name='opSort' value='"+operas[i].opSort+"'/></div>"+
        "<button style='margin-top:5%;' type='button' class='btn btn-block btn-success btn-lg' onclick='updateOpera("+operas[i].id+")'>提交</button></div>"
    });
}

function updateOpera(operaId){
    var opName = $("#opName").val();
    var opActor = $("#opActor").val();
    var opDepartment = $("#opDepartment").val();
    var opSort = $("#opSort").val();
    $.ajax({
        type: "post",
        url : getContextPath() + "/opera/updateOpera",
        dataType:'json',
        data: {
            "operaId":operaId,
            "opName":opName,
            "opActor":opActor,
            "opSort":opSort,
            "opDepartment":opDepartment
        },
        success: function(data){
            var updateSuccess = data.data;
            if(updateSuccess){
                layer.msg('修改成功', {
                    time: 500, //20s后自动关闭
                    // btn: ['明白了', '知道了']
                });
                refreshOperaPage();
            }else{
                layer.msg('修改失败', {
                    time: 500, //20s后自动关闭
                    // btn: ['明白了', '知道了']
                });
            }
        }
    });
    layer.closeAll();
}

function confirmAddOpera(){
    layer.open({
        type: 1,
        closeBtn: false,
        shift: 7,
        shadeClose: true,
        content: "<div style='width:350px;'><div style='width:320px;margin-left: 3%;' class='form-group has-feedback'><p>请输入节目名称</p><input id='opName' class='form-control' type='text' name='opName' /></div>" +
        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'><p>请输入节目演员</p><input id='opActor' class='form-control' type='text' name='opActor' /></div>"+
        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'><p>请输入节目部门</p><input id='opDepartment' class='form-control' type='text' name='opDepartment' /></div>"+
        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'><p>请输入节目顺序</p><input id='opSort' class='form-control' type='number' name='opSort' /></div>"+
        "<button style='margin-top:5%;'type='button' class='btn btn-block btn-success btn-lg' onclick='addOpera()'>提交</button></div>"
    });
}

function addOpera() {
    var opName = $("#opName").val();
    var opActor = $("#opActor").val();
    var opDepartment = $("#opDepartment").val();
    var opSort = $("#opSort").val();
    $.ajax({
        type: "post",
        url : getContextPath() + "/opera/addOpera",
        dataType:'json',
        data: {
            "opName":opName,
            "opActor":opActor,
            "opSort":opSort,
            "opDepartment":opDepartment
        },
        success: function (data) {
            var addSuccess = data.data;
            if (addSuccess) {
                layer.msg('添加成功', {
                    time: 500, //20s后自动关闭
                    // btn: ['明白了', '知道了']
                });
                refreshOperaPage();
            } else {
                layer.msg('添加失败', {
                    time: 500, //20s后自动关闭
                    // btn: ['明白了', '知道了']
                });
            }
        }
    });
    layer.closeAll();
}