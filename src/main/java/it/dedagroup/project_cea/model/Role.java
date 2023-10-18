package it.dedagroup.project_cea.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Role {
	ADMIN("ADMIN"),
	CUSTOMER("CUSTOMER"),
	SECRETARY("SECRETARY"),
	TECHNICIAN("TECHNICIAN"),
	ADMINISTRATOR("ADMINISTRATOR");
	@Getter
	private String role;
}
