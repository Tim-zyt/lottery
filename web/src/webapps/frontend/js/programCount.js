/**
 * Created by 01170626 on 2016/12/12.
 */
$(document).ready(function () {
    refreshPage();
});

function refreshPage(){
    $.ajax({
        type: "post",
        url : getContextPath() + "/opera/getAllOperas",
        dataType:'json',
        data: {
        },
        success: function(data){
            var operas = data.data;
            var operaHtml = "";
            var barChartData = {
                // labels: ["January", "February", "March", "April", "May", "June", "July"],
                labels: [],
                datasets: [
                    {
                        label: "鲜花",
                        fillColor: "rgba(210, 214, 222, 1)",
                        strokeColor: "rgba(210, 214, 222, 1)",
                        pointColor: "rgba(210, 214, 222, 1)",
                        pointStrokeColor: "#c1c7d1",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(220,220,220,1)",
                        data: []
                    },
                    {
                        label: "跑车",
                        fillColor: "rgba(60,141,188,0.9)",
                        strokeColor: "rgba(60,141,188,0.8)",
                        pointColor: "#3b8bba",
                        pointStrokeColor: "rgba(60,141,188,1)",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(60,141,188,1)",
                        data: []
                    },
                    {
                        label: "火箭",
                        fillColor: "rgba(60,141,188,0.9)",
                        strokeColor: "rgba(60,141,188,0.8)",
                        pointColor: "#3b8bba",
                        pointStrokeColor: "rgba(60,141,188,1)",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(60,141,188,1)",
                        data: []
                    }
                ]
            };
            var iLen = operas.length;
            for(var i = iLen - 1 ; i >=0  ; i--){
                if(operas[i].opName!="抽奖"){
                    var score = 1*operas[i].opFlower+5*operas[i].opCar+25*operas[i].opRocket;
                    operaHtml+="<tr><td>"+operas[i].opName+"</td><td>"+operas[i].opFlower+"</td><td>"+
                        operas[i].opCar+"</td><td>"+operas[i].opRocket+"</td><td>"+score+"</td></tr>";
                    barChartData.labels.push(operas[i].opName);
                    barChartData.datasets[0].data.push(operas[i].opFlower);
                    barChartData.datasets[1].data.push(operas[i].opCar);
                    barChartData.datasets[2].data.push(operas[i].opRocket);

                }
            }
            $("#programCount").html(operaHtml);

            var barChartCanvas = $("#barChart").get(0).getContext("2d");
            var barChart = new Chart(barChartCanvas);
            barChartData.datasets[1].fillColor = "#00a65a";
            barChartData.datasets[1].strokeColor = "#00a65a";
            barChartData.datasets[1].pointColor = "#00a65a";
            var barChartOptions = {
                //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
                scaleBeginAtZero: true,
                //Boolean - Whether grid lines are shown across the chart
                scaleShowGridLines: true,
                //String - Colour of the grid lines
                scaleGridLineColor: "rgba(0,0,0,.05)",
                //Number - Width of the grid lines
                scaleGridLineWidth: 1,
                //Boolean - Whether to show horizontal lines (except X axis)
                scaleShowHorizontalLines: true,
                //Boolean - Whether to show vertical lines (except Y axis)
                scaleShowVerticalLines: true,
                //Boolean - If there is a stroke on each bar
                barShowStroke: true,
                //Number - Pixel width of the bar stroke
                barStrokeWidth: 2,
                //Number - Spacing between each of the X value sets
                barValueSpacing: 5,
                //Number - Spacing between data sets within X values
                barDatasetSpacing: 1,
                //String - A legend template
                legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
                //Boolean - whether to make the chart responsive
                responsive: true,
                maintainAspectRatio: true
            };

            barChartOptions.datasetFill = false;
            barChart.Bar(barChartData, barChartOptions);
        }
    });
}

