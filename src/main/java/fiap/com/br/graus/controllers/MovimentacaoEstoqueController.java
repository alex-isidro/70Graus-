package fiap.com.br.graus.controllers;

import fiap.com.br.graus.model.MovimentacaoEstoque;
import fiap.com.br.graus.services.MovimentacaoEstoqueService;
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
@RequestMapping("movi-estoque")
@Slf4j
public class MovimentacaoEstoqueController {

    @Autowired
    private MovimentacaoEstoqueService service;

    @GetMapping
    public CollectionModel<EntityModel<MovimentacaoEstoque>> listAll(){
        List<EntityModel<MovimentacaoEstoque>> movimentacoes = service.findAll()
                .stream()
                .map(this::toModel)
                .toList();

        return CollectionModel.of(movimentacoes,
                linkTo(methodOn(MovimentacaoEstoqueController.class).listAll()).withSelfRel(),
                linkTo(methodOn(MovimentacaoEstoqueController.class).listarPaginado(null)).withRel("paginado"));
    }

    @GetMapping("/paginado")
    public ResponseEntity<PagedModel<EntityModel<MovimentacaoEstoque>>> listarPaginado(
            @PageableDefault(size = 5, sort = "dataMovimentacao", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<MovimentacaoEstoque> page = service.findAll(pageable);

        List<EntityModel<MovimentacaoEstoque>> movimentacoes = page.getContent()
                .stream()
                .map(this::toModel)
                .toList();

        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
                page.getSize(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages()
        );

        return ResponseEntity.ok(PagedModel.of(movimentacoes, metadata,
                linkTo(methodOn(MovimentacaoEstoqueController.class).listarPaginado(pageable)).withSelfRel(),
                linkTo(methodOn(MovimentacaoEstoqueController.class).listAll()).withRel("todas-movimentacoes")));
    }

    @PostMapping
    public ResponseEntity<EntityModel<MovimentacaoEstoque>> create(@RequestBody @Valid MovimentacaoEstoque movimentacaoEstoque){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toModel(service.add(movimentacaoEstoque)));
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<MovimentacaoEstoque>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(toModel(service.findById(id)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<MovimentacaoEstoque>> update(
            @PathVariable Long id,
            @RequestBody @Valid MovimentacaoEstoque movimentacaoEstoque
    ) {
        return ResponseEntity.ok(toModel(service.update(id, movimentacaoEstoque)));
    }

    private EntityModel<MovimentacaoEstoque> toModel(MovimentacaoEstoque movimentacao) {
        EntityModel<MovimentacaoEstoque> model = EntityModel.of(movimentacao,
                linkTo(methodOn(MovimentacaoEstoqueController.class).getById(movimentacao.getId())).withSelfRel(),
                linkTo(methodOn(MovimentacaoEstoqueController.class).listAll()).withRel("movimentacoes"),
                linkTo(methodOn(MovimentacaoEstoqueController.class).listarPaginado(null)).withRel("movimentacoes-paginadas"));

        if (movimentacao.getEstoque() != null && movimentacao.getEstoque().getId() != null) {
            model.add(linkTo(methodOn(EstoqueController.class).getEstoqueById(movimentacao.getEstoque().getId())).withRel("estoque"));
        }

        if (movimentacao.getFuncionario() != null && movimentacao.getFuncionario().getId() != null) {
            model.add(linkTo(methodOn(FuncionarioController.class).getById(movimentacao.getFuncionario().getId())).withRel("funcionario"));
        }

        return model;
    }
}
