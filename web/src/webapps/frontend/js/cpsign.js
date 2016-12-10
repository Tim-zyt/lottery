var wxJsTime = (new Date().getTime() + "").substr(0, 10);
var wxJsNonceStr = UUID.prototype.createUUID();

$.ajax({
    type: "get",
    url: getContextPath() + "/weixin/jsSignature?noncestr=" + wxJsNonceStr + "&timestamp=" + wxJsTime + "&htmlPath=/frontend/cpSign.html",
    success: function (data) {
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: appId, // 必填，公众号的唯一标识
            timestamp: wxJsTime, // 必填，生成签名的时间戳
            nonceStr: wxJsNonceStr, // 必填，生成签名的随机串
            signature: data,// 必填，签名
            jsApiList: ["chooseImage", "previewImage", "uploadImage", "downloadImage"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
    }
});

function cpSubmit() {
    var sfnum1 = $("#sfnum1").val();
    var sfnum2 = $("#sfnum2").val();
    var imgSrc = $("#imgSrc").val();
    // if(imgSrc==null||imgSrc==undefined||imgSrc==""){
    //     alert("请上传图片再签到");
    // }else{
        $.ajax({
            type: "post",
            url : getContextPath() + "/cp/cpsign",
            dataType:'json',
            data: {
                "sfnum1":sfnum1,
                "sfnum2":sfnum2,
                "imgSrc":imgSrc
            },
            success: function(data){
                var code = data.data;
                if(code == "true"){
                    var p = "<p style='color:green;' >" + data.message + "</p>" ;
                    $("#msg").append(p);
                }else {
                    var p = "<p style='color:red;' >" + data.message + "</p>" ;
                    $("#msg").append(p);
                }
            }
        });
    // }
}

jQuery(function ($) {
    $(document).ready(function () {
        $(".cpSignup").click(function () {
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    wx.uploadImage({
                        localId: localIds[0], // 需要上传的图片的本地ID，由chooseImage接口获得
                        isShowProgressTips: 1, // 默认为1，显示进度提示
                        success: function (res2) {
                            var serverId = res2.serverId; // 返回图片的服务器端ID
                            showImg(serverId);
                            // $("p.login-box-msg").html(serverId);
                        }
                    });
                }
            });
        });
    });

    function showImg(serverId) {
            $.ajax({
                type: "get",
                url: getContextPath() + "/weixin/accessToken",
                success: function (data) {
                        var accessToken = data;
                        var imgSrc = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + serverId;
                        $("#cpDiv").css("display", "block");
                        $("#cpImg").attr("src", imgSrc);
                        $("#imgSrc").val(imgSrc);
                }
            });
    }

});
