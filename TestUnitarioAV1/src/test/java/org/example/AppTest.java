package org.example;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {

    private Produto produto;
    private Produto produtoComPrecoNegativo;
    private Produto produtoComEstoqueNegativo;
    private Venda venda;
    private Venda vendaEstoqueIndisponivel;
    private Venda vendaEstoqueIgual;


    @BeforeEach
    public void setUp() {
        produto = new Produto("Nescau", 18, 36);
        produtoComPrecoNegativo = new Produto("Nescau com preco invalido", -18, 36);
        produtoComEstoqueNegativo = new Produto("Nescau com estoque invalido", 18, -36);


        venda = new Venda(produto, 10);
        vendaEstoqueIndisponivel = new Venda(produto, 40);
        vendaEstoqueIgual = new Venda(produto, 36);


    }

    @Test
    public void deveCriarProdutoValido() {
        Assertions.assertEquals("Nescau", produto.getNome());
        Assertions.assertEquals(18, produto.getPreco());
        Assertions.assertEquals(36, produto.getEstoque());
    }

    @Test
    public void deveCriarUmProdutoComPrecoInvalido() {
        Assertions.assertEquals(-18, produtoComPrecoNegativo.getPreco());
    }

    @Test
    public void deveCriarProdutoComEstoqueNegativo() {
        Assertions.assertEquals(-36, produtoComEstoqueNegativo.getEstoque());
    }

    @Test
    public void deveAlterarNomeDoProduto() {
        produto.setNome("Teste");
        Assertions.assertEquals("Teste", produto.getNome());
    }

    @Test
    public void deveAlterarPrecoDoProduto() {
        produto.setPreco(35);
        Assertions.assertEquals(35, produto.getPreco());
    }

    @Test
    public void deveAlterarEstoqueValido() {
        produto.setEstoque(30);
        Assertions.assertEquals(30, produto.getEstoque());
    }


    @Test
    public void deveAlterarParaPrecoNegatico() {
        produto.setPreco(-30);
        Assertions.assertEquals(-30, produto.getPreco());
    }


    @Test
    public void deveRealizarVendaComEstoqueDIsponivel() {
        Assertions.assertTrue(venda.realizarVenda());
    }


    @Test
    public void deveRealizarVendaComEstoqueIgual() {
        Assertions.assertTrue(vendaEstoqueIgual.realizarVenda());
    }

    @Test
    public void deveRealizarVendaComEstoqueIndisponivel() {
        Assertions.assertFalse(vendaEstoqueIndisponivel.realizarVenda());
    }


    @Test
    public void deveTestarValorTotalVenda() {
        venda.realizarVenda();
        Assertions.assertEquals(180, venda.getTotalVenda());

    }

    @Test
    public void deveTestarAumentoEstoqueAposVenda() {
        venda.realizarVenda();
        produto.aumentarEstoque(1);

        Assertions.assertEquals(27, produto.getEstoque());

    }

    @Test
    public void deveTestarDiminuicaoEstoqueAposVenda() {
        venda.realizarVenda();

        Assertions.assertEquals(26, produto.getEstoque());

    }


    @Test
    public void deveTestarVendaComProdutoInexistente() {
        Produto produtoNull = null;

        Venda venda = new Venda(produtoNull, 10);

        Assertions.assertThrows(NullPointerException.class, () -> {
            venda.realizarVenda();
        });

    }

    @Test
    public void deveTestarVendaComQuantidadeNegativa() {
        Produto produto = new Produto("Teste", 10, 10);

        Venda venda = new Venda(produto, 11);

        Assertions.assertFalse(venda.realizarVenda());
    }


    //Testar alteração do estoque após a tentativa de venda com estoque insuficiente.


    @Test
    public void deveTestarAlterarEstoqueAposVendaComEstoqueInsuficiente() {
        Produto produto = new Produto("Teste", 10, 10);

        Venda venda = new Venda(produto, 11);

        Assertions.assertFalse(venda.realizarVenda());

        produto.aumentarEstoque(10);
        Assertions.assertEquals(20, produto.getEstoque());

    }


    @Test
    public void vendaComEstoqueCompartilhado() {
        Produto p1 = new Produto("Teste", 18, 20);
        Venda venda1 = new Venda(p1, 10);
        Venda venda2 = new Venda(p1, 10);

        venda1.realizarVenda();
        venda2.realizarVenda();

        Assertions.assertEquals(0, p1.getEstoque());

    }

    //Testar calcular total de venda quando o preço do produto for alterado antes da venda.

    @Test
    public void deveCalcularTotalVendaQuandoPrecoForAlteradoAntesDaVenda() {
        Produto p1 = new Produto("Teste", 18, 20);

        Venda venda1 = new Venda(p1, 10);

        p1.setPreco(36);
        venda1.realizarVenda();

        Assertions.assertEquals(360, venda1.getTotalVenda());

    }

    //Testar comportamento da venda quando o estoque inicial é zero.

    @Test
    public void deveTestarComportamentoComEstoque0 () {
        Produto p1 = new Produto("Teste", 18, 0);

        Venda venda1 = new Venda(p1, 10);

        Assertions.assertFalse(venda1.realizarVenda());

        // TODO: Tenho q fazer isso
    }

    //Testar aumento do estoque após uma reposição e verificar se a venda é bem-sucedida posteriormente.

    @Test
    public void deveTestarEstoqueVerificarVenda () {
        Produto p1 = new Produto("Teste", 18, 0);

        Venda venda1 = new Venda(p1, 10);

        p1.aumentarEstoque(10);

        Assertions.assertTrue(venda1.realizarVenda());
    }
}
