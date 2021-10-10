package br.com.supera.game.store.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToMany(mappedBy = "shopping_cart")
    private List<ItemsShoppingCart> itemsShoppingCarts;

    @Column
    private BigDecimal shipment;

    @Column(name="subtotal")
    private BigDecimal subtotalPrice;

    @Column(name = "total")
    private BigDecimal totalPrice;

    @Column(name="shopping_cart_date")
    private LocalDateTime dateShoppingCartCreate;

    @Column(name="purchased")
    private boolean purchased = false;


    public ShoppingCart(LocalDateTime dateShoppingCartCreate) {
        this.dateShoppingCartCreate = dateShoppingCartCreate;
    }

    public BigDecimal getShipment() {
        return shipment;
    }

    public void setShipment(BigDecimal shipment) {
        this.shipment = shipment;
    }


    public void setSubtotalPrice(BigDecimal subtotalPrice) {
        this.subtotalPrice = subtotalPrice;
    }


    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}