package it.dedagroup.project_cea.dto.response;

import java.util.List;


import lombok.Data;

@Data
public class AdministratorDtoResponse {
	private String name;
	private List<CondominiumDtoResponse> condominiums;
	
}
