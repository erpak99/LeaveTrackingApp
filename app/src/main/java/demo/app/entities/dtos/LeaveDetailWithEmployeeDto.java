package demo.app.entities.dtos;

import java.util.Date;

import demo.app.core.status.LeaveStatus;

public class LeaveDetailWithEmployeeDto {

	private Date startDate;
	
	private Date endDate;
	
	private String leaveDescription;
	
	private LeaveStatus leaveStatus;
	
	private String firstName;

	private String lastName;
	
	public LeaveDetailWithEmployeeDto() {
		
	}

	public LeaveDetailWithEmployeeDto(Date startDate, Date endDate, String leaveDescription, LeaveStatus leaveStatus,
			String firstName, String lastName) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveDescription = leaveDescription;
		this.leaveStatus = leaveStatus;
		this.firstName = firstName;
		this.lastName = lastName;
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
	
	
	
	
}
