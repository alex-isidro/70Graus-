package fiap.com.br.graus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Estoque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long produtoId;

    @NotNull
    @Min(0)
    private Integer quantidadeDisponivel;

    @NotNull
    @Min(0)
    private Integer quantidadeMinima;

    public void movimentar(int quantidade, boolean entrada) {
        int fator = entrada ? 1 : -1;
        int novaQuantidade = this.quantidadeDisponivel + (quantidade * fator);

        if (novaQuantidade < 0) {
            throw new RuntimeException("Estoque insuficiente");
        }

        this.quantidadeDisponivel = novaQuantidade;
    }
}