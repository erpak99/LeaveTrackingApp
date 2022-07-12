package demo.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.app.core.DataResult;
import demo.app.core.ErrorDataResult;
import demo.app.entities.Employee;
import demo.app.entities.dtos.EmployeeWithSupervisorDto;
import demo.app.services.EmployeeService;


@RestController
@RequestMapping("/employees")
public class EmployeesController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeesController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Employee>> getAllEmployees() {
		return this.employeeService.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public DataResult<Employee> getOneEmployee(@PathVariable int id) {
		return this.employeeService.getOneEmployee(id);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createOneEmployee(@Valid @RequestBody Employee employee) {
		return ResponseEntity.ok(this.employeeService.createOneEmployee(employee));
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)	// validation hatalarini kontrol eder
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String,String> validationErrors = new HashMap<String, String>(); // Map<String,String> -> hata hangi alanda ve hatanın tipi nedir
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Validation errors");
		return errors;
		
	}						 
	
	@GetMapping("/getbyemail")
	public DataResult<Employee> getByEmail(@RequestParam String email) {
		return this.employeeService.getByEmail(email);
	}
	
	@GetMapping("/getbyemployeenameandsupervisorid")
	public DataResult<List<Employee>> getByFirstNameAndSupervisorId(@RequestParam String firstName, @RequestParam int supervisorId) {
		return this.employeeService.getByFirstNameAndSupervisor_SupervisorId(firstName, supervisorId);
	}
	
	@GetMapping("/getbyfirstnamecontains")
	public DataResult<List<Employee>> getByFirstNameContains(@RequestParam String firstName) {
		return this.employeeService.getByFirstNameContains(firstName);
	}

	@GetMapping("/getbyfirstnamestartswith")
	public DataResult<List<Employee>> getByFirstNameStartsWith(@RequestParam String firstName) {
		return this.employeeService.getByFirstNameStartsWith(firstName);
	}


	@GetMapping("/getbyemailandsupervisor")
	public DataResult<List<Employee>> getByEmailAndSupervisor(@RequestParam String email, @RequestParam int supervisorId) {
		return this.employeeService.getByEmailAndSupervisor(email, supervisorId);
	}	


	@GetMapping("/getbysupervisorid")
	public DataResult<List<Employee>> getBySupervisor_SupervisorIdIn(@RequestParam List<Integer> supervisors) {
		return this.employeeService.getBySupervisor_SupervisorIdIn(supervisors);
	}
	
	@GetMapping("/getallbypage")
	public DataResult<List<Employee>> getAll(int pageNo, int pageSize) {
		return this.employeeService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getallasc")
	public DataResult<List<Employee>> getAllSorted() {
		return this.employeeService.getAllSorted();
	}
	
	@GetMapping("/employeewithsupervisordetails")
	public DataResult<List<EmployeeWithSupervisorDto>> getEmployeeWithSupervisorDetails() {
		return this.employeeService.getEmployeeWithSupervisorDetails();
	}
}
