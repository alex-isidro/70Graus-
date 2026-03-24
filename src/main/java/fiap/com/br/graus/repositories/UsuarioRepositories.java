package fiap.com.br.graus.repositories;

import fiap.com.br.graus.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositories extends JpaRepository<Usuario, Long> {
}
