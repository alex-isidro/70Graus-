package fiap.com.br.graus.controllers;

import fiap.com.br.graus.model.Produto;
import fiap.com.br.graus.services.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @GetMapping("/nome")
    public List<Produto> buscarPorNome(@RequestParam String nome) {
        return service.getByNome(nome);
    }

    @GetMapping("/preco")
    public List<Produto> buscarPorPreco(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return service.getByPrecoRange(min, max);
    }

    @GetMapping("/tamanho")
    public List<Produto> buscarPorTamanho(@RequestParam String tamanho) {
        return service.getByTamanho(tamanho);
    }
    @GetMapping("/categoria")
    public List<Produto> buscarPorCategoria(@RequestParam String categoria) {
        return service.getByCategoria(categoria);
    }

    @GetMapping("/cor")
    public List<Produto> buscarPorCor(@RequestParam String cor) {
        return service.getByCor(cor);
    }
}