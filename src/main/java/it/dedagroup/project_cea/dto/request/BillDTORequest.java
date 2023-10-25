package it.dedagroup.project_cea.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BillDTORequest {
	
	@NotBlank(message = "Il campo Id_Lettura non può essere vuoto")
	@Min(value = 1, message = "Il campo Id_Lettura deve essere maggiore di 1")
	private long id_scan;
	private LocalDate delivergDay = LocalDate.now();
}
