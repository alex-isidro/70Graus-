package fiap.com.br.graus.model;

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
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
    private String senha;

    @OneToMany(mappedBy = "funcionario")
    private List<MovimentacaoEstoque> movimentacoes;
}