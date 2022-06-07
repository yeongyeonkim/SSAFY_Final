package restapi.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import restapi.persistence.model.Comment;
import restapi.persistence.model.Schedule;
import restapi.persistence.model.SolveCount;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
}
 