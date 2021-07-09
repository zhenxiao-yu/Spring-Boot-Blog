package com.zxy.service;
//dependencies
import com.zxy.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TagService {
    //save tag
    Tag saveTag(Tag type);
    //retrieve tag
    Tag getTag(Long id);
    //retrieve tag by name
    Tag getTagByName(String name);
    //list of tags in pages
    Page<Tag> listTag(Pageable pageable);
    //list of tags
    List<Tag> listTag();
    //list of most used tags
    List<Tag> listTagTop(Integer size);
    //list of tags sorted by id
    List<Tag> listTag(String ids);
    //update tag
    Tag updateTag(Long id, Tag type);
    //delete tag
    void deleteTag(Long id);
}
