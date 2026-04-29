package fiap.com.br.graus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

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
}