/**
 * 
 */
package com.googlecode.menugen.dao.impl;

import org.springframework.stereotype.Repository;

import com.googlecode.menugen.dao.IngredientDao;
import com.googlecode.menugen.domain.Ingredient;

/**
 * Perform CRUD operations on {@link Ingredient}
 */
@Repository
public class IngredientDaoImpl extends DataAccessObjectImpl<Ingredient, Long>
		implements IngredientDao {
}
