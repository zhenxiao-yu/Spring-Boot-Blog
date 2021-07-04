package com.zxy.service;

import com.zxy.NotFoundException;
import com.zxy.dao.TypeRepository;
import com.zxy.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //notation
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeRepository typeRepository; //declare new type repository

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type getType(Long id) {
        return null;
    }

    @Override
    public Type getTypeByName(String name) {
        return null;
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        return null;
    }

    @Override
    public Type updateType(Long id, Type type) {
        return null;
    }

    @Override
    public void deleteType(Long id) {

    }
}
