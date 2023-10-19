package it.dedagroup.project_cea.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookInterventionDto {

	@Min(value=1,
			message="Id cannot be less than 1")
	@Getter @Setter
	private long idCustomer;
	@Min(value=1,
			message="Id cannot be less than 1")
	@Getter @Setter
	private long idApartment;
	@Getter @Setter
	private LocalDate interventionDate;
}
