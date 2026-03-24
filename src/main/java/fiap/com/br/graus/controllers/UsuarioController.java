package fiap.com.br.graus.controllers;

import fiap.com.br.graus.model.Usuario;
import fiap.com.br.graus.services.UsuarioServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServices service;

    @GetMapping
    public List<Usuario> listAll(){
        return service.getAllUsuario();
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(service.addUsuario(usuario));
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUsuarioById(id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        service.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(service.updateUsuario(id, usuario));
    }
}
