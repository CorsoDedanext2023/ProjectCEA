package it.dedagroup.project_cea.dto.request;

import java.time.LocalDate;

import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.model.StatusIntervention;
import it.dedagroup.project_cea.model.Technician;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import lombok.Data;

@Data
public class InterventionDTORequest {
	
	private long id;
	private LocalDate interventionDate;
	private Technician technician;
	private Apartment apartment;
	private boolean isAvailable;
	private TypeOfIntervention type;
	private StatusIntervention status;


}
