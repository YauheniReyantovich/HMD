package org.reyantovich.yauheni.service;

import org.reyantovich.yauheni.pojo.Developer;
import org.springframework.stereotype.Service;

@Service
public interface DeveloperService {
    Developer getDeveloper();
    void addDeveloper(Developer developer);

}
