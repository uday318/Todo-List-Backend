package com.todo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.Entity.GenericResponseEntity;
import com.todo.Entity.Task;
import com.todo.Error.DataValidationException;
import com.todo.Service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

	// create Task
	@PostMapping("/create")
	public ResponseEntity<GenericResponseEntity> createTask(@RequestBody Task task) throws DataValidationException {

		GenericResponseEntity response = taskService.createTask(task);
		return ResponseEntity.status(response.getCode()).body(response);
	}

	@GetMapping("getById/{id}")
	public Optional<Task> getTaskById(@PathVariable Long id) {
		return taskService.getTaskById(id);

	}

	@GetMapping("/getAll")
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}

	@GetMapping("/getAllPage")
	public ResponseEntity<Page<Task>> getAllTasksByPagination(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size) {

		Page<Task> tasks = taskService.getAllTasksByPagination(page, size);
		return ResponseEntity.ok(tasks);
	}

	@GetMapping("/getTasksByListId/{todoListId}")
	public List<Task> getTasksByListId(@PathVariable Long todoListId) {
		return taskService.getTasksByListId(todoListId);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<GenericResponseEntity> updateTask(@PathVariable Long id, @RequestBody Task task)
			throws DataValidationException {
		GenericResponseEntity response = taskService.updateTask(id, task);
		return ResponseEntity.status(response.getCode()).body(response);
	}

	// delete task with its all Steps
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<GenericResponseEntity> deleteTask(@PathVariable Long id) {

		GenericResponseEntity response = taskService.deleteTask(id);
		return ResponseEntity.status(response.getCode()).body(response);
	}

	// Mark Multiple tasks are important
	@PutMapping("/markImportant")
	public ResponseEntity<GenericResponseEntity> markTasksAsImportant(@RequestBody List<Long> taskIds) {
		GenericResponseEntity response = taskService.markTasksAsImportant(taskIds);
		return ResponseEntity.status(response.getCode()).body(response);

	}

	// Remove Multiple tasks are important
	@PutMapping("/removeImportant")
	public ResponseEntity<GenericResponseEntity> removeTasksAsImportant(@RequestBody List<Long> taskIds) {
		GenericResponseEntity response = taskService.removeTasksAsImportant(taskIds);
		return ResponseEntity.status(response.getCode()).body(response);

	}

}
