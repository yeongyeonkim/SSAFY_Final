package restapi.persistence.model;
 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "interview")
public class Interview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interv_id")
	private Long id;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "interv_id")
	private List<Comment> comments=new ArrayList<Comment>();

	@Column(name = "question")
	private String question;

	@Column(name = "companytype")
	private String companytype;

	@Column(name = "fromwhen")
	private String fromwhen;

	@Column(name = "company")
	private String company;
	
	@Column(name = "category")
	private String category;

	// 오답률
	@Column(name = "wrong_cnt")
	private int wrong_cnt;

	@ColumnDefault("0")
	@Column(name = "know_well")
	private int know_well;
	 
	@ColumnDefault("0")
	@Column(name = "not_sure")
	private int not_sure;
	 
	@ColumnDefault("0")
	@Column(name = "not_know")
	private int not_know;
	
	@Column(name = "approval")
	private String approval;
	
	@Column(name = "reject_reason")
	private String reject_reason;
	
	@Column(name = "type")
	private String type;


}
