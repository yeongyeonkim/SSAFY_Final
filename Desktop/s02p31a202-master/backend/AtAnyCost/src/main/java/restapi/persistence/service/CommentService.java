package restapi.persistence.service;

import java.util.List;

import restapi.persistence.model.Comment;
import restapi.web.dto.CommentDto;

public interface CommentService {
	List<Comment> findAll();
	Comment findById(long id);
	public Comment insert(CommentDto com, long intervId, long userId);
	public Comment update(CommentDto com);
	public void delete(long id, long intervId);
	public Comment like(long commentId, long userId);
	public int likesNumber(long id);
	public List<Comment> Best();
	public List<Comment> getMyComment(long userId);
	public List<Comment> getTodayComment(long userId);
}
