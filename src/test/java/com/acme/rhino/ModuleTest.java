package com.acme.rhino;

import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModuleTest extends AbstractTest {

	@Before
	public void setUp() {
		engine = new ModuleEngine(Arrays.asList(toURI("/module/")));
		p = new HashMap<>();
	}

	@After
	public void tearDown() {
		engine.exit();
	}

	@Test
	public void noRequiredValues() throws IOException {
		assertArrayEquals(new String[] {"5", "6"},
				parse("module/acme.js").getRequiredValues());
	}

}
