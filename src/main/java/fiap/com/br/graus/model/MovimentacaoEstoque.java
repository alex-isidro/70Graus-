package fiap.com.br.graus.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class MovimentacaoEstoque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long estoqueId;

    @NotNull
    private Long funcionarioId;

    @NotBlank
    private String tipoMovimentacao;

    @NotNull
    @Min(1)
    private Integer quantidade;

    @NotNull
    private LocalDate dataMovimentacao;
}