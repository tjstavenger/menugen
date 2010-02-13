/**
 * 
 */
package com.googlecode.menugen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.menugen.dao.UnitDao;
import com.googlecode.menugen.domain.Unit;
import com.googlecode.menugen.service.UnitService;

/**
 * Perform CRUD services on {@link Unit}
 */
@Service
public class UnitServiceImpl implements UnitService {
	@Autowired
	private UnitDao unitDao;

	/**
	 * Create a new {@link Unit}
	 * 
	 * @param unit
	 *            {@link Unit} to create
	 * @return new {@link Unit}
	 * 
	 * @see com.googlecode.menugen.service.UnitService#create(com.googlecode.menugen
	 *      .domain.Unit)
	 */
	@Override
	@Transactional
	public Unit create(Unit unit) {
		return unitDao.saveOrUpdate(unit);
	}

	/**
	 * Delete an existing {@link Unit}
	 * 
	 * @param unit
	 *            existing {@link Unit} to delete
	 * 
	 * @see com.googlecode.menugen.service.UnitService#delete(com.googlecode.menugen
	 *      .domain.Unit)
	 */
	@Override
	@Transactional
	public void delete(Unit unit) {
		unitDao.delete(unit);
	}

	/**
	 * Retrieve all {@link Unit} in alphabetical order.
	 * 
	 * @return List of {@link Unit}
	 * 
	 * @see com.googlecode.menugen.service.UnitService#retrieve()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Unit> retrieve() {
		return unitDao.findAll();
	}

	/**
	 * Update an existing {@link Unit}
	 * 
	 * @param unit
	 *            existing {@link Unit} to update
	 * @return updated {@link Unit}
	 * 
	 * @see com.googlecode.menugen.service.UnitService#update(com.googlecode.menugen
	 *      .domain.Unit)
	 */
	@Override
	@Transactional
	public Unit update(Unit unit) {
		return unitDao.saveOrUpdate(unit);
	}
}