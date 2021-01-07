package com.example.numbergenerator.controller.dto;

public class SingleTaskResultDTO extends TaskResultDTO{

  private String result;

  public SingleTaskResultDTO(String result) {
    super();
    this.result = result;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }



}
