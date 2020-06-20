
package restapi.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import restapi.persistence.model.Interview;
import restapi.persistence.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public boolean existsByUid(String uid);
	
    public boolean existsByEmail(String email);
    
    @Modifying
    @Query(value="update User u set u.uid = :#{#user.uid}, u.password = :#{#user.password} WHERE u.id = :#{#user.id}", nativeQuery=false)
	Integer update(@Param("user") User user );
    
    @Query("select u from User u where u.uid = :uid")
    User fbi(@Param("uid") String uid);
    
    @Query("select u from User u where u.email = :email")
    User fbe(@Param("email") String email);
    
    @Query("select u.password from User u where u.email = :email AND u.uid = :uid")
    String findPassword(@Param("email") String email, @Param("uid") String uid);
    
    User findByEmail(String email);
}