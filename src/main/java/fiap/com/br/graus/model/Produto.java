package fiap.com.br.graus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

@Entity
@Data
@RestController
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;

    @NotBlank
    @Size(min = 10, max = 500)
    private String descricao;

    @NotNull
    @DecimalMin("0.01")
    private Double preco;

    @NotBlank
    @Pattern(regexp = "^SKU[A-Z0-9]{6,}$")
    private String sku;

    @NotBlank
    private String tamanho;

    @NotBlank
    private String cor;

    @NotBlank
    private String marca;

    @NotNull
    private Boolean ativo;

    @NotBlank
    private String categoria;

}