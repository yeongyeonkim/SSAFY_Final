package restapi.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import restapi.persistence.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	@Query("select c.likes from Comment c where c.id = :id")
	int likesNumber(@Param("id") Long id);
}
 