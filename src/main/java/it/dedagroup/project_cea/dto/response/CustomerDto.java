package it.dedagroup.project_cea.dto.response;

import java.util.List;
import it.dedagroup.project_cea.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
//TODO Implementare le validation in CustomerDto
public class CustomerDto {
	private String name;
	private String surname;
	private String username;
	private String taxCode;
	private Role role;
	private boolean isAvailable;
}
