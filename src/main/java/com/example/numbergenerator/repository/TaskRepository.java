package com.example.numbergenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.numbergenerator.repository.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

}
