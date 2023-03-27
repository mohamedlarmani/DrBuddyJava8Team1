package com.develhope.drbuddy.controller;


import com.develhope.drbuddy.entities.Secretary;
import com.develhope.drbuddy.entities.dto.SecretaryResponseDto;
import com.develhope.drbuddy.repository.SecretaryRepository;
import com.develhope.drbuddy.service.SecretaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@RestController
@RequestMapping("/Secretary")
public class SecretaryController {


    @Autowired
    private SecretaryService secretaryService;

    @GetMapping("/getSecretary/{secretaryById}")
    public SecretaryResponseDto getSecretaryById(@PathVariable int secretaryId) {
        return secretaryService.getSecretary(secretaryId);
    }

}