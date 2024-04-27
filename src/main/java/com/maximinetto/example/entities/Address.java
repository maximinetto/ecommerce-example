package com.maximinetto.example.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "addresses")
public class Address {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Basic(optional = false)
  private String city;

  @Basic(optional = false)
  private String country;

  @Basic(optional = false)
  private String postcode;

  @Column(nullable = false)
  @Basic(optional = false)
  private String streetName;

  @Column(nullable = false)
  @Basic(optional = false)
  private Integer streetNumber;

  @Column(nullable = true)
  @Basic(optional = true)
  private String apto;

}
