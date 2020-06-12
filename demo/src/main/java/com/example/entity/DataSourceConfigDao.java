package com.example.entity;


import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSourceConfigDao extends JpaRepository<DataSourceConfigEntity,Integer> {


    /**
     *
     * @return  true 存在  false 不存在
     */
    default boolean isExit(String code){
        DataSourceConfigEntity configEntity = new DataSourceConfigEntity();
        configEntity.setCode(code);
        return  findOne(Example.of(configEntity)).isPresent();
    }
}
