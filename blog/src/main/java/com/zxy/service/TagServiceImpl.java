package com.zxy.service;

import com.zxy.NotFoundException;
import com.zxy.dao.TagRepository;
import com.zxy.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service //notation
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository; //declare new tag repository

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        //save current tag
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        //retrieve tag by id
        return tagRepository.findOne(id);
    }

    @Override
    public Tag getTagByName(String name) {
        //retrieve tag by name
        return tagRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        //list of tags
        return tagRepository.findAll(pageable);
    }


    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        //change tag
        Tag t = tagRepository.findOne(id);
        if (t == null) {
            throw new NotFoundException("Tag does not exist");
        }
        //pass the source date into the target data, performing update
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }



    @Transactional
    @Override
    public void deleteTag(Long id) {
        //delete tag
        tagRepository.delete(id);
    }
}
