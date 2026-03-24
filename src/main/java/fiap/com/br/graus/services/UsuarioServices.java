package fiap.com.br.graus.services;

import fiap.com.br.graus.model.Usuario;
import fiap.com.br.graus.repositories.UsuarioRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServices {
    @Autowired
    private UsuarioRepositories repositories;

    private Usuario findUsuarioById(Long id) {
        return repositories.findById(id).orElseThrow(
                () -> new RuntimeException("Despesas com id " + id + " não encontrado")
        );
    }

    public List<Usuario> getAllUsuario(){
        return repositories.findAll();
    }

    public Usuario addUsuario(Usuario usuario){
        return repositories.save(usuario);
    }

    public Usuario getUsuarioById(Long id){
        return findUsuarioById(id);
    }

    public void deleteUsuario(Long id) {
        findUsuarioById(id);
        repositories.deleteById(id);
    }

    public Usuario updateUsuario(Long id, Usuario newUsuario) {
        findUsuarioById(id);
        newUsuario.setId(id);
        return repositories.save(newUsuario);
    }
}
