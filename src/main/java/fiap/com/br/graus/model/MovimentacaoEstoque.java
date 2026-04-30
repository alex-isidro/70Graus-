package fiap.com.br.graus.model;

import java.time.LocalDate;

import fiap.com.br.graus.validation.TipoMovimentacaoValida;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class MovimentacaoEstoque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String tipoMovimentacao;

    @NotNull
    @Min(1)
    private Integer quantidade;

    @NotNull
    private LocalDate dataMovimentacao;

    @ManyToOne
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @NotBlank
    @TipoMovimentacaoValida
    private String tipoMovimentacao;
}