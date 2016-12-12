/**
 * Created by 01170626 on 2016/12/10.
 */
$(document).ready(function () {
    time();
    refreshShakePage();
});

function refreshShakePage(){
    $.ajax({
        type: "post",
        url : getContextPath() + "/shake/getTopN",
        dataType:'json',
        data: {
            "topSize":6
        },
        success: function(data){
            var shakeWinners = data.data;
            var shakeHtml = "";
            for(var i = 5 ; i >=0  ; i--){
                var percentage = Math.round(shakeWinners[i].shakeCount / 24 * 10000) / 100.00 + "%";
                shakeHtml+="<li class='item' style='background-color:rgba(255,255,255,0);'>" +
                    "<div class='product-img'><img style='border-radius: 50%;max-width: 100%;height: 100px;width: 100px;' class='animated shake' src='"+shakeWinners[i].headImgUrl+"'/></div>" +
                    "<div class='product-info' style='margin-left:12%'>" +
                    "<a class='product-title' style='font-size:22px;'>"+shakeWinners[i].userName+"<span class='label label-warning pull-right'>"+shakeWinners[i].shakeCount+"</span></a>" +
                    "<span class='product-description' style='margin-top:2%'><div class='progress sm'><div class='progress-bar progress-bar-aqua' style='width:"+percentage+"'></div></div></span></div></li>";
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


