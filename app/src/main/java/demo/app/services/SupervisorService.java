package demo.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import demo.app.core.DataResult;
import demo.app.core.ErrorResult;
import demo.app.core.Result;
import demo.app.core.SuccessDataResult;
import demo.app.core.SuccessResult;
import demo.app.entities.Supervisor;
import demo.app.repos.SupervisorRepository;

@Service
public class SupervisorService {

	private SupervisorRepository supervisorRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public SupervisorService(SupervisorRepository supervisorRepository) {
		super();
		this.supervisorRepository = supervisorRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	private Result isEmailUsedBefore(String email) {
		if(this.supervisorRepository.findByEmail(email) != null) {
			return new ErrorResult();
		}
		else 
			return new SuccessResult();
	}

	public DataResult<List<Supervisor>> getAllSupervisors() {
		return new SuccessDataResult<List<Supervisor>>(this.supervisorRepository.findAll(), "All supervisors are shown");
	}

	public DataResult<Supervisor> getOneSupervisor(int supervisorId) {
		return new SuccessDataResult<Supervisor>(this.supervisorRepository.findById(supervisorId).orElse(null),"Supervisor is shown");
	}

	public Result createOneSupervisor(Supervisor supervisor) {
	
		if(!this.isEmailUsedBefore(supervisor.getEmail()).isSuccess()) {
			return new ErrorResult("This email address is already used...Please use another email address...");
		}
		else {
			
			String encodedPassword = this.passwordEncoder.encode(supervisor.getPassword());
			supervisor.setPassword(encodedPassword);
			this.supervisorRepository.save(supervisor);
			return new SuccessResult("Supervisor is added successfully");
		}
	}

	public void deleteOneSupervisor(int supervisorId) {
		this.supervisorRepository.deleteById(supervisorId);
	}
	
	
	
	
}
