package it.dedagroup.project_cea.dto.response;

import java.util.List;


import it.dedagroup.project_cea.model.Intervention;
import lombok.Data;

@Data
public class TechnicianDTOResponse {
	
	private long id;
	
	private List<Intervention> interventions;
	
	private long id_secretary;
	
	private int workLoad = 5;
	
	private String name;

	private String surname;
	
	private String username;
	
	private String password;
}
