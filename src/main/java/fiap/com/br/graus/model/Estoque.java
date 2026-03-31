package fiap.com.br.graus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Estoque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long produtoId;
    private Integer quantidadeDisponivel;
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
