jQuery(function ($) {
    $(document).ready(function () {
        getSignedUser();
    });

    function getSignedUser(){
        $.ajax({
            type: "post",
            url : getContextPath() + "/user/getSignedUser",
            dataType:'json',
            data: {
            },
            success: function(data){
                var signedusers = data.data;
            }
        });
    }

});