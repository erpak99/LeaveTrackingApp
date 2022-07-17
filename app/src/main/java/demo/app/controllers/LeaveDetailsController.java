package demo.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.app.core.DataResult;
import demo.app.core.Result;
import demo.app.entities.LeaveDetail;
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
	
	@PostMapping("/create")
	public Result createLeaveRequest(@RequestBody LeaveDetail leaveDetail) {
		return this.leaveDetailService.createLeaveRequest(leaveDetail);
	}
	
	
}
