package com.zxy.service;

import com.zxy.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TagService {

    //save current tag
    Tag saveTag(Tag type);

    //retrieve tag by id
    Tag getTag(Long id);

    //retrieve tag by name
    Tag getTagByName(String name);

    //list of tags
    Page<Tag> listTag(Pageable pageable);

    //change tag
    Tag updateTag(Long id, Tag type);

    //delete tag
    void deleteTag(Long id);
}
