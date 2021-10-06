package br.com.supera.game.store.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCart {

    @Id
    @GeneratedValue
    public long id;

    public List<Product> chosenProducts;

    public BigDecimal shipment;

    public BigDecimal subtotalPrice;

    public BigDecimal totalPrice;

    public LocalDateTime dateShoppingCartCreate;

    public boolean purchased = false;

}