package it.dedagroup.project_cea.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BillDTOResponse {
	
	private double cost;
	private LocalDate paymentDay;
	private LocalDate DeliveringDay;
	private String name;
	private String surname;

}
