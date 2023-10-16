package it.dedagroup.project_cea.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	
	ADMINISTRATOR("ADMINISTRATOR"),
	SECRETARY("SECRETARY"),
	TECHNICIAN("TECHNICIAN"),
	CUSTOMER("CUSTOMER");
	
	private String role;

	
	
	

}
