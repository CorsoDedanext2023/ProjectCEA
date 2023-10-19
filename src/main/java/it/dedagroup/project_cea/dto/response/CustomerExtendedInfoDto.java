package it.dedagroup.project_cea.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerExtendedInfoDto {
	@NotBlank(message = "Nome non inserito correttamente")
	private String name;
	@NotBlank(message ="Cognome non inserito correttamente")
	private String surname;
	@NotBlank(message ="Codice fiscale non inserito correttamente")
	private String taxCode;
	@Min(value = 1,message = "Piano non inserito correttamente")
	private int floor;
	@Min(value = 1, message = "Interno non inserito correttamente")
	private int unitNumber;

}
