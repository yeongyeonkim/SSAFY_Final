package restapi.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.persistence.model.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long>{
	List<Interview> findByQuestionContaining(String text);
	List<Interview> findByApprovalContaining(String text);
}
