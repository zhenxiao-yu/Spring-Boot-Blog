package com.zxy.repo;

import com.zxy.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*Repository Interface*/

//extends JpaSpecificationExecutor for dynamic searching
public interface TagRepository extends JpaRepository<Tag, Long> {
    //find a tag by it's name
    Tag findByName(String name);

    //find most used tags
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
