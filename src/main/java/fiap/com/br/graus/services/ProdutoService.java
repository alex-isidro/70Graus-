package fiap.com.br.graus.services;

import fiap.com.br.graus.model.Produto;
import fiap.com.br.graus.projection.ProdutoSummary; // 🔥 IMPORTANTE
import fiap.com.br.graus.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> getAllProdutos() {
        return repository.findAll();
    }

    public Produto addProduto(Produto produto) {
        return repository.save(produto);
    }

    public Produto getProdutoById(Long id) {
        return findProdutoById(id);
    }

    public void deleteProduto(Long id) {
        findProdutoById(id);
        repository.deleteById(id);
    }

    public Produto updateProduto(Long id, Produto newProduto) {
        findProdutoById(id);
        newProduto.setId(id);
        return repository.save(newProduto);
    }

    private Produto findProdutoById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto com id " + id + " não encontrado")
        );
    }

    public Page<ProdutoSummary> getByNome(String nome, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Page<ProdutoSummary> getByPrecoRange(BigDecimal min, BigDecimal max, Pageable pageable) {
        return repository.findByPrecoBetween(min, max, pageable);
    }

    public Page<ProdutoSummary> getByTamanho(String tamanho, Pageable pageable) {
        return repository.findByTamanhoIgnoreCase(tamanho, pageable);
    }

    public Page<ProdutoSummary> getByCategoria(String categoria, Pageable pageable) {
        return repository.findByCategoriaIgnoreCase(categoria, pageable);
    }

    public Page<ProdutoSummary> getByCor(String cor, Pageable pageable) {
        return repository.findByCorIgnoreCase(cor, pageable);
    }
}