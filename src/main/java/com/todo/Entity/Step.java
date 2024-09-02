package com.todo.Entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Step {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Boolean isCompleted;
	
	@ManyToOne
	@JsonIncludeProperties({"id", "title"})
	private Task tasks;
	
	private String imageName;
	private String imageType;
	@Column(columnDefinition = "LONGBLOB")
	private byte[] imageData;

	public Step() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Step(Long id, String name, Boolean isCompleted, Task tasks) {
		super();
		this.id = id;
		this.name = name;
		this.isCompleted = isCompleted;
		this.tasks = tasks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Task getTasks() {
		return tasks;
	}

	public void setTasks(Task tasks) {
		this.tasks = tasks;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	
	
	
}
