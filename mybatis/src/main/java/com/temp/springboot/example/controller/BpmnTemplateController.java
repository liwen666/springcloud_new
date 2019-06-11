package com.temp.springboot.example.controller;

import com.temp.springboot.example.service.IBpmnTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/template")
public class BpmnTemplateController {
    @Autowired
    private IBpmnTempService bpmnTempService;
    @RequestMapping("/findTempList")
    @ResponseBody
    public Object findTempList(){
        return bpmnTempService.findTempList();
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Object findOne(){
        return bpmnTempService.findOne();
    }
}
