package it.dedagroup.project_cea.dto.response;

import java.time.LocalDate;

import it.dedagroup.project_cea.model.StatusIntervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import lombok.Data;

@Data
public class InterventionDTOResponse {
	
	private LocalDate date;
	private TypeOfIntervention type;
	private StatusIntervention status;
	private String condominiumAddress;
	private int unitNumber;
	private int floorNumber;
	private String customerName;
	private String customerSurname;
	private String technicianName;
	private String technicianSurname;

}
