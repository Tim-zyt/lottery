jQuery(function ($) {
    $(document).ready(function () {
        $("a.face").smohanfacebox({
            Event: "click",	//触发事件
            divid: "Smohan_FaceBox", //外层DIV ID
            textid: "Smohan_text" //文本框 ID
        });
        var restFlowers = getCookie("flower");
        $("strong.flower").html("* " + (restFlowers));
        $("img.flower").click(function () {
            var restFlowers = getCookie("flower");
            if (restFlowers > 0) {

                sendDanmuKu(1, null, function (data) {
                    if (data.data) {
                        setCookie("flower", restFlowers - 1, 86400);
                        $("strong.flower").html("* " + (restFlowers - 1));
                        alert("打赏一束鲜花成功！");
                    } else {
                        alert(data.message);
                    }
                });
            } else {
                alert("剩余礼物不足！");
            }
        });
        var restCars = getCookie("car");
        $("strong.car").html("* " + (restCars));
        $("img.car").click(function () {
            var restCars = getCookie("car");
            if (restCars > 0) {
                sendDanmuKu(2, null, function (data) {
                    if (data.data) {
                        setCookie("car", restCars - 1, 86400);
                        $("strong.car").html("* " + (restCars - 1));
                        alert("打赏一辆跑车成功！");
                    } else {
                        alert(data.message);
                    }
                });
            } else {
                alert("剩余礼物不足！");
            }
        });
        var restRockets = getCookie("rocket");
        $("strong.rocket").html("* " + (restRockets));
        $("img.rocket").click(function () {
            var restRockets = getCookie("rocket");
            if (restRockets > 0) {
                sendDanmuKu(3, null, function (data) {
                    if (data.data) {
                        setCookie("rocket", restRockets - 1, 86400);
                        $("strong.rocket").html("* " + (restRockets - 1));
                        alert("打赏一发火箭成功！");
                    } else {
                        alert(data.message);
                    }
                });
            } else {
                alert("剩余礼物不足！");
            }
        });
        $("button.send").click(function () {
            var content = $("textarea#Smohan_text").val();
            if (!isNull(content)) {
                sendDanmuKu(0, content, function (data) {
                    $("textarea#Smohan_text").val("");
                    alert("弹幕发送成功！");
                });
            }
        });
    });
});
function sendDanmuKu(type, content, fn) {
    $.ajax({
        type: "post",
        url: getContextPath() + "/danmuku/addDanmuKu",
        contentType: 'application/json',
        data: JSON.stringify({
            type: type,
            content: content
        }),
        success: fn
    });
}
