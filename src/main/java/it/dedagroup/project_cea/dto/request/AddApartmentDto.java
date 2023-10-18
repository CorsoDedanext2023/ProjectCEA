package it.dedagroup.project_cea.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddApartmentDto {
	private int unitNumber;
	private int floorNumber;
	private int id_customer;
	private int id_condominium;
}
