package com.example.bysix.controller;
import com.example.bysix.service.ExerciseTwoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RestController
@RequestMapping("/exerciseTwo")
public class ExerciseTwoController {

    private ExerciseTwoService exerciseTwoService;

    public ExerciseTwoController(ExerciseTwoService exerciseTwoService) {
        this.exerciseTwoService = exerciseTwoService;
    }

    @GetMapping("/countries/count")
    @ResponseStatus(HttpStatus.OK)
    public int getCountriesCount() {
        return exerciseTwoService.getCountriesCount();
    }

    @GetMapping("/countries/max-german-languages")
    @ResponseStatus(HttpStatus.OK)
    public String getCountryWithMostOfficialGermanLanguages() {
        return exerciseTwoService.getCountryWithMostOfficialGermanLanguages();
    }

    @GetMapping("/languages/count")
    @ResponseStatus(HttpStatus.OK)
    public int getTotalLanguagesCount() {
        return exerciseTwoService.getTotalLanguagesCount();
    }

    @GetMapping("/countries/most-languages")
    @ResponseStatus(HttpStatus.OK)
    public String getCountryWithMostLanguages() {
        return exerciseTwoService.getCountryWithMostLanguages();
    }

    @GetMapping("/languages/most-common")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getMostCommonLanguages() {
        return exerciseTwoService.getMostCommonLanguages();
    }


}
