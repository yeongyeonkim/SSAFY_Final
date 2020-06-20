package restapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolveCountDto {
	private String solver;
	private int os;
	private int network;
	private int algorithm;
	private int data_structure;
	private int computer_structure;
	private int database;
	private int web;
	private int etc;
}