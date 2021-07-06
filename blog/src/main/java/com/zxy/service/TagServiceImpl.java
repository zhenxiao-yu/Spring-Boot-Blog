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

import java.util.ArrayList;
import java.util.List;


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
        //list of pages
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        //list of tags
        return tagRepository.findAll();
    }


    @Override
    public List<Tag> listTag(String ids) {
        //list of tag ids
        return tagRepository.findAll(convertToList(ids));
    }

    //covert tag to list of tag ids
    private List<Long> convertToList(String ids) {
        //new arraylist
        List<Long> list = new ArrayList<>();
        //if not empty
        if (!"".equals(ids) && ids != null) {

            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
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
