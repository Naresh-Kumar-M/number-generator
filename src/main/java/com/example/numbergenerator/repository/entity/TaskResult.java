package com.example.numbergenerator.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class TaskResult {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "uuid", unique = true)
  private String id;
  private long goal;
  private long step;
  private String result;

  public TaskResult() {}

  public TaskResult(String id, long goal, long step, String result) {
    this.id = id;
    this.goal = goal;
    this.step = step;
    this.result = result;
  }

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

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

}
