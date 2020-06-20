package restapi.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import io.swagger.annotations.ApiOperation;
import restapi.persistence.model.Interview;
import restapi.persistence.model.User;
import restapi.persistence.service.SolveCountService;
import restapi.persistence.service.UserService;
import restapi.web.dto.InterviewResponseDto;
import restapi.web.dto.LoginDto;
import restapi.web.dto.UserDto;
import restapi.web.dto.UserResponseDto;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private SolveCountService scs;
	

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/user")
	public List<UserResponseDto> findAll() {
		List<User> userList = userService.findAll();
		List<UserResponseDto> list = new ArrayList<>();
		for (int i = 0; i < userList.size(); i++) {
			list.add(i, convertToResponseDto(userList.get(i)));
		}
		return list;
	}

	@PostMapping("/user/signup")
	@ApiOperation("회원가입")
	public ResponseEntity<Map<String, Object>> signup(@RequestBody UserDto userDto) throws Exception {
		userDto.setId(null);
		User user = convertToEntity(userDto);
		try {
			user.setAuth("Guest");
			user.setInterview(null);
			user.setDibslist(null);
			int i = userService.save(user);
			if (i == 1)
				return response("이미 가입된 이메일 입니다.", HttpStatus.CONFLICT, false);
			else if (i == 2)
				return response("중복된 아이디 입니다.", HttpStatus.CONFLICT, false);
			else {
				String email=userService.findByEmail(userDto.getEmail()).getEmail();
				scs.insert(email);
				
				return response(user, HttpStatus.CREATED, true);
			}
		} catch (Exception e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}

	@PostMapping("/user/signin")
	@ApiOperation("로그인")
	public ResponseEntity<Map<String, Object>> signin(@RequestBody LoginDto loginDto) {
		Map<String, Object> map = new HashMap<>();
		try {
			User user = userService.signin(loginDto.getUserid(), loginDto.getPassword());
			if (user != null) {
				return response(user, HttpStatus.ACCEPTED, true);
			} else {
				map.put("message", "아이디 혹은 비밀번호가 틀렸습니다. 다시 시도해주세요");
				return response(map, HttpStatus.CONFLICT, false);
			}
		} catch (Exception e) {
			return response(e.getMessage(), HttpStatus.CONFLICT, false);
		}
	}

	
	@GetMapping("/user/findId/{email}")
	@ApiOperation("아이디 찾기")
	public String findId(@PathVariable String email) {
		User user = userService.findByEmail(email);
		if(user == null)
			return "해당하는 유저를 찾을 수 없습니다.";
		else
			return user.getUid();
	}
	
	@GetMapping("/user/findPass/{email}/{userid}")
	@ApiOperation("비밀번호 찾기")
	public String findPass(@PathVariable String email, @PathVariable String userid) {
		return userService.findPassword(email, userid);
	}
	
	@PutMapping("/user/update")
	@ApiOperation("아이디, 비밀번호 수정")
	public void update(@RequestBody UserDto userDto) {
		User user = convertToEntity(userDto);
		userService.update(user);
	}

	@DeleteMapping("/user/{userid}")
	public void DeleteInterview(@PathVariable long userid) {
		this.userService.delete(userid);
	}

	@PutMapping("/user/dibs/{userid}/{interviewid}")
	@ApiOperation("찜하기")
	public User dibs(@PathVariable long userid, @PathVariable long interviewid) {
		return this.userService.dibs(userid, interviewid);
	}

	@GetMapping("/user/findall/{userid}")
	@ApiOperation("유저 찜 목록")
	public List<Interview> findByUserid(@PathVariable Long userid){
		return userService.findAllDibs(userid);
	}
	
	@GetMapping("/user/findallinterview/{userid}")
	@ApiOperation("유저 인터뷰 목록")
	public List<Interview> findInterview(@PathVariable Long userid){
		return userService.findAllInterviews(userid);
	}
	
	@GetMapping("/user/{userid}")
	@ApiOperation("아이디 찾기")
	public UserResponseDto findByUserid(@PathVariable String userid) {
		User user = userService.findByUserid(userid);
		System.out.println(user);
		UserResponseDto userResponseDto = convertToResponseDto(user);
		return userResponseDto;
	}

	@GetMapping("/user/checkemail/{email}")
	public Map<String, String> checkemail(@PathVariable String email) {
		Map<String, String> map = new HashMap<>();
		if (userService.checkEmail(email)) {
			map.put("result", "true");
		} else {
			map.put("result", "false");
		}
		return map;
	}

	@GetMapping("/user/checkuserid/{userid}")
	public Map<String, String> checkuserid(@PathVariable String userid) {
		Map<String, String> map = new HashMap<>();
		if (userService.checkUserid(userid)) {
			map.put("result", "true");
		} else {
			map.put("result", "false");
		}
		return map;
	}

	private UserResponseDto convertToResponseDto(User user) {
		UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);

		List<Interview> list = user.getInterview();
		String[] interview_temp = new String[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			interview_temp[i] = list.get(i).getId().toString();
		}
		userResponseDto.setInterview(interview_temp);

		String[] dibs_temp = null;
		if (user.getDibslist() != null)
			dibs_temp = user.getDibslist().split(" ");
		userResponseDto.setDibslist(dibs_temp);

		return userResponseDto;
	}

	private User convertToEntity(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		/*
		 * Column 작업
		 */
		return user;
	}

	private ResponseEntity<Map<String, Object>> response(Object data, HttpStatus httpstatus, boolean status) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", status);
		map.put("data", data);
		System.out.println("data : " + data + ", status  : " + status + ", : httpstatus: " + httpstatus);
		return new ResponseEntity<Map<String, Object>>(map, httpstatus);
	}
	
	@GetMapping("/user/changecheck/{email}/{password}")
	public boolean changecheck(@PathVariable String email, @PathVariable String password) {
		return userService.changecheck(email, password);
	}
	
}
