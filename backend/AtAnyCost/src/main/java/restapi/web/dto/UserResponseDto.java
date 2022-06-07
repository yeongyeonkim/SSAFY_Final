
package restapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
	private Long id;
	private String uid;
	private String password;
	private String email;
	private String auth;
	private String[] interview;
	private String[] dibslist;
}
