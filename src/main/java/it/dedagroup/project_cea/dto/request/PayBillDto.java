package it.dedagroup.project_cea.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayBillDto {

	@Min(value=1,
			message="Id cannot be less than 1")
	@Getter @Setter
	private long idBill;
	private LocalDate paymentDate;
}
