/**
 * 
 */
package com.tjpu.wtf.dao;

import java.util.List;

import com.tjpu.wtf.model.Category;
import com.tjpu.wtf.model.PageModel;

/**
 * @author hhz.zm
 *
 */
public interface CategoryDao {
	boolean addCategory(Category category);

	public List<Category> findAll();
	public boolean delete(int id);

	public Category findById(int id);

	boolean update(Category category);

	boolean findByName(String name);

	List<Category> findAllByCategoryName(String categoryName);

	PageModel<Category> findAll2(int pageNo, int pageSize);

	PageModel<Category> findAllByCategoryName2(String categoryName,
			int parseInt, int pageSize);
}
