package com.winston.controller;

import com.winston.service.LiuHeService;
import com.winston.vo.LiuHeVo;
import com.winston.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("main")
public class MainController {

    @Autowired
    private LiuHeService liuHeService;

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model){

        model.addAttribute("title","首页");
        return "main";
    }

    @RequestMapping(value = "/getAllData",method = RequestMethod.POST)
    @ResponseBody
    public Response getAllData(){

        List<LiuHeVo> allData = liuHeService.getData();

        return Response.setSuccess(allData);
    }

    /**
     * 次数分析页面跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "/analyCount",method = RequestMethod.GET)
    public String analyCount(Model model){

        model.addAttribute("title","次数分析");
        return "analyCount";
    }

    /**
     * 获取所有数据的次数统计
     * @param limit
     * @return
     */
    @RequestMapping(value = "/getCountData",method = RequestMethod.POST)
    @ResponseBody
    public Response getCountData(String limit){

        Map<String, List<Integer>> analyCountData = liuHeService.getAnalyCountData(Integer.parseInt(limit));

        return Response.setSuccess(analyCountData);
    }


    /**
     * 获取某一年的数据的次数统计
     * @param year
     * @param limit
     * @return
     */
    @RequestMapping(value = "/getCountDataByYear",method = RequestMethod.POST)
    @ResponseBody
    public Response getCountDataByYear(String year,String limit){

        Map<String, List<Integer>> analyCountData = liuHeService.getYearAnalyCountData(Integer.parseInt(year),Integer.parseInt(limit));

        return Response.setSuccess(analyCountData);
    }


}
