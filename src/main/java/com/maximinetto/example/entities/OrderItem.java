package com.maximinetto.example.entities;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "order_items")
public class OrderItem {
  
  @Id
  private UUID id;

  @ManyToOne
  @JoinColumn(name="order_id", nullable = false)
  private Order order;

  @Basic(optional = false)
  private Integer quantity;

  @Basic(optional = false)
  private BigDecimal price;
  
  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Transient
  private BigDecimal subTotal;

  @PrePersist
  @PreUpdate
  private void calculateSubTotal(){
    this.subTotal = price.multiply(new BigDecimal(quantity));
  }

}
