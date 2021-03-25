package com.rocket.test.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileComponent {

	
	private static final Logger log = LoggerFactory.getLogger(FileComponent.class);

	public Set<String> readFile(MultipartFile file) {
		try {
			StringBuilder sb = new StringBuilder();
			InputStream inputStream = file.getInputStream();
			new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
			                    .lines()
			                    .forEach(l -> {
			                    		if(!l.equalsIgnoreCase("Name,Url")) {
			                    			sb.append(l).append(",");
			                    		}      		
			                    });
			return this.filterPokemon(sb.toString());
		} catch (IOException e) {
			log.error("Error to read file", e);
			throw new IllegalArgumentException();
		}
	}
	
	private Set<String> filterPokemon(String csvString) {
		 return Arrays.stream(csvString.split(","))
			.filter(s -> !s.startsWith("https"))
			.collect(Collectors.toSet()); 
	}
}
