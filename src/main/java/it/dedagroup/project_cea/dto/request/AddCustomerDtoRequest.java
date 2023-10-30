package it.dedagroup.project_cea.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCustomerDtoRequest {
	
	@NotNull(message = "Insert a value on field name")
	@Pattern(regexp = "^[\\p{L} '-]+$", message = "Insert a valid name")
	private String name;
	@NotNull(message = "Insert a value on field surname")
	@Pattern(regexp = "^[\\p{L} '-]+$", message = "Insert a valid surname")
	private String surname;
	@NotEmpty(message="Username cannot be empty")
	private String username;
	@NotEmpty(message="Password cannot be empty")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
	private String password;
	@NotEmpty(message="Taxcode cannot be empty")
	@Pattern(regexp = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$", message = "Insert a valid tax code")
	private String taxCode;
	private long id_apartment;
}
