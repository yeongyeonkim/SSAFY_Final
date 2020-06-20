package restapi.persistence.service;

import java.util.List;

import restapi.persistence.model.Comment;
import restapi.persistence.model.Interview;
import restapi.persistence.model.Schedule;
import restapi.web.dto.InterviewDto;
import restapi.web.dto.ScheduleDto;

public interface ScheduleService {
	public List<Schedule> findAll();
	public List<Schedule> getSchedule(long interviewee);
	public Schedule insert(ScheduleDto schedule, long userId);
	public void delete(long scheduleId);
}
