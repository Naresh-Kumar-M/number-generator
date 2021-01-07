package com.example.numbergenerator.controller.dto;

import org.springframework.lang.NonNull;

public class TaskDTO {

  @NonNull
  private long goal;
  
  @NonNull
  private long step;

  public TaskDTO() {}

  public long getGoal() {
    return goal;
  }

  public void setGoal(long goal) {
    this.goal = goal;
  }

  public long getStep() {
    return step;
  }

  public void setStep(long step) {
    this.step = step;
  }

}
