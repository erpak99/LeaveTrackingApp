package demo.app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.app.entities.Employee;
import demo.app.entities.dtos.EmployeeWithSupervisorDto;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>  {

	Employee findByEmail(String email);
	
	Employee getByEmail(String email);
		
	List<Employee> getByFirstNameAndSupervisor_SupervisorId(String firstName, int supervisorId);
	
	List<Employee> getBySupervisor_SupervisorIdIn(List<Integer> supervisors);
	
	List<Employee> getByFirstNameContains(String firstName);
	
	List<Employee> getByFirstNameStartsWith(String firstName);
	
	@Query("From Employee where email =: email and supervisor.supervisorId =: supervisorId")
	List<Employee> getByEmailAndSupervisor(String email, int supervisorId);

	@Query("Select new demo.app.entities.dtos.EmployeeWithSupervisorDto(e.id, e.email, s.firstName, s.lastName) From Supervisor s Inner Join s.employees e")
	List<EmployeeWithSupervisorDto> getEmployeeWithSupervisorDetails();
	
}
