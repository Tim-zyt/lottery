$(document).ready(function () {
    initAwardCP();
});

function initAwardCP() {
    $.ajax({
        type: "post",
        url : getContextPath() + "/cpGift/getAllAwardCP",
        dataType:'json',
        data: {},
        success: function(data){
            var awardCP = data.data;
            var html = "";
            var iLen = awardCP.length;
            for(var i = 0 ; i < iLen ; i++){
                html += "<tr><td>"+ (i+1) +"</td>><td>"+ awardCP[i].user1SfNum +"</td><td>"+ awardCP[i].user1SfName +"</td><td>"+ awardCP[i].user2SfNum +"</td><td>"+ awardCP[i].user2SfName +"</td>></tr>";
            }
            $("#awardCP").html(html);
        }
    });
}