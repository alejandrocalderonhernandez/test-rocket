package com.rocket.test.service;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface ITestService {
	
	public Set<String> find(String keyword, MultipartFile csv);
	
}
