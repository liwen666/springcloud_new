package com.temp.springboot.example.service.impl;

import com.temp.springboot.example.bean.BpmnTemplateDef;
import com.temp.springboot.example.dao.ITempDefDao;
import com.temp.springboot.example.service.IBpmnTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BpmnServiceImpl implements IBpmnTempService {
    @Autowired
    private ITempDefDao iTempDefDao;
    @Override
    public List<BpmnTemplateDef> findTempList(){
        return iTempDefDao.tempList();
    }

    @Override
    public Object findOne() {
        return iTempDefDao.findOne();
    }
}
