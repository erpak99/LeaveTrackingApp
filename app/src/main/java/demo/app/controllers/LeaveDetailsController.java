package demo.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.app.core.DataResult;
import demo.app.core.Result;
import demo.app.core.status.LeaveStatus;
import demo.app.entities.LeaveDetail;
import demo.app.entities.dtos.LeaveDetailWithEmployeeDto;
import demo.app.entities.dtos.LeaveDurationWithEmployeeDto;
//import demo.app.requests.ApproveRejectRequest;
import demo.app.services.LeaveDetailService;

@RestController
@RequestMapping("/leavedetails")
public class LeaveDetailsController {

	private LeaveDetailService leaveDetailService;

	@Autowired
	public LeaveDetailsController(LeaveDetailService leaveDetailService) {
		this.leaveDetailService = leaveDetailService;
		
	}
	
	@GetMapping("/getall")
	public DataResult<List<LeaveDetail>> getAllLeaves() {
		return this.leaveDetailService.getAllLeaves();
	}
	
	@GetMapping("/{leaveId}")
	public DataResult<LeaveDetail> getOneLeaveById(@PathVariable int leaveId) {
		return this.leaveDetailService.getOneLeaveById(leaveId);
	}
	
	
	@GetMapping("/getbyleavedescription")
	public DataResult<List<LeaveDetail>> getByLeaveDescription(@RequestParam String leaveDescription) {
		return this.leaveDetailService.getByLeaveDescription(leaveDescription);
	}
	
	@GetMapping("/getbyleavestatus")
	public DataResult<List<LeaveDetail>> getByStatus(@RequestParam LeaveStatus leaveStatus) {
		return this.leaveDetailService.getByLeaveStatus(leaveStatus);
	}
	
	@PostMapping("/create")
	public Result createLeaveRequest(@RequestBody LeaveDetail leaveDetail) {
		return this.leaveDetailService.createLeaveRequest(leaveDetail); 

	}
	
	@GetMapping("/getallstartdatesasc")
	public DataResult<List<LeaveDetail>> getAllSorted() {
		return this.leaveDetailService.getAllSorted();
	}

	@GetMapping("/getleavedetailswithemployee")
	public DataResult<List<LeaveDetailWithEmployeeDto>> getLeaveDetailsWithEmployee() {
		return this.leaveDetailService.getLeaveDetailsWithEmployee();
	}

	@GetMapping("/getleavedetailsbydurationandemail")
	public DataResult<List<LeaveDetail>> getByLeaveDurationAndEmployee_Email(@RequestParam float leaveDuration, @RequestParam String email) {
		return this.leaveDetailService.getByLeaveDurationAndEmployee_Email(leaveDuration, email);
	}

	@GetMapping("/getleavedurationandstatuswithemployee")
	public DataResult<List<LeaveDurationWithEmployeeDto>> getLeaveDurationWithEmployeeDto() {
		return this.leaveDetailService.getLeaveDurationWithEmployeeDto();
	}
	
	@GetMapping("/getleavedetailsbystatusandid")
	public DataResult<List<LeaveDetail>> getByLeaveStatusAndEmployee_Id(@RequestParam LeaveStatus leaveStatus, @RequestParam int id) {
		return this.leaveDetailService.getByLeaveStatusAndEmployee_Id(leaveStatus, id);
	}
	
	@GetMapping("/getleavedetailsbyid") 
	public DataResult<List<LeaveDetail>> getByEmployee_Id(@RequestParam int id) {
		return this.leaveDetailService.getByEmployee_Id(id);
	}
	
	@GetMapping("/getleavesdetailsbysupervisorid")
	public DataResult<List<LeaveDetail>> getByLeaveStatusAndEmployee_Supervisor_SupervisorId(@RequestParam LeaveStatus leaveStatus, @RequestParam int supervisorId) {
		return this.leaveDetailService.getByLeaveStatusAndEmployee_Supervisor_SupervisorId(leaveStatus, supervisorId);
	}
	
	
}
	
