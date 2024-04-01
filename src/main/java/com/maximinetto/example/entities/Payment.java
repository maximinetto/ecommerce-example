package com.maximinetto.example.entities;

import java.util.UUID;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {

  @Id
  private UUID id;

  @Basic(optional = false)
  private Boolean digital;

  @Basic(optional = false)
  private Boolean cod;

}
