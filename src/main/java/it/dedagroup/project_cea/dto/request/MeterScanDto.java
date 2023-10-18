package it.dedagroup.project_cea.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MeterScanDto {

	private long idApartment;
	private double mcLiter;
}
