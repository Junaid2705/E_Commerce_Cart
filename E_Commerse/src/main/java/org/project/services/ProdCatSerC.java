package org.project.services;

import org.project.repository.DBConfig;
import org.project.repository.ProdCatRepoC;

import org.project.models.ProductCategories;

import java.sql.Connection;
import java.util.List;

public class ProdCatSerC implements ProdCatSerI {

    private ProdCatSerC categoryRepo;

    public ProdCatSerC(Connection connection) {
        this.categoryRepo = new ProdCatSerC(connection);
    }

    @Override
    public boolean addCategory(String categoryName) {
        return categoryRepo.addCategory(categoryName);
    }

    @Override
    public List<ProductCategories> getAllCategories() {
        return categoryRepo.getAllCategories();
    }

    @Override
    public boolean removeCategory(int categoryId) {
        return categoryRepo.removeCategory(categoryId);
    }

	
}
