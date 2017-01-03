jQuery(function ($) {
    $(document).ready(function () { 
        var payImg = $("div#sfpay");

        function checkStart() {
            $.ajax({
                type: "post",
                url: getContextPath() + "/pay/isCanSfPay",
                dataType: 'json',
                data: {},
                success: function (data) {
                    var temp = data.data;

                    function judge() {
                        if (temp == 1) {
                            payImg.html("");
                            payImg.append($("<a href='https://m.sf-pay.com/syf-redpackets/insiderActRedenvelope/L1l0QUtkL2UzU0V5NThFeUlRQWJ1YTluaHZJUDhkRGc' style='font-size:25px;font-family: Microsoft Yahei;color: white'>数据灯塔带你浪啦1</a>"));
                            payImg.removeClass("animated tada");
                            payImg.addClass("animated tada");

                            $.ajax({
                                type: "post",
                                url: getContextPath() + "/pay/isCanSfPay",
                                dataType: 'json',
                                data: {},
                                success: function (data) {
                                    temp = data.data;
                                    setTimeout(judge, 1000);
                                }, error: function () {
                                    setTimeout(judge, 1000);
                                }
                            });

                        } else if (temp == 2) {
                            payImg.html("");
                            payImg.append($("<a href='http://www.sogou.com' style='font-size:25px;font-family: Microsoft Yahei;color: white'>点我抢顺手付红包啦2</a>"));
                            payImg.removeClass("animated tada");
                            payImg.addClass("animated tada");

                            $.ajax({
                                type: "post",
                                url: getContextPath() + "/pay/isCanSfPay",
                                dataType: 'json',
                                data: {},
                                success: function (data) {
                                    temp = data.data;
                                    setTimeout(judge, 1000);
                                }, error: function () {
                                    setTimeout(judge, 1000);
                                }
                            });
                        } else if (temp == 3) {
                            payImg.html("");
                            payImg.append($("<a href='https://m.sf-pay.com/syf-redpackets/insiderActRedenvelope/VEZqRlFtdUFJQ2o4UlpwZFE0Z1RERWtMVEFIeFF2Q0M' style='font-size:25px;font-family: Microsoft Yahei;color: white''>数据灯塔带你浪啦3</a>"));
                            payImg.removeClass("animated tada");
                            payImg.addClass("animated tada");

                            $.ajax({
                                type: "post",
                                url: getContextPath() + "/pay/isCanSfPay",
                                dataType: 'json',
                                data: {},
                                success: function (data) {
                                    temp = data.data;
                                    setTimeout(judge, 1000);
                                }, error: function () {
                                    setTimeout(judge, 1000);
                                }
                            });
                        } else {
                            payImg.html("");
                            payImg.append($("<h3 class='animated bounceIn'>顺手付红包还未开始或已结束，请等待主持人安排</h3>"));
                            setTimeout(checkStart, 2000);
                        }
                    }

                    judge();
                },
                error: function () {
                    setTimeout(checkStart, 2000);
                }
            });

        }

        checkStart();
    });

});