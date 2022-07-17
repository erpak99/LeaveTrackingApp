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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.app.core.DataResult;
import demo.app.core.ErrorDataResult;
import demo.app.entities.Supervisor;
import demo.app.services.SupervisorService;

@RestController
@RequestMapping("/supervisors")
public class SupervisorsController {

	private SupervisorService supervisorService;

	@Autowired
	public SupervisorsController(SupervisorService supervisorService) {
		super();
		this.supervisorService = supervisorService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Supervisor>> getAllSupervisors() {
		return this.supervisorService.getAllSupervisors();
	}
	
	@GetMapping("/{supervisorId}")
	public DataResult<Supervisor> getOneSupervisor(@PathVariable int supervisorId) {
		return this.supervisorService.getOneSupervisor(supervisorId);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createOneSupervisor(@Valid @RequestBody Supervisor supervisor) {
		return ResponseEntity.ok( this.supervisorService.createOneSupervisor(supervisor));
	}
	
	@DeleteMapping("/{id}")
	public void deleteOneSupervisor(@PathVariable int supervisorId) {
		this.supervisorService.deleteOneSupervisor(supervisorId);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String,String> validationErrors = new HashMap<String, String>(); 
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Validation errors");
		return errors;
		
	}
	
}
