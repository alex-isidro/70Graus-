package fiap.com.br.graus.controllers;

import fiap.com.br.graus.model.Estoque;
import fiap.com.br.graus.services.EstoqueService;
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

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("estoque")
@Slf4j
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @GetMapping
    public CollectionModel<EntityModel<Estoque>> listAll(){
        List<EntityModel<Estoque>> estoques = service.findAll()
                .stream()
                .map(this::toModel)
                .toList();

        return CollectionModel.of(estoques,
                linkTo(methodOn(EstoqueController.class).listAll()).withSelfRel(),
                linkTo(methodOn(EstoqueController.class).listarPaginado(null)).withRel("paginado"));
    }

    @GetMapping("/paginado")
    public ResponseEntity<PagedModel<EntityModel<Estoque>>> listarPaginado(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Estoque> page = service.findAll(pageable);

        List<EntityModel<Estoque>> estoques = page.getContent()
                .stream()
                .map(this::toModel)
                .toList();

        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
                page.getSize(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages()
        );

        return ResponseEntity.ok(PagedModel.of(estoques, metadata,
                linkTo(methodOn(EstoqueController.class).listarPaginado(pageable)).withSelfRel(),
                linkTo(methodOn(EstoqueController.class).listAll()).withRel("todos-estoques")));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Estoque>> createEstoque(@RequestBody @Valid Estoque estoque){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toModel(service.add(estoque)));
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<Estoque>> getEstoqueById(@PathVariable Long id) {
        return ResponseEntity.ok(toModel(service.findById(id)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEstoque(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Estoque>> updateEstoque(
            @PathVariable Long id,
            @RequestBody @Valid Estoque estoque
    ) {
        return ResponseEntity.ok(toModel(service.update(id, estoque)));
    }

    private EntityModel<Estoque> toModel(Estoque estoque) {
        EntityModel<Estoque> model = EntityModel.of(estoque,
                linkTo(methodOn(EstoqueController.class).getEstoqueById(estoque.getId())).withSelfRel(),
                linkTo(methodOn(EstoqueController.class).listAll()).withRel("estoques"),
                linkTo(methodOn(EstoqueController.class).listarPaginado(null)).withRel("estoques-paginados"));

        if (estoque.getProduto() != null && estoque.getProduto().getId() != null) {
            model.add(linkTo(methodOn(ProdutoController.class).getProdutoById(estoque.getProduto().getId())).withRel("produto"));
        }

        return model;
    }
}
