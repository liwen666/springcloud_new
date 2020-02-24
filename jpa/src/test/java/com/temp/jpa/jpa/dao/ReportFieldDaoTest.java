package com.temp.jpa.jpa.dao;

import com.temp.jpa.ApplicationStart;
import com.temp.jpa.jpa.entity.DataObjectEntity;
import com.temp.jpa.jpa.entity.ReportFieldEntity;
import com.temp.jpa.jpa.enums.ReportEnum;
import com.temp.jpa.jpa.util.OP;
import com.temp.jpa.jpa.util.Predication;
import com.temp.jpa.jpa.util.SpecificationFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class)
@WebAppConfiguration
public class ReportFieldDaoTest {


    @Autowired
    private ReportFieldDao reportFieldDao;
//  @Autowired
//    private ReportFileldBiz reportFileldBiz;

    @Test
    public void insert() {

        ReportFieldEntity reportFieldEntity = new ReportFieldEntity();
        reportFieldEntity.setResourceId(82);
        reportFieldEntity.setCreateTime(new Date());
        reportFieldEntity.setUpdatePerson("test");
        reportFieldEntity.setConfigs("Max(user1.name)");
        reportFieldEntity.setModelType(ReportEnum.category);
        reportFieldEntity.setFieldName("分类字段 (用户实体1)");
        reportFieldEntity.setSqlType(ReportEnum.REPORT_FIELD);
        reportFieldDao.save(reportFieldEntity);

        ReportFieldEntity reportFieldEntity2 = new ReportFieldEntity();
        reportFieldEntity2.setResourceId(82);
        reportFieldEntity2.setCreateTime(new Date());
        reportFieldEntity2.setUpdatePerson("test");
        reportFieldEntity2.setConfigs("Max(user1.name)");
        reportFieldEntity2.setModelType(ReportEnum.value);
        reportFieldEntity2.setFieldName("计算字段 (用户实体1)");
        reportFieldEntity2.setSqlType(ReportEnum.REPORT_FIELD);
        reportFieldEntity2.setUsed(true);
        reportFieldDao.save(reportFieldEntity2);





    }

    @Test
    public void select() {


        Sort sort = new Sort(Sort.Direction.DESC, "resourceId");
        Pageable pageable = new PageRequest(0, 10, sort);

        Specification spec;
        /***********************单条件查询*************************/
        // 方式1
        Predication p = Predication.get(OP.EQ, "fieldName","计算字段 (用户实体1)" );
//        spec = SpecificationFactory.where(p);
//        // 方式2
//        spec = SpecificationFactory.equal("name", "计算字段 (用户实体1)");
        /***********************多条件查询*************************/
        List<Predication> ps = new ArrayList<>();
        ps.add(Predication.get(OP.LIKE, "fieldName", "计算字段 (用户实体1)"));
        ps.add(Predication.get(OP.EQ, "resourceId", 82));
        // 全and连接
        spec = SpecificationFactory.where(ps);
        // 全or连接
        spec = SpecificationFactory.or(ps);
        // and和or混合连接

        // where name like ?1 and age = ?2
        // and name like ?3 and age = ?4
        // or name like ?5 or age = ?6
        // 工具类实现
        spec = SpecificationFactory.wheres(SpecificationFactory.where(ps))
                .and(SpecificationFactory.where(ps))
                .or(SpecificationFactory.or(ps))
                .build();
        // JPA API辅助类实现
        spec = Specifications.where(SpecificationFactory.where(ps))
                .and(SpecificationFactory.where(ps))
                .or(SpecificationFactory.where(ps));

        // where name like ?1 and age = ?2
        // and ( name like ?3 or age = ?4 )
        // 工具类实现
        spec = SpecificationFactory.wheres(SpecificationFactory.where(ps))
                .and(SpecificationFactory.or(ps))
                .build();
        // JPA API辅助类实现
        spec = Specifications.where(SpecificationFactory.where(ps))
                .and(SpecificationFactory.or(ps));

        Page<ReportFieldEntity> chatPage = reportFieldDao.findAll(spec, pageable);
        ReportFieldEntity next = chatPage.iterator().next();
//        List all = reportFieldDao.findAll(spec);
    }
}