jQuery(function ($) {
    $(document).ready(function () {

        $(window).resize(function() {
            initWindowSize();
        });

        var ws = new WebSocket(cpGiftChannelAddress);
        ws.onopen = function(){
        };
        ws.onmessage = function(message){
            var cpGiftMessage = JSON.parse(message.data);
            if(cpGiftMessage.flag == 0){
                $("#cpGiftImg").remove();
                startTwinkle();
            }else if(cpGiftMessage.flag == 1){
                end();
                if(cpGiftMessage.luckCP.cpImg != null && cpGiftMessage.luckCP.cpImg != undefined && cpGiftMessage.luckCP.cpImg != ""){
                    showLuckCP(cpGiftMessage.luckCP);
                }
            }
        };
        function postToServer(){
            ws.send(document.getElementById("msg").value);
            document.getElementById("msg").value = "";
        }
        function closeConnect(){
            ws.close();
        }

    });

    //得到当前时刻临时的获奖人，一闪而过
    function getLuckCP(){
        var luckCps = $(".luckCp");
        var iLen = luckCps.length;
        for(var i = 0 ; i < iLen ; i++ ){
            luckCps[i].style.display = "none";
        }
        var show = getNRandom(1,0,iLen);
        var jLen = show.length;
        for(var j = 0 ; j < jLen ; j++ ){
            if(show[j] != undefined){
                luckCps[show[j]].style.display = "block";
            }
        }
    }

    //初始化,得到未获奖人
    function startTwinkle() {
        $.ajax({
            type: "post",
            url : getContextPath() + "/cpGift/init",
            dataType:'json',
            data: {
            },
            success: function(data){
                setWXImgUrl(data);
            }
        });
    }

    //展示获奖人的头像
    function showLuckCP(cp) {
        $.ajax({
            type: "get",
            url: getContextPath() + "/weixin/accessToken",
            success: function (data) {
                var accessToken = data;
                var imgSrc = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + cp.cpImg;
                var luckDivHtml  = "<div class='animated bounceInUp'><img src='" + imgSrc + "' alt='CP合影'  width='480px' height='550px'><a href='#' style='text-align: center;font-size: 24px;font-family: 微软雅黑, Microsoft YaHei;color: #fff;' class='users-list-name'>" + cp.user1SfName + cp.user1SfNum + "</a><a href='#' style='text-align: center;font-size: 24px;font-family: 微软雅黑, Microsoft YaHei;color: #fff;' class='users-list-name'>" + cp.user2SfName + cp.user2SfNum + "</a></div>";
                $("#cpGiftBox").prepend("<img id='cpGiftImg' src='image/winningList.png' class='animated bounceInUp'>");
                $("#luckDiv").html(luckDivHtml);
            }
        });


    }

    var timeout = false; //启动及关闭按钮

    //头像闪烁
    function twinkle()
    {
        if(!timeout){
            return;
        }
        getLuckCP();
        setTimeout(function(){twinkle();},100);
    }

    //开始抽奖
    function start() {
        timeout = true;
        twinkle();
    }

    //结束抽奖
    function end() {
        timeout = false;
    }

    //生成n个从min,max之间的随机数
    //思路：生成第i个[min,max]区间的随机数，并与之前i-1个数比较，如有重复，令i=i-1;重复生成第i个随机数。
    function getNRandom(n,min,max){
        var arr=[];
        for(var i=0;i<n;i++){
            arr[i]=parseInt(Math.random()*(max-min)+min);
            for(var j=0;j<i;j++){
                if(arr[i]==arr[j]){
                    i=i-1;
                    break;
                }
            }
        }
        return arr;
    }


    function setWXImgUrl(cpdata) {
        $.ajax({
            type: "get",
            url: getContextPath() + "/weixin/accessToken",
            success: function (data) {
                var accessToken = data;
                var cp = cpdata.data;
                var cpHtml = "";
                var iLen = cp.length;
                for(var i = iLen - 1 ; i >=0  ; i--){
                    var imgSrc = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + cp[i].cpImg;
                    cpHtml += "<div style='margin-top: 10%' id='" + i +"' style='display: none' class='luckCp'><img src='" + imgSrc + "' alt='CP合影'  width='480px' height='550px'><a href='#' style='text-align: center;font-size: 24px;font-family: 微软雅黑, Microsoft YaHei;color: #fff;' class='users-list-name'>" + cp[i].user1SfName + cp[i].user1SfNum + "</a><a href='#' style='text-align: center;font-size: 24px;font-family: 微软雅黑, Microsoft YaHei;color: #fff;' class='users-list-name'>" + cp[i].user2SfName + cp[i].user2SfNum + "</a></div>";
                }
                $("#luckDiv").html(cpHtml);

                start();
            }
        });
    }

    function initWindowSize() {
        var width = window.innerWidth;
        var height = window.innerHeight;
        $("#large-header").css({"width":width + "px","height": height + "px"});
        $("#cpGiftBox").css({"width":width + "px","height": height + "px"});
    }


});


