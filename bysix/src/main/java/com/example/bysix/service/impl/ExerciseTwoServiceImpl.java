package com.example.bysix.service.impl;


import com.example.bysix.service.ExerciseTwoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unchecked")
public class ExerciseTwoServiceImpl implements ExerciseTwoService {

	private final List<Map<String, Object>> LANGUAGES;

	public ExerciseTwoServiceImpl(@Value("classpath:countries.json") Resource resource) throws IOException {
		InputStream inputStream = resource.getInputStream();
		ObjectMapper objectMapper = new ObjectMapper();
		LANGUAGES = objectMapper.readValue(inputStream, List.class);
	}

	@Override
	public int getCountriesCount() {
		return LANGUAGES.size();
	}

	@Override
	public String getCountryWithMostOfficialGermanLanguages() {
		return LANGUAGES.stream()
				.filter(country -> country.get("languages").toString().contains("de"))
				.max(Comparator.comparingInt(country -> ((List<String>) country.get("languages")).size()))
				.map(country -> country.get("country").toString())
				.orElse("No country found");
	}

	@Override
	public int getTotalLanguagesCount() {
		return LANGUAGES.stream()
				.flatMap(country -> ((List<String>) country.get("languages")).stream())
				.distinct()
				.collect(Collectors.toList())
				.size();
	}

	@Override
	public String getCountryWithMostLanguages() {
		return LANGUAGES.stream()
				.max(Comparator.comparingInt(country -> ((List<String>) country.get("languages")).size()))
				.orElseThrow(() -> new RuntimeException("No country found"))
				.get("country").toString();
	}

	@Override
	public List<String> getMostCommonLanguages() {
		List<String> languages = LANGUAGES.stream()
				.flatMap(country -> ((List<String>) country.get("languages")).stream())
				.collect(Collectors.toList());

		List<String> mostCommonLanguages = new ArrayList<>();
		int maxCount = 0;
		for (String language : languages) {
			int count = (int) languages.stream().filter(l -> l.equals(language)).count();
			if (count > maxCount) {
				mostCommonLanguages.clear();
				mostCommonLanguages.add(language);
				maxCount = count;
			} else if (count == maxCount && !mostCommonLanguages.contains(language)) {
				mostCommonLanguages.add(language);
			}
		}
		return mostCommonLanguages;
	}


}
