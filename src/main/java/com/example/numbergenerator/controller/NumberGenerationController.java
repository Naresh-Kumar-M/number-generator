package com.example.numbergenerator.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.numbergenerator.controller.dto.BulkTaskResultsDTO;
import com.example.numbergenerator.controller.dto.SingleTaskResultDTO;
import com.example.numbergenerator.controller.dto.TaskDTO;
import com.example.numbergenerator.controller.dto.TaskResponseDTO;
import com.example.numbergenerator.controller.dto.TaskResultDTO;
import com.example.numbergenerator.controller.dto.TaskStatusDTO;
import com.example.numbergenerator.exception.InvalidTaskInputException;
import com.example.numbergenerator.exception.UnsupportedTaskActionException;
import com.example.numbergenerator.repository.entity.TaskStatus;
import com.example.numbergenerator.service.TaskService;
import com.example.numbergenerator.service.vo.TaskDataVO;
import com.example.numbergenerator.service.vo.TaskVO;

@Validated
@RestController
public class NumberGenerationController {

  private static final String ACTION_GET_NUMLIST = "get_numlist";
  private final TaskService taskService;
  private final DozerBeanMapper mapper;

  public NumberGenerationController(final TaskService taskService,
      final DozerBeanMapper mapper) {
    this.taskService = taskService;
    this.mapper = mapper;
  }

  @PostMapping("/api/generate")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public TaskResponseDTO createTask(@RequestBody TaskDTO task) {
    validateTaskInputs(task);
    TaskDataVO taskDataVO = mapper.map(task, TaskDataVO.class);
    TaskVO taskVO = new TaskVO(null, taskDataVO);
    taskVO = taskService.createTask(taskVO);
    return new TaskResponseDTO(taskVO.getId());
  }

  @PostMapping("/api/bulkGenerate")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public TaskResponseDTO createBulkTask(@RequestBody List<TaskDTO> taskList) {
    validateTaskInputs(taskList.toArray(new TaskDTO[0]));
    List<TaskDataVO> taskVoList = taskList.stream()       
        .map(task -> mapper.map(task, TaskDataVO.class))
        .collect(Collectors.toList());
    TaskVO taskVO = new TaskVO(null, taskVoList);
    taskVO = taskService.createTask(taskVO);
    return new TaskResponseDTO(taskVO.getId());
  }
  
  private void validateTaskInputs(TaskDTO... tasks) {
    for (TaskDTO task : tasks) {
      if (task.getGoal() < 0 || task.getStep() < 0) {
        throw new InvalidTaskInputException();
      }
    }
  }

  @GetMapping("/api/tasks/{taskId}/status")
  public TaskStatusDTO getTaskStatus(@PathVariable("taskId") final String taskId) {
    TaskStatus taskStatus = taskService.getTaskStatus(taskId);
    return new TaskStatusDTO(taskStatus.name());
  }

  @GetMapping("/api/tasks/{taskId}")
  public TaskResultDTO getTaskResult(@PathVariable("taskId") final String taskId,
      @RequestParam("action") final String action) {
    if (!StringUtils.equalsAnyIgnoreCase(action, ACTION_GET_NUMLIST)) {
      throw new UnsupportedTaskActionException();
    }
    List<String> result = taskService.getTaskResult(taskId);        
    if (result.size() == 1) {
      return new SingleTaskResultDTO(result.get(0));
    } else {
      return new BulkTaskResultsDTO(result);
    }
  }

}
