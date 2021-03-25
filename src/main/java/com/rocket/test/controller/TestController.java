package com.rocket.test.controller;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rocket.test.model.ResponseModel;
import com.rocket.test.service.ITestService;

@RestController
@RequestMapping(path = "v1/test")
public class TestController {
	
    @Autowired
    private ITestService service;
    
    @GetMapping(path = "/hello")
    public String greets() {
    	return "Hello";
    }
    
	@GetMapping(path = "/find")
	public ResponseEntity<ResponseModel> search(@RequestParam String filter, 
			@RequestPart MultipartFile csv) {
		if(!csv.getOriginalFilename().endsWith(".csv")) {
			return ResponseEntity.badRequest().header("error", "Please select a csv file").build();
		}
		try {
			Set<String> response = this.service.find(filter, csv);
			if(response.size() == 0) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(new ResponseModel(LocalDateTime.now(), response, "success"));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
		}
	}
}
