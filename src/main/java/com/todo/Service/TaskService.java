package com.todo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.todo.Entity.GenericResponseEntity;
import com.todo.Entity.Step;
import com.todo.Entity.Task;
import com.todo.Error.DataValidationException;
import com.todo.Repository.TaskRepo;

@Service
public class TaskService {

	@Autowired
	private TaskRepo taskRepo;

	// create task
	public GenericResponseEntity createTask(Task task) {

		if (task.getTitle() == null || task.getTitle().isEmpty()) {
			throw new DataValidationException("Task title cannot be null!!");
		}
		
		
		for (Step step : task.getSteps()) {
            step.setTasks(task);  
        }
		taskRepo.save(task);
		return new GenericResponseEntity(200, "Task save successfully");
	}

	// get task details by id
	public Optional<Task> getTaskById(Long id) {
		return taskRepo.findById(id);
	}

	// get All Task List
	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}

	// get tasks by list id
	public List<Task> getTasksByListId(Long todoListId) {
		return taskRepo.findAllByTodoListId(todoListId);
	}

	// update task
	public GenericResponseEntity updateTask(Long id, Task task) {
		Task taskId = taskRepo.findById(id).orElseThrow(() -> new DataValidationException("TodoList not found"));

		if (task.getTitle() == null || task.getTitle().isEmpty()) {
			throw new DataValidationException("Task title cannot be null!!");
		}
		taskId.setTitle(task.getTitle());
		taskId.setRemindDateTime(task.getRemindDateTime());
		taskId.setDueDateTime(task.getDueDateTime());
		taskId.setDescription(task.getDescription());
		taskId.setIsCompleted(task.getIsCompleted());
		taskId.setIsImportant(task.getIsImportant());
		taskId.setRepeatType(task.getRepeatType());
		taskId.setSteps(task.getSteps());

		taskRepo.save(taskId);
		return new GenericResponseEntity(200, "Task Updated successfully");
	}

	// delete task with its steps
	public GenericResponseEntity deleteTask(Long id) {
		Task taskId = taskRepo.findById(id).orElseThrow(() -> new DataValidationException("Task not found"));

		taskRepo.delete(taskId);
		return new GenericResponseEntity(200, "Task Deleted successfully");

	}

	public GenericResponseEntity markTasksAsImportant(List<Long> taskIds) {
		List<Task> tasks = taskRepo.findAllById(taskIds);
		for (Task task : tasks) {
			task.setIsImportant(true);
		}
		taskRepo.saveAll(tasks);
		return new GenericResponseEntity(200, "Task Marked As Important");
	}

	public GenericResponseEntity removeTasksAsImportant(List<Long> taskIds) {
		List<Task> tasks = taskRepo.findAllById(taskIds);
		for (Task task : tasks) {
			task.setIsImportant(false);
		}
		taskRepo.saveAll(tasks);
		return new GenericResponseEntity(200, "Task Removed From Important");
	}

	public Page<Task> getAllTasksByPagination(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return taskRepo.findAll(pageable);
	}
}
