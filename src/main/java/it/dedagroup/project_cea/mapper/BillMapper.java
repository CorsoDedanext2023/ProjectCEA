package it.dedagroup.project_cea.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.request.BillDTORequest;
import it.dedagroup.project_cea.dto.response.BillDTOResponse;
import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.service.def.ScanServiceDef;
import it.dedagroup.project_cea.util.Util;

@Component
public class BillMapper {
	
	@Autowired
	private ScanServiceDef scanService;
	
	public BillDTOResponse toBillDTOResponse(Bill b) {
		BillDTOResponse billDTOResp = new BillDTOResponse();
		billDTOResp.setCost(b.getCost());
		billDTOResp.setDeliveringDay(b.getDeliveringDay());
		billDTOResp.setPaymentDay(b.getPaymentDay());
		billDTOResp.setName(b.getScan().getApartment().getCustomer().getName());
		billDTOResp.setSurname(b.getScan().getApartment().getCustomer().getSurname());
		billDTOResp.setUnitNumber(b.getScan().getApartment().getUnitNumber());
		billDTOResp.setFloorNumber(b.getScan().getApartment().getFloorNumber());
		return billDTOResp;
	}
	
	public List<BillDTOResponse> toBillDTOResponseList(List<Bill> bills){
		return bills.stream().map(this::toBillDTOResponse).toList();
	}
	
	public Bill toBill(BillDTORequest dto) {
		Bill bill = new Bill();
		bill.setDeliveringDay(dto.getDelivergDay());
		bill.setScan(scanService.findById(dto.getId_scan()));
		bill.setCost(Util.waterCostCalculation(bill.getScan().getMcLiter()));
		bill.setPaymentDay(dto.getDelivergDay().plusMonths(1));
		return bill;
	}
}
