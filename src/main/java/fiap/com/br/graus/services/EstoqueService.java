package fiap.com.br.graus.services;

import fiap.com.br.graus.model.Estoque;
import fiap.com.br.graus.repositories.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository repository;

    private Estoque findEstoqueById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Estoque com id " + id + " não encontrado")
        );
    }

    @Cacheable(value = "estoquesLista")
    public List<Estoque> findAll() {
        return repository.findAll();
    }

    @Cacheable(value = "estoquesPaginados", key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    public Page<Estoque> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @CacheEvict(value = {"estoques", "estoquesLista", "estoquesPaginados", "produtosLista", "produtosPaginados"}, allEntries = true)
    public Estoque add(Estoque estoque){
        return repository.save(estoque);
    }

    @Cacheable(value = "estoques", key = "#id")
    public Estoque findById(Long id){
        return findEstoqueById(id);
    }

    @CacheEvict(value = {"estoques", "estoquesLista", "estoquesPaginados", "produtosLista", "produtosPaginados"}, allEntries = true)
    public void delete(Long id) {
        findEstoqueById(id);
        repository.deleteById(id);
    }

    @CacheEvict(value = {"estoques", "estoquesLista", "estoquesPaginados", "produtosLista", "produtosPaginados"}, allEntries = true)
    public Estoque update(Long id, Estoque newEstoque) {
        findEstoqueById(id);
        newEstoque.setId(id);
        return repository.save(newEstoque);
    }
}
