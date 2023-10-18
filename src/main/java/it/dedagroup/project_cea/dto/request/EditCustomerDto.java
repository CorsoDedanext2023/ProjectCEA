package it.dedagroup.project_cea.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCustomerDto {

	private long id;
	private String name;
	private String surname;
	private String username;
	private String oldPassword;
	private String newPassword;
	private String repeatNewPassword;
	private String taxCode;
}
