package it.dedagroup.project_cea.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.response.CondominiumDtoResponse;
import it.dedagroup.project_cea.model.Condominium;

@Component
public class CondominiumMapper {

	public CondominiumDtoResponse toDto(Condominium c) {
		return null;
	}
	
	public List<CondominiumDtoResponse> toListDto(List<Condominium>condominiums){
		return null;
	}
}
