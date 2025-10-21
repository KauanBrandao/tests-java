package com.snack.repositories;

import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;
    private Product product;
    private Product product2;


    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepository();
        productService = new ProductService();
        product = new Product(1, "Teste", 18, "");

        product2 = new Product(2, "Teste", 18, null);

        productRepository.append(product);

    }


    @Test
    public void deveSalvarImagemComSucesso() {

        productService.save(product);
        Assertions.assertEquals("/home/kauan/Downloads/1.", productService.getImagePathById(1));
    }

    @Test
    public void deveTestarSalvarImagemInexistente() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            String img = productService.getImagePathById(2);
            product2.setImage(img);
            productService.save(product2);
        });
    }


    @Test
    public void deveAtualizarUmProdutoExistente() {
        Product produtoASerAtualizado = new Product(1, "Produto atualizado", 20, "teste atualizado");

        productService.save(product);

        productService.update(produtoASerAtualizado);

        Assertions.assertEquals("/home/kauan/Downloads/1.", product.getImage());
    }

    @Test
    public void deveTestarExcluirUmProduto() {

        productService.save(product);
        productService.remove(1);

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            productService.getImagePathById(1);
        });
    }

    @Test
    public void deveObterCaminhoDaImagemPorId() {

        productService.save(product);
        Assertions.assertEquals("/home/kauan/Downloads/1.", productService.getImagePathById(1));
    }
}