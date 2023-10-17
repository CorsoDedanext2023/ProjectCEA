package it.dedagroup.project_cea.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.response.BillDTOResponse;
import it.dedagroup.project_cea.model.Bill;

@Component
public class BillMapper {
	
	public BillDTOResponse toBillDTOResponse(Bill b) {
		BillDTOResponse billDTOResp = new BillDTOResponse();
		billDTOResp.setCost(b.getCost());
		billDTOResp.setDeliveringDate(b.getDeliveringDate());
		billDTOResp.setPaymentDate(b.getPaymentDate());
		billDTOResp.setName(b.getMeter().getApartment().getCustomer().getName());
		billDTOResp.setSurname(b.getMeter().getApartment().getCustomer().getSurname());
		return billDTOResp;
	}
	
	public List<BillDTOResponse> toBillDTOResponseList(List<Bill> bills){
		return bills.stream().map(this::toBillDTOResponse).toList();
	}
}
