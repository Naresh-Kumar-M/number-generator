package com.example.numbergenerator.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.numbergenerator.repository.entity.TaskResult;

@Repository
public interface TaskResultRepository extends JpaRepository<TaskResult, String> {

  @Query("FROM TaskResult t where t.goal = :goal and t.step = :step")
  Optional<TaskResult> findByGoalAndStep(@Param("goal") long goal, @Param("step") long step);
}
