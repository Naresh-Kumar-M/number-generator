package com.example.numbergenerator.service;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.example.numbergenerator.repository.TaskDataRepository;
import com.example.numbergenerator.repository.TaskResultRepository;
import com.example.numbergenerator.repository.entity.TaskData;
import com.example.numbergenerator.repository.entity.TaskResult;
import com.example.numbergenerator.repository.entity.TaskStatus;

@Component
public class NumberGenerationTaskProcessor {

  private final TaskDataRepository taskDataRepository;
  private final TaskResultRepository taskResultRepository;

  public NumberGenerationTaskProcessor(final TaskDataRepository taskDataRepository,
      final TaskResultRepository taskResultRepository) {
    this.taskDataRepository = taskDataRepository;
    this.taskResultRepository = taskResultRepository;
  }


  @Scheduled(fixedDelayString = "${numbergeneration.scheduler.interval:10000}")
  public void reconcileLicensesWithLicenseServer() {
    List<String> queuedTaskIds = taskDataRepository.findTaskDataIdByStatus(TaskStatus.QUEUED);
    updateTaskStatusToInProgress(queuedTaskIds);
    for (String taskId : queuedTaskIds) {
      processTask(taskId);     
    }
  }

  @Transactional
  private void updateTaskStatusToInProgress(List<String> taskIds) {
    for (String taskId : taskIds) {
      TaskData taskData = taskDataRepository.findById(taskId).get();
      taskData.setStatus(TaskStatus.IN_PROGRESS);
      taskDataRepository.save(taskData);
    }
  }

  @Transactional
  private void processTask(final String taskId) {
    TaskData taskData = taskDataRepository.findById(taskId).get();
    try {
      Optional<TaskResult> result =
          taskResultRepository.findByGoalAndStep(taskData.getGoal(), taskData.getStep());
      if (result.isPresent()) {
        taskData.setResult(result.get());
      } else {
        String output = generateNumbers(taskData.getGoal(), taskData.getStep());
        TaskResult taskResult =
            new TaskResult(null, taskData.getGoal(), taskData.getStep(), output);
        taskResult = taskResultRepository.save(taskResult);
        taskData.setResult(taskResult);
      }
      taskData.setStatus(TaskStatus.SUCCESS);
    } catch (Exception e) {
      taskData.setStatus(TaskStatus.ERROR);
    }
    taskDataRepository.save(taskData);
  }

  private String generateNumbers(long goal, long step) {
    // Sleep for random time between 10 to 30 seconds
    sleep(ThreadLocalRandom.current().nextInt(10, 30));
    StringJoiner stringJoiner = new StringJoiner(",");
    while (goal >= 0) {
      stringJoiner.add(Long.toString(goal));
      goal -= step;
    }
    return stringJoiner.toString();
  }

  private void sleep(int seconds) {
    try {
      Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
