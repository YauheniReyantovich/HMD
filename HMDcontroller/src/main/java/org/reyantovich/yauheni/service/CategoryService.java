package org.reyantovich.yauheni.service;

import org.reyantovich.yauheni.model.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> getAllCategories();

    void addCategory(Category category);

}
