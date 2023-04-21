package com.develhope.drbuddy.controller;


import com.develhope.drbuddy.entities.dto.SecretaryRequestDto;
import com.develhope.drbuddy.entities.dto.SecretaryResponseDto;
import com.develhope.drbuddy.repository.SecretaryRepository;
import com.develhope.drbuddy.service.SecretaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Secretary")
public class SecretaryController {


    @Autowired
    private SecretaryRepository secretaryRepository;

    @Autowired
    private SecretaryService secretaryService;

    @PostMapping("/postSecretary")
    public SecretaryResponseDto postSecretary(@RequestBody SecretaryRequestDto request){
        return secretaryService.postSecretary(request);
    }

    @GetMapping("/getSecretary/{secretaryById}")
    public SecretaryResponseDto getSecretaryById(@PathVariable int secretaryId) {
        return secretaryService.getSecretary(secretaryId);
    }

    @PutMapping("/putSecretary/{id}")
    public SecretaryResponseDto putSecretary(@PathVariable int secretaryId, @RequestBody SecretaryRequestDto request){
        return secretaryService.putSecretary(secretaryId, request);
    }

    @DeleteMapping("/delete/{id}")
    public SecretaryResponseDto delete (@PathVariable int secretaryId){
        return secretaryService.delete(secretaryId);
    }


}