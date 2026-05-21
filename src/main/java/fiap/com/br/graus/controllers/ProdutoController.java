package fiap.com.br.graus.controllers;

import fiap.com.br.graus.dto.PageResponse;
import fiap.com.br.graus.model.Produto;
import fiap.com.br.graus.projection.ProdutoSummary;
import fiap.com.br.graus.services.ProdutoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("produtos")
@Slf4j
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public CollectionModel<EntityModel<Produto>> listAll() {
        List<EntityModel<Produto>> produtos = service.getAllProdutos()
                .stream()
                .map(this::toModel)
                .toList();

        return CollectionModel.of(produtos,
                linkTo(methodOn(ProdutoController.class).listAll()).withSelfRel(),
                linkTo(methodOn(ProdutoController.class).listarPaginado(null)).withRel("paginado"));
    }

    @GetMapping("/paginado")
    public ResponseEntity<PagedModel<EntityModel<Produto>>> listarPaginado(
            @PageableDefault(size = 5, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Produto> page = service.getAllProdutosPaginado(pageable);

        List<EntityModel<Produto>> produtos = page.getContent()
                .stream()
                .map(this::toModel)
                .toList();

        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
                page.getSize(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages()
        );

        PagedModel<EntityModel<Produto>> model = PagedModel.of(produtos, metadata,
                linkTo(methodOn(ProdutoController.class).listarPaginado(pageable)).withSelfRel(),
                linkTo(methodOn(ProdutoController.class).listAll()).withRel("todos-produtos"));

        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Produto>> createProduto(@RequestBody @Valid Produto produto) {
        Produto salvo = service.addProduto(produto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toModel(salvo));
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<Produto>> getProdutoById(@PathVariable Long id) {
        log.info("Obtendo dados do produto {}", id);
        return ResponseEntity.ok(toModel(service.getProdutoById(id)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        log.info("Deletando produto com id {}", id);
        service.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Produto>> updateProduto(@PathVariable Long id, @RequestBody @Valid Produto produto) {
        log.info("Atualizando produto com id {} com os dados {}", id, produto);
        return ResponseEntity.ok(toModel(service.updateProduto(id, produto)));
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

    private EntityModel<Produto> toModel(Produto produto) {
        return EntityModel.of(produto,
                linkTo(methodOn(ProdutoController.class).getProdutoById(produto.getId())).withSelfRel(),
                linkTo(methodOn(ProdutoController.class).listAll()).withRel("produtos"),
                linkTo(methodOn(ProdutoController.class).listarPaginado(null)).withRel("produtos-paginados"),
                linkTo(methodOn(EstoqueController.class).listAll()).withRel("estoques"));
    }
}
