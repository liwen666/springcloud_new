package com.temp.springboot.example.dao;

import com.temp.springboot.example.bean.BpmnTemplateDef;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ITempDefDao {
    List<BpmnTemplateDef> tempList();
    @Select("select * from act_hq_tem_def where rownum=1")
    @ResultMap("bpmnTemplateDefMap")
    Object findOne();
}
