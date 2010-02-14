/**
 * 
 */
package com.googlecode.menugen.domain.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.menugen.domain.Unit;
import com.googlecode.menugen.service.UnitService;
import com.googlecode.menugen.test.MenuGenTestCase;

/**
 * Load {@link Unit} data
 */
public class UnitTest extends MenuGenTestCase {

	@Autowired
	private UnitService unitService;

	/**
	 * Create the default units
	 */
	@Test
	public void createData() {
		addUnit("cup", "cup");
		addUnit("cups", "cups");
		addUnit("dash", "dash");
		addUnit("pinch", "pinch");
		addUnit("tsp", "teaspoons");
		addUnit("tbs", "tablespoons");
	}

	/**
	 * Add a new unit with the given abbreviation and name.
	 * 
	 * @param abbreviation
	 *            String abbreviation
	 * @param name
	 *            String full name
	 */
	private void addUnit(String abbreviation, String name) {
		Unit unit = new Unit();
		unit.setAbbreviation(abbreviation);
		unit.setId(null);
		unit.setName(name);
		unitService.create(unit);
	}
}
