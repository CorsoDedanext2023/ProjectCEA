package it.dedagroup.project_cea.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AceaBillRequest {
	private double cost;
	private long idCondominium;
	private double condominiumConsumption;
	private LocalDate deliveryDate;
	private LocalDate paymentDate;
}
