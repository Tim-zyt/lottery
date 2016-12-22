/**
 * Created by 01170626 on 2016/12/10.
 */
$(document).ready(function () {

    pageShakeState = 0;

    $(window).resize(function() {
        initWindowSize();
    });

    time();
});

var pageShakeState = 0;

function time()
{
    shakeController();
    setTimeout(time,1000);
}

function initWindowSize() {
    var width = window.innerWidth;
    var height = window.innerHeight;
    $("#large-header").css({"width":width + "px","height": height + "px"});
    $("#shakeDiv").css({"width":width + "px","height": height + "px"});
}

function shakeController(){
    $.ajax({
        type: "post",
        url : getContextPath() + "/config/getCurStateShake",
        dataType:'json',
        data: {
        },
        success: function(data){
            //获取config表里的CurStateAward的值
            var curShakeState = data.data
            if(curShakeState == 0){
                $("#shakeRace").css("display","none");
                $("#shakeDiv").css("display","none");
            }else if(pageShakeState != 1 && curShakeState == 1){
                //展示摇一摇的人
                getCurrentStatus();
                pageShakeState = curShakeState;
                $("#shakeRace").css("display","block");
                $("#shakeDiv").css("display","none");
            }else if(pageShakeState != 2 && curShakeState == 2){
                //从controller的缓存读到获奖者
                getShakeWinner();
                pageShakeState = curShakeState;
            }else pageShakeState = curShakeState;
        }
    });
}

function getShakeWinner(){
    $.ajax({
        type: "post",
        url : getContextPath() + "/shake/getShakeWinner",
        dataType:'json',
        data: {
        },
        success: function(data2){
            var shakeWinnerMessage = data2.data;
            var shakeWinnerHtml  = "<div class='animated bounceIn'><img src='" + shakeWinnerMessage.headImgUrl + "' alt='摇一摇获奖者'  width='480px' height='550px'><a href='#' style='text-align: center;font-size: 24px;font-family: 微软雅黑, Microsoft YaHei;color: #ffff00;' class='users-list-name'>" + shakeWinnerMessage.userNo + "</a><a href='#' style='text-align: center;font-size: 24px;font-family: 微软雅黑, Microsoft YaHei;color: #ffff00;' class='users-list-name'>" + shakeWinnerMessage.userName + "</a></div>";
            $("#radiation").prepend("<img src='image/winningList.png' class='animated bounceIn'>");
            //获奖界面显示出来
            $("#shakeDiv").css("display","block");
            // 隐藏原界面
            $("#shakeRace").css("display","none");
            $("#head").css("display","none");
            //拼出获奖者头像、工号和姓名
            $("#shakeWinnerDiv").html(shakeWinnerHtml);
        }
    });
}

function getCurrentStatus(){
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

