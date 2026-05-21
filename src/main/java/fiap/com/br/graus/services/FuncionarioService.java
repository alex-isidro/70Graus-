package fiap.com.br.graus.services;

import fiap.com.br.graus.model.Funcionario;
import fiap.com.br.graus.repositories.FuncionarioRepository;
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
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    private Funcionario findFuncionarioById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Funcionario com id " + id + " não encontrado")
        );
    }

    @Cacheable(value = "funcionariosLista")
    public List<Funcionario> getAllFuncionario(){
        return repository.findAll();
    }

    @Cacheable(value = "funcionariosPaginados", key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    public Page<Funcionario> getAllFuncionario(Pageable pageable){
        return repository.findAll(pageable);
    }

    @CacheEvict(value = {"funcionarios", "funcionariosLista", "funcionariosPaginados"}, allEntries = true)
    public Funcionario addFuncionario(Funcionario funcionario){
        return repository.save(funcionario);
    }

    @Cacheable(value = "funcionarios", key = "#id")
    public Funcionario getFuncionarioById(Long id){
        return findFuncionarioById(id);
    }

    @CacheEvict(value = {"funcionarios", "funcionariosLista", "funcionariosPaginados"}, allEntries = true)
    public void deleteFuncionario(Long id) {
        findFuncionarioById(id);
        repository.deleteById(id);
    }

    @CacheEvict(value = {"funcionarios", "funcionariosLista", "funcionariosPaginados"}, allEntries = true)
    public Funcionario updateFuncionario(Long id, Funcionario newFuncionario) {
        findFuncionarioById(id);
        newFuncionario.setId(id);
        return repository.save(newFuncionario);
    }
}
