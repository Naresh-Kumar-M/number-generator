package com.example.numbergenerator.service.vo;

import com.example.numbergenerator.repository.entity.TaskStatus;

public class TaskDataVO {

  private String id;
  private long goal;
  private long step;
  private TaskStatus status = TaskStatus.QUEUED;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

}
