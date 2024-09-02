package com.todo.Service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.todo.Entity.GenericResponseEntity;
import com.todo.Entity.Step;
import com.todo.Error.DataValidationException;
import com.todo.Repository.StepRepo;

@Service
public class StepService {

	@Autowired
	private StepRepo stepRepo;

	public Step createStep(Step step, MultipartFile imageFile) throws IOException {

		if (step.getName() == null || step.getName().isEmpty()) {
			throw new DataValidationException("Name Cannot null");
		}

		if (step.getTasks() == null) {
			throw new DataValidationException("Task cannot be null");
		}

		step.setImageName(imageFile.getOriginalFilename());
		step.setImageType(imageFile.getContentType());
		step.setImageData(imageFile.getBytes());

		return stepRepo.save(step);
		
	}

	public Optional<Step> getStepById(Long id) {
		return stepRepo.findById(id);
	}

	public GenericResponseEntity updateStep(Long id, Step step) {
		Step stepId = stepRepo.findById(id).orElseThrow(() -> new DataValidationException("Step not found"));

		stepId.setName(step.getName());
		stepId.setIsCompleted(step.getIsCompleted());
		stepRepo.save(stepId);
		return new GenericResponseEntity(200, "Task Updated successfully");
	}

	public void deleteStep(Long id) {
		Step stepId = stepRepo.findById(id).orElseThrow(() -> new DataValidationException("Step not found"));
		stepRepo.delete(stepId);

	}

	public Page<Step> getAllStepsByPagination(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return stepRepo.findAll(pageable);				
	}
}
