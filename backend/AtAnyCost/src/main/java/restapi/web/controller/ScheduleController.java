package restapi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import restapi.persistence.model.Comment;
import restapi.persistence.model.Schedule;
import restapi.persistence.model.SolveCount;
import restapi.persistence.service.CommentService;
import restapi.persistence.service.ScheduleService;
import restapi.persistence.service.SolveCountService;
import restapi.web.dto.CommentDto;
import restapi.web.dto.ScheduleDto;

@RestController
@CrossOrigin
public class ScheduleController {
 
	@Autowired
	private ScheduleService ss;
	
	@GetMapping("/schedule/{userId}")
	public ResponseEntity<List<Schedule>> getSchedule(@PathVariable long userId) throws Exception{
		return ResponseEntity.ok(ss.getSchedule(userId));
	}
	
	@PostMapping("/schedule/insert/{userId}")
	public ResponseEntity<Schedule> insertSchedule(@RequestBody ScheduleDto sche, @PathVariable long userId) throws Exception{
		return ResponseEntity.ok(ss.insert(sche, userId));
	}
	
	@DeleteMapping("/schedule/delete/{scheduleId}")
	public void DeleteInterview(@PathVariable long scheduleId) {
		this.ss.delete(scheduleId);
	}
	
}
