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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	private List<Interview> interview = new ArrayList<>();
	
	@Column(name = "uid")
	private String uid;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "auth")
	private String auth;
	
	@Column(name = "dibslist")
	private String dibslist;
	
}

