package fiap.com.br.graus.controllers;

import fiap.com.br.graus.dto.PageResponse;
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
    public PageResponse<ProdutoSummary> buscarPorNome(
            @RequestParam String nome,
            Pageable pageable) {

        Page<ProdutoSummary> page = service.getByNome(nome, pageable);

        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @GetMapping("/preco")
    public PageResponse<ProdutoSummary> buscarPorPreco(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max,
            Pageable pageable) {

        Page<ProdutoSummary> page = service.getByPrecoRange(min, max, pageable);

        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @GetMapping("/tamanho")
    public PageResponse<ProdutoSummary> buscarPorTamanho(
            @RequestParam String tamanho,
            Pageable pageable) {
        Page<ProdutoSummary> page = service.getByTamanho(tamanho, pageable);
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @GetMapping("/categoria")
    public PageResponse<ProdutoSummary> buscarPorCategoria(
            @RequestParam String categoria,
            Pageable pageable) {

        Page<ProdutoSummary> page = service.getByCategoria(categoria, pageable);

        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @GetMapping("/cor")
    public PageResponse<ProdutoSummary> buscarPorCor(
            @RequestParam String cor,
            Pageable pageable) {

        Page<ProdutoSummary> page = service.getByCor(cor, pageable);

        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }
}