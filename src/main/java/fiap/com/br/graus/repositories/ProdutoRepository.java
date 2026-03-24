package fiap.com.br.graus.repositories;

import fiap.com.br.graus.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
