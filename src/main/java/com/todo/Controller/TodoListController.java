package com.todo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.Entity.GenericResponseEntity;
import com.todo.Entity.MoveTaskRequest;
import com.todo.Entity.TodoList;
import com.todo.Service.TodoListService;

@RestController
@RequestMapping("/todoList")
public class TodoListController {

	@Autowired
	private TodoListService todoListService;

	// create todoList
	@PostMapping("/create")
	public TodoList createTodoList(@RequestBody TodoList todoList) {
		return todoListService.createTodoList(todoList);
	}
	
	 @GetMapping("getById/{id}")
	    public Optional<TodoList> getTodoListById(@PathVariable Long id) {
	        return todoListService.getTodoListById(id);
	             
	    }

	@GetMapping("/getAll")
	public List<TodoList> getAllTodoLists() {
		return todoListService.getAllTodoLists();
	}
	
	 @PutMapping("/update/{id}")
	    public TodoList updateTodoList(@PathVariable Long id, @RequestBody TodoList todoListDetails) {
	        return todoListService.updateTodoList(id, todoListDetails);
	    }

	// delete list with its all tasks
	@DeleteMapping("/delete/{id}")
	public void deleteList(@PathVariable Long id) {
		todoListService.deleteList(id);
	}
	
	//mark as Completed
	@PutMapping("/markAsComplete")
	public ResponseEntity<GenericResponseEntity> markListIsCompleted(@RequestBody List<Long> listIds) {
		GenericResponseEntity response = todoListService.markListIsCompleted(listIds);
		return ResponseEntity.status(response.getCode()).body(response);

	}
	
	//Move task to another list
	 @PutMapping("/moveTask")
	    public ResponseEntity<GenericResponseEntity> moveTaskToAnotherList(@RequestBody MoveTaskRequest moveTask)  {
		 GenericResponseEntity response = todoListService.moveTaskToAnotherList(moveTask);
		 return ResponseEntity.status(response.getCode()).body(response);
	 }
}
