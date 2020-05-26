package com.temp.jpa.jpa.entity;

import org.junit.Test;

import javax.persistence.Table;
import java.lang.annotation.Annotation;

import static org.junit.Assert.*;

public class ObjectFieldEntityTest {

    @Test
    public void name() {

        BaseEntity baseEntity = new ObjectFieldEntity();
        Class<? extends BaseEntity> aClass = baseEntity.getClass();
        Annotation[] annotations = aClass.getAnnotations();
        for(Annotation annotation:annotations){
            System.out.println(annotation.annotationType().getName());
            if(annotation.annotationType().getName().equals("javax.persistence.Table")){
                String name = ((Table) annotation).name();
                System.out.println(name);
            }
        }
    }
}