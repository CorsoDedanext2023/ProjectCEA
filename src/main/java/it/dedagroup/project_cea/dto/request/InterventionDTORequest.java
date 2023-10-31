package it.dedagroup.project_cea.dto.request;

import java.time.LocalDate;

import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.model.StatusIntervention;
import it.dedagroup.project_cea.model.Technician;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import lombok.Data;

@Data
public class InterventionDTORequest {
	//TODO inserire le validation
	private long idIntervention;
	private LocalDate interventionDate;
	private long idTechnician;
	private long idApartment;
	private boolean isAvailable;
	private TypeOfIntervention type;
	private StatusIntervention status;

}
