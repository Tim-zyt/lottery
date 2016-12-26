jQuery(function ($) {
    $(document).ready(function () {
        var payImg = $("div#sfpay");
        var checkIfStart = setInterval(function checkStart() {
            $.ajax({
                type: "post",
                url: getContextPath() + "/pay/isCanSfPay",
                dataType: 'json',
                data: {},
                success: function judge(data) {
                    var temp = data.data;
                    if (temp == 1) {
                        payImg.html("");
                        payImg.append($("<a href='http://www.baidu.com'>点我抢顺手付红包啦1</a>"));
                        payImg.removeClass("animated tada");
                        payImg.addClass("animated tada");
                        clearInterval(checkIfStart);

                        var payInterval = setInterval(function () {
                            $.ajax({
                                type: "post",
                                url: getContextPath() + "/pay/isCanSfPay",
                                dataType: 'json',
                                data: {},
                                success: function (data) {
                                    judge(data);
                                }
                            });
                        }, 1000);
                    } else if(temp == 2){
                        payImg.html("");
                        payImg.append($("<a href='http://www.sogou.com'>点我抢顺手付红包啦2</a>"));
                        payImg.removeClass("animated tada");
                        payImg.addClass("animated tada");
                        clearInterval(checkIfStart);

                        var payInterval = setInterval(function () {
                            $.ajax({
                                type: "post",
                                url: getContextPath() + "/pay/isCanSfPay",
                                dataType: 'json',
                                data: {},
                                success: function (data) {
                                    judge(data);
                                }
                            });
                        }, 1000);
                    } else if(temp == 3){
                        payImg.html("");
                        payImg.append($("<a href='http://www.so.com'>点我抢顺手付红包啦3</a>"));
                        payImg.removeClass("animated tada");
                        payImg.addClass("animated tada");
                        clearInterval(checkIfStart);

                        var payInterval = setInterval(function () {
                            $.ajax({
                                type: "post",
                                url: getContextPath() + "/pay/isCanSfPay",
                                dataType: 'json',
                                data: {},
                                success: function (data) {
                                    var temp = data.data;
                                    if (temp == 0) {
                                        clearInterval(payInterval);
                                        checkIfStart = setInterval(checkStart, 2000);
                                        payImg.html("");
                                        payImg.append($("<h3 class='animated bounceIn'>顺手付红包还未开始或已结束，请等待主持人安排</h3>"));
                                    }
                                }
                            });
                        }, 1000);
                    } else {
                        payImg.html("");
                        payImg.append($("<h3 class='animated bounceIn'>顺手付红包还未开始或已结束，请等待主持人安排</h3>"));
                    }
                }
            });
        }, 2000);
    });
});