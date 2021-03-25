package com.rocket.test.service;


import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rocket.test.component.FileComponent;

@Service
public class TestService implements ITestService {

	@Autowired
	private FileComponent fileComponent;
	
	public Set<String> find(String keyword, MultipartFile csv) {
		return fileComponent.readFile(csv).stream()
			.filter(s -> s.contains(keyword))
			.collect(Collectors.toSet()); 
	}
}
