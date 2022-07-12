package demo.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.app.core.DataResult;
import demo.app.core.Result;
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
	
	@GetMapping("/getallsupervisors")
	public DataResult<List<Supervisor>> getAllSupervisors() {
		return this.supervisorService.getAllSupervisors();
	}
	
	@GetMapping("/{supervisorId}")
	public DataResult<Supervisor> getOneUser(@PathVariable int supervisorId) {
		return this.supervisorService.getOneSupervisor(supervisorId);
	}

	@PostMapping("/createonesupervisor")
	public Result createOneSupervisor(@RequestBody Supervisor supervisor) {
		return this.supervisorService.createOneSupervisor(supervisor);
	}
	
}
