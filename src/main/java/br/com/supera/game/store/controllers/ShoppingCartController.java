package br.com.supera.game.store.controllers;

import br.com.supera.game.store.model.Product;
import br.com.supera.game.store.model.ShoppingCart;
import br.com.supera.game.store.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;


public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ResponseEntity<ShoppingCart> createNewShoppingCart() {
        try {
            //Funcionalidade: considerando que o ecommerce possui uma camada de usuario, verificar id existe no banco.
            //Se presente:
            ShoppingCart newShoppingCart = new ShoppingCart(LocalDateTime.now());
            //Conectar ShoppingCart ao User
            shoppingCartRepository.save(newShoppingCart);
            return ResponseEntity.status(HttpStatus.OK).body(newShoppingCart);
            //Caso não tiver a id, retornar http status not found
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-produto/{id}")
    public ResponseEntity<Product> addItemShoppingCart(Long id, Product productChosen) {
        try {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void freteRegra(){

  }
}
