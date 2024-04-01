package com.maximinetto.example.entities;

import java.math.BigDecimal;
import java.time.Instant;

import org.hibernate.validator.constraints.UUID;

import com.maximinetto.example.enums.OrderStatus;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

  @Id
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private User customer;

  private OrderStatus status;

  @Column(name = "total_price")
  @Basic(optional = false)
  private BigDecimal totalPrice;

  @Temporal(TemporalType.TIMESTAMP)
  @Basic(optional = false)
  private Instant date;

  @Basic(optional = false)
  private BigDecimal discount;

  @ManyToOne
  @JoinColumn(name="address_id", nullable = false)
  private Address address;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="payment_id", nullable = false)
  private Payment payment;
  
}
