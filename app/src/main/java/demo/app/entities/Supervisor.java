package demo.app.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "supervisors")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "employees" })

public class Supervisor {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supervisor_id")
	private int supervisorId;

	@Column(name = "supervisor_firstname")
	private String firstName;

	@Column(name = "supervisor_lastname")
	private String lastName;

	@Column(name = "supervisor_email")
	private String email;

	@Column(name = "supervisor_password")
	private String password;

	@Column(name = "supervisor_phonenumber")
	private String phoneNumber;

	@OneToMany(mappedBy = "supervisor")
	private List<Employee> employees;

	public Supervisor() {

	}

	public Supervisor(int supervisorId, String firstName, String lastName, String email, String password,
			String phoneNumber, List<Employee> employees) {
		super();
		this.supervisorId = supervisorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.employees = employees;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
