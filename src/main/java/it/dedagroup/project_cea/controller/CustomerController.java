package it.dedagroup.project_cea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.project_cea.facade.CustomerFacade;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerFacade customerFacade;
}
