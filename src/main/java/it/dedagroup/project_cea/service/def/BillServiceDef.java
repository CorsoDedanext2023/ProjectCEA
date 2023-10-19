package it.dedagroup.project_cea.service.def;

import java.time.LocalDate;
import java.util.List;

import it.dedagroup.project_cea.model.Bill;

public interface BillServiceDef {
	
	public List<Bill> findAll();

	public List<Bill> findAllByDeliveryDate(LocalDate deliveryDate);
	
	public List<Bill> findAllBillByScan_Apartment_Customer_Id (long customerId);
}
