package com.example.numbergenerator.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.numbergenerator.repository.entity.TaskData;
import com.example.numbergenerator.repository.entity.TaskStatus;

@Repository
public interface TaskDataRepository extends JpaRepository<TaskData, String> {

  @Query("SELECT t.id FROM TaskData t where t.status = :status")
  List<String> findTaskDataIdByStatus(@Param("status") TaskStatus status);
}
