/**
 * 
 */
package com.googlecode.menugen.domain.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.googlecode.menugen.domain.Unit;
import com.googlecode.menugen.service.UnitService;
import com.googlecode.menugen.test.MenuGenTestCase;

/**
 * Load {@link Unit} data
 */
@TransactionConfiguration(defaultRollback = false)
public class UnitTest extends MenuGenTestCase {

	@Autowired
	private UnitService unitService;

	/**
	 * Create the default units
	 */
	@Test
	public void createData() {
		addUnit("cup");
		addUnit("cups");
		addUnit("dash");
		addUnit("pinch");
		addUnit("tsp");
		addUnit("tbs");
	}

	/**
	 * Add a new unit with the given abbreviation and name.
	 * 
	 * @param abbreviation
	 *            String abbreviation
	 */
	private void addUnit(String abbreviation) {
		Unit unit = new Unit();
		unit.setAbbreviation(abbreviation);
		unit.setId(null);
		unitService.create(unit);
	}
}
