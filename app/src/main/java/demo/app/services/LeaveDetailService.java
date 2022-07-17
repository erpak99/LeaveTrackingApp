package demo.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.app.core.DataResult;
import demo.app.core.Result;
import demo.app.core.SuccessDataResult;
import demo.app.core.SuccessResult;
import demo.app.entities.LeaveDetail;
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
		this.leaveDetailRepository.save(leaveDetail);
		return new SuccessResult("Leave is added");
	}

	public DataResult<LeaveDetail> getOneLeaveById(int leaveId) {
		return new SuccessDataResult<LeaveDetail>( this.leaveDetailRepository.findById(leaveId).orElseThrow(null),"Leave by specific id");
	}
	
	
	
}
