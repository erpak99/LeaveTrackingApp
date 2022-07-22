package demo.app.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import demo.app.core.status.LeaveStatus;

@Entity
@Table(name = "leavedetails")
public class LeaveDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leave_id")
	private int leaveId;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "leave_duration")
	private float leaveDuration;
	
	@Column(name = "leave_description")
	private String leaveDescription;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "leave_status")
	private LeaveStatus leaveStatus;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	public LeaveDetail() {
		
	}

	public LeaveDetail(int leaveId, Date startDate, Date endDate,
					   float leaveDuration, String leaveDescription,
					   LeaveStatus leaveStatus, Employee employee) {
		super();
		this.leaveId = leaveId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveDuration = leaveDuration;
		this.leaveDescription = leaveDescription;
		this.leaveStatus = leaveStatus;
		this.employee = employee;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public float getLeaveDuration() {
		return leaveDuration;
	}

	public void setLeaveDuration(float leaveDuration) {
		this.leaveDuration = leaveDuration;
	}

	public String getLeaveDescription() {
		return leaveDescription;
	}

	public void setLeaveDescription(String leaveDescription) {
		this.leaveDescription = leaveDescription;
	}

	public LeaveStatus getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(LeaveStatus leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
