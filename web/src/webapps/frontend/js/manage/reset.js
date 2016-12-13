/**
 * Created by 01170626 on 2016/12/7.
 */
function resetUsers() {
    $.ajax({
        type: "post",
        url : getContextPath() + "/reset/resetData",
        dataType:'json',
        data: {
        },
        success: function(data){
            var flag = data.data;
            if(flag){
                // layer.msg('删除成功', {
                //     time: 500, //20s后自动关闭
                //     // btn: ['明白了', '知道了']
                // });
                alert("数据库初始化成功");
            }else{
                alert("数据库初始化失败失败");
            }
        }
    });
}