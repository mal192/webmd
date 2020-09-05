package com.example.webmd.rep;

import com.example.webmd.MSG;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRep  extends CrudRepository <MSG, Integer> {
//https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
    //поиск по тегу (tag)
    List<MSG>findByTag(String tag); //findByTag формируется по ключевым словам руководтва Spring
}
