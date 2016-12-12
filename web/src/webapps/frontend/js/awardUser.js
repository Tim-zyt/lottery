$(document).ready(function () {
    initAwardUser();
});

function initAwardUser() {
    $.ajax({
        type: "post",
        url : getContextPath() + "/award/getAllAwardUser",
        dataType:'json',
        data: {},
        success: function(data){
            var awardUsers = data.data;
            var html = "";
            var iLen = awardUsers.length;
            for(var i = 0 ; i < iLen ; i++){
                html += "<tr><td>"+ awardUsers[i].awName +"</td><td>"+ awardUsers[i].sfName +"</td><td>"+ awardUsers[i].sfNum +"</td></tr>";
            }
            $("#awardUsers").html(html);
        }
    });
}