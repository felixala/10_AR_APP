package com.felixlaura.controller;

import com.felixlaura.binding.CitizenApp;
import com.felixlaura.service.CitizenAppService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ArRestController {

    private CitizenAppService service;

    @PostMapping("/app")
    public ResponseEntity<String> createApplication(@RequestBody CitizenApp app){

        Integer appId = service.createApplication(app);

        if(appId > 0){
            return new ResponseEntity<>("App created with App id " + appId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid SSN", HttpStatus.BAD_REQUEST);
        }
    }

}
