jQuery(function ($) {
    $(document).ready(function () {
        initWindowSize();

        $.ajax({
            type: "get",
            url: getContextPath() + "/weixin/accessToken",
            success: function (data) {
                accessToken = data;
                pageStateAwardCp = 0;
                $(window).resize(function() {
                    initWindowSize();
                });
                cpGiftTime();
            }
        });


    });


    var pageStateAwardCp = 0;

    function pageController(){
        $.ajax({
            type: "post",
            url : getContextPath() + "/config/getCurStateCp",
            dataType:'json',
            data: {
            },
            success: function(data){
                //获取config表里的CurStateAward的值
                var curStateAwarCp = data.data
                if(curStateAwarCp == 0){
                    //把页面图片和人头抹掉
                    $("#cpGiftImg").remove();
                    $("#luckDiv").html("");
                    $("#loading").css("display","block");
                    pageStateAwardCp = curStateAwarCp;
                }else if(pageStateAwardCp != 1 && curStateAwarCp == 1){
                    $("#loading").css("display","block");
                    //头像闪烁
                    $("#cpGiftImg").remove();
                    // $("#loading").css("display","none");
                    startTwinkle();
                    pageStateAwardCp = curStateAwarCp;
                }else if(pageStateAwardCp == 1 && curStateAwarCp == 1){
                    $("#loading").css("display","none");
                } else if(pageStateAwardCp != 2 && curStateAwarCp == 2){
                    $("#loading").css("display","block");
                    pageStateAwardCp = curStateAwarCp;
                    end();
                    //读Controller里的获奖人缓存
                    $.ajax({
                        type: "post",
                        url : getContextPath() + "/cpGift/getCacheLuckCp",
                        dataType:'json',
                        data: {
                        },
                        success: function(data2){
                            $("#loading").css("display","none");
                            var cp = data2.data;
                            timeout = false;
                            var imgSrc = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + cp.cpImg;
                            var luckDivHtml  = "<div class='animated bounceInUp'><img src='" + imgSrc + "' alt='CP合影'  style='border-radius: 50%;' width='480px' height='550px'><a href='#' style='text-align: center;font-size: 24px;font-family: 微软雅黑, Microsoft YaHei;color: #f39c12;' class='users-list-name'>" + cp.user1SfName + cp.user1SfNum + "</a><a href='#' style='text-align: center;font-size: 24px;font-family: 微软雅黑, Microsoft YaHei;color: #f39c12;' class='users-list-name'>" + cp.user2SfName + cp.user2SfNum + "</a></div>";
                            $("#winningList").remove();
                            $("#cpGiftBox").prepend("<img id='cpGiftImg' src='http://hashzhang.com/data/frontend/image/winningList.png' id='winningList' class='animated bounceInUp'>");
                            $("#luckDiv").html(luckDivHtml);
                            pageStateAwardCp = curStateAwarCp;
                        }
                    });
                }else if (pageStateAwardCp == 2 && curStateAwarCp == 2){
                    $("#loading").css("display","none");
                }else{
                    $("#loading").css("display","none");
                }

                setTimeout(cpGiftTime,1000);
            },error:function () {
                setTimeout(cpGiftTime,1000);
            }
        });
    }


    //页面轮询
    function cpGiftTime()
    {
        pageController();
    }


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

    var accessToken = "";

    function setWXImgUrl(cpdata) {
        $.ajax({
            type: "get",
            url: getContextPath() + "/weixin/accessToken",
            success: function (data) {
                $("#loading").css("display","none");
                accessToken = data;
                var cp = cpdata.data;
                var cpHtml = "";
                var iLen = cp.length;
                for(var i = iLen - 1 ; i >=0  ; i--){
                    var imgSrc = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + cp[i].cpImg;
                    cpHtml += "<div style='margin-top: 10%' id='" + i +"' style='display: none' class='luckCp'><img src='" + imgSrc + "' alt='CP合影' style='border-radius: 50%;' width='480px' height='550px'><a href='#' style='text-align: center;font-size: 24px;font-family: 微软雅黑, Microsoft YaHei;color: #f39c12;' class='users-list-name'>" + cp[i].user1SfName + cp[i].user1SfNum + "</a><a href='#' style='text-align: center;font-size: 24px;font-family: 微软雅黑, Microsoft YaHei;color: #f39c12;' class='users-list-name'>" + cp[i].user2SfName + cp[i].user2SfNum + "</a></div>";
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
        $("#luckDiv").css({"height": height + "px"});
        $("#loading").css({"margin-left":width/2-200+"px"});
    }

});