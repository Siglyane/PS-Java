package br.com.supera.game.store.controllers;

import br.com.supera.game.store.model.ItemsShoppingCart;
import br.com.supera.game.store.model.Product;
import br.com.supera.game.store.model.ShoppingCart;
import br.com.supera.game.store.repository.ProductRepository;
import br.com.supera.game.store.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

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
    public ResponseEntity<Product> addItemShoppingCart(Long id, @RequestBody ItemsShoppingCart itemShoppingCart) {
        try {
            Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(id);
            Optional<Product> product = productRepository.findById(itemShoppingCart.getProduct().getId());
            //Caso o produto tivesse estoque, verificar o estoque, para ver se é possivel adicionar na venda.

            if (product.isPresent() && shoppingCart.isPresent() && !shoppingCart.get().isPurchased()) {
                ItemsShoppingCart addNewItem = new ItemsShoppingCart(
                        itemShoppingCart.getQuantity(),
                        itemShoppingCart.getUnPrice(),
                        itemShoppingCart.getProduct(),
                        itemShoppingCart.getShoppingCart()
                );
                BigDecimal totalPrice = addNewItem.getUnPrice().multiply(BigDecimal.valueOf(addNewItem.getQuantity()));
                addNewItem.setTotalPrice(totalPrice);
                //Ao separar a camada de service e controller, o ideal seria fazer um método para
                // a regra de negocio do frete.
                shoppingCart.get().setShipment(shoppingCart.get().getShipment().add(BigDecimal.valueOf(10)));

                return new ResponseEntity<>(HttpStatus.OK);

            } else {

            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void freteRegra(){

  }
}
