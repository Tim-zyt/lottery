jQuery(function ($) {
    $(document).ready(function () {
        var ws = new WebSocket(danmukuChannelAddress);
        ws.onopen = function () {
        };
        var fontFamily = ['STHeiti Light', 'STHeiti', 'STKaiti', 'STSong', 'STFangsong', 'LiHei Pro Medium', 'LiSong Pro Light', 'BiauKai', 'Apple LiGothic Medium', 'Apple LiSung Light', 'LiSu', 'YouYuan', 'STXinwei', 'Microsoft YaHei'];
        var animation = ['bounceInRight'];
        var fontColor = ['#009933', '#330099', '#330000', '#6600CC', '#660000', '#990099', '#996600'];
        var count = 0;
        var danmuCount = 0;

        var intervalMessageCount = 0;
        setInterval(function () {
            intervalMessageCount = 0;
        },10);

        ws.onmessage = function (message) {
            var myDate = new Date();
            var danmukuWall = $(".direct-chat-messages");
            var danmukuMessage = JSON.parse(message.data);
            count += 1;
            intervalMessageCount += 1;
            if(intervalMessageCount>1){
                return;
            }
            var present = [{},
                {
                    div: "<div style='position:absolute;top:" + (count % 5 + 1) * 100 + "px;padding-left:" + (count % 7 + 1) * 10 + "%' class='col-lg-12'><p><img src='image/present/flower.gif' style='height: 60px' class='img-responsive'></p></div>",
                    description: "送出了一束鲜花！",
                    animationIn: "animated bounceIn",
                    animationOut: "animated bounceOut"
                },
                {
                    div: "<div style='position:absolute;top:" + (count % 5 + 1) * 100 + "px;padding-left:30%;padding-right:30%' class='col-lg-12'><p><img src='image/present/plane.png' style='width: 120px' class='img-responsive'></p></div>",
                    description: "送出了一架飞机！！",
                    animationIn: "animated bounceInRight",
                    animationOut: "animated bounceOutLeft"
                },
                {
                    div: "<div style='position:absolute;top:25%;padding-left:" + (count % 7 + 1) * 10 + "%;'class='col-lg-12'><p><img src='image/present/rocket.png' style='height:250px' class='img-responsive'></p></div>",
                    description: "送出了一发火箭！！！",
                    animationIn: "animated bounceInUp",
                    animationOut: "animated bounceOutUp"
                }
            ];

            switch (danmukuMessage.type) {
                case 0:
                    danmuCount+=1;
                    for (i = 0; i < 60; i++) {
                        var reg=new RegExp('<emt>' + (i + 1) + '</emt>',"g");
                        danmukuMessage.content = danmukuMessage.content.replace(reg, '<img src="image/face/' + (i + 1) + '.gif">');
                    }
                    danmukuWall.append($("<div id='" + danmuCount + "' class='direct-chat-msg animated " + animation[count % animation.length] + "'style='opacity:1' >" +
                        "<div class='direct-chat-info clearfix'><span class='direct-chat-name pull-left'>" + danmukuMessage.sfUserNum + "&nbsp" + danmukuMessage.sfUserName + "</span><span class='direct-chat-timestamp pull-right'>" + myDate.Format("hh:mm:ss") + "</span></div>" +
                        "<img class='direct-chat-img' src='" + danmukuMessage.wxAvatar + "' alt='message user image'>" +
                        " <div style='position: relative;margin: 5px 0 0 50px;padding: 5px 10px 5px 10px;font-size: 22px;font-family: " + fontFamily[count % fontFamily.length] + ";font-weight: " + (count % 10 + 5) * 100 + ";color:" + fontColor[count % fontColor.length] + "'>" + danmukuMessage.content + "</div>"
                        + "</div>"));
                    break;
                default:
                    var currentPresent = $(present[danmukuMessage.type].div);
                    currentPresent.find("p").append($("<strong style='color: "+fontColor[count % fontColor.length]+"'>" + danmukuMessage.sfUserNum + "&nbsp" + danmukuMessage.sfUserName + "&nbsp" + present[danmukuMessage.type].description + "</strong>"));
                    danmukuWall.after(currentPresent);
                    currentPresent.addClass(present[danmukuMessage.type].animationIn);
                    setTimeout(function () {
                        currentPresent.removeClass(present[danmukuMessage.type].animationIn);
                        currentPresent.addClass(present[danmukuMessage.type].animationOut);
                        setTimeout(function () {
                            currentPresent.remove();
                        }, 2000)
                    }, 2000);
            }
            if (danmuCount > 12) {
                var currentTop = $("#" + (danmuCount - 12));
                currentTop.remove();
            }
            danmukuWall[0].scrollTop
                = danmukuWall[0].scrollHeight;
        };
        function postToServer() {
            ws.send(document.getElementById("msg").value);
            document.getElementById("msg").value = "";
        }

        function closeConnect() {
            ws.close();
        }
    });
});