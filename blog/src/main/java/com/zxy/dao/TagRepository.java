package com.zxy.dao;

import com.zxy.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {
    //find tag by name in the repository
    Tag findByName(String name);

    //find most used tags
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
