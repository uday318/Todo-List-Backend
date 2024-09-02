package com.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.Entity.TodoList;

public interface TodoListRepo extends JpaRepository<TodoList, Long> {


}
