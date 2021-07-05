package com.zxy.dao;

import com.zxy.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
    //find tag by name in the repository
    Tag findByName(String name);
}
