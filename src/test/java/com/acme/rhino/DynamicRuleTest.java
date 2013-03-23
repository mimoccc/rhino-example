package com.acme.rhino;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class DynamicRuleTest extends AbstractTest {

	@Test
	public void noRequiredValues() throws IOException {
		assertArrayEquals(
				new String[] {},
				DynamicRuleFactory.build(
						read("rule.values.no-required-values.js"))
						.getRequiredValues());
	}

	@Test
	public void withRequiredValues() throws IOException {
		assertArrayEquals(new String[] { "foo", "bar" }, DynamicRuleFactory
				.build(read("rule.values.with-required-values.js"))
				.getRequiredValues());
	}

	@Test
	public void evaluateDynamicResult() throws IOException {
		final DynamicRule rule = DynamicRuleFactory
				.build(read("rule.evaluate.dynamic-result.js"));

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
		final DynamicRule rule = DynamicRuleFactory
				.build(read("rule.evaluate.inner-function.js"));

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
