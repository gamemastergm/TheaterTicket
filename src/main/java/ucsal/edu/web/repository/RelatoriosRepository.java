package ucsal.edu.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucsal.edu.web.model.Relatorios;

@Repository
public interface RelatoriosRepository extends JpaRepository<Relatorios, Long> {
	
}