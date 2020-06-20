package restapi.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "schedule")
public class Schedule implements Comparable<Schedule>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
	private Long id;
	
	@Column(name = "dates")
	private String dates;
	
	@Column(name = "company")
	private String company;
	
	@Column(name = "place")
	private String place;
	
	@Column(name = "memo")
	private String memo;
	
	@Column(name = "interviewee_id")
	private Long interviewee_id;

	@Override
	public int compareTo(Schedule o) {
		return dates.compareTo(o.getDates());
	}
	

}
