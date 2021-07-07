package com.zxy.dao;

import com.zxy.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//blog repo
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
    //JpaSpecificationExecutor help with dynamic searching

    //find recommended blogs from the database
    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    //find search results from the database
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);
}
