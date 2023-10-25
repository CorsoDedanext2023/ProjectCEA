package it.dedagroup.project_cea.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class CondominiumDTORequest {
	
	@NotBlank(message = "Il campo indirizzo non può essere vuoto")
	private String address;
	@NotBlank(message = "Il campo iD dell' amministratore non può essere vuoto")
	@Min(value = 1, message = "L'id deve essere maggiore di 1")
	private long id_administrator;

}
