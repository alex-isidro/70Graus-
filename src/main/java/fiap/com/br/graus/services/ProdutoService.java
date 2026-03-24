package fiap.com.br.graus.services;

import fiap.com.br.graus.model.Produto;
import fiap.com.br.graus.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
}