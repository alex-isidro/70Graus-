package fiap.com.br.graus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
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
    private BigDecimal preco;

    @NotBlank
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

    @OneToMany(mappedBy = "produto")
    private List<Estoque> estoques;
}