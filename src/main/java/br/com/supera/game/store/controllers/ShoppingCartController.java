package br.com.supera.game.store.controllers;

import br.com.supera.game.store.model.ItemsShoppingCart;
import br.com.supera.game.store.model.Product;
import br.com.supera.game.store.model.ShoppingCart;
import br.com.supera.game.store.repository.ItemsShoppingCartRepository;
import br.com.supera.game.store.repository.ProductRepository;
import br.com.supera.game.store.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/shopping-cart/itens")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemsShoppingCartRepository itemsShoppingCartRepository;


    @PostMapping
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
    public ResponseEntity addItemShoppingCart(Long id, @RequestBody ItemsShoppingCart itemShoppingCart) {
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

                return new ResponseEntity<>(itemsShoppingCartRepository.save(addNewItem), HttpStatus.CREATED);

            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Não é permitido adicionar novos itens" +
                        "sem cumprir todos os requisitos.");
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/retirar-itens/{id}")
    public ResponseEntity deleteItemShoppingCart(@PathVariable("id") long id) {
        try {
            Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(id);
            if (shoppingCart.isPresent() && !shoppingCart.get().isPurchased()) {
                itemsShoppingCartRepository.deleteById(id);
                shoppingCart.get().setShipment(shoppingCart.get().getShipment().subtract(BigDecimal.valueOf(10)));

                return ResponseEntity.status(HttpStatus.OK).body("Item retirado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Não é permitido deletar um item que não existe ou se o carrinho já foi finalizada");
            }

        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<ShoppingCart> purchaseShoppingCart(@PathVariable("id") Long id) {
        try {
            Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(id);
            if(shoppingCart.isPresent() && !shoppingCart.get().isPurchased()) {
                ShoppingCart shoppingCartUpdate = shoppingCart.get();

                BigDecimal totalPrice = itemsShoppingCartRepository.valueTotalPrice(id);

                shoppingCartUpdate.setSubtotalPrice(totalPrice);
                int testFreeShipment = shoppingCartUpdate.getShipment().compareTo(BigDecimal.valueOf(250));
                if(testFreeShipment >= 0){
                    shoppingCartUpdate.setShipment(BigDecimal.valueOf(0));
                }
                shoppingCartUpdate.setTotalPrice(totalPrice.add(shoppingCartUpdate.getShipment()));
                shoppingCartUpdate.setPurchased(true);

                return new ResponseEntity<>(shoppingCartRepository.save(shoppingCartUpdate), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
