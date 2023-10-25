package it.dedagroup.project_cea.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCustomerDtoRequest {
	private String name;
	private String surname;
	private String username;
	private String password;
	private String taxCode;
	private long id_apartment;
}
