package ucsal.edu.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucsal.edu.web.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
	
}