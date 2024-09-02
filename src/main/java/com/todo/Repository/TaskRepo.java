package com.todo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.Entity.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {
	
	List<Task> findAllByTodoListId(Long id);
	

}
