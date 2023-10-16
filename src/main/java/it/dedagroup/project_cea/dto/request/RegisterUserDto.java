package it.dedagroup.project_cea.dto.request;

import it.dedagroup.project_cea.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {


    @NotBlank(message = "Il nome non può essere vuoto")
	private String name;
	@NotBlank(message = "Il cognome non uò essere vuoto")
	private String surname;
	@NotBlank(message = "L'username non può essere vuoto")
	private String username;
	@NotBlank(message = "la password non può essere vuota")
	@Size(min = 6, message = "inserire almeno sei caratteri per la password")
	@Pattern(regexp = "^(?=.*[!@#&()–_[{}]:;',?/*~$^+=<>]).*$", message = "La password deve contenere almeno un simbolo")
	@Pattern(regexp = "^(?=.*[0-9]).*$", message = "La password deve contenere almeno un numero")
	@Pattern(regexp = "^(?=.*[a-z]).*$", message = "La password deve contenere almeno una lettera minuscola")
	@Pattern(regexp = "^(?=.*[A-Z]).*$", message = "La password deve contenere almeno una lettera maiuscola")
	private String password;
	private Role role;
	private String taxCode;

}
