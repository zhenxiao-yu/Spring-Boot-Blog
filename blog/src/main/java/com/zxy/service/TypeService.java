package com.zxy.service;
//dependencies

import com.zxy.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TypeService {
    //save category
    Type saveType(Type type);

    //retrieve category by id
    Type getType(Long id);

    //retrieve category by name
    Type getTypeByName(String name);

    //list of categories in pages
    Page<Type> listType(Pageable pageable);

    //list of categories
    List<Type> listType();

    //list of most used categories
    List<Type> listTypeTop(Integer size);

    //update category
    Type updateType(Long id, Type type);

    //delete category
    void deleteType(Long id);
}
