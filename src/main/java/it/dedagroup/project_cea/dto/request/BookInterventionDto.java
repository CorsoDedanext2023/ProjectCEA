package it.dedagroup.project_cea.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookInterventionDto {

	private long idCustomer;
	private long idApartment;
	private LocalDate interventionDate;
}
