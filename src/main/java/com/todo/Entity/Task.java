package com.todo.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String description;
	private LocalDateTime remindDateTime;
	private LocalDateTime dueDateTime;
	@Enumerated(EnumType.STRING)
	private RepeatEnum repeatType;
	private Boolean isCompleted;
	private Boolean isImportant;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id", "title"})
	private TodoList todoList;
	
	@OneToMany(mappedBy = "tasks", cascade = CascadeType.ALL ,orphanRemoval = true)
	@JsonIncludeProperties({"id", "name" ,"isCompleted"})
	private List<Step> steps = new ArrayList<>();

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task(Long id, String title, String description, LocalDateTime remindDateTime, LocalDateTime dueDateTime,
			RepeatEnum repeatType, Boolean isCompleted, Boolean isImportant, TodoList todoList, List<Step> steps) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.remindDateTime = remindDateTime;
		this.dueDateTime = dueDateTime;
		this.repeatType = repeatType;
		this.isCompleted = isCompleted;
		this.isImportant = isImportant;
		this.todoList = todoList;
		this.steps = steps;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getRemindDateTime() {
		return remindDateTime;
	}

	public void setRemindDateTime(LocalDateTime remindDateTime) {
		this.remindDateTime = remindDateTime;
	}

	public LocalDateTime getDueDateTime() {
		return dueDateTime;
	}

	public void setDueDateTime(LocalDateTime dueDateTime) {
		this.dueDateTime = dueDateTime;
	}

	public RepeatEnum getRepeatType() {
		return repeatType;
	}

	public void setRepeatType(RepeatEnum repeatType) {
		this.repeatType = repeatType;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Boolean getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(Boolean isImportant) {
		this.isImportant = isImportant;
	}

	public TodoList getTodoList() {
		return todoList;
	}

	public void setTodoList(TodoList todoList) {
		this.todoList = todoList;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	
	
}
