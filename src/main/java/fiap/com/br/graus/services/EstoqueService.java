package fiap.com.br.graus.services;

import fiap.com.br.graus.model.Estoque;
import fiap.com.br.graus.repositories.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository repository;

    private Estoque findUsuarioById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "ID " + id + " não encontrado")
        );
    }

    public List<Estoque> findAll() {
        return repository.findAll();
    }

    public Estoque add(Estoque estoque){
        return repository.save(estoque);
    }
    public Estoque findById(Long id){
        return findUsuarioById(id);
    }

    public void delete(Long id) {
        findUsuarioById(id);
        repository.deleteById(id);
    }

    public Estoque update(Long id, Estoque newEstoque) {
        findUsuarioById(id);
        newEstoque.setId(id);
        return repository.save(newEstoque);
    }

}
