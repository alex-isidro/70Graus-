package fiap.com.br.graus.repositories;

import fiap.com.br.graus.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByPrecoBetween(BigDecimal min, BigDecimal max);

    List<Produto> findByNomeContainingIgnoreCase(String nome);

    List<Produto> findByTamanhoIgnoreCase(String tamanho);

    List<Produto> findByCategoriaIgnoreCase(String categoria);

    List<Produto> findByCorIgnoreCase(String cor);
}
