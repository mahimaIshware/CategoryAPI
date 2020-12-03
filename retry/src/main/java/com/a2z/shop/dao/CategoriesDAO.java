package com.a2z.shop.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Component;

import com.a2z.shop.category.Category;

@Component
public class CategoriesDAO {
	public static final Logger logger = org.slf4j.LoggerFactory.getLogger(CategoriesDAO.class);
	
	private transient static JdbcTemplate jdbcTemplate;
	@Autowired
	private transient DataSource datasource;
	
	public CategoriesDAO(DataSource datasource)throws SQLException {
		logger.info("Inside the test clean DAO");
		this.datasource = datasource;
		jdbcTemplate = new JdbcTemplate(this.datasource);
	}

	public StringBuilder addCategoryProducts(Category Category) {
		StringBuilder response=new StringBuilder("Successfully adding Categories");
		logger.info("In TestCleanDAO - EnterInDb()");
	
		try {
		String query="INSERT INTO vendor_category_details (categoryName,categoryDescription,vendorId,categoryStatus,create_ts,update_ts) VALUES(?,?,?,?,?,?)";
			jdbcTemplate.update((Connection connection)->{
				PreparedStatement st=connection.prepareStatement(query);
				st.setString(1,Category.getCategoryName());                
				st.setString(2,Category.getCategoryDescription());      
				st.setInt(3, Category.getVendorId());
				st.setString(4, Category.getCategoryStatus());
				st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
				st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
				
				int i=st.executeUpdate();
				if(i>0)
				{
					logger.info("successfully inserted");
				}
			return st;
			});
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return response;
	}
	
	
	
	public ArrayList<Category> getCategories(int vendorId) {
		final String methodName = "getCategories()";
		logger.info("{}: Logger DB ", methodName);
		ArrayList<Category> categorysList= new ArrayList<Category>();
		String get_Category_Query="SELECT categoryName,categoryDescription,categoryStatus FROM vendor_category_details WHERE vendorId="+vendorId;
		try {
			logger.info("get_Category_Query:"+get_Category_Query+":"+vendorId);
			
			 jdbcTemplate.query(get_Category_Query, new RowMapper<Object>(){
				 public ArrayList<Category> mapRow(ResultSet rs,int rowNum) throws SQLException {
					 logger.info("extractData()....");
					while(rs.next()) 
					{
					    Category Category = new Category();
						Category.setCategoryName(rs.getString(1));
						Category.setCategoryDescription(rs.getString(2));
						Category.setCategoryStatus(rs.getString(3));
						
						categorysList.add(Category);
					}
					return categorysList;
				}
			});
			logger.info("GETTING CATEGORIES..");
			
			
		}
		catch(Exception e)
		{
			logger.error("get_Category_Query:"+get_Category_Query+":"+vendorId+" Not working ");
			System.out.println(e);
		}
	
		return categorysList;
	}
	
}
