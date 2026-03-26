package fiap.com.br.graus.controllers;

import fiap.com.br.graus.model.MovimentacaoEstoque;
import fiap.com.br.graus.services.MovimentacaoEstoqueService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("movi-estoque")
@Slf4j
public class movimentacaoEstoqueController {

    @Autowired
    private MovimentacaoEstoqueService service;

    @GetMapping
    public List<MovimentacaoEstoque> listAll(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<MovimentacaoEstoque> create(@RequestBody @Valid MovimentacaoEstoque movimentacaoEstoque){
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(service.add(movimentacaoEstoque));
    }

    @GetMapping("{id}")
    public ResponseEntity<MovimentacaoEstoque> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<MovimentacaoEstoque> update(@PathVariable Long id, @RequestBody MovimentacaoEstoque movimentacaoEstoque) {
        return ResponseEntity.ok(service.update(id, movimentacaoEstoque));
    }
}
