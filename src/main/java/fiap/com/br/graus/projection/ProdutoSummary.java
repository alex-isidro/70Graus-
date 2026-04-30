package fiap.com.br.graus.projection;

import java.math.BigDecimal;

public interface ProdutoSummary {

    Long getId();

    String getNome();

    BigDecimal getPreco();

    String getTamanho();

    Boolean getAtivo();
}