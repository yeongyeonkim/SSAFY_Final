package restapi.persistence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapi.persistence.dao.InterviewRepository;
import restapi.persistence.dao.UserRepository;
import restapi.persistence.model.Comment;
import restapi.persistence.model.Interview;
import restapi.persistence.model.User;
import restapi.web.dto.InterviewDto;

@Service
@Transactional
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewRepository interviewRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Interview> findAll() {
		return interviewRepository.findAll();
	}
	
	@Override
	public Interview findById(long id) {
		return interviewRepository.findById(id).get();
	}
	
	public List<Interview> MyInterview(long userId){
		return userRepository.findById(userId).get().getInterview();
	}
	
	public List<Interview> solvedInterview(long userId){
		String target=userRepository.findById(userId).get().getUid();
		
		List<Interview> allinter=interviewRepository.findAll();
		List<Interview> solvedinter=new ArrayList<Interview>();
				
		for(int i=0;i<allinter.size();i++) {
			Interview inter=allinter.get(i);
			
			for(int j=0;j<inter.getComments().size();j++)
				if(inter.getComments().get(j).getWriter().equals(target)) {
					solvedinter.add(inter);
					break;
				}
		}
		
		return solvedinter;
	}
	
	public List<Interview> random5(){
		List<Interview> random5=random10();
		
		for(int i=0;i<5;i++)
			random5.remove(0);
		
		return random5;
	}
	
	public List<Interview> random10(){
		List<Interview> all=interviewRepository.findAll();
		
		for(int i=all.size()-1;i>=0;i--)
			if(!all.get(i).getApproval().equals("yes"))
				all.remove(i);
		
		Random r=new Random();
		int idx[]=new int[10];
		
		for(int i=0;i<10;i++) {
			 idx[i]=r.nextInt(all.size());
			 
			 for(int j=0;j<i;j++)
				 if(idx[i]==idx[j])
					 i--;
		}
		
		List<Interview> random10=new ArrayList<Interview>();
		for(int i=0;i<10;i++)
			random10.add(all.get(idx[i]));
		
		List<Interview> random10WithMostLikeComment=new ArrayList<Interview>();
		for(int i=0;i<10;i++) {
			Interview inter=random10.get(i);
			
			List<Comment> com=inter.getComments();
			for(int x=com.size()-1;x>=1;x--) {
				if(com.get(x).getLikes() < com.get(x-1).getLikes())
					com.remove(x);
				else if(com.get(x).getLikes() > com.get(x-1).getLikes())
					com.remove(x-1);
				else com.remove(x);
			}
			
			inter.setComments(com);
			
			random10WithMostLikeComment.add(inter);
		}
		
		return random10WithMostLikeComment;
	}
	
	
	public Interview insert(InterviewDto interview, long userId) {
		Interview interv = new Interview();
		interv.setQuestion(interview.getQuestion());
		interv.setCompanytype(interview.getCompanytype());
		interv.setFromwhen(interview.getFromwhen());
		interv.setCompany(interview.getCompany());
		interv.setCategory(interview.getCategory());
		interv.setApproval("notyet");
		interv.setType(interview.getType());

		Optional<User> user = this.userRepository.findById(userId);
		List<Interview> list = new ArrayList<Interview>();

		int j = 0;
		if (user.get().getInterview().size() != 0) {
			while(j < user.get().getInterview().size()) {
				list.add(user.get().getInterview().get(j++));
			}
		}
		list.add(interv);
		user.get().setInterview(list);

		return interviewRepository.save(interv);
	}
	
	public Interview update(InterviewDto interview) {
		Interview i=interviewRepository.findById(interview.getId()).get();
		i.setQuestion(interview.getQuestion());
		i.setCompanytype(interview.getCompanytype());
		i.setFromwhen(interview.getFromwhen());
		i.setCompany(interview.getCompany());
		i.setCategory(interview.getCategory());
		i.setType(interview.getType());
		
		return interviewRepository.save(i);
	}
	
	public void delete(long id) {
		interviewRepository.deleteById(id);
	}
	public List<Interview> searchByText(String text) {
		return interviewRepository.findByQuestionContaining(text);
	}
	
	public void Approve(long id) {
		Interview i=interviewRepository.findById(id).get();
		
		i.setApproval("yes");
		
		interviewRepository.save(i);
	}
	
	public void Reject(InterviewDto interview) {
		Interview i=interviewRepository.findById(interview.getId()).get();
		
		i.setApproval("no");
		i.setReject_reason(interview.getReject_reason());
		
		interviewRepository.save(i);
	}
	
	public List<Interview> getnotyet() {
		return interviewRepository.findByApprovalContaining("notyet");
	}
	
	public List<Interview> getapproved() {
		return interviewRepository.findByApprovalContaining("yes");
	}
	
	@Override
	public List<Comment> findAllComments(long id) {
		Interview interview = interviewRepository.findById(id).get();
		return interview.getComments();
	}
}
