jQuery(function ($) {
    $(document).ready(function () {
        var shakeImg = $("div#shakeimg");
        var checkIfStart = setInterval(function checkStart() {
            $.ajax({
                type: "post",
                url: getContextPath() + "/shake/isCanShark",
                dataType: 'json',
                data: {},
                success: function (data) {
                    if (data.data) {
                        shakeImg.html("");
                        shakeImg.append($("<img src='image/phone/shake.png'/>"));
                        shakeImg.removeClass("animated tada");
                        shakeImg.addClass("animated tada");
                        clearInterval(checkIfStart);
                        var shakeTimes = 0;
                        var last_update = new Date().getTime();
                        var last_x = 0;
                        var last_y = 0;
                        var last_z = 0;

                        function deviceMotionHandler(eventData) {
                            var acceleration = eventData.accelerationIncludingGravity;
                            var curTime = new Date().getTime();
                            if ((curTime - last_update) > 100) {
                                var diffTime = curTime - last_update;
                                last_update = curTime;
                                var x = acceleration.x;
                                var y = acceleration.y;
                                var z = acceleration.z;
                                var speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
                                var status = document.getElementById("status");

                                if (speed > 1000) {
                                    shakeTimes++;
                                    shakeImg.addClass("animated wobble");
                                    setTimeout(function () {
                                        shakeImg.removeClass("animated wobble");
                                    }, 1000)
                                }
                                last_x = x;
                                last_y = y;
                                last_z = z;
                            }
                        }

                        if (window.DeviceMotionEvent) {
                            window.addEventListener('devicemotion', deviceMotionHandler, false);
                        } else {
                            alert('你的手机太差了，不能参与摇一摇抽奖，扔掉买个新的吧。--顺丰科技忠劝');
                        }
                        var shakeInterval = setInterval(function () {
                            $.ajax({
                                type: "post",
                                url: getContextPath() + "/shake/saveShakeCount",
                                dataType: 'json',
                                data: {
                                    shakeCount:shakeTimes
                                },
                                success: function (data) {
                                }
                            });
                            $.ajax({
                                type: "post",
                                url: getContextPath() + "/shake/isCanShark",
                                dataType: 'json',
                                data: {},
                                success: function (data) {
                                    if (!data.data) {
                                        clearInterval(shakeInterval);
                                        checkIfStart = setInterval(checkStart, 2000);
                                        shakeImg.html("");
                                        shakeImg.append($("<h3 class='animated bounceIn'>摇奖还未开始或已结束，请等待主持人安排</h3>"));
                                    }
                                }
                            });
                        }, 1000);
                    } else {
                        shakeImg.html("");
                        shakeImg.append($("<h3 class='animated bounceIn'>摇奖还未开始或已结束，请等待主持人安排</h3>"));
                    }
                }
            });
        }, 2000);
    });
});