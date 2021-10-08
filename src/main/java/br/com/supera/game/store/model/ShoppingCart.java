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
    public long id;

    @OneToMany
    private List<Product> chosenProducts;

    @Column
    private BigDecimal shipment;

    @Column(name="subtotal")
    private BigDecimal subtotalPrice;

    @Column(name = "total")
    private BigDecimal totalPrice;

    @Column(name="shopping_cart_date")
    private LocalDateTime dateShoppingCartCreate;

    private boolean purchased = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Product> getChosenProducts() {
        return chosenProducts;
    }

    public void setChosenProducts(List<Product> chosenProducts) {
        this.chosenProducts = chosenProducts;
    }

    public BigDecimal getShipment() {
        return shipment;
    }

    public void setShipment(BigDecimal shipment) {
        this.shipment = shipment;
    }

    public BigDecimal getSubtotalPrice() {
        return subtotalPrice;
    }

    public void setSubtotalPrice(BigDecimal subtotalPrice) {
        this.subtotalPrice = subtotalPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getDateShoppingCartCreate() {
        return dateShoppingCartCreate;
    }

    public void setDateShoppingCartCreate(LocalDateTime dateShoppingCartCreate) {
        this.dateShoppingCartCreate = dateShoppingCartCreate;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}