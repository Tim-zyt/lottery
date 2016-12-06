/**
 * Created by 01139954 on 2016/12/6.
 */


    function startGift() {
        $.ajax({
            type: "post",
            url : getContextPath() + "/gift/start",
            dataType:'json',
            data: {
                manCount:10
            },
            success: function(data){
                //alert(data);
            }
        });
    }

    function endGift() {
        $.ajax({
            type: "post",
            url : getContextPath() + "/gift/end",
            dataType:'json',
            data: {
            },
            success: function(data){
                //alert(data);
            }
        });
    }
