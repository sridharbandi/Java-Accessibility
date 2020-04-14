var ctx = document.getElementById("myChart").getContext('2d');
var barChartData = {
        labels: [<#list urls as url > '${ url }',</#list >],
        datasets: [{
                label: 'Critical',
                backgroundColor: '#dc3545',
                data: [<#list critical as issue > ${ issue },</#list >]
        }, {
                label: 'Serious',
                backgroundColor: '#ffc107',
                data: [<#list serious as issue > ${ issue },</#list >]
        }, {
                label: 'Moderate',
                backgroundColor: '#17a2b8',
                data: [<#list moderate as issue > ${ issue },</#list >]
        }, {
                label: 'Minor',
                backgroundColor: '#f8f9fa',
                data: [<#list minor as issue > ${ issue },</#list >]
}]

};
var myChart = new Chart(ctx, {
        type: 'bar',
        data: barChartData,
        options: {
                tooltips: {
                        mode: 'index',
                        intersect: false,
                        callbacks: {
                                title: function (tooltipItems, data) {
                                        var idx = tooltipItems[0].index;
                                        return data.labels[idx];
                                }
                        }
                },
                scales: {
                        xAxes: [{
                                ticks: {
                                        callback: function (value) {
                                                return value.substr(0, 10);
                                        },
                                        beginAtZero: true
                                },
                                stacked: true
                        }],
                        yAxes: [{
                                stacked: true
                        }]
                }
        }
});
