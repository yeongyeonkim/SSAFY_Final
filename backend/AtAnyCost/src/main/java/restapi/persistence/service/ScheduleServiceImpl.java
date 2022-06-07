package restapi.persistence.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapi.persistence.dao.InterviewRepository;
import restapi.persistence.dao.ScheduleRepository;
import restapi.persistence.dao.UserRepository;
import restapi.persistence.model.Comment;
import restapi.persistence.model.Interview;
import restapi.persistence.model.Schedule;
import restapi.persistence.model.User;
import restapi.web.dto.InterviewDto;
import restapi.web.dto.ScheduleDto;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Schedule> findAll() {
		return scheduleRepository.findAll();
	}
	
	public List<Schedule> getSchedule(long interviewee_id){

		List<Schedule> schedules=findAll();
		
		for(int i=schedules.size()-1;i>=0;i--)
			if(schedules.get(i).getInterviewee_id()!=interviewee_id)
				schedules.remove(i);
		
		Collections.sort(schedules);
		
		return schedules;
	}
	
	public Schedule insert(ScheduleDto schedule, long userId) {
		Schedule sch=new Schedule();
		sch.setDates(schedule.getDates());
		sch.setCompany(schedule.getCompany());
		sch.setPlace(schedule.getPlace());
		sch.setMemo(schedule.getMemo());
		
		sch.setInterviewee_id(userRepository.findById(userId).get().getId());
		
		return scheduleRepository.save(sch);
	}
	
	public void delete(long scheduleId) {
		scheduleRepository.deleteById(scheduleId);
	}
	
}
