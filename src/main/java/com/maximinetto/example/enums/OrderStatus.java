package com.maximinetto.example.enums;


public enum OrderStatus {
  PENDING("P"), DELIVERED("D"), OUT_OF_DELIVERED("O"), CANCELED("C"), ACCEPTED("A");
  
  private String status;

  private OrderStatus(String status){
    this.status = status;
  }

  public String getStatus(){
    return status;
  }
}
