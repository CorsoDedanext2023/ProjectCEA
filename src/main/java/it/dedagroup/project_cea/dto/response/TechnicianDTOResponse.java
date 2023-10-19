package it.dedagroup.project_cea.dto.response;

import java.util.List;


import it.dedagroup.project_cea.model.Intervention;
import lombok.Data;

@Data
public class TechnicianDTOResponse {
	
	private long id;
	
	private List<Intervention> interventions;
	
	private long id_secretary;
	
	private int max_intervention_for_technician = 5;
	
	private String nome;

	private String cognome;
	
	private String username;
	
	private String password;
}
