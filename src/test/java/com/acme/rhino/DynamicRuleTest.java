package com.acme.rhino;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mozilla.javascript.EvaluatorException;

public class DynamicRuleTest extends AbstractTest {

	@Before
	public void setUp() {
		engine = new SimpleEngine();
		p = new HashMap<>();
	}

	@After
	public void tearDown() {
		engine.exit();
	}

	@Test(expected = NullPointerException.class)
	public void nonExistingJavaScriptFile() throws IOException {
		parse("non-existing.js");
	}

	@Test(expected = ClassCastException.class)
	public void emptyJavaScriptFile() throws IOException {
		parse("rule.empty-file.js").getRequiredValues();
	}

	@Test(expected = EvaluatorException.class)
	public void malformedJavaScriptFile() throws IOException {
		parse("rule.malformed.js");
	}

	@Test
	public void noRequiredValues() throws IOException {
		assertArrayEquals(new String[] {},
				parse("rule.values.no-required-values.js").getRequiredValues());
	}

	@Test
	public void withRequiredValues() throws IOException {
		assertArrayEquals(new String[] { "foo", "bar" },
				parse("rule.values.with-required-values.js")
						.getRequiredValues());
	}

	@Test
	public void evaluateDynamicResult() throws IOException {
		final DynamicRule rule = parse("rule.evaluate.dynamic-result.js");

		p.put("foo", "true");
		p.put("bar", "false");
		assertEquals(1, rule.build(p).getValue());

		p.put("foo", "false");
		p.put("bar", "true");
		assertEquals(2, rule.build(p).getValue());

		p.put("foo", "false");
		p.put("bar", "false");
		assertEquals(3, rule.build(p).getValue());
	}

	@Test
	public void evaluateWithInnerFunction() throws IOException {
		final DynamicRule rule = parse("rule.evaluate.inner-function.js");

		p.put("foo", "true");
		p.put("bar", "false");
		assertEquals(1, rule.build(p).getValue());

		p.put("foo", "false");
		p.put("bar", "true");
		assertEquals(2, rule.build(p).getValue());

		p.put("foo", "false");
		p.put("bar", "false");
		assertEquals(3, rule.build(p).getValue());
	}

}
