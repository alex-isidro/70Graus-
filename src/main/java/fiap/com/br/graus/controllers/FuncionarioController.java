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
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping
    public List<Funcionario> listAll(){
        return service.getAllFuncionario();
    }

    @PostMapping
    public ResponseEntity<Funcionario> createFuncionario(@RequestBody @Valid Funcionario funcionario){
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(service.addFuncionario(funcionario));
    }

    @GetMapping("{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFuncionarioById(id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        service.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        return ResponseEntity.ok(service.updateFuncionario(id, funcionario));
    }
}
