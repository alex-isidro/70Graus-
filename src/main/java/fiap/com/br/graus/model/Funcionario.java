package fiap.com.br.graus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Funcionario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 40)
    private String nome;

    @NotBlank
    @Email(message = "Email inválido")
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
            message = "A senha deve conter pelo menos uma letra maiúscula, uma minúscula e um número")
    private String senha;

    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<MovimentacaoEstoque> movimentacoes;
}