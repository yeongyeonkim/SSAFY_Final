package restapi.persistence.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
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
import restapi.web.dto.SolveCountDto;

@Service
@Transactional
public class SolveCountServiceImpl implements SolveCountService {

	@Autowired
	private SolveCountRepository scr;
	
	@Autowired
	private UserRepository userRepository;

	public SolveCount insert(String email) {
		SolveCount sc=new SolveCount();
		
		sc.setSolver(userRepository.findByEmail(email).getUid());
		
		return scr.save(sc);
	}
	
	public HashMap<String, Integer> getone(String solver) {
		SolveCount sc= scr.findBySolver(solver);
		
		HashMap<String, Integer> hm=new HashMap<String, Integer>();
		
		hm.put("os", sc.getOs());
		hm.put("network", sc.getNetwork());
		hm.put("algorithm", sc.getAlgorithm());
		hm.put("data_structure", sc.getData_structure());
		hm.put("computer_structure", sc.getComputer_structure());
		hm.put("db", sc.getDb());
		hm.put("web", sc.getWeb());
		hm.put("etc", sc.getEtc());
		
		return hm;
	}
	
	
}
