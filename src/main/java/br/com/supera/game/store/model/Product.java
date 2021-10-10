package br.com.supera.game.store.model;



import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable=false)
   private String name;

   @Column(nullable=false)
   private BigDecimal price;

   @Column
   private short score;

   @Column
   private String image;


   public long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public BigDecimal getPrice() {
      return price;
   }


   public short getScore() {
      return score;
   }

}