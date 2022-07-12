package demo.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import demo.app.core.DataResult;
import demo.app.core.ErrorResult;
import demo.app.core.Result;
import demo.app.core.SuccessDataResult;
import demo.app.core.SuccessResult;
import demo.app.entities.Employee;
import demo.app.entities.dtos.EmployeeWithSupervisorDto;
import demo.app.repos.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	private Result isEmailUsedBefore(String email) {
		if(this.employeeRepository.findByEmail(email) != null) {
			return new ErrorResult();
		}
		else 
			return new SuccessResult();
	}

	public DataResult<List<Employee>> getAllEmployees() {
		return new SuccessDataResult<List<Employee>>(this.employeeRepository.findAll(), "All employees are shown");
	}

	public DataResult<Employee> getOneEmployee(int id) {
		return new SuccessDataResult<Employee>(this.employeeRepository.findById(id).orElse(null),"Employee is shown");
	}

	public Result createOneEmployee(Employee employee) {
		if(!this.isEmailUsedBefore(employee.getEmail()).isSuccess()) {
			return new ErrorResult("This email address is already used...Please use another email address...");
		}
		else {
			
			String encodedPassword = this.passwordEncoder.encode(employee.getPassword());
			employee.setPassword(encodedPassword);
			this.employeeRepository.save(employee);
			return new SuccessResult("Employee is added successfully");
		}
	}
	
	public DataResult<Employee> getByEmail(String email) {
		return new SuccessDataResult<Employee>(this.employeeRepository.getByEmail(email),"Employee was shown with e-mail address");
	}
	
	public DataResult<List<Employee>> getByFirstNameAndSupervisor_SupervisorId(String firstName, int supervisorId){
		return new SuccessDataResult<List<Employee>>(this.employeeRepository.getByFirstNameAndSupervisor_SupervisorId(firstName,supervisorId),"Name of employee and id of supervisor");
	}


	public DataResult<List<Employee>> getBySupervisor_SupervisorIdIn(List<Integer> supervisors){
		return new SuccessDataResult<List<Employee>>(this.employeeRepository.getBySupervisor_SupervisorIdIn(supervisors), "List of employees by supervisor id");
	}

	
	public DataResult<List<Employee>> getByFirstNameContains(String firstName){
		return new SuccessDataResult<List<Employee>>(this.employeeRepository.getByFirstNameContains(firstName), "Employees by some letters in their name");
	}		
		
	public DataResult<List<Employee>> getByFirstNameStartsWith(String firstName) {
		return new SuccessDataResult<List<Employee>>(this.employeeRepository.getByFirstNameStartsWith(firstName), "Employees by their starting words");		
	}
		
	public DataResult<List<Employee>> getByEmailAndSupervisor(String email, int supervisorId) {
		return new SuccessDataResult<List<Employee>>(this.employeeRepository.getByEmailAndSupervisor(email, supervisorId), "Employees by email and supervisor");
	}
	
	public DataResult<List<Employee>> getAll(int pageNo, int pageSize) {
														
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		return new SuccessDataResult<List<Employee>>(this.employeeRepository.findAll(pageable).getContent(),"Employees by page no and page size");
	}
	
	public DataResult<List<Employee>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
		return new SuccessDataResult<List<Employee>>(this.employeeRepository.findAll(sort), "Ascending order for employees");
	}
	
	public DataResult<List<EmployeeWithSupervisorDto>> getEmployeeWithSupervisorDetails() {
		return new SuccessDataResult<List<EmployeeWithSupervisorDto>>(this.employeeRepository.getEmployeeWithSupervisorDetails(), "Id and email of employees and info of supervisors");
	}

}
