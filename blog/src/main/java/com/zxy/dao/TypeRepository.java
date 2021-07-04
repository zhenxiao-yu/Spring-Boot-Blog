package com.zxy.dao;

import com.zxy.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Long> {
    //find category by name in the repository
    Type findByName(String name);
}
