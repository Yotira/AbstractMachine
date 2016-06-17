package com.yotira.abstract_machine.controller;

import com.yotira.abstract_machine.service.AbstractMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.AbstractController;

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

    @RequestMapping(value = "out",method = RequestMethod.POST)
    public String index(@RequestParam("control") String control,
                        @RequestParam("denv") String denv){
        abstractMachineService.Run();
        return "";
    }
}