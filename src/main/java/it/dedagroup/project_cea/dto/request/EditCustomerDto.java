package it.dedagroup.project_cea.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCustomerDto {

	@Min(value=1,
			message="Id cannot be less than 1")
	private long id;
	@NotEmpty(message="Name cannot be empty")
	private String name;
	@NotEmpty(message="Surname cannot be empty")
	private String surname;
	@NotEmpty(message="Username cannot be empty")
	private String username;
	@NotEmpty(message="Old password cannot be empty")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
	private String oldPassword;
	@NotEmpty(message="New password cannot be empty")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
	private String newPassword;
	@NotEmpty(message="New password repeat cannot be empty")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
	private String repeatNewPassword;
	@NotEmpty(message="Taxcode cannot be empty")
	@Pattern(regexp = "[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]")
	private String taxCode;
}
