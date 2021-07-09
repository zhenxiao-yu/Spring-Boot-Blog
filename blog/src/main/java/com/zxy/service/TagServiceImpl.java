package com.zxy.service;
//dependencies

import com.zxy.NotFoundException;
import com.zxy.repo.TagRepository;
import com.zxy.entity.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class TagServiceImpl implements TagService {

    //declare new tag repository
    @Autowired
    private TagRepository tagRepository;

    //save tag
    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    //retrieve tag
    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.findOne(id);
    }

    //retrieve tag by name
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    //list of tags in pages
    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    //list of tags
    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    //list of most used tags
    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);
        return tagRepository.findTop(pageable);
    }

    //list of tags sorted by id
    @Override
    public List<Tag> listTag(String ids) { //1,2,3
        return tagRepository.findAll(convertToList(ids));
    }

    //convert to list method
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        //if not empty
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    //update tag
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.findOne(id);
        if (t == null) {
            throw new NotFoundException("Tag does not exist!");
        }
        //pass the source date into the target data, performing update
        BeanUtils.copyProperties(tag, t);
        return tagRepository.save(t);
    }

    //delete tag
    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.delete(id);
    }
}
