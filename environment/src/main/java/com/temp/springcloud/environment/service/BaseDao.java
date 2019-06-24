package com.temp.springcloud.environment.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseDao<T> extends JpaRepository<T, Serializable>, JpaSpecificationExecutor<T> {

}
