package fiap.com.br.graus.services;

import fiap.com.br.graus.model.Produto;
import fiap.com.br.graus.projection.ProdutoSummary;
import fiap.com.br.graus.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "produtosPaginados", key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    public Page<Produto> getAllProdutosPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Cacheable(value = "produtosLista")
    public List<Produto> getAllProdutos() {
        return repository.findAll();
    }

    @CacheEvict(value = {"produtos", "produtosLista", "produtosPaginados", "produtosBusca"}, allEntries = true)
    public Produto addProduto(Produto produto) {
        return repository.save(produto);
    }

    @Cacheable(value = "produtos", key = "#id")
    public Produto getProdutoById(Long id) {
        return findProdutoById(id);
    }

    @CacheEvict(value = {"produtos", "produtosLista", "produtosPaginados", "produtosBusca"}, allEntries = true)
    public void deleteProduto(Long id) {
        findProdutoById(id);
        repository.deleteById(id);
    }

    @CacheEvict(value = {"produtos", "produtosLista", "produtosPaginados", "produtosBusca"}, allEntries = true)
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

    @Cacheable(value = "produtosBusca", key = "'nome-' + #nome + '-' + #pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    public Page<ProdutoSummary> getByNome(String nome, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    @Cacheable(value = "produtosBusca", key = "'preco-' + #min + '-' + #max + '-' + #pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    public Page<ProdutoSummary> getByPrecoRange(BigDecimal min, BigDecimal max, Pageable pageable) {
        return repository.findByPrecoBetween(min, max, pageable);
    }

    @Cacheable(value = "produtosBusca", key = "'tamanho-' + #tamanho + '-' + #pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    public Page<ProdutoSummary> getByTamanho(String tamanho, Pageable pageable) {
        return repository.findByTamanhoIgnoreCase(tamanho, pageable);
    }

    @Cacheable(value = "produtosBusca", key = "'categoria-' + #categoria + '-' + #pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    public Page<ProdutoSummary> getByCategoria(String categoria, Pageable pageable) {
        return repository.findByCategoriaIgnoreCase(categoria, pageable);
    }

    @Cacheable(value = "produtosBusca", key = "'cor-' + #cor + '-' + #pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    public Page<ProdutoSummary> getByCor(String cor, Pageable pageable) {
        return repository.findByCorIgnoreCase(cor, pageable);
    }
}
