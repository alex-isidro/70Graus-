package fiap.com.br.graus.data;

import fiap.com.br.graus.model.Produto;
import fiap.com.br.graus.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final ProdutoRepository produtoRepository;
     public void run(String... args) throws Exception {
         Produto p1 = new Produto();
         p1.setNome("Camiseta Básica Branca");
         p1.setDescricao("Camiseta 100% algodão, confortável e versátil");
         p1.setPreco(new BigDecimal("49.90"));
         p1.setSku("SKUCB001");
         p1.setTamanho("M");
         p1.setCor("Branco");
         p1.setMarca("70 Graus");
         p1.setAtivo(true);
         p1.setCategoria("Camisetas");
         produtoRepository.save(p1);

         Produto p2 = new Produto();
         p2.setNome("Calça Jeans Azul");
         p2.setDescricao("Calça jeans tradicional, resistente e durável");
         p2.setPreco(new BigDecimal("129.90"));
         p2.setSku("SKUCJ002");
         p2.setTamanho("G");
         p2.setCor("Azul Escuro");
         p2.setMarca("70 Graus");
         p2.setAtivo(true);
         p2.setCategoria("Calças");
         produtoRepository.save(p2);

         Produto p3 = new Produto();
         p3.setNome("Jaqueta de Inverno");
         p3.setDescricao("Jaqueta acolchoada, impermeável e quentinha");
         p3.setPreco(new BigDecimal("299.90"));
         p3.setSku("SKUJI003");
         p3.setTamanho("G");
         p3.setCor("Preto");
         p3.setMarca("70 Graus");
         p3.setAtivo(true);
         p3.setCategoria("Jaquetas");
         produtoRepository.save(p3);

         Produto p4 = new Produto();
         p4.setNome("Vestido Floral");
         p4.setDescricao("Vestido elegante com estampa floral");
         p4.setPreco(new BigDecimal("189.90"));
         p4.setSku("SKUVF004");
         p4.setTamanho("P");
         p4.setCor("Rosa");
         p4.setMarca("70 Graus");
         p4.setAtivo(true);
         p4.setCategoria("Vestidos");
         produtoRepository.save(p4);

         Produto p5 = new Produto();
         p5.setNome("Bermuda Casual");
         p5.setDescricao("Bermuda confortável para uso casual");
         p5.setPreco(new BigDecimal("79.90"));
         p5.setSku("SKUBC005");
         p5.setTamanho("M");
         p5.setCor("Bege");
         p5.setMarca("70 Graus");
         p5.setAtivo(true);
         p5.setCategoria("Bermudas");
         produtoRepository.save(p5);

         Produto p6 = new Produto();
         p6.setNome("Blusa de Malha");
         p6.setDescricao("Blusa meia malha, perfeita para compor looks");
         p6.setPreco(new BigDecimal("69.90"));
         p6.setSku("SKUBM006");
         p6.setTamanho("G");
         p6.setCor("Cinza");
         p6.setMarca("70 Graus");
         p6.setAtivo(true);
         p6.setCategoria("Blusas");
         produtoRepository.save(p6);

         Produto p7 = new Produto();
         p7.setNome("Legging Preta");
         p7.setDescricao("Legging confortável com ajuste perfeito");
         p7.setPreco(new BigDecimal("89.90"));
         p7.setSku("SKUL007");
         p7.setTamanho("M");
         p7.setCor("Preto");
         p7.setMarca("70 Graus");
         p7.setAtivo(true);
         p7.setCategoria("Leggings");
         produtoRepository.save(p7);

         Produto p8 = new Produto();
         p8.setNome("Camisa Social Branca");
         p8.setDescricao("Camisa social em algodão, ideal para ambientes profissionais");
         p8.setPreco(new BigDecimal("139.90"));
         p8.setSku("SKUCS008");
         p8.setTamanho("GG");
         p8.setCor("Branco");
         p8.setMarca("70 Graus");
         p8.setAtivo(true);
         p8.setCategoria("Camisa");
         produtoRepository.save(p8);

         Produto p9 = new Produto();
         p9.setNome("Shorts Linho");
         p9.setDescricao("Shorts em linho, fresco e elegante");
         p9.setPreco(new BigDecimal("99.90"));
         p9.setSku("SKUSL009");
         p9.setTamanho("P");
         p9.setCor("Natural");
         p9.setMarca("70 Graus");
         p9.setAtivo(true);
         p9.setCategoria("Shorts");
         produtoRepository.save(p9);

         Produto p10 = new Produto();
         p10.setNome("Cardigan Confortável");
         p10.setDescricao("Cardigan em tricô, macio e aconchegante");
         p10.setPreco(new BigDecimal("159.90"));
         p10.setSku("SKUCARD010");
         p10.setTamanho("M");
         p10.setCor("Vermelho");
         p10.setMarca("70 Graus");
         p10.setAtivo(true);
         p10.setCategoria("Cardigans");
         produtoRepository.save(p10);
     }
}
