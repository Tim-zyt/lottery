/**
 * Created by 01170626 on 2016/12/5.
 */
jQuery(function ($) {
    $(document).ready(function () {
        $.ajax({
            type: "post",
            url : getContextPath() + "/award/getAllAwKinds",
            dataType:'json',
            data: {
            },
            success: function(data){
                var awKinds = data.data;
                var awardHtml = "";
                var iLen = awKinds.length;
                for(var i = iLen - 1 ; i >=0  ; i--){
                    // userHtml += "<li style='width: 10%;'><img src='"+ signedusers[i].wxHeadimgurl +"'alt='User Image'><a href='#' style='font-size: 20px;font-family: 微软雅黑, Microsoft YaHei;color: #0099FF;' class='users-list-name'>" + signedusers[i].sfName + "</a></li>";
                    // awardHtml+=awKinds[i];
                    awardHtml+="<li style='width: 100%;'>"+awKinds[i]+ "</li>";
                }
                $("#awKinds").html(awardHtml);
            }
        });
    });
});