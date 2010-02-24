/**
 * 
 */
package com.googlecode.menugen.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.menugen.dao.RecipeDao;
import com.googlecode.menugen.domain.Recipe;

/**
 * Perform CRUD operations on {@link Recipe}
 */
@Repository
public class RecipeDaoImpl extends DataAccessObjectImpl<Recipe, Long> implements
		RecipeDao {

	/**
	 * Search for recipes matching the given criteria.
	 * 
	 * @param criteria
	 *            String search criteria
	 * @return List of {@link Recipe} matching the given criteria
	 */
	@Override
	public List<Recipe> search(String criteria) {
		return findAll();
	}
}
