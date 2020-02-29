require.config({

    baseUrl:"/js/",
    paths:{
        jquery:"./plugin/jquery.min",
        "echarts":"./plugin/dist/echarts"
    }
});
require(["jquery"],function($){

    var currentPage = $('#current-page').attr("current-page");
    var currentModule = $('#current-page').attr("current-module");
    if(currentModule){

        require([currentModule],function(currentModule){
            //调用模块初始化方法
            currentModule.init(currentPage);
            return;
        });

    }
});