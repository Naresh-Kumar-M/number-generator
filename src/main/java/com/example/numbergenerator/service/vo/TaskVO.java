package com.example.numbergenerator.service.vo;

import java.util.ArrayList;
import java.util.List;

public class TaskVO {

  private String id;
  private List<TaskDataVO> taskData = new ArrayList<>();

  public TaskVO() {}

  public TaskVO(String id, List<TaskDataVO> taskData) {
    this.id = id;
    this.taskData = taskData;
  }

  public TaskVO(String id, TaskDataVO taskData) {
    this.id = id;
    this.taskData.add(taskData);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<TaskDataVO> getTaskData() {
    return taskData;
  }

  public void setTaskData(List<TaskDataVO> taskData) {
    this.taskData = taskData;
  }

}
