package ucsal.edu.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucsal.edu.web.model.HistoricoCredito;

@Repository
public interface HistoricoCreditoRepository extends JpaRepository<HistoricoCredito, Long> {
	
}