package com.example.bysix.service.impl;


import com.example.bysix.service.ExerciseOneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ExerciseOneServiceImpl implements ExerciseOneService {

	@Override
	public List<String> processNumbers(int limit) {
		List<String> numbers = IntStream.rangeClosed(1, limit)
				.mapToObj(i -> {
					if (i % 3 == 0 && i % 5 == 0) {
						return "Visual Nuts";
					} else if (i % 3 == 0) {
						return "Visual";
					} else if (i % 5 == 0) {
						return "Nuts";
					} else {
						return String.valueOf(i);
					}
				})
				.collect(Collectors.toList());

		return numbers;
	}

}
