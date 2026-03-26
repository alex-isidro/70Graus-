package fiap.com.br.graus.controllers;

import fiap.com.br.graus.model.Funcionario;
import fiap.com.br.graus.services.FuncionarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("usuario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping
    public List<Funcionario> listAll(){
        return service.getAllUsuario();
    }

    @PostMapping
    public ResponseEntity<Funcionario> createUsuario(@RequestBody @Valid Funcionario funcionario){
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(service.addUsuario(funcionario));
    }

    @GetMapping("{id}")
    public ResponseEntity<Funcionario> getUsuarioById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUsuarioById(id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        service.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Funcionario> updateUsuario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        return ResponseEntity.ok(service.updateUsuario(id, funcionario));
    }
}
