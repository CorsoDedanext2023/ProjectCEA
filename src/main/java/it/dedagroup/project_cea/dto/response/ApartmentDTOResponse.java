package it.dedagroup.project_cea.dto.response;

import lombok.Data;

//TODO Implementare i vari attributi di Apartment
//TODO Implementare le validation di Apartment
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class ApartmentDTOResponse {
	private long id;
	private int unitNumber;
	private int floorNumber;
	private long  id_customer;
	private ScanDTOResponse meter;
	private long id_condominium;
}
