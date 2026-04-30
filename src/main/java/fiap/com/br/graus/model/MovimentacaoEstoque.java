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
    @TipoMovimentacaoValida
    private String tipoMovimentacao;

    @NotNull
    @Min(1)
    private Integer quantidade;

    @NotNull
    private LocalDate dataMovimentacao;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "estoque_id", nullable = false)
    private Estoque estoque;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;


}