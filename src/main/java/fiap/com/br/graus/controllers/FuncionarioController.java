package fiap.com.br.graus.controllers;

import fiap.com.br.graus.dto.FuncionarioResponse;
import fiap.com.br.graus.model.Funcionario;
import fiap.com.br.graus.services.FuncionarioService;
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
@Slf4j
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping
    public CollectionModel<EntityModel<FuncionarioResponse>> listAll(){
        List<EntityModel<FuncionarioResponse>> funcionarios = service.getAllFuncionario()
                .stream()
                .map(this::toModel)
                .toList();

        return CollectionModel.of(funcionarios,
                linkTo(methodOn(FuncionarioController.class).listAll()).withSelfRel(),
                linkTo(methodOn(FuncionarioController.class).listarPaginado(null)).withRel("paginado"));
    }

    @GetMapping("/paginado")
    public ResponseEntity<PagedModel<EntityModel<FuncionarioResponse>>> listarPaginado(
            @PageableDefault(size = 5, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Funcionario> page = service.getAllFuncionario(pageable);

        List<EntityModel<FuncionarioResponse>> funcionarios = page.getContent()
                .stream()
                .map(this::toModel)
                .toList();

        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
                page.getSize(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages()
        );

        return ResponseEntity.ok(PagedModel.of(funcionarios, metadata,
                linkTo(methodOn(FuncionarioController.class).listarPaginado(pageable)).withSelfRel(),
                linkTo(methodOn(FuncionarioController.class).listAll()).withRel("todos-funcionarios")));
    }

    @PostMapping
    public ResponseEntity<EntityModel<FuncionarioResponse>> createFuncionario(
            @RequestBody @Valid Funcionario funcionario){

        Funcionario f = new Funcionario();
        f.setNome(funcionario.getNome());
        f.setEmail(funcionario.getEmail());
        f.setSenha(funcionario.getSenha());

        Funcionario salvo = service.addFuncionario(f);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toModel(salvo));
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<FuncionarioResponse>> getById(@PathVariable Long id) {
        Funcionario f = service.getFuncionarioById(id);
        return ResponseEntity.ok(toModel(f));
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<FuncionarioResponse>> update(
            @PathVariable Long id,
            @RequestBody @Valid Funcionario funcionario) {

        Funcionario f = new Funcionario();
        f.setNome(funcionario.getNome());
        f.setEmail(funcionario.getEmail());
        f.setSenha(funcionario.getSenha());

        Funcionario atualizado = service.updateFuncionario(id, f);

        return ResponseEntity.ok(toModel(atualizado));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    private EntityModel<FuncionarioResponse> toModel(Funcionario funcionario) {
        FuncionarioResponse response = new FuncionarioResponse(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getEmail()
        );

        return EntityModel.of(response,
                linkTo(methodOn(FuncionarioController.class).getById(funcionario.getId())).withSelfRel(),
                linkTo(methodOn(FuncionarioController.class).listAll()).withRel("funcionarios"),
                linkTo(methodOn(FuncionarioController.class).listarPaginado(null)).withRel("funcionarios-paginados"),
                linkTo(methodOn(MovimentacaoEstoqueController.class).listAll()).withRel("movimentacoes"));
    }
}
