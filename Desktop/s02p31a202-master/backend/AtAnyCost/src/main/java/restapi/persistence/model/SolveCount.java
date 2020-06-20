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
@Table(name = "solvecount")
public class SolveCount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "solvecount_id")
	private Long id;
	
	@Column(name = "solver")
	private String solver;
	
	@ColumnDefault("0")
	@Column(name = "os")
	private int os;

	@ColumnDefault("0")
	@Column(name = "network")
	private int network;

	@ColumnDefault("0")
	@Column(name = "algorithm")
	private int algorithm;
	
	@ColumnDefault("0")
	@Column(name = "data_structure")
	private int data_structure;
	
	@ColumnDefault("0")
	@Column(name = "computer_structure")
	private int computer_structure;
	
	@ColumnDefault("0")
	@Column(name = "db")
	private int db;
	
	@ColumnDefault("0")
	@Column(name = "web")
	private int web;
	
	@ColumnDefault("0")
	@Column(name = "etc")
	private int etc;

}
