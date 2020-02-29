//定义一个模块
define(['echarts','jquery'],function(echarts){
    var module = {};
    var data;
    var initData = function(){
        $.ajax({
            type: "post",
            url: "getAllData",
            dataType: "json",
            async:false,
            success: function (result) {
                if (result.success) {
                    data = result.obj;
                }
            },
            error: function (jqXHR) {
                console.log("发生错误：" + jqXHR.status);
            }
        });
    };
   var  initEchart = function(){
       var myChart = echarts.init(document.getElementById('main'));
       var dataMap = {};
       var no = [];
       var yearData = [];
       var yearDataNoFormat = [];
       for(var i = 1; i <=160;i++){
           no.push(i);
       }
       for(var year = 1976;year <= 2019;year++){

           yearData.push(year+'-01-01');
           yearDataNoFormat.push(year);
       }
       function dataFormatter(obj) {
           var pList = [1,2,3,4,5,6,7,8,9,10];
           var temp;
           for (var year = 2010; year <= 2011; year++) {
               var max = 0;
               var sum = 0;
               temp = obj[year];
               for (var i = 0, l = temp.length; i < l; i++) {
                   max = Math.max(max, temp[i]);
                   sum += temp[i];
                   obj[year][i] = {
                       name : pList[i],
                       value : temp[i]
                   }
               }
               obj[year + 'max'] = Math.floor(max / 100) * 100;
               obj[year + 'sum'] = sum;
           }
           return obj;
       };
       var myDataFormat = function(type){
           var obj = {};
           for (var item in data) {
               var myObj = data[item];
               var childData = myObj.data;
               var max = 0;
               var sum = 0;
               var resData = [];
               for (var i = 0; i < childData.length; i++) {
                   switch (type) {
                       case 't':
                           max = Math.max(max, childData[i].t);
                           sum += Number(childData[i].t);
                           resData.push({
                               name: no[i],
                               value: childData[i].t
                           });
                           break;
                       case 'p1':
                           max = Math.max(max, childData[i].p1);
                           sum += Number(childData[i].p1);
                           resData.push({
                               name: no[i],
                               value: childData[i].p1
                           });
                           break;
                       case 'p2':
                           max = Math.max(max, childData[i].p2);
                           sum += Number(childData[i].p2);
                           resData.push({
                               name: no[i],
                               value: childData[i].p2
                           });
                           break;
                       case 'p3':
                           max = Math.max(max, childData[i].p3);
                           sum += Number(childData[i].p3);
                           resData.push({
                               name: no[i],
                               value: childData[i].p3
                           });
                           break;
                       case 'p4':
                           max = Math.max(max, childData[i].p4);
                           sum += Number(childData[i].p4);
                           resData.push({
                               name: no[i],
                               value: childData[i].p4
                           });
                           break;
                       case 'p5':
                           max = Math.max(max, childData[i].p5);
                           sum += Number(childData[i].p5);
                           resData.push({
                               name: no[i],
                               value: childData[i].p5
                           });
                           break;
                       case 'p6':
                           max = Math.max(max, childData[i].p6);
                           sum += Number(childData[i].p6);
                           resData.push({
                               name: no[i],
                               value: childData[i].p6
                           });
                           break;
                   }
               }
               obj[myObj.year] = resData;
               obj[myObj.year + 'max'] = Math.floor(max / 100) * 100;
               obj[myObj.year + 'sum'] = sum;
           }
           return obj;
       };
       dataMap.dataT = myDataFormat('t');
       dataMap.dataP1 =myDataFormat('p1');
       dataMap.dataP2 =myDataFormat('p2');
       dataMap.dataP3 =myDataFormat('p3');
       dataMap.dataP4 =myDataFormat('p4');
       dataMap.dataP5 =myDataFormat('p5');
       dataMap.dataP6 =myDataFormat('p6');
       /* dataMap.dataT = dataFormatter({
            //max : 60000,
            2011:[2,34,43,49,3,4,5,3,4,5],
            2010:[2,34,43,49,3,4,5,3,4,5]
        });

        dataMap.dataP1 = dataFormatter({

            //max : 60000,
            2011:[2,34,43,49,3,4,5,3,4,5],
            2010:[2,34,43,49,3,4,5,3,4,5]
        });

        dataMap.dataP2 = dataFormatter({

            //max : 60000,
            2011:[2,34,43,49,3,4,5,3,4,5],
            2010:[2,34,43,49,3,4,5,3,4,5]
        });
        dataMap.dataP3 = dataFormatter({

            //max : 60000,
            2011:[2,34,43,49,10,33,44,55,12,33],
            2010:[2,34,43,49,3,4,5,3,4,5]
        });
        dataMap.dataP4 = dataFormatter({

            //max : 60000,
            2011:[2,34,43,49,3,4,5,3,4,5],
            2010:[2,34,43,49,3,4,5,3,4,5]
        });
        dataMap.dataP5 = dataFormatter({

            //max : 60000,
            2011:[2,34,43,49,3,4,5,3,4,5],
            2010:[2,34,43,49,3,4,5,3,4,5]
        });
        dataMap.dataP6 = dataFormatter({

            //max : 60000,
            2011:[2,34,43,49,3,4,5,3,4,5],
            2010:[2,34,43,49,3,4,5,3,4,5]
        });*/
       var options = [];
       for(var year in yearDataNoFormat){

           var j = yearDataNoFormat[year];
           options.push({
               title: {text: j+'全国宏观经济指标'},
               series: [
                   {data: dataMap.dataT[j.toString()]},
                   {data: dataMap.dataP1[j.toString()]},
                   {data: dataMap.dataP2[j.toString()]},
                   {data: dataMap.dataP3[j.toString()]},
                   {data: dataMap.dataP4[j.toString()]},
                   {data: dataMap.dataP5[j.toString()]},
                   {data: dataMap.dataP6[j.toString()]}
               ]
           });
       }
       option = {
           baseOption: {
               timeline: {
                   axisType: 'category',
                   autoPlay: false,
                   playInterval: 1000,
                   data: [
                       '2010-01-01',
                       '2011-01-01'
                   ],
                   label: {
                       formatter : function(s) {
                           return (new Date(s)).getFullYear();
                       }
                   }
               },
               title: {
                   subtext: '数据来自国家统计局'
               },
               tooltip: {
               },
               legend: {
                   x: 'right',
                   data: ['特码', '小马1', '小马2', '小马3', '小马4', '小马5','小马6'],
                   selected: {
                       '小马1': false, '小马2': false, '小马3': false,'小马4': false,'小马5': false,'小马6': false
                   }
               },
               calculable : true,
               grid: {
                   top: 80,
                   bottom: 100,
                   tooltip: {
                       trigger: 'axis',
                       axisPointer: {
                           type: 'shadow',
                           label: {
                               show: true,
                               formatter: function (params) {
                                   return params.value.replace('\n', '');
                               }
                           }
                       }
                   }
               },
               yAxis: [
                   {
                       type: 'value',
                       name: '码数'
                   }
               ],xAxis:[
                   {
                       "axisLabel":{interval: 0},
                       "data":[1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
                       "splitLine": {show: false},
                       "type": "category"
                   }
               ],
               series: [
                   {name: '特码', type: 'bar',label: {normal: {show: true}}},
                   {name: '小马1', type: 'bar',label: {normal: {show: true}}},
                   {name: '小马2', type: 'bar',label: {normal: {show: true}}},
                   {name: '小马3', type: 'bar',label: {normal: {show: true}}},
                   {name: '小马4', type: 'bar',label: {normal: {show: true}}},
                   {name: '小马5', type: 'bar',label: {normal: {show: true}}},
                   {name: '小马6', type: 'bar',label: {normal: {show: true}}},
                   {
                       name: '特码',
                       type: 'pie',
                       center: ['75%', '35%'],
                       radius: '28%',
                       z: 100
                   }
               ],
               dataZoom:{
                   y:20,
                   right:0,
                   realtime:true, //拖动滚动条时是否动态的更新图表数据
                   height:20,//滚动条高度
                   start:0,
                   end:25,
                   show: true
               }
           }
       };

       //设置x轴  option.baseOption.xAxis
       option.baseOption.xAxis[0].data = no;
       option.baseOption.timeline.data = yearData;
       option.options = options;
       // 为echarts对象加载数据
       myChart.setOption(option);
   };
   module.init = function(){

       initData();
       initEchart();
    }
    return module;
});
