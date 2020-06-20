package restapi.persistence.service;

import java.util.List;

import restapi.persistence.model.Comment;
import restapi.persistence.model.Interview;
import restapi.web.dto.InterviewDto;

public interface InterviewService {
	
    public List<Interview> findAll();
    public Interview findById(long id);
	public Interview insert(InterviewDto interview, long userId);
	public Interview update(InterviewDto interview);
	public void delete(long interview_id);
	public void Approve(long id);
	public void Reject(InterviewDto interview);
	public List<Interview> searchByText(String text);
	public List<Interview> getnotyet();
	public List<Comment> findAllComments(long id);
	public List<Interview> getapproved();
	public List<Interview> random10();
	public List<Interview> random5();
	public List<Interview> MyInterview(long userId);
	public List<Interview> solvedInterview(long userId);
}
