package restapi.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import restapi.persistence.model.Comment;
import restapi.persistence.model.SolveCount;

public interface SolveCountRepository extends JpaRepository<SolveCount, Long>{
	Boolean existsBySolver(String target);
	SolveCount findBySolver(String solver);
}
 