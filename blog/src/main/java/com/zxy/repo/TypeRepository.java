package com.zxy.repo;

import com.zxy.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*Repository Interface*/

//extends JpaSpecificationExecutor for dynamic searching
public interface TypeRepository extends JpaRepository<Type, Long> {
    //find a category by it's name
    Type findByName(String name);

    //find most used categories
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
