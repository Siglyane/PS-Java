package br.com.supera.game.store.repository;

import br.com.supera.game.store.model.ItemsShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ItemsShoppingCartRepository extends JpaRepository<ItemsShoppingCart, Long> {

    @Query(value = "SELECT SUM(total_price) FROM items_shopping_cart where id_shopping_cart = :id_shopping_cart", nativeQuery = true)
    public BigDecimal valueTotalPrice(@Param("id_shopping_cart") Long idShoppingCart);
}
