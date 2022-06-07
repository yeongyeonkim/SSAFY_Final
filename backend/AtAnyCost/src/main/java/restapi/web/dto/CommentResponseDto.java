package restapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
	private Long comment_id;
	private String content;
	private String writer;
	private int like;
	private int dislike;
	private String likes;
	private String dislikes;
}