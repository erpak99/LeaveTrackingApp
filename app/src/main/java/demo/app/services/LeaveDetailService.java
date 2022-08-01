package demo.app.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
	
	public DataResult<LeaveDetail> updateOneLeaveDetail(int leaveId, LeaveDetail newLeaveDetail) {
		Optional<LeaveDetail> leaveDetail = leaveDetailRepository.findById(leaveId);
		if(leaveDetail.isPresent()) {
			LeaveDetail foundLeaveDetail = leaveDetail.get();
			foundLeaveDetail.setStartDate(newLeaveDetail.getStartDate());
			foundLeaveDetail.setEndDate(newLeaveDetail.getEndDate());
			foundLeaveDetail.setLeaveDuration(newLeaveDetail.getLeaveDuration());
			foundLeaveDetail.setLeaveDescription(newLeaveDetail.getLeaveDescription());
			leaveDetailRepository.save(foundLeaveDetail);
			return new SuccessDataResult<LeaveDetail>(foundLeaveDetail, "Leave detail is updated");
		} else 
			return null;
	}

	public DataResult<LeaveDetail> getOneLeaveById(int leaveId) {
		return new SuccessDataResult<LeaveDetail>(this.leaveDetailRepository.findById(leaveId).orElseThrow(null),
				"Leave by specific id");
	}

	public DataResult<List<LeaveDetail>> getByLeaveDescription(String leaveDescription) {
		return new SuccessDataResult<List<LeaveDetail>>(
				this.leaveDetailRepository.getByLeaveDescription(leaveDescription), "Leaves by description");
	}

	public DataResult<List<LeaveDetail>> getByLeaveStatus(LeaveStatus leaveStatus) {
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository.getByLeaveStatus(leaveStatus),
				"Leaves by status");
	}
	
	public DataResult<List<LeaveDetail>> getByStartDateContains(String date) {
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository.getByStartDateContains(date),
				"Leaves by specific time");
	}

	public DataResult<List<LeaveDetail>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.ASC, "startDate");
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository.findAll(sort),
				"Asc order for leaves by start date");
	}

	public DataResult<List<LeaveDetailWithEmployeeDto>> getLeaveDetailsWithEmployee() {
		return new SuccessDataResult<List<LeaveDetailWithEmployeeDto>>(
				this.leaveDetailRepository.getLeaveDetailsWithEmployee(), "Leave and employee info together");
	}

	public DataResult<List<LeaveDetail>> getByLeaveDurationAndEmployee_Email(float leaveDuration, String email) {
		return new SuccessDataResult<List<LeaveDetail>>(
				this.leaveDetailRepository.getByLeaveDurationAndEmployee_Email(leaveDuration, email),
				"Leave durations of employees");
	}

	public DataResult<List<LeaveDurationWithEmployeeDto>> getLeaveDurationWithEmployeeDto() {
		return new SuccessDataResult<List<LeaveDurationWithEmployeeDto>>(
				this.leaveDetailRepository.getLeaveDurationWithEmployeeDto(), "Leave durations,status and employees");
	}

	public DataResult<List<LeaveDetail>> getByLeaveStatusAndEmployee_Id(LeaveStatus leaveStatus, int id) {
		return new SuccessDataResult<List<LeaveDetail>>(
				this.leaveDetailRepository.getByLeaveStatusAndEmployee_Id(leaveStatus, id),
				"Leaves by status for specific employee");
	}

	public DataResult<List<LeaveDetail>> getByEmployee_Id(int id) {
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository.getByEmployee_Id(id),
				"All leaves by specific employee id");
	}

	public DataResult<List<LeaveDetail>> getByLeaveStatusAndEmployee_Supervisor_SupervisorId(LeaveStatus leaveStatus,
			int supervisorId) {
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository
				.getByLeaveStatusAndEmployee_Supervisor_SupervisorId(leaveStatus, supervisorId),
				"Leaves by specific status and supervisor id");
	}

	public DataResult<List<LeaveDetail>> getByEmployee_Supervisor_SupervisorId(int supervisorId) {
		return new SuccessDataResult<List<LeaveDetail>>(
				this.leaveDetailRepository.getByEmployee_Supervisor_SupervisorId(supervisorId),
				"Leaves by specific supervisor id");
	}
																					
	public DataResult<List<LeaveDetail>> findByStartDateBetween(Date start, Date end) {
		return new SuccessDataResult<List<LeaveDetail>>(
				this.leaveDetailRepository.findByStartDateBetween(start, end), "Leaves between two dates");
	}
	
	public DataResult<List<LeaveDetail>> findByStartDateGreaterThan(Date startDate) {
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository.findByStartDateGreaterThan(startDate));
	} 
	
	public DataResult<List<LeaveDetail>> findByLeaveDurationGreaterThanAndEmployee_Id(float leaveDuration, int id) {
		return new SuccessDataResult<List<LeaveDetail>>(
				this.leaveDetailRepository.findByLeaveDurationGreaterThanAndEmployee_Id(leaveDuration, id),
				"List of leaves taken more than a certain day by employee id");
	}
	
	public DataResult<List<LeaveDetail>> findByStartDateLessThanAndEmployee_Supervisor_SupervisorId(Date startDate,int supervisorId) {
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository
				.findByStartDateLessThanAndEmployee_Supervisor_SupervisorId(startDate, supervisorId),
				"Leaves before the specific date which belong to specific supervisor");
	}
	
	public DataResult<List<LeaveDetail>> getByLeaveStatusAndStartDateLessThanAndEmployee_Id(LeaveStatus leaveStatus,
			Date startDate, int id) {
		return new SuccessDataResult<List<LeaveDetail>>(this.leaveDetailRepository
				.getByLeaveStatusAndStartDateLessThanAndEmployee_Id(leaveStatus, startDate, id),
				"List of leaves for a user before a certain date by status");
	}

	public DataResult<LeaveDetail> approveLeave(int leaveId) {
		Optional<LeaveDetail> leaveDetail = leaveDetailRepository.findById(leaveId);
		if(leaveDetail.isPresent()) {
			LeaveDetail approvedLeave = leaveDetail.get();
			approvedLeave.setLeaveStatus(LeaveStatus.APPROVE);
			leaveDetailRepository.save(approvedLeave);
			return new SuccessDataResult<LeaveDetail>(approvedLeave,"Leave is approved");
		}
		return null;
	}

	public DataResult<LeaveDetail> rejectLeave(int leaveId) {
		Optional<LeaveDetail> leaveDetail = leaveDetailRepository.findById(leaveId);
		if(leaveDetail.isPresent()) {
			LeaveDetail rejectedLeave = leaveDetail.get();
			rejectedLeave.setLeaveStatus(LeaveStatus.REJECT);
			leaveDetailRepository.save(rejectedLeave);
			return new SuccessDataResult<LeaveDetail>(rejectedLeave, "Leave is rejected");
		}	
		return null;
	}

	
}

