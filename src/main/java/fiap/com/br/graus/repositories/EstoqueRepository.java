package fiap.com.br.graus.repositories;

import fiap.com.br.graus.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}
