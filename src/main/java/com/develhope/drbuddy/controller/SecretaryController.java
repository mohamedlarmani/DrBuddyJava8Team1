package com.develhope.drbuddy.controller;


import com.develhope.drbuddy.entities.Secretary;
import com.develhope.drbuddy.service.SecretaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;


@RestController
@RequestMapping("/Secretary")
public class SecretaryController {


    @Autowired
    private SecretaryService secretaryService;

    @PostMapping
    public Secretary SecretaryRepository(@RequestBody Secretary secretary) {
        return secretaryService.saveSecretary(secretary);
    }

    @GetMapping("/{secretaryId}")
    public Optional<Secretary> getSecretaryById(@PathVariable int secretaryId) {
        return secretaryService.getSecretaryById(secretaryId);
    }
}