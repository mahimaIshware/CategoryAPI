package com.a2z.shop.eo;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2z.shop.category.Category;
import com.a2z.shop.dao.CategoriesDAO;
import com.a2z.shop.model.Product;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CategoriesEO {
	public static Logger logger = LoggerFactory.getLogger(CategoriesEO.class);
	@Autowired
	CategoriesDAO categoriesDAO;
	String vendorId;
	@Autowired
	private transient DataSource datasource;

	public String addCategoryProducts(Category Category) {
   
		logger.info("In EditCategoriesEO - EnterInDb()");
		String Response="Error Entering Categories Details";

		StringBuilder responseBuilder=new StringBuilder(" ADDING UPDATING USER");
		try{
			responseBuilder = new CategoriesDAO(datasource).addCategoryProducts(Category);
		}catch(Exception e)
		{
			logger.info("EXCEPTION IN EO - Add Categories Info: {}"+ e.getMessage(), "MESAGE");
		}
		return responseBuilder.toString();
	}

	public String getCategories(int vendorId) {
		ArrayList<Category> categoryList;
		String response = "Getting Categories as per vendorID";
		try{
			categoryList = categoriesDAO.getCategories(vendorId);
			ObjectMapper objmapper = new ObjectMapper();
			response = objmapper.writeValueAsString(categoryList);
		}catch(Exception e)
		{
			logger.info("EXCEPTION IN EO - GET Categories: {}"+ e.getMessage(), e);
		}
		return response;
	}

	
	

	

}
