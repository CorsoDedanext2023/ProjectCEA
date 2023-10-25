package it.dedagroup.project_cea.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTORequest {
	
	@NotBlank(message = "l'username non può essere vuota")
	@Size(min = 5, message = "inserire almeno cinque caratteri per l'username")
	private String username;
	@NotBlank(message = "la password non può essere vuota")
//	@Size(min = 6, message = "inserire almeno sei caratteri per la password")
//	@Pattern(regexp = "^(?=.*[!@#&()–_[{}]:;',?/*~$^+=<>]).*$", message = "La password deve contenere almeno un simbolo")
//	@Pattern(regexp = "^(?=.*[0-9]).*$", message = "La password deve contenere almeno un numero")
//	@Pattern(regexp = "^(?=.*[a-z]).*$", message = "La password deve contenere almeno una lettera minuscola")
//	@Pattern(regexp = "^(?=.*[A-Z]).*$", message = "La password deve contenere almeno una lettera maiuscola")
	private String password;

}
