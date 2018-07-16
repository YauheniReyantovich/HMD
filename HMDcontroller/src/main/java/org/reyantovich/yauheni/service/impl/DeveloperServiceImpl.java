package org.reyantovich.yauheni.service.impl;

import org.reyantovich.yauheni.pojo.Developer;
import org.reyantovich.yauheni.runner.DeveloperRunner;
import org.reyantovich.yauheni.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeveloperServiceImpl implements DeveloperService{

    private DeveloperRunner developerRunner;

    public Developer getDeveloper(){
        return developerRunner.getDeveloper(16);
    }

    public void addDeveloper(Developer developer){
        developerRunner.addDeveloper(
                developer.getFirstName(),
                developer.getLastName(),
                developer.getSpecialty(),
                developer.getExperience()
        );
    }

    @Autowired
    public DeveloperServiceImpl(DeveloperRunner developerRunner) {
        this.developerRunner = developerRunner;
    }
}
