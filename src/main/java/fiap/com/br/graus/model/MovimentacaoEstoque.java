package fiap.com.br.graus.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MovimentacaoEstoque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long estoqueId;
    private Long funcionarioId;
    private String tipoMovimentacao;
    private Integer quantidade;
    private LocalDate dataMovimentacao;
}
