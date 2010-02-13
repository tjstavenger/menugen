/**
 * 
 */
package com.googlecode.menugen.dao.impl;

import org.springframework.stereotype.Repository;

import com.googlecode.menugen.dao.MeasuredIngredientDao;
import com.googlecode.menugen.domain.MeasuredIngredient;

/**
 * Perform CRUD operations on {@link MeasuredIngredient}
 */
@Repository
public class MeasuredIngredientDaoImpl extends
		DataAccessObjectImpl<MeasuredIngredient, Long> implements
		MeasuredIngredientDao {

}
