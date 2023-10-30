package it.dedagroup.project_cea.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookInterventionDto {

	@Min(value=1,
			message="Id cannot be less than 1")
	private long idCustomer;
	@Min(value=1,
			message="Id cannot be less than 1")
	private long idCondominium;
	@Min(value=1,
			message="Id cannot be less than 1")
	private long idApartment;
	@NotNull
	private String interventionDate;
}
