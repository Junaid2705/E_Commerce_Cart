package org.project.repository;
import java.util.List;
import org.project.models.ProductCatModel;

public interface ProductCatRepo {
	
	 boolean isProductCat(ProductCatModel procat);

	    List<ProductCatModel> getAllProductCats();
	    
	    boolean addProductCat(ProductCatModel procat);
	    List<ProductCatModel> getProductCatsByName(String name);
	    ProductCatModel getProductCatById(int id);
	    boolean updateProductCat(int id, ProductCatModel procat);
	    boolean deleteProductCat(int id);

}