package com.zxy.service;

import com.zxy.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    //save current category
    Type saveType(Type type);

    //retrieve category by id
    Type getType(Long id);

    //retrieve category by name
    Type getTypeByName(String name);

    //list of pages
    Page<Type> listType(Pageable pageable);

    //list of categories
    List<Type> listType();

    //change category
    Type updateType(Long id,Type type);

    //delete category
    void deleteType(Long id);
}
