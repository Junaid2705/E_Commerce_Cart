
package org.project.services;

import java.util.List;

import org.project.models.ProductCatModel;
import org.project.repository.ProductCatRepo;
import org.project.repository.ProductCatRepoImpl;

public class ProductCatServiceImpl implements ProductCatService {

    //  repository object-> 
    ProductCatRepo productrepo = new ProductCatRepoImpl();

    @Override
    public boolean isProductCat(ProductCatModel procat) {
        // Cal  repository method to check  product category exists
        return productrepo.isProductCat(procat); 
    }
    
    
    @Override
    public List<ProductCatModel> getAllProductCats() {
    	
    	 // cal repo method get all categories
        return productrepo.getAllProductCats(); 
    }
    
    
    @Override
    public boolean addProductCat(ProductCatModel procat) {
    	
    	
//    	Call  repo method fr add category
        return productrepo.addProductCat(procat);  
    }


	@Override
	public List<ProductCatModel> getProductCatsByName(String name) {
		// TODO Auto-generated method stub
		  return productrepo.getProductCatsByName(name);
	}


	@Override
	public ProductCatModel getProductCatById(int id) {
		// TODO Auto-generated method stub
		return productrepo.getProductCatById(id);
	}


	@Override
	public boolean updateProductCat(int id, ProductCatModel procat) {
		// TODO Auto-generated method stub
		return productrepo.updateProductCat(id, procat);
	}


	@Override
	public boolean deleteProductCat(int id) {
		// TODO Auto-generated method stub
		return productrepo.deleteProductCat(id);
	}
}