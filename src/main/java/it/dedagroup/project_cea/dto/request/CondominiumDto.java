package it.dedagroup.project_cea.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class CondominiumDto {
	
	@NotBlank(message = "Inserimento della via non valida")
	private String address;
	@NotBlank(message = "Inserimento dell'amministratore non valida")
	@Min(value = 1, message = "Inserimento dell'id dell'amministratore non valido")
	private long id_administrator;

}
