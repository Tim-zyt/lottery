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
            var operaHtml = "";
            var iLen = operas.length;
            for(var i = iLen - 1 ; i >=0  ; i--){
                awardHtml+="<tr><td><span style='cursor:pointer;' class='label label-success'>"+"选中"+"</span></td><td>"+operas[i].opName+"</td><td>"+operas[i].opActor+"</td><td>"+
                    operas[i].opDepartment+"</td><td><span style='cursor:pointer;' class='label label-info' onclick='confirmUpdateOpera("+i+")'>"+"编辑"+"</span></td><td>" +
                    "<span class='label label-danger' style='cursor:pointer;' onclick='comfirmDeleteOpera("+operas[i].id+")'>"+"删除"+"</span></td></tr>";
            }
            $("#operas").html(operaHtml);
        }
    });
}

function comfirmDeleteOpera(operaId) {
    layer.confirm('确定要删除该节目吗？', {
        btn: ['删除','取消'] //按钮
    }, function(){
        deleteOpera(awardId);
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
        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'><p>请输入节目部门</p><input id='opDepartment' class='form-control' type='number' name='opDepartment' value='"+operas[i].opDepartment+"'/></div>"+
        "<button style='margin-top:5%;' type='button' class='btn btn-block btn-success btn-lg' onclick='updateOpera("+operas[i].id+")'>提交</button></div>"
    });
}

function updateOpera(operaId){
    var opName = $("#opName").val();
    var opActor = $("#opActor").val();
    var opDepartment = $("#opDepartment").val();
    $.ajax({
        type: "post",
        url : getContextPath() + "/opera/updateOpera",
        dataType:'json',
        data: {
            "operaId":operaId,
            "opName":opName,
            "opActor":opActor,
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
        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'><p>请输入节目部门</p><input id='opDepartment' class='form-control' type='number' name='opDepartment' /></div>"+
        "<button style='margin-top:5%;'type='button' class='btn btn-block btn-success btn-lg' onclick='addOpera()'>提交</button></div>"
    });
}

function addOpera() {
    var opName = $("#opName").val();
    var opActor = $("#opActor").val();
    var opDepartment = $("#opDepartment").val();
    $.ajax({
        type: "post",
        url : getContextPath() + "/opera/updateOpera",
        dataType:'json',
        data: {
            "opName":opName,
            "opActor":opActor,
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