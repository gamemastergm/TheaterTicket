package ucsal.edu.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucsal.edu.web.model.Titular;

@Repository
public interface TitularRepository extends JpaRepository<Titular, Long> {
	
}