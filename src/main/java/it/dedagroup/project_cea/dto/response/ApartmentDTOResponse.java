package it.dedagroup.project_cea.dto.response;

import lombok.Data;

//TODO Implementare i vari attributi di Apartment
//TODO Implementare le validation di Apartment
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class ApartmentDTOResponse {
	private String customerUsername;
	private String condominiumAddress;
	private int unitNumber;
	private int floorNumber;
}
