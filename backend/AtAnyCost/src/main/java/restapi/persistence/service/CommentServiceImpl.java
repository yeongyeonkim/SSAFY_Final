package restapi.persistence.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapi.persistence.dao.CommentRepository;
import restapi.persistence.dao.InterviewRepository;
import restapi.persistence.dao.SolveCountRepository;
import restapi.persistence.dao.UserRepository;
import restapi.persistence.model.Comment;
import restapi.persistence.model.Interview;
import restapi.persistence.model.SolveCount;
import restapi.persistence.model.User;
import restapi.web.dto.CommentDto;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private InterviewRepository interviewRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SolveCountRepository scr;

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public Comment findById(long id) {
		Optional<Comment> com = commentRepository.findById(id);
		return com.get();
	}
	
	public List<Comment> getTodayComment(long userId){
		List<Comment> todayComment=getMyComment(userId);
		
		Date time=new Date();
		SimpleDateFormat format; 
		format = new SimpleDateFormat("yyyy-MM-dd");
		String today=format.format(time);
		
		for(int i=todayComment.size()-1;i>=0;i--) {
			if(!todayComment.get(i).getRegdate().equals(today))
				todayComment.remove(i);
		}
		
		return todayComment;
	}
	
	public List<Comment> getMyComment(long userId){
		List<Comment> allcom=commentRepository.findAll();
		
		String comwriter=userRepository.findById(userId).get().getUid();
		for(int i=allcom.size()-1;i>=0;i--) {
			if(!allcom.get(i).getWriter().equals(comwriter))
				allcom.remove(i);
		}
		
		return allcom;
	}

	public Comment insert(CommentDto com, long intervId, long userId) {
		Comment c = new Comment();
		c.setContent(com.getContent());
		
		User u=userRepository.findById(userId).get();
		c.setWriter(u.getUid());
		
		
		String category=this.interviewRepository.findById(intervId).get().getCategory();
		SolveCount sc=this.scr.findBySolver(u.getUid());
		switch(category) {
		case "운영체제":
			sc.setOs(sc.getOs()+1);
			break;
		case "네트워크":
			sc.setNetwork(sc.getNetwork()+1);
			break;
		case "알고리즘":
			sc.setAlgorithm(sc.getAlgorithm()+1);
			break;
		case "자료구조":
			sc.setData_structure(sc.getData_structure()+1);
			break;
		case "컴퓨터구조":
			sc.setComputer_structure(sc.getComputer_structure()+1);
			break;
		case "데이터베이스":
			sc.setDb(sc.getDb()+1);
			break;
		case "웹":
			sc.setWeb(sc.getWeb()+1);
			break;
		case "기타":
			sc.setEtc(sc.getEtc()+1);
		}
		scr.save(sc);
		
		Date time=new Date();
		SimpleDateFormat format; 
		format = new SimpleDateFormat("yyyy-MM-dd");
		c.setRegdate(format.format(time));
		
		System.out.println("시간");
		System.out.println(format.format(time));

		Interview interview = this.interviewRepository.findById(intervId).get();
		
		List<Comment> comment = new ArrayList<Comment>();

		int i = 0;
		if (interview.getComments().size() != 0) {
			while (i < interview.getComments().size()) {
				comment.add(interview.getComments().get(i++));
			}
		}
		comment.add(c);
		interview.setComments(comment);
		
		return commentRepository.save(c);
	}

	public Comment update(CommentDto com) {
		Comment comment = convertToEntity(com);
		return commentRepository.save(comment);
	}

	public void delete(long id, long intervId) {
		String writer=commentRepository.findById(id).get().getWriter();
		SolveCount sc=this.scr.findBySolver(writer);
		
		String category=this.interviewRepository.findById(intervId).get().getCategory();
		
		switch(category) {
		case "운영체제":
			sc.setOs(sc.getOs()-1);
			break;
		case "네트워크":
			sc.setNetwork(sc.getNetwork()-1);
			break;
		case "알고리즘":
			sc.setAlgorithm(sc.getAlgorithm()-1);
			break;
		case "자료구조":
			sc.setData_structure(sc.getData_structure()-1);
			break;
		case "컴퓨터구조":
			sc.setComputer_structure(sc.getComputer_structure()-1);
			break;
		case "데이터베이스":
			sc.setDb(sc.getDb()-1);
			break;
		case "웹":
			sc.setWeb(sc.getWeb()-1);
			break;
		case "기타":
			sc.setEtc(sc.getEtc()-1);
		}
		scr.save(sc);
		
		commentRepository.deleteById(id);
	}

	@Override
	public Comment like(long commentId, long userId) {
		Comment comment = commentRepository.findById(commentId).get();
		int id = (int) userId;
		String temp = "";
		String[] likes = null;
		int likes_length = 0;
		if (comment.getLikelist() != null) {
			likes = comment.getLikelist().split(" ");
			likes_length = likes.length;
		}
		for (int i = 0; i < likes_length; i++) {
			if (id == Integer.parseInt(likes[i])) {
				for (int j = 0; j < likes_length; j++) {
					if (i == j)
						continue;
					temp += likes[j] + " ";
				}
				int size = comment.getLikes() - 1;
				comment.setLikes(size);
				if (size == 0)
					comment.setLikelist(null);
				else
					comment.setLikelist(temp);
				commentRepository.save(comment);
				return commentRepository.findById(comment.getId()).get();
			}
		}
		for (int i = 0; i < likes_length; i++) {
			temp += likes[i] + " ";
		}
		temp += id + " ";
		comment.setLikes(comment.getLikes() + 1);
		comment.setLikelist(temp);
		temp = "";

		commentRepository.save(comment);
		return commentRepository.findById(comment.getId()).get();
	}

	private Comment convertToEntity(CommentDto commentDto) {
		Comment comment = modelMapper.map(commentDto, Comment.class);
		return comment;
	}

	public int likesNumber(long id) {
		return commentRepository.likesNumber(id);
	}

	@Override
	public List<Comment> Best() {
		List<Comment> list = this.findAll();
		int list_size = list.size();
		if (list_size != 0) {
			Collections.sort(list, new Comparator<Comment>() {
				@Override
				public int compare(Comment o1, Comment o2) {
					return -Integer.compare(o1.getLikes(), o2.getLikes());
				}
			});
		}
		List<Comment> temp = new ArrayList<>();
		list_size = list_size >= 3 ? 3 : list_size;
		for (int i = 0; i < list_size; i++) {
			temp.add(list.get(i));
		}
		return temp;
	}

}
