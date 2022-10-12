package com.felixlaura.service;

import com.felixlaura.binding.CitizenApp;
import com.felixlaura.entity.CitizenAppEntity;
import com.felixlaura.repository.CitizenAppRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CitizenAppServiceImpl implements CitizenAppService{

    private CitizenAppRepository repository;

    @Override
    public Integer createApplication(CitizenApp app) {
        String endpoint = "https://ssa-web-api.herokuapp.com/ssn/{ssn}";
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resEntity = restTemplate.getForEntity(endpoint, String.class, app.getSsn());

        String stateName = resEntity.getBody();

        if("New Jersey".equals(stateName)){
            //Create Application
            CitizenAppEntity entity = new CitizenAppEntity();
            BeanUtils.copyProperties(app, entity);
            entity.setStateName(stateName);

            CitizenAppEntity save = repository.save(entity);
            return save.getAppId();
        }
        return 0;  
    }
}
