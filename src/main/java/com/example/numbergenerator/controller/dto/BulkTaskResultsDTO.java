package com.example.numbergenerator.controller.dto;

import java.util.List;

public class BulkTaskResultsDTO extends TaskResultDTO{

  private List<String> results;

  public BulkTaskResultsDTO(List<String> result) {
    super();
    this.results = result;
  }

  public List<String> getResults() {
    return results;
  }

  public void setResults(List<String> result) {
    this.results = result;
  }

}
