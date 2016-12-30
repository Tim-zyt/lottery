
    $(document).ready(function () {
        initAllCP();
        refreshPage();
    });

    function refreshPage()
    {
        initAllCP();
        setTimeout(function(){refreshPage();},5000);
    }

    function initAllCP() {  
        $.ajax({
            type: "POST",
            url: getContextPath() + "/cpGift/getAllCP",
            success: function (data) {
                setWXImgUrl(data);
            }
        });
    }

    function unConfirmCPImg(cpId) {
        $.ajax({
            type: "post",
            url: getContextPath() + "/cpGift/unConfirmCPImg",
            data:{
                "cpId":cpId
            },
            success: function (data) {
                $("#unconfirmBtn" + cpId).css("display","none");
                $("#confirmBtn" + cpId).css("display","");
            }
        });
    }

    function confirmCPImg(cpId) {
        $.ajax({
            type: "post",
            url: getContextPath() + "/cpGift/confirmCPImg",
            data:{
                "cpId":cpId
            },
            success: function (data) {
                $("#unconfirmBtn" + cpId).css("display","");
                $("#confirmBtn" + cpId).css("display","none");
            }
        });
    }

    //
    // function setWXImgUrl(cpdata) {
    //     $.ajax({
    //         type: "get",
    //         url: getContextPath() + "/weixin/accessToken",
    //         success: function (data) {
    //             var accessToken = data;
    //             var cp = cpdata.data;
    //             var cpHtml = "";
    //             var iLen = cp.length;
    //             for(var i = iLen - 1 ; i >=0  ; i--){
    //                 var imgSrc = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + cp[i].cpImg;
    //                 cpHtml += "<li style='width: 20%;height:45%;'>" +
    //                 "<img style='border-radius: 0;max-width: 90%;height: 200px;width: 200px;' src='" +imgSrc +"' alt=''>" +
    //                 "<a href='#' style='text-align: center;font-size: 20px;font-family: 微软雅黑, Microsoft YaHei;color: #0099FF;' class='users-list-name'>" + cp[i].user1SfName + cp[i].user1SfNum + "</a>" +
    //                 "<a href='#' style='text-align: center;font-size: 20px;font-family: 微软雅黑, Microsoft YaHei;color: #0099FF;' class='users-list-name'>" + cp[i].user2SfName + cp[i].user2SfNum + "</a>"
    //                 if(cp[i].awCount == 100){
    //                     cpHtml += "<div id='unconfirmBtn" + cp[i].id + "' style='display: none' class='btn btn-primary' onclick='unConfirmCPImg(" +  cp[i].id + ")'>已通过</div>"+
    //                     "<div id='confirmBtn" + cp[i].id + "'  class='btn btn-default' onclick='confirmCPImg(" +  cp[i].id + ")'>未通过</div></li>"
    //                 }else {
    //                     cpHtml += "<div id='unconfirmBtn" + cp[i].id + "' class='btn btn-primary' onclick='unConfirmCPImg(" +  cp[i].id + ")'>已通过</div>"+
    //                         "<div id='confirmBtn" + cp[i].id + "' style='display: none' class='btn btn-default' onclick='confirmCPImg(" +  cp[i].id + ")'>未通过</div></li>"
    //                 }
    //
    //             }
    //             $("#cpList").html(cpHtml);
    //         }
    //     });
    // }


    function setWXImgUrl(cpdata) {

                var accessToken = "123";
                var cp = cpdata.data;
                var cpHtml = "";
                var iLen = cp.length;
                for(var i = iLen - 1 ; i >=0  ; i--){
                    var imgSrc = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + cp[i].cpImg;
                    cpHtml += "<li style='width: 20%;height:45%;'>" +
                        "<img style='border-radius: 0;max-width: 90%;height: 200px;width: 200px;' src='" +imgSrc +"' alt=''>" +
                        "<a href='#' style='text-align: center;font-size: 20px;font-family: 微软雅黑, Microsoft YaHei;color: #0099FF;' class='users-list-name'>" + cp[i].user1SfName + cp[i].user1SfNum + "</a>" +
                        "<a href='#' style='text-align: center;font-size: 20px;font-family: 微软雅黑, Microsoft YaHei;color: #0099FF;' class='users-list-name'>" + cp[i].user2SfName + cp[i].user2SfNum + "</a>"
                    if(cp[i].awCount == 100){
                        cpHtml += "<div id='unconfirmBtn" + cp[i].id + "' style='display: none' class='btn btn-primary' onclick='unConfirmCPImg(" +  cp[i].id + ")'>已通过</div>"+
                            "<div id='confirmBtn" + cp[i].id + "'  class='btn btn-default' onclick='confirmCPImg(" +  cp[i].id + ")'>未通过</div></li>"
                    }else {
                        cpHtml += "<div id='unconfirmBtn" + cp[i].id + "' class='btn btn-primary' onclick='unConfirmCPImg(" +  cp[i].id + ")'>已通过</div>"+
                            "<div id='confirmBtn" + cp[i].id + "' style='display: none' class='btn btn-default' onclick='confirmCPImg(" +  cp[i].id + ")'>未通过</div></li>"
                    }

                }
                $("#cpList").html(cpHtml);


    }






