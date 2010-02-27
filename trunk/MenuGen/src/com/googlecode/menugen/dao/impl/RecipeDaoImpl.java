/**
 * 
 */
package com.googlecode.menugen.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
	 * Find all recipes that serve at least the given number.
	 * 
	 * @param serves
	 *            number of servings
	 * @return List of {@link Recipe} that can serve the given number
	 */
	@Override
	public List<Recipe> findByServes(Integer serves) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.ge(Recipe.SERVES, serves));
		List<Recipe> recipes = criteria.list();

		return recipes;
	}

	/**
	 * Search for recipes matching the given criteria.
	 * 
	 * @param criteria
	 *            String search criteria
	 * @return List of {@link Recipe} matching the given criteria
	 */
	@Override
	public List<Recipe> search(String criteria) {
		Criteria crit = createCriteria();
		crit.add(Restrictions.ilike(Recipe.NAME, "%" + criteria + "%"));

		// TODO search ingredients and instructions? (subqueries)
		// Disjunction or = Restrictions.disjunction();
		// or.add(Restrictions.ilike(Recipe.NAME, "%" + criteria + "%"));
		// or.add(Restrictions.ilike(Recipe.INSTRUCTIONS, criteria));
		// or.add(Restrictions.ilike(Recipe.MEASURED_INGREDIENT_NAME,
		// criteria));
		// crit.add(or);

		List<Recipe> recipes = crit.list();

		return recipes;
	}
}
