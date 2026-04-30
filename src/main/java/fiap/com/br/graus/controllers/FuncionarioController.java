package fiap.com.br.graus.controllers;

import fiap.com.br.graus.dto.FuncionarioResponse;
import fiap.com.br.graus.model.Funcionario;
import fiap.com.br.graus.services.FuncionarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping
    public List<FuncionarioResponse> listAll(){
        return service.getAllFuncionario()
                .stream()
                .map(f -> new FuncionarioResponse(
                        f.getId(),
                        f.getNome(),
                        f.getEmail()
                ))
                .toList();
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponse> createFuncionario(
            @RequestBody @Valid Funcionario funcionario){

        Funcionario f = new Funcionario();
        f.setNome(funcionario.getNome());
        f.setEmail(funcionario.getEmail());
        f.setSenha(funcionario.getSenha());

        Funcionario salvo = service.addFuncionario(f);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new FuncionarioResponse(
                        salvo.getId(),
                        salvo.getNome(),
                        salvo.getEmail()
                ));
    }

    @GetMapping("{id}")
    public ResponseEntity<FuncionarioResponse> getById(@PathVariable Long id) {

        Funcionario f = service.getFuncionarioById(id);

        return ResponseEntity.ok(new FuncionarioResponse(
                f.getId(),
                f.getNome(),
                f.getEmail()
        ));
    }

    @PutMapping("{id}")
    public ResponseEntity<FuncionarioResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid Funcionario funcionario) {

        Funcionario f = new Funcionario();
        f.setNome(funcionario.getNome());
        f.setEmail(funcionario.getEmail());
        f.setSenha(funcionario.getSenha());

        Funcionario atualizado = service.updateFuncionario(id, f);

        return ResponseEntity.ok(new FuncionarioResponse(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getEmail()
        ));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}
