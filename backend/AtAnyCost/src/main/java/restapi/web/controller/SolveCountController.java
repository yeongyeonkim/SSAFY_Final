package restapi.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import restapi.persistence.model.SolveCount;
import restapi.persistence.service.CommentService;
import restapi.persistence.service.SolveCountService;
import restapi.web.dto.CommentDto;

@RestController
@CrossOrigin
public class SolveCountController {
 
	@Autowired
	private SolveCountService scs;
	
	@GetMapping("/solvecount/{solver}")
	   public ArrayList<Integer> SolvedToday(@PathVariable String solver) throws Exception{
	      
	      HashMap<String, Integer> temp = scs.getone(solver);
	      ArrayList<Integer> count = new ArrayList<>();   
	      
	      count.add(temp.get("algorithm"));
	      count.add(temp.get("computer_structure"));
	      count.add(temp.get("data_structure"));
	      count.add(temp.get("db"));
	      count.add(temp.get("etc"));
	      count.add(temp.get("network"));
	      count.add(temp.get("os"));
	      count.add(temp.get("web"));
	      
	      return count;
	   }
}
