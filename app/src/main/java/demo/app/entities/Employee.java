package demo.app.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@Entity
@Table(name = "employees")
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "leavedetails" })
@JsonIgnoreType
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private int id;
	
	@Column(name = "employee_firstname")
	@NotNull
	@NotBlank
	@Size(min = 2, message = "Length of first name can not be less than 2 characters")
	private String firstName;

	@Column(name = "employee_lastname")
	@NotNull
	@NotBlank
	@Size(min = 2, message = "Length of last name can not be less than 2 characters")
	private String lastName;
						
	@Column(name = "employee_email")
	@Email
	@NotNull
	@NotBlank
	private String email;
	
	@Column(name = "employee_password")
	@NotNull
	@NotBlank
	private String password;
	
	@Column(name = "employee_phonenumber")
	private String phoneNumber;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supervisor_id")
	private Supervisor supervisor;
	
	@OneToMany(mappedBy = "employee")
	private List<LeaveDetail> leaveDetails;
	
	public Employee() {
		
	}

	public Employee(int id, String firstName, String lastName, String email, String password,
					String phoneNumber, Supervisor supervisor, List<LeaveDetail> leaveDetails) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.supervisor = supervisor;
		this.leaveDetails = leaveDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public List<LeaveDetail> getLeaveDetails() {
		return leaveDetails;
	}

	public void setLeaveDetails(List<LeaveDetail> leaveDetails) {
		this.leaveDetails = leaveDetails;
	}

}
