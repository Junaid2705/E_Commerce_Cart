package org.project.repository;

import java.util.List;


import org.project.models.ProductCategories;
import java.util.List;

public interface ProdCatRepoI {

    boolean addCategory(String categoryName);

    List<ProductCategories> getAllCategories();

    boolean removeCategory(int categoryId);
}
