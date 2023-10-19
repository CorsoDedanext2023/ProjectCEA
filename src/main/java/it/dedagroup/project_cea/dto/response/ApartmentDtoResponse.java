package it.dedagroup.project_cea.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//TODO Implementare i vari attributi di Apartment

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentDtoResponse {
	private long id;
	private int unitNumber;
	private int floorNumber;
	private long id_customer;
	private long id_condominium;
}
