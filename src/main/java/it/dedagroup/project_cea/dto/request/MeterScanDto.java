package it.dedagroup.project_cea.dto.request;

import jakarta.validation.constraints.Min;
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
	@Getter @Setter
	private long idApartment;
	@Min(value=0,
			message="mcLiter cannot be less than 0")
	@Getter @Setter
	private double mcLiter;
}
