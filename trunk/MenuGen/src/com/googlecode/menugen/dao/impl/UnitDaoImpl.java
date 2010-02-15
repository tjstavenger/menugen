/**
 * 
 */
package com.googlecode.menugen.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.googlecode.menugen.dao.UnitDao;
import com.googlecode.menugen.domain.Unit;

/**
 * Perform CRUD operations on {@link Unit}
 */
@Repository
public class UnitDaoImpl extends DataAccessObjectImpl<Unit, Long> implements
		UnitDao {

	/**
	 * Get all {@link Unit} in ascending order by abbreviation.
	 * 
	 * @return List of {@link Unit}
	 * 
	 * @see com.googlecode.menugen.dao.impl.DataAccessObjectImpl#findAll()
	 */
	@Override
	public List<Unit> findAll() {
		Criteria criteria = createCriteria();
		criteria.addOrder(Order.asc(Unit.ABBREVIATION));
		List<Unit> units = criteria.list();

		return units;
	}
}
