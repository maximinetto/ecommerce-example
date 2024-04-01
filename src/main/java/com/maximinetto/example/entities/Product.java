package com.maximinetto.example.entities;

import java.math.BigDecimal;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Product name is required")
  @Basic(optional = false)
  private String name;

  @Basic(optional = false)
  private BigDecimal price;

  @Basic(optional = false)
  private Integer amount;

  private Short rating;

  private String image;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="brand_id", nullable = true, insertable = false, updatable = false)
  private Brand brand;

}
