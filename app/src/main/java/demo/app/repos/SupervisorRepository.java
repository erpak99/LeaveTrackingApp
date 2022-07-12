package demo.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.app.entities.Supervisor;

public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {

	Supervisor findByEmail(String email);

}
