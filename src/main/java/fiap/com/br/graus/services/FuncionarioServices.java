package fiap.com.br.graus.services;

import fiap.com.br.graus.model.Funcionario;
import fiap.com.br.graus.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioServices {
    @Autowired
    private FuncionarioRepository repository;

    private Funcionario findUsuarioById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Despesas com id " + id + " não encontrado")
        );
    }

    public List<Funcionario> getAllUsuario(){
        return repository.findAll();
    }

    public Funcionario addUsuario(Funcionario funcionario){
        return repository.save(funcionario);
    }

    public Funcionario getUsuarioById(Long id){
        return findUsuarioById(id);
    }

    public void deleteUsuario(Long id) {
        findUsuarioById(id);
        repository.deleteById(id);
    }

    public Funcionario updateUsuario(Long id, Funcionario newFuncionario) {
        findUsuarioById(id);
        newFuncionario.setId(id);
        return repository.save(newFuncionario);
    }
}
