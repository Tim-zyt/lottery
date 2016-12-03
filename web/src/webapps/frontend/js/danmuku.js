jQuery(function ($) {
    $(document).ready(function () {
        var ws = new WebSocket(danmukuChannelAddress);
        ws.onopen = function(){
        };
        var fontFamily = ['STHeiti Light', 'STHeiti', 'STKaiti','STSong','STFangsong','LiHei Pro Medium','LiSong Pro Light','BiauKai','Apple LiGothic Medium','Apple LiSung Light','LiSu','YouYuan','STXinwei','Microsoft YaHei'];
        var animation = ['bounceInRight'];
        var fontColor = ['#009933','#330099','#330000','#6600CC','#660000','#990099','#996600'];
        var count = 0;

        ws.onmessage = function(message){
            var myDate = new Date();
            var danmukuWall = $(".direct-chat-messages");
            count+=1;
            danmukuWall.append($("<div id='"+ count +"' class='direct-chat-msg animated "+animation[count%animation.length]+"'>" +
                "<div class='direct-chat-info clearfix'><span class='direct-chat-name pull-left'>862911</span><span class='direct-chat-timestamp pull-right'>"+myDate.Format("hh:mm:ss")+"</span></div>" +
                "<img class='direct-chat-img' src='lib/AdminLTE/dist/img/user1-128x128.jpg' alt='message user image'>" +
                " <div style='position: relative;margin: 5px 0 0 50px;padding: 5px 10px 5px 10px;font-size: 22px;font-family: "+fontFamily[count%fontFamily.length]+";font-weight: "+(count%10+5)*100+";color:"+fontColor[count%fontColor.length]+"'>"+message.data+"</div>"
                +"</div>"));
            if(count >12){
                var currentTop = $("#"+(count-12));
                currentTop.remove();
            }
            danmukuWall[0].scrollTop
                =danmukuWall[0].scrollHeight;
            var currentPresent = $("<div style='position:absolute;top:"+ (count%5+1)*100 +"px;padding-left:30%;padding-right:30%' class='col-lg-12'><p><img src='image/present/car.gif' style='height: 120px' class='img-responsive'></p></div>");
            danmukuWall.after(currentPresent);
            currentPresent.addClass("animated bounceInRight");
            setTimeout(function () {
                currentPresent.removeClass("animated bounceInRight");
                currentPresent.addClass("animated bounceOutLeft");
                setTimeout(function () {
                    currentPresent.remove();
                },2000)
            },2000);
            var currentPresent2 = $("<div style='position:absolute;top:25%;padding-left:"+(count%7+1)*10+"%;class='col-lg-12'><p><img src='image/present/rocket.png' style='height:500px' class='img-responsive'></p></div>");
            danmukuWall.after(currentPresent2);
            currentPresent2.addClass("animated bounceInUp");
            setTimeout(function () {
                currentPresent2.removeClass("animated bounceInUp");
                currentPresent2.addClass("animated bounceOutUp");
                setTimeout(function () {
                    currentPresent2.remove();
                },2000)
            },2000);
            var currentPresent3 = $("<div style='position:absolute;top:"+ (count%5+1)*100 +"px;padding-left:"+(count%7+1)*10+"%' class='col-lg-12'><p><img src='image/present/flower.gif' style='height: 60px' class='img-responsive'></p><strong>862911送出一束花</strong></div>");
            danmukuWall.after(currentPresent3);
            currentPresent3.addClass("animated bounceIn");
            setTimeout(function () {
                currentPresent3.removeClass("animated bounceIn");
                currentPresent3.addClass("animated bounceOut");
                setTimeout(function () {
                    currentPresent3.remove();
                },2000)
            },2000);
        };
        function postToServer(){
            ws.send(document.getElementById("msg").value);
            document.getElementById("msg").value = "";
        }
        function closeConnect(){
            ws.close();
        }
    });
});