package restapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterviewDto {
	private Long id;
	private String question;
	private String companytype;
	private String fromwhen;
	private String company;
	private String category;
	private String approval;
	private String type;
	private String reject_reason;
}
