package com.a2z.shop.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.a2z.shop.category.Category;
import com.a2z.shop.eo.CategoriesEO;

@Component
public class CategoriesBO {
	public static final Logger logger = LoggerFactory.getLogger(CategoriesBO.class);
	@Autowired
	
	CategoriesEO categoriesEO;
    String Vendor_Id;


	public String addCategoryProducts(Category Category) {
		
		logger.info("In EditCategoriesBO - EnterInDb()");
		String response = categoriesEO.addCategoryProducts(Category);
		return response;
	}

	public String getCategories(int vendorId) {
		String response = categoriesEO.getCategories(vendorId);

		return response;
	}






}
