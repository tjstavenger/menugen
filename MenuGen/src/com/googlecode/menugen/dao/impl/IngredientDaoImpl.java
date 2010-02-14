/**
 * 
 */
package com.googlecode.menugen.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.googlecode.menugen.dao.IngredientDao;
import com.googlecode.menugen.domain.Ingredient;

/**
 * Perform CRUD operations on {@link Ingredient}
 */
@Repository
public class IngredientDaoImpl extends DataAccessObjectImpl<Ingredient, Long>
		implements IngredientDao {

	/**
	 * Get all {@link Ingredient} in ascending order by name.
	 * 
	 * @return List of {@link Ingredient}
	 * 
	 * @see com.googlecode.menugen.dao.impl.DataAccessObjectImpl#findAll()
	 */
	@Override
	public List<Ingredient> findAll() {
		Criteria criteria = createCriteria();
		criteria.addOrder(Order.asc(Ingredient.NAME));
		List<Ingredient> ingredients = criteria.list();

		return ingredients;
	}
}