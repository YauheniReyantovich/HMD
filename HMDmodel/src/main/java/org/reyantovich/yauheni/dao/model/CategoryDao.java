package org.reyantovich.yauheni.dao.model;

import org.reyantovich.yauheni.model.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryDao {

    List<Category> getAllCategories();

    void addCategory(Category category);

}
