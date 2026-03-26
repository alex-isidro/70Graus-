package fiap.com.br.graus.controllers;

import fiap.com.br.graus.model.Estoque;
import fiap.com.br.graus.services.EstoqueService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estoque")
@Slf4j
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @GetMapping
    public List<Estoque> listAll(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Estoque> createEstoque(@RequestBody @Valid Estoque estoque){
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(service.add(estoque));
    }

    @GetMapping("{id}")
    public ResponseEntity<Estoque> getFuncionarioById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Estoque> updateFuncionario(@PathVariable Long id, @RequestBody Estoque estoque) {
        return ResponseEntity.ok(service.update(id, estoque));
    }
}
