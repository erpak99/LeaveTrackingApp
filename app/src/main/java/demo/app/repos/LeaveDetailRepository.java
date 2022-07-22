package demo.app.repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.app.core.status.LeaveStatus;
import demo.app.entities.LeaveDetail;
import demo.app.entities.dtos.LeaveDetailWithEmployeeDto;
import demo.app.entities.dtos.LeaveDurationWithEmployeeDto;

public interface LeaveDetailRepository extends JpaRepository<LeaveDetail, Integer> {
	
	List<LeaveDetail> findByLeaveStatus(LeaveStatus leaveStatus);

	List<LeaveDetail> getByLeaveDescription(String leaveDescription);
	
	List<LeaveDetail> getByLeaveStatus(LeaveStatus leaveStatus);

	List<LeaveDetail> getByLeaveDurationAndEmployee_Email(float leaveDuration, String email);

	@Query("Select new demo.app.entities.dtos.LeaveDetailWithEmployeeDto(l.startDate, l.endDate, l.leaveDescription, l.leaveStatus,e.firstName,e.lastName) From Employee e Inner Join e.leaveDetails l")
	List<LeaveDetailWithEmployeeDto> getLeaveDetailsWithEmployee();

	@Query("Select new demo.app.entities.dtos.LeaveDurationWithEmployeeDto(l.leaveDuration,l.leaveStatus, e.email) From Employee e Inner Join e.leaveDetails l ")
	List<LeaveDurationWithEmployeeDto> getLeaveDurationWithEmployeeDto();

	List<LeaveDetail> getByLeaveStatusAndEmployee_Id(LeaveStatus leaveStatus, int id);
						
	List<LeaveDetail> getByEmployee_Id(int id);

	List<LeaveDetail> getByLeaveStatusAndEmployee_Supervisor_SupervisorId(LeaveStatus leaveStatus, int supervisorId);

					
}
