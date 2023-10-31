package it.dedagroup.project_cea.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MeterScanDto {

	@Min(value=1,
			message="Id cannot be less than 1")
	private long idApartment;
	@Min(value=0,
			message="mcLiter cannot be less than 0")
	private double mcLiter;
	@NotNull(message = "Insert a value in scan date field")
	@Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", message = "Insert a valid date")
	private String scanDate;
}
