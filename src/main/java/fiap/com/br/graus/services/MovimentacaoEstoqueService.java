package fiap.com.br.graus.services;


import fiap.com.br.graus.model.MovimentacaoEstoque;
import fiap.com.br.graus.repositories.MovimentacaoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MovimentacaoEstoqueService {
    @Autowired
    private MovimentacaoEstoqueRepository repository;

    private MovimentacaoEstoque findmovimentacaoById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "ID " + id + " não encontrado")
        );
    }

    public List<MovimentacaoEstoque> findAll() {
        return repository.findAll();
    }

    public MovimentacaoEstoque Add(MovimentacaoEstoque movimentacao){
        return repository.save(movimentacao);
    }
    public MovimentacaoEstoque findById(Long id){
        return findmovimentacaoById(id);
    }

    public void delete(Long id) {
        findmovimentacaoById(id);
        repository.deleteById(id);
    }

    public MovimentacaoEstoque update(Long id, MovimentacaoEstoque newMovimentacao) {
        findmovimentacaoById(id);
        newMovimentacao.setId(id);
        return repository.save(newMovimentacao);
    }
}
