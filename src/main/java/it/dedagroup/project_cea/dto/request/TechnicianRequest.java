package it.dedagroup.project_cea.dto.request;

import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TechnicianRequest {
	
	private long id;
	
	private List<InterventionRequest> interventions;
	
	private long id_secretary;
	
	private int max_intervention_for_technician = 5;
	
	@NotBlank(message = "il nome non può essere vuoto")
	private String nome;
	
	@NotBlank(message = "il cognome non può essere vuoto")
	private String cognome;
	
	@NotBlank(message = "la username non può essere vuota")
	@UniqueElements
	private String username;
	
	@NotBlank(message = "la password non può essere vuota")
	private String password;
}
