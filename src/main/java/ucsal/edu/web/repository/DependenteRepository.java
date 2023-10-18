package ucsal.edu.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucsal.edu.web.model.Dependente;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Long> {
	
}