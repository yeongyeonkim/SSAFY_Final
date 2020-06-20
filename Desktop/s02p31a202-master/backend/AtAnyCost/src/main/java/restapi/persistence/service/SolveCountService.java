package restapi.persistence.service;

import java.util.HashMap;
import java.util.List;

import restapi.persistence.model.Comment;
import restapi.persistence.model.SolveCount;
import restapi.web.dto.CommentDto;

public interface SolveCountService {
	public SolveCount insert(String email);
	public HashMap<String, Integer> getone(String solver);
}
