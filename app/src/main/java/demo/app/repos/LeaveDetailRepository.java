package demo.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.app.entities.LeaveDetail;

public interface LeaveDetailRepository extends JpaRepository<LeaveDetail, Integer> {
	
}
