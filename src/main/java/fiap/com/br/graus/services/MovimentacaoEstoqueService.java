package fiap.com.br.graus.services;


import fiap.com.br.graus.model.Estoque;
import fiap.com.br.graus.model.MovimentacaoEstoque;
import fiap.com.br.graus.repositories.EstoqueRepository;
import fiap.com.br.graus.repositories.MovimentacaoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import fiap.com.br.graus.repositories.FuncionarioRepository;
import fiap.com.br.graus.model.Funcionario;

import java.util.List;

@Service
public class MovimentacaoEstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private MovimentacaoEstoqueRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private MovimentacaoEstoque findmovimentacaoById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "ID " + id + " não encontrado")
        );
    }

    public List<MovimentacaoEstoque> findAll() {
        return repository.findAll();
    }

    public MovimentacaoEstoque add(MovimentacaoEstoque movimentacao) {
        Estoque estoque = estoqueRepository.findById(
                movimentacao.getEstoque().getId()
        ).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Estoque não encontrado"
        ));

        Funcionario funcionario = funcionarioRepository.findById(
                movimentacao.getFuncionario().getId()
        ).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Funcionário não encontrado"
        ));

        estoque.movimentar(
                movimentacao.getQuantidade(),
                movimentacao.getTipoMovimentacao().equalsIgnoreCase("ENTRADA")
        );

        estoqueRepository.save(estoque);

        movimentacao.setEstoque(estoque);
        movimentacao.setFuncionario(funcionario);

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
