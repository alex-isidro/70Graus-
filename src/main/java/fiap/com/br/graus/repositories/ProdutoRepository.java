package fiap.com.br.graus.repositories;

import fiap.com.br.graus.model.Produto;
import fiap.com.br.graus.projection.ProdutoSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Page<ProdutoSummary> findByTamanhoIgnoreCase(String tamanho, Pageable pageable);

    Page<ProdutoSummary> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<ProdutoSummary> findByPrecoBetween(BigDecimal min, BigDecimal max, Pageable pageable);

    Page<ProdutoSummary> findByCategoriaIgnoreCase(String categoria, Pageable pageable);

    Page<ProdutoSummary> findByCorIgnoreCase(String cor, Pageable pageable);
}
