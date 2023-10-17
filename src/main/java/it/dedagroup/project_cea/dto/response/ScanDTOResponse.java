package it.dedagroup.project_cea.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class ScanDTOResponse {
	
	private double mcLiter;
	private String condominiumAddress;
	private List<BillDTOResponse> bills;

}
