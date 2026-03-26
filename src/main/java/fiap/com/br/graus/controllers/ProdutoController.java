package fiap.com.br.graus.controllers;

import fiap.com.br.graus.model.Produto;
import fiap.com.br.graus.services.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
@Slf4j
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> listAll() {
        return service.getAllProdutos();
    }

    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addProduto(produto));
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        log.info("Obtendo dados do produto {}", id);
        return ResponseEntity.ok(service.getProdutoById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        log.info("Deletando produto com id {}", id);
        service.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
        log.info("Atualizando produto com id {} com os dados {}", id, produto);
        return ResponseEntity.ok(service.updateProduto(id, produto));
    }
}