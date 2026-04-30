package fiap.com.br.graus.repositories;

import fiap.com.br.graus.model.Produto;
import fiap.com.br.graus.projection.ProdutoSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("""
    SELECT 
            p.id AS id,
            p.nome AS nome,
            p.preco AS preco,
            p.tamanho AS tamanho,
            p.ativo AS ativo
        FROM Produto p
        WHERE LOWER(p.tamanho) = LOWER(:tamanho)
    """)
    Page<ProdutoSummary> findByTamanhoIgnoreCase(@Param("tamanho") String tamanho, Pageable pageable);


    @Query("""
    SELECT 
        p.id AS id,
        p.nome AS nome,
        p.preco AS preco,
        p.tamanho AS tamanho,
        p.ativo AS ativo
    FROM Produto p
    WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))
""")
    Page<ProdutoSummary> findByNomeContainingIgnoreCase(@Param("nome") String nome, Pageable pageable);

    @Query("""
    SELECT 
        p.id AS id,
        p.nome AS nome,
        p.preco AS preco,
        p.tamanho AS tamanho,
        p.ativo AS ativo
    FROM Produto p
    WHERE p.preco BETWEEN :min AND :max
""")
    Page<ProdutoSummary> findByPrecoBetween(@Param("min") BigDecimal min, @Param("max") BigDecimal max, Pageable pageable);

    @Query("""
    SELECT 
        p.id AS id,
        p.nome AS nome,
        p.preco AS preco,
        p.tamanho AS tamanho,
        p.ativo AS ativo
    FROM Produto p
    WHERE LOWER(p.categoria) = LOWER(:categoria)
""")
    Page<ProdutoSummary> findByCategoriaIgnoreCase(@Param("categoria") String categoria, Pageable pageable);


    @Query("""
    SELECT 
        p.id AS id,
        p.nome AS nome,
        p.preco AS preco,
        p.tamanho AS tamanho,
        p.ativo AS ativo
    FROM Produto p
    WHERE LOWER(p.cor) = LOWER(:cor)
""")
    Page<ProdutoSummary> findByCorIgnoreCase(@Param("cor") String cor, Pageable pageable);
}
