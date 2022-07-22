package demo.app.entities.dtos;

import demo.app.core.status.LeaveStatus;

public class LeaveDurationWithEmployeeDto {

	private float leaveDuration;

	private LeaveStatus leaveStatus;

	private String email;

	public LeaveDurationWithEmployeeDto() {

	}

	public LeaveDurationWithEmployeeDto(float leaveDuration, LeaveStatus leaveStatus, String email) {
		super();
		this.leaveDuration = leaveDuration;
		this.leaveStatus = leaveStatus;
		this.email = email;
	}

	public float getLeaveDuration() {
		return leaveDuration;
	}

	public void setLeaveDuration(float leaveDuration) {
		this.leaveDuration = leaveDuration;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LeaveStatus getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(LeaveStatus leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

}
