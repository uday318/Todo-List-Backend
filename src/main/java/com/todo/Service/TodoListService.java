package com.todo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.Entity.GenericResponseEntity;
import com.todo.Entity.MoveTaskRequest;
import com.todo.Entity.Step;
import com.todo.Entity.Task;
import com.todo.Entity.TodoList;
import com.todo.Error.DataValidationException;
import com.todo.Repository.TaskRepo;
import com.todo.Repository.TodoListRepo;

@Service
public class TodoListService {

	@Autowired
	private TodoListRepo todoListRepo;

	@Autowired
	private TaskRepo taskRepo;

	// create todoList
	public TodoList createTodoList(TodoList todoList) {

		return todoListRepo.save(todoList);
	}

	// delete list with its all tasks
	public void deleteList(Long id) {
		TodoList listId = todoListRepo.findById(id)
				.orElseThrow(() -> new DataValidationException("TodoList not found"));

		todoListRepo.delete(listId);

	}

	// get todoList By Id
	public Optional<TodoList> getTodoListById(Long id) {
		return todoListRepo.findById(id);
	}

	// get All TodoList
	public List<TodoList> getAllTodoLists() {
		return todoListRepo.findAll();
	}

	// update todoList
	public TodoList updateTodoList(Long id, TodoList todoList) {
		TodoList listId = todoListRepo.findById(id)
				.orElseThrow(() -> new DataValidationException("TodoList not found"));

		listId.setTitle(todoList.getTitle());

		return todoListRepo.save(listId);

	}

	// Mark List is Completed
	public GenericResponseEntity markListIsCompleted(List<Long> listIds) {
		List<TodoList> lists = todoListRepo.findAllById(listIds);
		for (TodoList todoList : lists) {
			for (Task task : todoList.getTasks()) {
				task.setIsCompleted(true);
				for (Step step : task.getSteps()) {
					step.setIsCompleted(true);
				}
			}
			todoList.setIsCompleted(true);
		}

		todoListRepo.saveAll(lists);
		return new GenericResponseEntity(200, "Marked As  Completed");
	}

	// Move task to another List
	public GenericResponseEntity moveTaskToAnotherList(MoveTaskRequest moveTask) {
		// List<Task> tasks =taskRepo.findAllByTodoListId(listId);
		Task task = taskRepo.findById(moveTask.getTaskId())
				.orElseThrow(() -> new DataValidationException("Task not found "));
		TodoList newList = todoListRepo.findById(moveTask.getListId())
				.orElseThrow(() -> new DataValidationException("TodoList not found"));
		
		task.setTodoList(newList);
		taskRepo.save(task);
		return new GenericResponseEntity(200, "Task Moved Successfully");
	}
}
