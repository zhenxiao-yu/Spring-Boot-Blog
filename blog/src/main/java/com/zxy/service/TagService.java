package com.zxy.service;

import com.zxy.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TagService {

    //save current tag
    Tag saveTag(Tag type);

    //retrieve tag by id
    Tag getTag(Long id);

    //retrieve tag by name
    Tag getTagByName(String name);

    //list of pages
    Page<Tag> listTag(Pageable pageable);

    //list of tags
    List<Tag> listTag();

    //list of tag ids
    List<Tag> listTag(String ids);

    //change tag
    Tag updateTag(Long id, Tag type);

    //delete tag
    void deleteTag(Long id);
}
