package org.reyantovich.yauheni.service.impl;

import org.reyantovich.yauheni.dao.model.CategoryDao;
import org.reyantovich.yauheni.model.pojo.Category;
import org.reyantovich.yauheni.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Override
    public void addCategory(Category category) {
        categoryDao.addCategory(category);
    }

    @Autowired
    CategoryServiceImpl(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }

}
