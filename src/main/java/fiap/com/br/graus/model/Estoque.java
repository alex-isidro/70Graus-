package fiap.com.br.graus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Estoque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @JsonIgnore
    @OneToMany(mappedBy = "estoque")
    private List<MovimentacaoEstoque> movimentacoes;

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