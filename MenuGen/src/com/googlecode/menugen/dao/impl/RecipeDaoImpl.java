/**
 * 
 */
package com.googlecode.menugen.dao.impl;

import org.springframework.stereotype.Repository;

import com.googlecode.menugen.dao.RecipeDao;
import com.googlecode.menugen.domain.Recipe;

/**
 * Perform CRUD operations on {@link Recipe}
 */
@Repository
public class RecipeDaoImpl extends DataAccessObjectImpl<Recipe, Long> implements
		RecipeDao {

}
