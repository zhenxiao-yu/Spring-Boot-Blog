package com.zxy.repo;

import com.zxy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*Repository Interface*/

//extends JpaSpecificationExecutor for dynamic searching
public interface UserRepository extends JpaRepository<User, Long> {
    //find user based on username and password
    User findByUsernameAndPassword(String username, String password);
}
