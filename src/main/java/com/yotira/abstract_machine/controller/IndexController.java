package com.yotira.abstract_machine.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yotira.abstract_machine.common.cache.AbstractMachineCache;
import com.yotira.abstract_machine.common.entity.Output;
import com.yotira.abstract_machine.service.AbstractMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;

/**
 * @author chenyuting
 * @date 2016/6/16 16:47
 */
@Controller
@RequestMapping(value = "")
public class IndexController{

    @Autowired
    private AbstractMachineService abstractMachineService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String toIndex(){
        return "index";
    }

    @RequestMapping(value = "calculate",method = RequestMethod.POST)
    @ResponseBody
    public JSONArray index(@RequestParam("control") String control,
                            @RequestParam("denv") String denv){
        System.err.println(control + "   " + denv);
        AbstractMachineCache mc = new AbstractMachineCache();
        JSONArray jsonArray = new JSONArray();

        mc = abstractMachineService.init(control, denv);
        List<Output> outputList = abstractMachineService.Run(mc);
        jsonArray = JSONArray.parseArray(JSON.toJSONString(outputList));
        System.err.println(jsonArray);
        return jsonArray;
    }
}