jQuery(function ($) {
    $(document).ready(function () {
        initLuckman();

        var ws = new WebSocket(giftChannelAddress);
        ws.onopen = function(){
        };
        ws.onmessage = function(message){
            var giftMessage = JSON.parse(message.data);
            if(giftMessage.flag == 0){
                start(giftMessage.luckmanCount);
            }else if(giftMessage.flag == 1){
                end();
                showLuckman(giftMessage.luckmans);
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
    function getLuckman(manCount){
        var luckmans = $(".luckman");
        var iLen = luckmans.length;
        for(var i = 0 ; i < iLen ; i++ ){
            luckmans[i].style.display = "none";
        }
        if(manCount > iLen || manCount < 0){
            manCount = 0; //输入控制
        }
        var show = getNRandom(manCount,0,iLen);
        var jLen = show.length;
        for(var j = 0 ; j < jLen ; j++ ){
            if(show[j] != undefined){
                luckmans[show[j]].style.display = "block";
            }
        }
    }

    //初始化,得到未获奖人
    function initLuckman() {
        $.ajax({
            type: "post",
            url : getContextPath() + "/user/getUnAwardUser",
            dataType:'json',
            data: {
            },
            success: function(data){
                var users = data.data;
                var usersHtml = "";
                var iLen = users.length;
                for(var i = iLen - 1 ; i >=0  ; i--){
                    usersHtml += "<li style='width: 10%;height:16%;display:none' class='luckman'><div style='text-align: center;'><img style='border-radius: 50%;max-width: 90%;height: auto;' src='"+ users[i].wxHeadimgurl +"'alt='User Image'><a href='#' style='text-align: center;font-size: 20px;font-family: 微软雅黑, Microsoft YaHei;color: #0099FF;' class='users-list-name'>" + users[i].sfName + "</a></div></li>";
                }
                $("#luckmanList").html(usersHtml);
            }
        });
    }

    //展示获奖人的头像
    function showLuckman(luckmans) {
        var users = luckmans;
        var usersHtml = "";
        var iLen = users.length;
        for(var i = iLen - 1 ; i >=0  ; i--){
            usersHtml += "<li style='width: 10%;height:16%;' class='luckman'><div style='text-align: center;'><img style='border-radius: 50%;max-width: 90%;height: auto;' src='"+ users[i].wxHeadimgurl +"'alt='User Image'><a href='#' style='text-align: center;font-size: 20px;font-family: 微软雅黑, Microsoft YaHei;color: #0099FF;' class='users-list-name'>" + users[i].sfName + "</a></div></li>";
        }
        $("#luckmanList").html(usersHtml);
    }

    var timeout = false; //启动及关闭按钮

    //头像闪烁
    function twinkle(manCount)
    {
        if(!timeout){
            return;
        }
        getLuckman(manCount);
        setTimeout(function(){twinkle(manCount);},100);
    }

    //开始抽奖
    function start(manCount) {
        initLuckman();
        timeout = true;
        twinkle(manCount);
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

});


