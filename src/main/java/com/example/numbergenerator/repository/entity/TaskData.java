package com.example.numbergenerator.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class TaskData {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "uuid", unique = true)
  private String id;
  
  private long goal;
  private long step;
  private TaskStatus status;

  @OneToOne
  @JoinColumn(name = "fk_result")
  private TaskResult result;


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

  public TaskResult getResult() {
    return result;
  }

  public void setResult(TaskResult result) {
    this.result = result;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

}
