package it.dedagroup.project_cea.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class TechnicianRequest {
	
	private long id;
	
	private List<InterventionRequest> interventions;
	
	private long id_secretary;
}
