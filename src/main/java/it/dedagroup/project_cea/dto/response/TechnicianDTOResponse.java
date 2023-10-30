package it.dedagroup.project_cea.dto.response;

import java.util.List;


import it.dedagroup.project_cea.model.Intervention;
import lombok.Data;

@Data
public class TechnicianDTOResponse {
	
	private long id;
	
	private List<Intervention> interventions;
	
	private List<Long> id_secretaries; //cambiato da singola segretaria a multiple, perchè  un tecnico può, tramite diversi interventi, avere associate doverse segretarie

	private int workLoad = 5;
	
	private String name;

	private String surname;
	
	private String username;
	
	private String password;
}
