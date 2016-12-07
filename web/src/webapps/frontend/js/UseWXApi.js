var wxJsTime = (new Date().getTime()+"").substr(0,10);
var wxJsNonceStr = UUID.prototype.createUUID();

$.ajax({
    type: "get",
    url : getContextPath() + "/weixin/jsSignature?noncestr="+wxJsNonceStr+"&timestamp="+wxJsTime+"&htmlPath=/frontend/main.html",
    success: function(data){
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: appId, // 必填，公众号的唯一标识
            timestamp: wxJsTime, // 必填，生成签名的时间戳
            nonceStr: wxJsNonceStr, // 必填，生成签名的随机串
            signature: data,// 必填，签名
            jsApiList: ["chooseImage","previewImage","uploadImage","downloadImage"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
    }
});

wx.ready(function(){
});
var last_update = new Date().getTime();
var last_x =0;
var last_y=0;
var last_z= 0;
jQuery(function ($) {
    $(document).ready(function () {
        $(".cpSignup").click(function () {
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    alert(localIds);
                    wx.uploadImage({
                        localId: localIds[0], // 需要上传的图片的本地ID，由chooseImage接口获得
                        isShowProgressTips: 1, // 默认为1，显示进度提示
                        success: function (res2) {
                            var serverId = res2.serverId; // 返回图片的服务器端ID
                            $("p.login-box-msg").html(serverId);
                        }
                    });
                }
            });
        });
        if (window.DeviceMotionEvent) {
            window.addEventListener('devicemotion', deviceMotionHandler, false);
        } else {
            alert('你的手机太差了，不能参与摇一摇抽奖，扔掉买个新的吧。--顺丰科技忠劝');
        }
    });
    var shakeTimes = 0;
    function deviceMotionHandler(eventData) {
        var acceleration = eventData.accelerationIncludingGravity;
        var curTime = new Date().getTime();

        if ((curTime - last_update) > 100) {
            var diffTime = curTime - last_update;
            last_update = curTime;
            var x = acceleration.x;
            var y = acceleration.y;
            var z = acceleration.z;
            var speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
            var status = document.getElementById("status");

            if (speed > 1000) {
                $("p.login-box-msg").html(shakeTimes++);
            }
            last_x = x;
            last_y = y;
            last_z = z;
        }
    }
});



