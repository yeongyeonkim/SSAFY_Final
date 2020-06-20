package restapi.web.controller;

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
import restapi.persistence.service.CommentService;
import restapi.web.dto.CommentDto;

@RestController
@CrossOrigin
public class CommentController {
 
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/comment/today/{userId}")
	public ResponseEntity<List<Comment>> SolvedToday(@PathVariable long userId) throws Exception{
		return ResponseEntity.ok(commentService.getTodayComment(userId));
	}
	
	@GetMapping("/comment/my/{userId}")
	public ResponseEntity<List<Comment>> MyComment(@PathVariable long userId) throws Exception{
		return ResponseEntity.ok(commentService.getMyComment(userId));
	}
	
	@PostMapping("/comment/insert/{intervId}/{userId}")
	public ResponseEntity<?> CommentInsert(@RequestBody CommentDto com, @PathVariable long intervId
			, @PathVariable long userId) throws Exception {
		return ResponseEntity.ok(commentService.insert(com, intervId, userId));
	}
	
	@GetMapping("/comment/detail/{commentId}")
	public ResponseEntity<Comment> CommentDetail(@PathVariable long commentId) throws Exception{
		Comment com = commentService.findById(commentId);
		if(com!=null) {
			return ResponseEntity.ok().body(com);
		}
		return new ResponseEntity<Comment>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/comment/list")
	public ResponseEntity<List<Comment>> CommentList() {
		return ResponseEntity.ok().body(this.commentService.findAll());
	}
	
	@PutMapping("/comment/update")
	public ResponseEntity<Comment> UpdateComment(@RequestBody CommentDto com) throws Exception{
		return ResponseEntity.ok(commentService.update(com));
	}
	
	@DeleteMapping("/comment/delete/{commentId}/{intervId}")
	public void DeleteComment(@PathVariable long commentId, @PathVariable long intervId) {
		this.commentService.delete(commentId, intervId);
	}
	
	@PutMapping("/comment/like/{commentId}/{userId}")
	public Comment UpdateLike(@PathVariable long commentId, @PathVariable long userId) {
		return this.commentService.like(commentId, userId);
	}
	
	@GetMapping("/comment/likesNumber/{commentId}")
	public int likesNumber(@PathVariable long commentId) {
		return this.commentService.likesNumber(commentId);
	}
	
	@GetMapping("/comment/Best")
	public List<Comment> Best(){
		return this.commentService.Best();
	}
}
