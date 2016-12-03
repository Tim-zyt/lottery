
function getLuckman(){
    var luckmans = $(".luckman");
    var iLen = luckmans.length;
    for(var i = 0 ; i < iLen ; i++ ){
        luckmans[i].style.display = "none";
    }
    var show = getNRandom(5,0,iLen);
    var jLen = show.length;
    for(var j = 0 ; j < jLen ; j++ ){
        if(show[j] != undefined){
            luckmans[show[j]].style.display = "block";
        }
    }
}

var timeout = false; //启动及关闭按钮
function time()
{
    if(!timeout){
        return;
    }
    getLuckman();
    setTimeout(time,100);
}

//开始抽奖
function start() {
    timeout = true;
    time();
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


