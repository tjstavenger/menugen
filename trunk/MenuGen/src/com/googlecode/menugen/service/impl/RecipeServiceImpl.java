/**
 * 
 */
package com.googlecode.menugen.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.menugen.dao.RecipeDao;
import com.googlecode.menugen.domain.Recipe;
import com.googlecode.menugen.service.RecipeService;

/**
 * @author tstavenger
 * 
 */
@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeDao recipeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.menugen.service.RecipeService#create(com.googlecode.menugen
	 * .domain.Recipe)
	 */
	@Override
	@Transactional
	public Recipe create(Recipe recipe) {
		return recipeDao.saveOrUpdate(recipe);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.menugen.service.RecipeService#delete(com.googlecode.menugen
	 * .domain.Recipe)
	 */
	@Override
	@Transactional
	public void delete(Recipe recipe) {
		recipeDao.delete(recipe);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.menugen.service.RecipeService#retrieve(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public Recipe retrieve(Long id) {
		return recipeDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.menugen.service.RecipeService#search(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Recipe> search(String criteria) {
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.menugen.service.RecipeService#update(com.googlecode.menugen
	 * .domain.Recipe)
	 */
	@Override
	@Transactional
	public Recipe update(Recipe recipe) {
		return recipeDao.saveOrUpdate(recipe);
	}
}