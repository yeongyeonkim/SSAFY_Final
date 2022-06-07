package restapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterviewResponseDto {
	private Long id;
	private String question;
	private String companytype;
	private String fromwhen;
	private String company;
	private int wrong_cnt;
	private int know_well;
	private int not_sure;
	private int not_know;
	private String category;
	private String approval;
	private String type;
	private String reject_reason;
}
