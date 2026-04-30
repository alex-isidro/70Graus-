package fiap.com.br.graus.controllers;

import fiap.com.br.graus.model.Produto;
import fiap.com.br.graus.projection.ProdutoSummary;
import fiap.com.br.graus.services.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<ProdutoSummary> buscarPorNome(
            @RequestParam String nome,
            Pageable pageable) {
        return service.getByNome(nome, pageable);
    }

    @GetMapping("/preco")
    public Page<ProdutoSummary> buscarPorPreco(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max,
            Pageable pageable) {
        return service.getByPrecoRange(min, max, pageable);
    }

    @GetMapping("/tamanho")
    public Page<ProdutoSummary> buscarPorTamanho(
            @RequestParam String tamanho,
            Pageable pageable) {
        return service.getByTamanho(tamanho, pageable);
    }

    @GetMapping("/categoria")
    public Page<ProdutoSummary> buscarPorCategoria(
            @RequestParam String categoria,
            Pageable pageable) {
        return service.getByCategoria(categoria, pageable);
    }

    @GetMapping("/cor")
    public Page<ProdutoSummary> buscarPorCor(
            @RequestParam String cor,
            Pageable pageable) {
        return service.getByCor(cor, pageable);
    }
}