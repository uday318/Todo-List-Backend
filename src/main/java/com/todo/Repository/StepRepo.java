package com.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.Entity.Step;

public interface StepRepo extends JpaRepository<Step, Long>{

}
