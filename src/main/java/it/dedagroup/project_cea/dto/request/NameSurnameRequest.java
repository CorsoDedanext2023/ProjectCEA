package it.dedagroup.project_cea.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameSurnameRequest {
	@NotNull(message = "Insert a value on field name")
	@Pattern(regexp = "^[\\p{L} '-]+$", message = "Insert a valid name")
	private String name;
	@NotNull(message = "Insert a value on field surname")
	@Pattern(regexp = "^[\\p{L} '-]+$", message = "Insert a valid surname")
	private String surname;
}
