jQuery(function ($) {
    $(document).ready(function () {
        time();

        var ws = new WebSocket(signWallChannelAddress);
        ws.onopen = function(){
        };
        ws.onmessage = function(message){
            // var user = message.data;
            // var signUserHtml = "<li style='width: 10%;'><img src='"+ user.wxHeadimgurl +"'alt='User Image'><a href='#' style='font-size: 20px;font-family: 微软雅黑, Microsoft YaHei;color: #0099FF;' class='users-list-name'>" + user.sfName + "</a></li>";
            // $("#users").prepend(signUserHtml);
        };
        function postToServer(){
            ws.send(document.getElementById("msg").value);
            document.getElementById("msg").value = "";
        }
        function closeConnect(){
            ws.close();
        }
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
                for(var i = iLen - 1 ; i >=0  ; i--){
                    userHtml += "<li style='width: 10%;'><img src='"+ signedusers[i].wxHeadimgurl +"'alt='User Image'><a href='#' style='font-size: 20px;font-family: 微软雅黑, Microsoft YaHei;color: #0099FF;' class='users-list-name'>" + signedusers[i].sfName + "</a></li>";
                }
                $("#users").html(userHtml);
            }
        });
    }

    function time()
    {
        getSignedUser();
        setTimeout(time,1000);
    }

});