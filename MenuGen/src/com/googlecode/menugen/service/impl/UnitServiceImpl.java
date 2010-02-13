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
 * @author tstavenger
 * 
 */
@Service
public class UnitServiceImpl implements UnitService {
	@Autowired
	private UnitDao unitDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.menugen.service.UnitService#create(com.googlecode.menugen
	 * .domain.Unit)
	 */
	@Override
	@Transactional
	public Unit create(Unit unit) {
		return unitDao.saveOrUpdate(unit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.menugen.service.UnitService#delete(com.googlecode.menugen
	 * .domain.Unit)
	 */
	@Override
	@Transactional
	public void delete(Unit unit) {
		unitDao.delete(unit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.googlecode.menugen.service.UnitService#retrieve()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Unit> retrieve() {
		return unitDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.menugen.service.UnitService#update(com.googlecode.menugen
	 * .domain.Unit)
	 */
	@Override
	@Transactional
	public Unit update(Unit unit) {
		return unitDao.saveOrUpdate(unit);
	}
}