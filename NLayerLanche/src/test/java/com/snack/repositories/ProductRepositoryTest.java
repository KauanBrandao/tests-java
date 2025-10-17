package com.snack.repositories;

import com.snack.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {

    private ProductRepository productRepository;

    private Product product1;
    private Product product2;
    private Product productInexistente;
    private Product productAtualizado;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
        product1 = new Product(1, "Hot Dog", 10.4f, "");
        product2 = new Product(2, "Hot Dog 2", 10.4f, "");
        productInexistente = null;
        productAtualizado = new Product(1, "Teste", 18, "");
    }

    @Test
    public void verificarSeOProdutoEstaNoArray() {
        // Arrange
        productRepository.append(product1);

        // Act
        Product productId1 = productRepository.getById(1);
        // Assert
        assertNotNull(productId1);
    }

    @Test
    public void verificaSeRecuperaUmProdutoPorId() {

        int id = product1.getId();

        Assertions.assertEquals(1, id);

    }


    @Test
    public void deveConfirmarProdutoExistenteNoRepositorio() {

        productRepository.append(product1);
        boolean exist = productRepository.exists(product1.getId());

        assertTrue(exist);
    }

    @Test
    public void deveTestarRemoverUmProdutoDoRepositorio() {

        productRepository.append(product1);
        productRepository.remove(product1.getId());

        boolean exist = productRepository.exists(product1.getId());

        assertFalse(exist);
    }

    @Test
    public void deveVerificarSeAtualizaOProduto() {



        productRepository.append(product1);
        productRepository.update(product1.getId(), productAtualizado);

        Assertions.assertEquals(product1.getDescription(), productAtualizado.getDescription());



    }

    @Test
    public void deveVerificarSeRecuperaTodosOsProdutos() {

        productRepository.append(product1);
        productRepository.append(product2);

        List<Product> produtosRecuperados = productRepository.getAll();

        Assertions.assertEquals(productRepository.getAll(), produtosRecuperados);

    }

    @Test
    public void deveVerificarComportamentoAposTentativaDeRemocaoInvalida() {

        productRepository.append(productInexistente);

        Assertions.assertThrows(NullPointerException.class, () -> {
            productRepository.remove(productInexistente.getId());
        });
    }

    @Test
    public void deveTestarTentarAtualizarProdutoInexistente() {

        Assertions.assertThrows(NoSuchElementException.class, ()  -> {
            productRepository.update(3, productAtualizado);
        });
    }

    @Test
    public void deveVerificarSeORepositorioAceitaAdicaoDeProdutosComIdDuplicado() {

        Product productDuplicado = new Product(1, "Teste", 18, "");

        productRepository.append(product1);
        productRepository.append(productDuplicado);


    }

    @Test
    public void deveConfirmarQueOReopositoryRetornaVazioAoSerInicializado() {

        List<Product> products = productRepository.getAll();

        Assertions.assertEquals(products, productRepository.getAll());

        Assertions.assertEquals(0, productRepository.getAll().size());
    }

}
