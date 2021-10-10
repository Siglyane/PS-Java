package br.com.supera.game.store.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
/*
* Por a interação entre as classes Product e Shopping Cart ser Many to Many,
* ou seja, cada shopping cart pode ter diversos produtos e cada produtos pode
* estar em diversos shopping cart
* é criada está classe, que auxilia a relação entre elas.
* */
@Entity
@Table(name = "items_shopping_cart")
public class ItemsShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "un_price")
    private BigDecimal unPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_shopping_cart")
    private ShoppingCart shoppingCart;

    public ItemsShoppingCart(int quantity, BigDecimal unPrice, Product product, ShoppingCart shoppingCart) {
        this.quantity = quantity;
        this.unPrice = unPrice;
        this.product = product;
        this.shoppingCart = shoppingCart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnPrice() {
        return unPrice;
    }

    public void setUnPrice(BigDecimal unPrice) {
        this.unPrice = unPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
