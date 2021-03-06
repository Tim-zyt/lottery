function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires+";path=/";
}
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
}
function delCookie(name) {
    setCookie(name, "", -1);
}
function getContextPath() {
    var pathName = document.location.pathname;

    var curWwwPath = window.document.location.href;
    var pos = curWwwPath.indexOf(pathName);

    var localhostPath = curWwwPath.substring(0, pos);

    var length = pathName.split("/");

    var result = '';
    if (length.length > 3) {
        if (length[1] != 'index') {
            result = '/' + length[1];
        }
    }
    return localhostPath;
}
function getHtmlPath() {
    return getContextPath() + '/html'; 
}
//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}
function isNull(str) {
    if (str == null || str == undefined || str == '') { 
        return true;
    } else {
        var regu = "^[ ]+$";
        var re = new RegExp(regu);
        return re.test(str);
    }
}
$.fn.extend({
    arrayVal: function () {
        var self = $(this);
        var result = [];

        for (i = 0; i < self.length; i++) {
            result.push(self[i].value);
        }
        return result;
    }
});
function max(a) {
    var max = a[0];
    for (var i = 1; i < a.length; i++) {
        if (a[i] > max)
            max = a[i];
    }
    return max;
}

//---------------------------------------------------
// 判断闰年
//---------------------------------------------------
Date.prototype.isLeapYear = function()
{
    return (0==this.getYear()%4&&((this.getYear()%100!=0)||(this.getYear()%400==0)));
}

//---------------------------------------------------
// 日期格式化
// 格式 YYYY/yyyy/YY/yy 表示年份
// MM/M 月份
// W/w 星期
// dd/DD/d/D 日期
// hh/HH/h/H 时间
// mm/m 分钟
// ss/SS/s/S 秒
//---------------------------------------------------
Date.prototype.Format = function(formatStr)
{
    var str = formatStr;
    var Week = ['日','一','二','三','四','五','六'];

    str=str.replace(/yyyy|YYYY/,this.getFullYear());
    str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));

    str=str.replace(/MM/,this.getMonth()>9?this.getMonth().toString():'0' + this.getMonth());
    str=str.replace(/M/g,this.getMonth());

    str=str.replace(/w|W/g,Week[this.getDay()]);

    str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());
    str=str.replace(/d|D/g,this.getDate());

    str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());
    str=str.replace(/h|H/g,this.getHours());
    str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());
    str=str.replace(/m/g,this.getMinutes());

    str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());
    str=str.replace(/s|S/g,this.getSeconds());

    return str;
}

//+---------------------------------------------------
//| 求两个时间的天数差 日期格式为 YYYY-MM-dd
//+---------------------------------------------------
function daysBetween(DateOne,DateTwo)
{
    var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));
    var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);
    var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));

    var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));
    var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);
    var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));

    var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);
    return Math.abs(cha);
}

//+---------------------------------------------------
//| 日期计算
//+---------------------------------------------------
Date.prototype.DateAdd = function(strInterval, Number) {
    var dtTmp = this;
    switch (strInterval) {
        case 's' :return new Date(Date.parse(dtTmp) + (1000 * Number));
        case 'n' :return new Date(Date.parse(dtTmp) + (60000 * Number));
        case 'h' :return new Date(Date.parse(dtTmp) + (3600000 * Number));
        case 'd' :return new Date(Date.parse(dtTmp) + (86400000 * Number));
        case 'w' :return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));

        case 'q' :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) +
            Number*3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(),
            dtTmp.getSeconds());

        case 'm' :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) +
            Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(),
            dtTmp.getSeconds());

        case 'y' :return new Date((dtTmp.getFullYear() + Number),
            dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(),
            dtTmp.getSeconds());
    }
}

//+---------------------------------------------------
//| 比较日期差 dtEnd 格式为日期型或者有效日期格式字符串
//+---------------------------------------------------
Date.prototype.DateDiff = function(strInterval, dtEnd) {
    var dtStart = this;
    if (typeof dtEnd == 'string' )//如果是字符串转换为日期型
    {
        dtEnd = StringToDate(dtEnd);
    }
    switch (strInterval) {
        case 's' :return parseInt((dtEnd - dtStart) / 1000);
        case 'n' :return parseInt((dtEnd - dtStart) / 60000);
        case 'h' :return parseInt((dtEnd - dtStart) / 3600000);
        case 'd' :return parseInt((dtEnd - dtStart) / 86400000);
        case 'w' :return parseInt((dtEnd - dtStart) / (86400000 * 7));
        case 'm' :return (dtEnd.getMonth()+1)+((dtEnd.getFullYear()-dtStart.getFullYear())*12) - (dtStart.getMonth()+1);
        case 'y' :return dtEnd.getFullYear() - dtStart.getFullYear();
    }
}

//+---------------------------------------------------
//| 日期输出字符串，重载了系统的toString方法
//+---------------------------------------------------
Date.prototype.toString = function(showWeek)
{
    var myDate= this;
    var str = myDate.toLocaleDateString();
    if (showWeek)
    {
        var Week = ['日','一','二','三','四','五','六'];
        str += ' 星期' + Week[myDate.getDay()];
    }
    return str;
}

//+---------------------------------------------------
//| 日期合法性验证
//| 格式为：YYYY-MM-DD或YYYY/MM/DD
//+---------------------------------------------------
function IsValidDate(DateStr)
{
    var sDate=DateStr.replace(/(^\s+|\s+$)/g,''); //去两边空格;
    if(sDate=='') return true;
    //如果格式满足YYYY-(/)MM-(/)DD或YYYY-(/)M-(/)DD或YYYY-(/)M-(/)D或YYYY-(/)MM-(/)D就替换为''
    //数据库中，合法日期可以是:YYYY-MM/DD(2003-3/21),数据库会自动转换为YYYY-MM-DD格式
    var s = sDate.replace(/[\d]{ 4,4 }[\-/]{ 1 }[\d]{ 1,2 }[\-/]{ 1 }[\d]{ 1,2 }/g,'');
    if (s=='') //说明格式满足YYYY-MM-DD或YYYY-M-DD或YYYY-M-D或YYYY-MM-D
    {
        var t=new Date(sDate.replace(/\-/g,'/'));
        var ar = sDate.split(/[-/:]/);
        if(ar[0] != t.getYear() || ar[1] != t.getMonth()+1 || ar[2] != t.getDate())
        {
            //alert('错误的日期格式！格式为：YYYY-MM-DD或YYYY/MM/DD。注意闰年。');
            return false;
        }
    }
    else
    {
        //alert('错误的日期格式！格式为：YYYY-MM-DD或YYYY/MM/DD。注意闰年。');
        return false;
    }
    return true;
}

//+---------------------------------------------------
//| 日期时间检查
//| 格式为：YYYY-MM-DD HH:MM:SS
//+---------------------------------------------------
function CheckDateTime(str)
{
    var reg = /^(\d+)-(\d{ 1,2 })-(\d{ 1,2 }) (\d{ 1,2 }):(\d{ 1,2 }):(\d{ 1,2 })$/;
    var r = str.match(reg);
    if(r==null)return false;
    r[2]=r[2]-1;
    var d= new Date(r[1],r[2],r[3],r[4],r[5],r[6]);
    if(d.getFullYear()!=r[1])return false;
    if(d.getMonth()!=r[2])return false;
    if(d.getDate()!=r[3])return false;
    if(d.getHours()!=r[4])return false;
    if(d.getMinutes()!=r[5])return false;
    if(d.getSeconds()!=r[6])return false;
    return true;
}

//+---------------------------------------------------
//| 把日期分割成数组
//+---------------------------------------------------
Date.prototype.toArray = function()
{
    var myDate = this;
    var myArray = Array();
    myArray[0] = myDate.getFullYear();
    myArray[1] = myDate.getMonth();
    myArray[2] = myDate.getDate();
    myArray[3] = myDate.getHours();
    myArray[4] = myDate.getMinutes();
    myArray[5] = myDate.getSeconds();
    return myArray;
}

//+---------------------------------------------------
//| 取得日期数据信息
//| 参数 interval 表示数据类型
//| y 年 m月 d日 w星期 ww周 h时 n分 s秒
//+---------------------------------------------------
Date.prototype.DatePart = function(interval)
{
    var myDate = this;
    var partStr='';
    var Week = ['日','一','二','三','四','五','六'];
    switch (interval)
    {
        case 'y' :partStr = myDate.getFullYear();break;
        case 'm' :partStr = myDate.getMonth()+1;break;
        case 'd' :partStr = myDate.getDate();break;
        case 'w' :partStr = Week[myDate.getDay()];break;
        case 'ww' :partStr = myDate.WeekNumOfYear();break;
        case 'h' :partStr = myDate.getHours();break;
        case 'n' :partStr = myDate.getMinutes();break;
        case 's' :partStr = myDate.getSeconds();break;
    }
    return partStr;
};
// On creation of a UUID object, set it's initial value
function UUID(){
    this.id = this.createUUID();
}

// When asked what this Object is, lie and return it's value
UUID.prototype.valueOf = function(){ return this.id; };
UUID.prototype.toString = function(){ return this.id; };

//
// INSTANCE SPECIFIC METHODS
//
UUID.prototype.createUUID = function(){
    //
    // Loose interpretation of the specification DCE 1.1: Remote Procedure Call
    // since JavaScript doesn't allow access to internal systems, the last 48 bits 
    // of the node section is made up using a series of random numbers (6 octets long).
    //  
    var dg = new Date(1582, 10, 15, 0, 0, 0, 0);
    var dc = new Date();
    var t = dc.getTime() - dg.getTime();
    var tl = UUID.getIntegerBits(t,0,31);
    var tm = UUID.getIntegerBits(t,32,47);
    var thv = UUID.getIntegerBits(t,48,59) + '1'; // version 1, security version is 2
    var csar = UUID.getIntegerBits(UUID.rand(4095),0,7);
    var csl = UUID.getIntegerBits(UUID.rand(4095),0,7);
    // since detection of anything about the machine/browser is far to buggy, 
    // include some more random numbers here
    // if NIC or an IP can be obtained reliably, that should be put in
    // here instead.
    var n = UUID.getIntegerBits(UUID.rand(8191),0,7) +
        UUID.getIntegerBits(UUID.rand(8191),8,15) +
        UUID.getIntegerBits(UUID.rand(8191),0,7) +
        UUID.getIntegerBits(UUID.rand(8191),8,15) +
        UUID.getIntegerBits(UUID.rand(8191),0,15); // this last number is two octets long
    return tl + tm  + thv  + csar + csl + n;
};

//Pull out only certain bits from a very large integer, used to get the time
//code information for the first part of a UUID. Will return zero's if there 
//aren't enough bits to shift where it needs to.
UUID.getIntegerBits = function(val,start,end){
    var base16 = UUID.returnBase(val,16);
    var quadArray = new Array();
    var quadString = '';
    var i = 0;
    for(i=0;i<base16.length;i++){
        quadArray.push(base16.substring(i,i+1));
    }
    for(i=Math.floor(start/4);i<=Math.floor(end/4);i++){
        if(!quadArray[i] || quadArray[i] == '') quadString += '0';
        else quadString += quadArray[i];
    }
    return quadString;
};

//Replaced from the original function to leverage the built in methods in
//JavaScript. Thanks to Robert Kieffer for pointing this one out
UUID.returnBase = function(number, base){
    return (number).toString(base).toUpperCase();
};

//pick a random number within a range of numbers
//int b rand(int a); where 0 <= b <= a
UUID.rand = function(max){
    return Math.floor(Math.random() * (max + 1));
};

