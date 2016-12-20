/**
 * Created by 01170626 on 2016/12/10.
 */
$(document).ready(function () {
    $(window).resize(function() {
        initWindowSize();
    });
    time();
    refreshShakePage();
    var ws = new WebSocket(shakeChannelAddress);
    ws.onopen = function(){
    };
    ws.onmessage = function(message){
        var shakeWinnerMessage = JSON.parse(message.data);
        // var shakeWinnerHtml  = "<div><img src='" + shakeWinnerMessage.headImgUrl + "' style='width:40%'><a href='#' style='text-align: center;font-size: 24px;font-family: 微软雅黑, Microsoft YaHei;color: #0099FF;' class='users-list-name'>" + shakeWinnerMessage.userNo + shakeWinnerMessage.userName + "</a></div>";
        var shakeWinnerHtml  = "<div class='animated bounceIn'><img src='" + shakeWinnerMessage.headImgUrl + "' alt='摇一摇获奖者'  width='480px' height='550px'><a href='#' style='text-align: center;font-size: 24px;font-family: 微软雅黑, Microsoft YaHei;color: #fff;' class='users-list-name'>" + shakeWinnerMessage.userNo + "</a><a href='#' style='text-align: center;font-size: 24px;font-family: 微软雅黑, Microsoft YaHei;color: #fff;' class='users-list-name'>" + shakeWinnerMessage.userName + "</a></div>";
        $("#radiation").prepend("<img src='image/winningList.png' class='animated bounceIn'>");
        //获奖界面显示出来
        $("#shakeDiv").css("display","block");
        // 隐藏原界面
        $("#shakeRace").css("display","none");
        $("#head").css("display","none");
        //拼出获奖者头像、工号和姓名
        $("#shakeWinnerDiv").html(shakeWinnerHtml);
    };
    function postToServer(){
        ws.send(document.getElementById("msg").value);
        document.getElementById("msg").value = "";
    }
    function closeConnect(){
        ws.close();
    }
});

function refreshShakePage(){
    $.ajax({
        type: "get",
        url : getContextPath() + "/shake/getTopN?topSize=6",
        success: function(data){
            var shakeWinners = data.data;
            var shakeHtml = "";
            var percentage = new Array(shakeWinners.length);
            for(var m = 0 ; m <shakeWinners.length-1  ; m++){
                if(shakeWinners[m].shakeCount == shakeWinners[m+1].shakeCount ){
                    shakeWinners[m+1].shakeCount--;
                }
                continue;
            }
            for(var i = 0 ; i <shakeWinners.length  ; i++){
                // var percentage = Math.round(shakeWinners[i].shakeCount / 24 * 10000) / 100.00 + "%";
                // shakeHtml+="<li class='item' style='background-color:rgba(255,255,255,0);'>" +
                //     "<div class='product-img'><img style='border-radius: 50%;max-width: 100%;height: 100px;width: 100px;' class='animated shake' src='"+shakeWinners[i].headImgUrl+"'/></div>" +
                //     "<div class='product-info' style='margin-left:12%'>" +
                //     "<a class='product-title' style='font-size:22px;'>"+shakeWinners[i].userName+"<span class='label label-warning pull-right'>"+shakeWinners[i].shakeCount+"</span></a>" +
                //     "<span class='product-description' style='margin-top:2%'><div class='progress sm'><div class='progress-bar progress-bar-aqua' style='width:"+percentage+"'></div></div></span></div></li>";
                // var percentage = (100-15*i)+"%";
                percentage[i] = (Math.round(shakeWinners[i].shakeCount / shakeWinners[0].shakeCount * 10000) / 100.00-5*i) + "%";
                shakeHtml+="<li class='item' style='background-color:rgba(255,255,255,0);'>" +
                    "<div class='product-img'><img style='border-radius: 50%;max-width: 100%;height: 100px;width: 100px;' class='animated shake' src='"+shakeWinners[i].headImgUrl+"'/></div>" +
                    "<div class='product-info' style='margin-left:12%'>" +
                    "<a class='product-title' style='font-size:22px;'>"+shakeWinners[i].userName+"<span class='label label-warning pull-right'>"+shakeWinners[i].shakeCount+"</span></a>" +
                    "<span class='product-description' style='margin-top:2%'><div class='progress sm'><div class='progress-bar progress-bar-yellow' style='width:"+percentage[i]+"'></div></div></span></div></li>";
            }
            $("#shake").html(shakeHtml);

        }
    });
}

function time()
{
    refreshShakePage();
    setTimeout(time,1000);
}

function initWindowSize() {
    var width = window.innerWidth;
    var height = window.innerHeight;
    $("#large-header").css({"width":width + "px","height": height + "px"});
    $("#shakeDiv").css({"width":width + "px","height": height + "px"});
}


