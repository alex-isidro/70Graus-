package fiap.com.br.graus.data;

import fiap.com.br.graus.model.Estoque;
import fiap.com.br.graus.model.Funcionario;
import fiap.com.br.graus.model.MovimentacaoEstoque;
import fiap.com.br.graus.model.Produto;
import fiap.com.br.graus.repositories.EstoqueRepository;
import fiap.com.br.graus.repositories.FuncionarioRepository;
import fiap.com.br.graus.repositories.ProdutoRepository;
import fiap.com.br.graus.services.MovimentacaoEstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final MovimentacaoEstoqueService movimentacaoEstoqueService;

    @Override
    public void run(String... args) throws Exception {

        Produto p1 = criarProduto(
                "Camiseta Básica Branca",
                "Camiseta 100% algodão, confortável e versátil",
                "49.90",
                "SKUCB001",
                "M",
                "Branco",
                "70 Graus",
                "Camisetas"
        );

        Produto p2 = criarProduto(
                "Calça Jeans Azul",
                "Calça jeans tradicional, resistente e durável",
                "129.90",
                "SKUCJ002",
                "G",
                "Azul Escuro",
                "70 Graus",
                "Calças"
        );

        Produto p3 = criarProduto(
                "Jaqueta de Inverno",
                "Jaqueta acolchoada, impermeável e quentinha",
                "299.90",
                "SKUJI003",
                "G",
                "Preto",
                "70 Graus",
                "Jaquetas"
        );

        Produto p4 = criarProduto(
                "Vestido Floral",
                "Vestido elegante com estampa floral",
                "189.90",
                "SKUVF004",
                "P",
                "Rosa",
                "70 Graus",
                "Vestidos"
        );

        Produto p5 = criarProduto(
                "Bermuda Casual",
                "Bermuda confortável para uso casual",
                "79.90",
                "SKUBC005",
                "M",
                "Bege",
                "70 Graus",
                "Bermudas"
        );

        Produto p6 = criarProduto(
                "Blusa de Malha",
                "Blusa meia malha, perfeita para compor looks",
                "69.90",
                "SKUBM006",
                "G",
                "Cinza",
                "70 Graus",
                "Blusas"
        );

        Produto p7 = criarProduto(
                "Legging Preta",
                "Legging confortável com ajuste perfeito",
                "89.90",
                "SKUL007",
                "M",
                "Preto",
                "70 Graus",
                "Leggings"
        );

        Produto p8 = criarProduto(
                "Camisa Social Branca",
                "Camisa social em algodão, ideal para ambientes profissionais",
                "139.90",
                "SKUCS008",
                "GG",
                "Branco",
                "70 Graus",
                "Camisa"
        );

        Produto p9 = criarProduto(
                "Shorts Linho",
                "Shorts em linho, fresco e elegante",
                "99.90",
                "SKUSL009",
                "P",
                "Natural",
                "70 Graus",
                "Shorts"
        );

        Produto p10 = criarProduto(
                "Cardigan Confortável",
                "Cardigan em tricô, macio e aconchegante",
                "159.90",
                "SKUCARD010",
                "M",
                "Vermelho",
                "70 Graus",
                "Cardigans"
        );

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Kelson");
        funcionario.setEmail("kelson@email.com");
        funcionario.setSenha("Senha123");
        funcionarioRepository.save(funcionario);

        Estoque e1 = criarEstoque(p1, 20, 5);
        Estoque e2 = criarEstoque(p2, 15, 4);
        Estoque e3 = criarEstoque(p3, 8, 2);
        Estoque e4 = criarEstoque(p4, 12, 3);
        Estoque e5 = criarEstoque(p5, 25, 5);
        Estoque e6 = criarEstoque(p6, 18, 4);
        Estoque e7 = criarEstoque(p7, 30, 6);
        Estoque e8 = criarEstoque(p8, 10, 2);
        Estoque e9 = criarEstoque(p9, 22, 5);
        Estoque e10 = criarEstoque(p10, 9, 2);

        criarMovimentacao(e1, funcionario, "ENTRADA", 10);
        criarMovimentacao(e1, funcionario, "SAIDA", 3);

        criarMovimentacao(e2, funcionario, "ENTRADA", 5);
        criarMovimentacao(e3, funcionario, "SAIDA", 2);
        criarMovimentacao(e5, funcionario, "ENTRADA", 8);
        criarMovimentacao(e7, funcionario, "SAIDA", 4);
    }

    private Produto criarProduto(
            String nome,
            String descricao,
            String preco,
            String sku,
            String tamanho,
            String cor,
            String marca,
            String categoria
    ) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(new BigDecimal(preco));
        produto.setSku(sku);
        produto.setTamanho(tamanho);
        produto.setCor(cor);
        produto.setMarca(marca);
        produto.setAtivo(true);
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    private Estoque criarEstoque(Produto produto, Integer quantidadeDisponivel, Integer quantidadeMinima) {
        Estoque estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setQuantidadeDisponivel(quantidadeDisponivel);
        estoque.setQuantidadeMinima(quantidadeMinima);

        return estoqueRepository.save(estoque);
    }

    private void criarMovimentacao(
            Estoque estoque,
            Funcionario funcionario,
            String tipoMovimentacao,
            Integer quantidade
    ) {
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
        movimentacao.setEstoque(estoque);
        movimentacao.setFuncionario(funcionario);
        movimentacao.setTipoMovimentacao(tipoMovimentacao);
        movimentacao.setQuantidade(quantidade);
        movimentacao.setDataMovimentacao(LocalDate.now());

        movimentacaoEstoqueService.add(movimentacao);
    }
}
