package fiap.com.br.graus.services;

import fiap.com.br.graus.model.Funcionario;
import fiap.com.br.graus.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServices {
    @Autowired
    private UsuarioRepository repositoriy;

    private Funcionario findUsuarioById(Long id) {
        return repositoriy.findById(id).orElseThrow(
                () -> new RuntimeException("Despesas com id " + id + " não encontrado")
        );
    }

    public List<Funcionario> getAllUsuario(){
        return repositoriy.findAll();
    }

    public Funcionario addUsuario(Funcionario funcionario){
        return repositoriy.save(funcionario);
    }

    public Funcionario getUsuarioById(Long id){
        return findUsuarioById(id);
    }

    public void deleteUsuario(Long id) {
        findUsuarioById(id);
        repositoriy.deleteById(id);
    }

    public Funcionario updateUsuario(Long id, Funcionario newFuncionario) {
        findUsuarioById(id);
        newFuncionario.setId(id);
        return repositoriy.save(newFuncionario);
    }
}
