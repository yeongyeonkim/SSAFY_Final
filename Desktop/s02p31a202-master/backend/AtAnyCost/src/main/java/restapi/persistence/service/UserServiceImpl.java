
package restapi.persistence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapi.persistence.dao.InterviewRepository;
import restapi.persistence.dao.UserRepository;
import restapi.persistence.model.Interview;
import restapi.persistence.model.User;
import restapi.persistence.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InterviewRepository interviewRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public int save(User user) {
		String email = user.getEmail();
		String userid = user.getUid();
		if (userRepository.existsByEmail(email))
			return 1;
		else if(userRepository.existsByUid(userid))
			return 2;
		else {
			userRepository.save(user);
			return 0;
		}
	}

	@Override
	public void update(User user) {
		userRepository.update(user);
	}


	public void delete(long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User signin(String userid, String password) {
		User user = userRepository.fbi(userid);
		if (user != null) {
			if (user.getPassword().equals(password))
				return user;
			else
				throw new RuntimeException("아이디 혹은 비밀번호가 틀립니다.");
		}
		return null;
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByUserid(String userid) {
		return userRepository.fbi(userid);
	}


	@Override
	public User dibs(long userId, long interviewId) {
		User user = userRepository.findById(userId).get();
		int id = (int) interviewId;
		String temp = "";
		String[] dibs = null;
		int dibs_length = 0;
		if (user.getDibslist() != null) {
			dibs = user.getDibslist().split(" ");
			dibs_length = dibs.length;
		}
		for(int i=0; i<dibs_length; i++) {
			if(id == Integer.parseInt(dibs[i])) {
				for(int j=0; j<dibs_length; j++) {
					if(i == j) continue;
					temp += dibs[j] + " ";
				}
				int size = dibs_length - 1;
				if(size == 0) user.setDibslist(null);
				else user.setDibslist(temp);
				userRepository.save(user);
				return userRepository.findById(user.getId()).get();
			}
		}
		for (int i = 0; i < dibs_length; i++) {
			temp += dibs[i] + " ";
		}  
		temp += id + " ";
		user.setDibslist(temp);
		temp = "";

		userRepository.save(user);
		return userRepository.findById(user.getId()).get();
	}
	
	@Override
	public List<Interview> findAllDibs(long userid) {
		String dibslist = userRepository.findById(userid).get().getDibslist();
		List<Interview> list = new ArrayList<>();
		if(dibslist != null) {
			String[] info = dibslist.split(" ");
			for(int i=0; i<info.length; i++) {
				list.add(interviewRepository.findById(Long.parseLong(info[i])).get());
			}
		}
		return list;
	}

	@Override
	public List<Interview> findAllInterviews(long userid) {
		User user = userRepository.findById(userid).get();
		return user.getInterview();
	}
	
	@Override
	public boolean checkUserid(String userid) {
		return userRepository.existsByUid(userid);
	}

	@Override
	public boolean checkEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public String findPassword(String email, String userid) {
		return userRepository.findPassword(email, userid);
	}

	@Override
	public boolean changecheck(String email, String password) {
		User user = userRepository.fbe(email);
		if(user == null) return false;
		if(user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

}
