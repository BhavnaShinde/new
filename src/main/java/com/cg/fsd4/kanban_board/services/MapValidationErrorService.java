package com.cg.fsd4.kanban_board.services;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MapValidationErrorService {
	/**
	 * creating mapValidationErrorMethod which returns the errorMap when is encounters
	 * and errors and null if there are no errors
	 * 
	 * @param result
	 * @return
	 */
	public ResponseEntity<?> mapValidationError(BindingResult result) {
		if (result.hasErrors()) {
			Map<String, String>errorMap = new HashMap<>();
			for(FieldError fielderror : result.getFieldErrors()) {
				errorMap.put(fielderror.getField(), fielderror.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}