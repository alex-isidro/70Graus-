package fiap.com.br.graus.services;

import fiap.com.br.graus.model.Funcionario;
import fiap.com.br.graus.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Funcionario> getAllFuncionario(){
        return repository.findAll();
    }

    public Funcionario addFuncionario(Funcionario funcionario){
        return repository.save(funcionario);
    }

    public Funcionario getFuncionarioById(Long id){
        return findFuncionarioById(id);
    }

    public void deleteFuncionario(Long id) {
        findFuncionarioById(id);
        repository.deleteById(id);
    }

    public Funcionario updateFuncionario(Long id, Funcionario newFuncionario) {
        findFuncionarioById(id);
        newFuncionario.setId(id);
        return repository.save(newFuncionario);
    }
}
