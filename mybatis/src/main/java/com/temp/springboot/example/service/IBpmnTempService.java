package com.temp.springboot.example.service;

import com.temp.springboot.example.bean.BpmnTemplateDef;

import java.util.List;

public interface IBpmnTempService {
    List<BpmnTemplateDef> findTempList();

    Object findOne();
}
