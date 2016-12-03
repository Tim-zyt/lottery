jQuery(function ($) {
    $(document).ready(function () {
        getSignedUser();
    });

    function getSignedUser(){
        $.ajax({
            type: "post",
            url : getContextPath() + "/user/getSignedUser",
            dataType:'json',
            data: {
            },
            success: function(data){
                var signedusers = data.data;
                var userHtml = "";
                var iLen = signedusers.length;
                for(var i = 0 ; i < iLen ; i++){
                    userHtml += "<li style='width: 10%;'><img src='"+ signedusers[i].wxHeadimgurl +"'alt='User Image'><a href='#' style='font-size: 20px;font-family: 微软雅黑, Microsoft YaHei;color: #0099FF;' class='users-list-name'>" + signedusers[i].sfName + "</a></li>";
                }
                $("#users").html(userHtml);
            }
        });
    }

});