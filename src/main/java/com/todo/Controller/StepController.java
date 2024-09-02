package com.todo.Controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.Entity.GenericResponseEntity;
import com.todo.Entity.Step;
import com.todo.Error.DataValidationException;
import com.todo.Service.StepService;

@RestController
@RequestMapping("/step")
public class StepController {

	@Autowired
	private StepService stepService;

	// create Step
//	@PostMapping("/create")
	@PostMapping(value = "/create")
	public ResponseEntity<?> createStep(@RequestParam String step,@RequestParam MultipartFile imageFile)
			throws DataValidationException, IOException {
		
		try {
			
			ObjectMapper objectMapper = new ObjectMapper();
			Step stepp = objectMapper.readValue(step, Step.class);
		Step step1 = stepService.createStep(stepp,imageFile);
		return new ResponseEntity<>(step1 , HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get step By id
	@GetMapping("/getStepById/{id}")
	public Optional<Step> getStepById(@PathVariable Long id) {
		return stepService.getStepById(id);
	}
	
	@GetMapping("/getAllPage")
	public ResponseEntity<Page<Step>> getAllStepsByPagination(
			@RequestParam(defaultValue = "0") int page ,@RequestParam(defaultValue = "20") int size){
		
		Page<Step> steps = stepService.getAllStepsByPagination(page,size);
		return ResponseEntity.ok(steps);
	}

	// update Step
	@PutMapping("/update/{id}")
	public ResponseEntity<GenericResponseEntity> updateStep(@PathVariable Long id, @RequestBody Step step) {
		GenericResponseEntity response = stepService.updateStep(id, step);
		return ResponseEntity.status(response.getCode()).body(response);
	}

    // delete step
	@DeleteMapping("/delete/{id}")
	public void deleteStep(@PathVariable Long id) {
		stepService.deleteStep(id);
	}

}
