package com.example.numbergenerator.controller.dto;

public class TaskResponseDTO {

  private String task;

  public TaskResponseDTO(final String taskId) {
    this.task = taskId;
  }

  public String getTask() {
    return task;
  }

  public void setTask(String taskId) {
    this.task = taskId;
  }

}
