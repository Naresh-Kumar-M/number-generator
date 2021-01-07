package com.example.numbergenerator.repository.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Task {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "uuid", unique = true)
  private String id;
  
  @OneToMany(cascade=CascadeType.ALL)
  @JoinColumn(name = "fk_task_data")
  private List<TaskData> taskData;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<TaskData> getTaskData() {
    return taskData;
  }

  public void setTaskData(List<TaskData> taskData) {
    this.taskData = taskData;
  }

}
