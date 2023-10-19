package it.dedagroup.project_cea.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BillDTORequest {
	
	@NotBlank(message = "Id scan non valido")
	@Min(value = 1, message = "Id scan non valido")
	private long id_scan;
	
	private LocalDate delivergDay = LocalDate.now();
}
