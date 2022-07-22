package demo.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import demo.app.core.DataResult;
import demo.app.core.Result;
import demo.app.core.SuccessDataResult;
import demo.app.core.SuccessResult;
import demo.app.core.status.LeaveStatus;
import demo.app.entities.LeaveDetail;
import demo.app.entities.dtos.LeaveDetailWithEmployeeDto;
import demo.app.entities.dtos.LeaveDurationWithEmployeeDto;
import demo.app.repos.LeaveDetailRepository;

@Service
public class LeaveDetailService {

	private LeaveDetailRepository leaveDetailRepository;
	
	@Autowired
	public LeaveDetailService(LeaveDetailRepository leaveDetailRepository) {
		this.leaveDetailRepository = leaveDetailRepository;
	}

	public DataResult<List<LeaveDetail>> getAllLeaves() {
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository.findAll(), "All leaves");
	}

	public Result createLeaveRequest(LeaveDetail leaveDetail) {
		leaveDetail.setLeaveStatus(LeaveStatus.PENDING);
		this.leaveDetailRepository.save(leaveDetail);			
		return new SuccessResult("Leave is added");
	}

	public DataResult<LeaveDetail> getOneLeaveById(int leaveId) {
		return new SuccessDataResult<LeaveDetail>( this.leaveDetailRepository.findById(leaveId).orElseThrow(null),"Leave by specific id");
	}

	public DataResult<List<LeaveDetail>> getByLeaveDescription(String leaveDescription) {
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository.getByLeaveDescription(leaveDescription), "Leaves by description") ;
	}
	
	public DataResult<List<LeaveDetail>> getByLeaveStatus(LeaveStatus leaveStatus) {
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository.getByLeaveStatus(leaveStatus), "Leaves by status");
	}

	public DataResult<List<LeaveDetail>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.ASC, "startDate"); 
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository.findAll(sort),"Asc order for leaves by start date");
	}
	
	public DataResult<List<LeaveDetailWithEmployeeDto>> getLeaveDetailsWithEmployee() {
		return new SuccessDataResult<List<LeaveDetailWithEmployeeDto>>(this.leaveDetailRepository.getLeaveDetailsWithEmployee(),"Leave and employee info together");
	}
																				
	public DataResult<List<LeaveDetail>> getByLeaveDurationAndEmployee_Email(float leaveDuration, String email) {
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository.getByLeaveDurationAndEmployee_Email(leaveDuration, email),"Leave durations of employees");
	}
	
	public DataResult<List<LeaveDurationWithEmployeeDto>> getLeaveDurationWithEmployeeDto() {																						
		return new SuccessDataResult<List<LeaveDurationWithEmployeeDto>>(this.leaveDetailRepository.getLeaveDurationWithEmployeeDto(),"Leave durations,status and employees");
	}
	
	public DataResult<List<LeaveDetail>> getByLeaveStatusAndEmployee_Id(LeaveStatus leaveStatus, int id) {
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository.getByLeaveStatusAndEmployee_Id(leaveStatus, id), "Leaves by status for specific employee");
	}
	
	public DataResult<List<LeaveDetail>> getByEmployee_Id(int id) {
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository.getByEmployee_Id(id),"All leaves by specific id");
	}

	public DataResult<List<LeaveDetail>> getByLeaveStatusAndEmployee_Supervisor_SupervisorId(LeaveStatus leaveStatus, int supervisorId) {
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository.getByLeaveStatusAndEmployee_Supervisor_SupervisorId(leaveStatus, supervisorId), "Leaves by specific supervisor id");
	}
																					

	
}

