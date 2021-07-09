package com.zxy.service;
//dependencies
import com.zxy.NotFoundException;
import com.zxy.repo.TypeRepository;
import com.zxy.entity.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TypeServiceImpl implements TypeService {

    //declare new category repository
    @Autowired
    private TypeRepository typeRepository;

    //save category
    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    //retrieve category by id
    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.findOne(id);
    }

    //retrieve category by name
    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    //list of categories in pages
    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    //list of categories
    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    //list of most used categories
    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        //sort from most to lest
        Pageable pageable = new PageRequest(0,size,sort);
        return typeRepository.findTop(pageable);
    }

    //update category
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findOne(id);
        if (t == null) {
            throw new NotFoundException("Category does not exist!");
        }
        //pass the source date into the target data, performing update
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    //delete category
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.delete(id);
    }
}
