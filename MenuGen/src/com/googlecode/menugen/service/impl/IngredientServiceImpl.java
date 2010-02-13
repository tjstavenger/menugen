/**
 * 
 */
package com.googlecode.menugen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.menugen.dao.IngredientDao;
import com.googlecode.menugen.domain.Ingredient;
import com.googlecode.menugen.service.IngredientService;

/**
 * @author tstavenger
 * 
 */
@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientDao ingredientDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.menugen.service.IngredientService#create(com.googlecode
	 * .menugen.domain.Ingredient)
	 */
	@Override
	@Transactional
	public Ingredient create(Ingredient ingredient) {
		return ingredientDao.saveOrUpdate(ingredient);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.menugen.service.IngredientService#delete(com.googlecode
	 * .menugen.domain.Ingredient)
	 */
	@Override
	@Transactional
	public void delete(Ingredient ingredient) {
		ingredientDao.delete(ingredient);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.googlecode.menugen.service.IngredientService#retrieve()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Ingredient> retrieve() {
		return ingredientDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.menugen.service.IngredientService#update(com.googlecode
	 * .menugen.domain.Ingredient)
	 */
	@Override
	@Transactional
	public Ingredient update(Ingredient ingredient) {
		return ingredientDao.saveOrUpdate(ingredient);
	}
}