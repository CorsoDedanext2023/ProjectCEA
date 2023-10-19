package it.dedagroup.project_cea.dto.request;

import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TechnicianDTORequest {
	@NotBlank(message = "ID cannot be empty")
	@Positive(message = "ID must be a positive number")
	private long id;
	
	private List<InterventionDTORequest> interventions;
	
	private long id_secretary;
	
	private int max_intervention_for_technician = 5;
	
	@NotBlank(message = "il nome non può essere vuoto")
	private String name;
	
	@NotBlank(message = "il cognome non può essere vuoto")
	private String surname;
	
	@NotBlank(message = "la username non può essere vuota")
	@UniqueElements
	private String username;
	
	@NotBlank(message = "la password non può essere vuota")
	private String password;
}
