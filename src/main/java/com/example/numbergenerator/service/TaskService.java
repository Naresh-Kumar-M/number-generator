package com.example.numbergenerator.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.numbergenerator.exception.TaskNotFoundException;
import com.example.numbergenerator.repository.TaskRepository;
import com.example.numbergenerator.repository.entity.Task;
import com.example.numbergenerator.repository.entity.TaskData;
import com.example.numbergenerator.repository.entity.TaskStatus;
import com.example.numbergenerator.service.vo.TaskVO;

@Service
public class TaskService {

  private final TaskRepository taskRepository;
  private final DozerBeanMapper mapper;

  public TaskService(final TaskRepository taskRepository,
      final DozerBeanMapper mapper) {
    this.taskRepository = taskRepository;
    this.mapper = mapper;
  }

  @Transactional
  public TaskVO createTask(TaskVO taskVO) {
    Task task = mapper.map(taskVO, Task.class);
    task = taskRepository.save(task);
    return mapper.map(task, TaskVO.class);
  }

  @Transactional(readOnly = true)
  public TaskStatus getTaskStatus(String taskId) {
    Optional<Task> task = taskRepository.findById(taskId);
    if (task.isEmpty()) {
      throw new TaskNotFoundException();
    }
    return getTaskStatus(task.get());
  }

  private TaskStatus getTaskStatus(Task task) {
    Set<TaskStatus> status = task.getTaskData()
        .stream()
        .map(TaskData::getStatus)
        .collect(Collectors.toSet());
    if (status.contains(TaskStatus.ERROR)) {
      return TaskStatus.ERROR;
    } else if (status.contains(TaskStatus.IN_PROGRESS)) {
      return TaskStatus.IN_PROGRESS;
    } else if (status.contains(TaskStatus.QUEUED)) {
      return TaskStatus.QUEUED;
    } else {
      return TaskStatus.SUCCESS;
    }
  }

  @Transactional(readOnly = true)
  public List<String> getTaskResult(String taskId) {
    Optional<Task> task = taskRepository.findById(taskId);
    if (task.isEmpty()) {
      throw new TaskNotFoundException();
    }
    TaskStatus status = getTaskStatus(task.get());
    if (status != TaskStatus.SUCCESS) {
      return List.of(status.name());
    }
    return task.get().getTaskData()
        .stream()
        .map(taskData -> taskData.getResult().getResult())
        .collect(Collectors.toList());
  }

}
