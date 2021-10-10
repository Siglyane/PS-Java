package br.com.supera.game.store.controllers;

import br.com.supera.game.store.model.Product;
import br.com.supera.game.store.repository.ProductRepository;
import br.com.supera.game.store.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }
}
