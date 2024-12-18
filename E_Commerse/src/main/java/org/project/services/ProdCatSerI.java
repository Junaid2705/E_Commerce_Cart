package org.project.services;

import org.project.models.ProductCategories;
import java.util.List;

public interface ProdCatSerI {

    boolean addCategory(String categoryName);

    List<ProductCategories> getAllCategories();

    boolean removeCategory(int categoryId);
}
