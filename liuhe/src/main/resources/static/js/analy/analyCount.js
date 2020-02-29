//定义一个模块
var countLimit = 10;
var countYearLimit = 10;
var countYear =  new Date().getFullYear();
define(['echarts', 'jquery'], function (echarts) {
    var module = {};
    var analyCountData;
    var myChart = echarts.init(document.getElementById('analyAll'));
    var myYearChart = echarts.init(document.getElementById('analyByYear'));
    var analyYearCountData;
    var initYearCountData = function () {
        $.ajax({
            type: "post",
            url: "getCountDataByYear",
            dataType: "json",
            data: {year:countYear,limit: countYearLimit},
            async: false,
            success: function (result) {
                if (result.success) {
                    analyYearCountData = result.obj;
                }
            },
            error: function (jqXHR) {
                console.log("发生错误：" + jqXHR.status);
            }
        });
    };
    var initCountData = function () {
        $.ajax({
            type: "post",
            url: "getCountData",
            dataType: "json",
            data: {limit: countLimit},
            async: false,
            success: function (result) {
                if (result.success) {
                    analyCountData = result.obj;
                }
            },
            error: function (jqXHR) {
                console.log("发生错误：" + jqXHR.status);
            }
        });
    };
    var initAllCountEchart = function () {
        myChart.showLoading();
        option = {
            title: {
                text: '总次数统计'
            },
            backgroundColor: '#FFFFFF',
            xAxis: {
                data: analyCountData["AK"]
            },
            yAxis: {},
            //*********重点配置selected*************//
            legend: {
                selected: {
                    'A': true,
                    'T': false,
                    'P1': false,
                    'P2': false,
                    'P3': false,
                    'P4': false,
                    'P5': false,
                    'P6': false
                },
                data: ['A', 'T', 'P1', 'P2', 'P3', 'P4', 'P5', 'P6']
            },
            //******************************************//
            series: [{
                name: 'A',
                type: 'bar',
                data: analyCountData["AV"],
                label: {normal: {show: true}}
            }, {
                name: 'T',
                type: 'bar',
                data: analyCountData["TV"],
                label: {normal: {show: true}}
            }, {
                name: 'P1',
                type: 'bar',
                data: analyCountData["P1V"],
                label: {normal: {show: true}}
            }, {
                name: 'P2',
                type: 'bar',
                data: analyCountData["P2V"],
                label: {normal: {show: true}}
            }, {
                name: 'P3',
                type: 'bar',
                data: analyCountData["P3V"],
                label: {normal: {show: true}}
            }, {
                name: 'P4',
                type: 'bar',
                data: analyCountData["P4V"],
                label: {normal: {show: true}}
            }, {
                name: 'P5',
                type: 'bar',
                data: analyCountData["P5V"],
                label: {normal: {show: true}}
            }, {
                name: 'P6',
                type: 'bar',
                data: analyCountData["P6V"],
                label: {normal: {show: true}}
            }]
        };
        myChart.hideLoading();
        myChart.setOption(option);
        myChart.on("legendselectchanged", function (e) {
            for (var index = 0; index < option.legend.data.length; index++) {
                if (e.name == option.legend.data[index]) {
                    option.legend.selected[e.name] = true;
                    option.xAxis = {data: analyCountData[e.name + "K"]};
                } else {
                    option.legend.selected[option.legend.data[index]] = false; // 将状态设置为未选中
                }
            }
            myChart.setOption(option);
        });
    };
    //按年统计
    var initYearCountEchart = function () {
        myYearChart.showLoading();
        option = {
            title: {
                text: '按年次数统计'
            },
            backgroundColor: '#FFFFFF',
            xAxis: {
                data: analyYearCountData["AK"]
            },
            yAxis: {},
            //*********重点配置selected*************//
            legend: {
                selected: {
                    'A': true,
                    'T': false,
                    'P1': false,
                    'P2': false,
                    'P3': false,
                    'P4': false,
                    'P5': false,
                    'P6': false
                },
                data: ['A', 'T', 'P1', 'P2', 'P3', 'P4', 'P5', 'P6']
            },
            //******************************************//
            series: [{
                name: 'A',
                type: 'bar',
                data: analyYearCountData["AV"],
                label: {normal: {show: true}}
            }, {
                name: 'T',
                type: 'bar',
                data: analyYearCountData["TV"],
                label: {normal: {show: true}}
            }, {
                name: 'P1',
                type: 'bar',
                data: analyYearCountData["P1V"],
                label: {normal: {show: true}}
            }, {
                name: 'P2',
                type: 'bar',
                data: analyYearCountData["P2V"],
                label: {normal: {show: true}}
            }, {
                name: 'P3',
                type: 'bar',
                data: analyYearCountData["P3V"],
                label: {normal: {show: true}}
            }, {
                name: 'P4',
                type: 'bar',
                data: analyYearCountData["P4V"],
                label: {normal: {show: true}}
            }, {
                name: 'P5',
                type: 'bar',
                data: analyYearCountData["P5V"],
                label: {normal: {show: true}}
            }, {
                name: 'P6',
                type: 'bar',
                data: analyYearCountData["P6V"],
                label: {normal: {show: true}}
            }]
        };
        myYearChart.hideLoading();
        myYearChart.setOption(option);
        myYearChart.on("legendselectchanged", function (e) {
            for (var index = 0; index < option.legend.data.length; index++) {
                if (e.name == option.legend.data[index]) {
                    option.legend.selected[e.name] = true;
                    option.xAxis = {data: analyYearCountData[e.name + "K"]};
                } else {
                    option.legend.selected[option.legend.data[index]] = false; // 将状态设置为未选中
                }
            }
            myChart.setOption(option);
        });
    };
    var initHtml = function () {
        var currentYear = new Date().getFullYear();
        var options = "";
        //初始化下拉框
        for (var i = 1976; i <= currentYear; i++) {
            //选中最新的一年
            if(currentYear == i){
                options += "<option value='" + i + "' selected>" + i + "年" + "</option>";
            }else{
                options += "<option value='" + i + "'>" + i + "年" + "</option>";
            }
        }
        $('#year').html(options);
    };
    $('#countAllBtn').unbind("click").click(function(){
        //获取多选框选中的值
        var limit = $("input[name='limitNum']:checked").val();
        if(limit != countLimit){
            countLimit = limit;
            //重新获取数据
            initCountData();
            //重新渲染
            initAllCountEchart();
        }
    });

    $('#countYearBtn').unbind("click").click(function(){
        //获取多选框选中的值
        var limit = $("input[name='limitYearNum']:checked").val();
        var year= $("#year").val();
        if(limit != countYearLimit || countYear != year){
            countYearLimit = limit;
            countYear = year;
            //重新获取数据
            initYearCountData();
            //重新渲染
            initYearCountEchart();
        }
    });

    module.init = function () {
        //所有数据的统计排名
        initCountData();
        initAllCountEchart();
        //获取年数据排行
        initYearCountData();
        initYearCountEchart();

        initHtml()
    };
    return module;
});
