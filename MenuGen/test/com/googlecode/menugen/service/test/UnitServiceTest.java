package com.googlecode.menugen.service.test;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.menugen.domain.Unit;
import com.googlecode.menugen.service.UnitService;
import com.googlecode.menugen.test.MenuGenTestCase;

public class UnitServiceTest extends MenuGenTestCase {

	@Autowired
	private UnitService unitService;

	@Test
	public void testRetrieve() {
		List<Unit> units = unitService.retrieve();

		assertFalse("Should have units", units.isEmpty());
	}
}
