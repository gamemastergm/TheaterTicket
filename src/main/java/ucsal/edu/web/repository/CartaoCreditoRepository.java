package ucsal.edu.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucsal.edu.web.model.CartaoCredito;
import ucsal.edu.web.model.Titular;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {
	
}