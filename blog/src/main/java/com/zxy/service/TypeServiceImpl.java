package com.zxy.service;

import com.zxy.NotFoundException;
import com.zxy.dao.TypeRepository;
import com.zxy.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //notation
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeRepository typeRepository; //declare new type repository

    @Transactional
    @Override
    public Type saveType(Type type) {
        //save current category
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        //retrieve category by id
        return typeRepository.findOne(id);
    }

    @Override
    public Type getTypeByName(String name) {
        //retrieve category by name
        return typeRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        //list of pages
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        //list of categories
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        //list of most used categories
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size"); //sort from most to lest
        Pageable pageable = new PageRequest(0,size,sort);
        return typeRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        //change category
        Type t = typeRepository.findOne(id);
        if (t == null) {
            throw new NotFoundException("Category does not exist");
        }
        //pass the source date into the target data, performing update
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        //delete category
        typeRepository.delete(id);
    }
}
