package com.snack.repositories;

import com.snack.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class ProductServiceTest {

    private ProductRepository productRepository;

    private Product product;


    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepository();
        product = new Product(1, "Teste", 18, "");

        productRepository.append(product);

    }


    @Test
    public void deveSalvarImagemComSucesso() {
        String img = "/images/img.jpeg";

        product.setImage(img);

        Assertions.assertEquals(img, productRepository.getById(1).getImage());
    }

    @Test
    public void deveTestarSalvarImagemInexistente() {
        String img = "/images/img2.jpeg";

        product.setImage(img);

        Assertions.assertEquals(img, productRepository.getById(1).getImage());
    }


    @Test
    public void deveAtualizarUmProdutoExistente() {
        Product produtoAtualizado = new Product(1, "Produto atualizado", 20, "");


        productRepository.update(1, produtoAtualizado);

        Assertions.assertEquals("Produto atualizado", productRepository.getById(1).getDescription());

    }

    @Test
    public void deveTestarExcluirUmProduto() {

        productRepository.remove(1);

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            productRepository.getById(1);
        });
    }

    @Test
    public void deveObterCaminhoDaImagemPorId() {

        Assertions.assertEquals("", productRepository.getById(1).getImage());
    }
}