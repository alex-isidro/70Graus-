package fiap.com.br.graus.services;

import fiap.com.br.graus.model.Estoque;
import fiap.com.br.graus.model.Funcionario;
import fiap.com.br.graus.model.MovimentacaoEstoque;
import fiap.com.br.graus.repositories.EstoqueRepository;
import fiap.com.br.graus.repositories.FuncionarioRepository;
import fiap.com.br.graus.repositories.MovimentacaoEstoqueRepository;
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
public class MovimentacaoEstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private MovimentacaoEstoqueRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private MovimentacaoEstoque findMovimentacaoById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Movimentação com id " + id + " não encontrada")
        );
    }

    @Cacheable(value = "movimentacoesLista")
    public List<MovimentacaoEstoque> findAll() {
        return repository.findAll();
    }

    @Cacheable(value = "movimentacoesPaginadas", key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    public Page<MovimentacaoEstoque> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @CacheEvict(value = {
            "movimentacoes", "movimentacoesLista", "movimentacoesPaginadas",
            "estoques", "estoquesLista", "estoquesPaginados"
    }, allEntries = true)
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

    @Cacheable(value = "movimentacoes", key = "#id")
    public MovimentacaoEstoque findById(Long id){
        return findMovimentacaoById(id);
    }

    @CacheEvict(value = {"movimentacoes", "movimentacoesLista", "movimentacoesPaginadas"}, allEntries = true)
    public void delete(Long id) {
        findMovimentacaoById(id);
        repository.deleteById(id);
    }

    @CacheEvict(value = {"movimentacoes", "movimentacoesLista", "movimentacoesPaginadas"}, allEntries = true)
    public MovimentacaoEstoque update(Long id, MovimentacaoEstoque newMovimentacao) {
        findMovimentacaoById(id);
        newMovimentacao.setId(id);
        return repository.save(newMovimentacao);
    }
}
