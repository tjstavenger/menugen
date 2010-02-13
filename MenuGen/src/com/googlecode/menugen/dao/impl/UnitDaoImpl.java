/**
 * 
 */
package com.googlecode.menugen.dao.impl;

import org.springframework.stereotype.Repository;

import com.googlecode.menugen.dao.UnitDao;
import com.googlecode.menugen.domain.Unit;

/**
 * Perform CRUD operations on {@link Unit}
 */
@Repository
public class UnitDaoImpl extends DataAccessObjectImpl<Unit, Long> implements
		UnitDao {

}
