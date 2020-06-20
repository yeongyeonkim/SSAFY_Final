package restapi.web.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
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
import restapi.persistence.model.Interview;
import restapi.persistence.service.InterviewService;
import restapi.web.dto.InterviewDto;

@RestController
@CrossOrigin
public class InterviewController {

	@Autowired
	private InterviewService interviewService;
	
	@PostMapping("/interview/insert/{userId}")
	public ResponseEntity<?> InterviewInsert(@RequestBody InterviewDto interv, @PathVariable long userId) throws Exception {
		return ResponseEntity.ok(interviewService.insert(interv, userId));
	}
	
	@GetMapping("/interview/detail/{intervId}")
	public ResponseEntity<Interview> InterviewDetail(@PathVariable long intervId) throws Exception{
		Interview interv = interviewService.findById(intervId);
		if(interv!=null) {
			return ResponseEntity.ok().body(interv);
		}
		return new ResponseEntity<Interview>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/interview/my/{userId}")
	public ResponseEntity<List<Interview>> MyInterview(@PathVariable long userId){
		return ResponseEntity.ok(interviewService.MyInterview(userId));
	}
	
	@GetMapping("/interview/isolve/{userId}")
	public ResponseEntity<List<Interview>> solvedInterview(@PathVariable long userId){
		return ResponseEntity.ok(interviewService.solvedInterview(userId));
	}
	
	@GetMapping("/interview/random10")
	public ResponseEntity<List<Interview>> QuestionRandom10() {
		return ResponseEntity.ok().body(this.interviewService.random10());
	}
	
	@GetMapping("/interview/random5")
	public ResponseEntity<List<Interview>> InterviewRandom5() {
		return ResponseEntity.ok().body(this.interviewService.random5());
	}
	
	@GetMapping("/interview/list")
	public ResponseEntity<List<Interview>> InterviewList() {
		return ResponseEntity.ok().body(this.interviewService.getapproved());
	}
	
	@PutMapping("/interview/update")
	public ResponseEntity<Interview> UpdateInterview(@RequestBody InterviewDto interv) throws Exception{
		return ResponseEntity.ok(interviewService.update(interv));
	}
	
	@DeleteMapping("/interview/delete/{intervId}")
	public void DeleteInterview(@PathVariable long intervId) {
		this.interviewService.delete(intervId);
	}
	
	@PutMapping("/interview/approve/{intervId}")
	public void ApproveInterview(@PathVariable long intervId) throws Exception{
		this.interviewService.Approve(intervId);
	}
	
	@PutMapping("/interview/reject")
	public void RejectInterview(@RequestBody InterviewDto interv) throws Exception{
		this.interviewService.Reject(interv);
	}
	
	@GetMapping("/interview/search/{text}")
	public ResponseEntity<List<Interview>> InterviewSearch(@PathVariable String text) {
		return ResponseEntity.ok().body(this.interviewService.searchByText(text));
	}
	
	@GetMapping("/interview/unapproved")
	public ResponseEntity<List<Interview>> InterviewNotyet() {
		return ResponseEntity.ok().body(this.interviewService.getnotyet());
	}
	
	@GetMapping("/interview/findAllComments/{interviewid}")
	public ResponseEntity<List<Comment>> findAllComments(@PathVariable Long interviewid){
		return ResponseEntity.ok().body(this.interviewService.findAllComments(interviewid));
	}
}
