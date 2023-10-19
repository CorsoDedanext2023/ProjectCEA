package it.dedagroup.project_cea.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.dto.request.RegisterUserDTORequest;
import it.dedagroup.project_cea.mapper.UserMapper;
import it.dedagroup.project_cea.service.def.UserServiceDef;

@Service
public class AllFacade {
	
	@Autowired
	private UserServiceDef userService;
	
	@Autowired
	private UserMapper userMapper;
	
	public String registerUser(RegisterUserDTORequest user) {
		userService.addUser(userMapper.toUser(user));
		return "Registrazione avvenuta con successo";
	}


}
