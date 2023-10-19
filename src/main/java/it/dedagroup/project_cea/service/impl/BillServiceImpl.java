package it.dedagroup.project_cea.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.repository.BillRepository;
import it.dedagroup.project_cea.service.def.BillServiceDef;

@Service
public class BillServiceImpl implements BillServiceDef {
	
	@Autowired
	BillRepository billRepo;

	@Override
	public List<Bill> findAll() {
		return billRepo.findAll();
	}

	@Override
	public List<Bill> findAllByDeliveryDate(LocalDate deliveryDate) {
		return billRepo.findAllBillByDeliveringDay(deliveryDate);
	}

	@Override
	public void addBill(Bill bill) {
		billRepo.save(bill);
		
	}
	

}
