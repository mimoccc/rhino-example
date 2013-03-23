package com.acme.rhino;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;

import com.google.common.collect.Maps;

public class DynamicRuleTest extends AbstractTest {

	@Test
	public void noRequiredValues() throws IOException {
		final DynamicRule rule = DynamicRuleFactory
				.build(getFile("rule.values.no-required-values.js"));
		final String[] requiredModels = rule.getRequiredValues();
		assertArrayEquals(requiredModels, new String[] {});
	}

	@Test
	public void withRequiredValues() throws IOException {
		final DynamicRule rule = DynamicRuleFactory
				.build(getFile("rule.values.with-required-values.js"));
		final String[] requiredModels = rule.getRequiredValues();
		assertArrayEquals(requiredModels, new String[] { "foo", "bar" });
	}

	@Test
	public void evaluateDynamicResult() throws IOException {
		final DynamicRule rule = DynamicRuleFactory
				.build(getFile("rule.evaluate.dynamic-result.js"));

		final HashMap<String, String> valueMap = Maps
				.<String, String> newHashMap();
		valueMap.put("foo", "true");
		valueMap.put("bar", "false");
		assertEquals(1, rule.build(valueMap).getValue());

		valueMap.put("foo", "false");
		valueMap.put("bar", "true");
		assertEquals(2, rule.build(valueMap).getValue());

		valueMap.put("foo", "false");
		valueMap.put("bar", "false");
		assertEquals(3, rule.build(valueMap).getValue());
	}

	@Test
	public void evaluateWithInnerFunction() throws IOException {
		final DynamicRule rule = DynamicRuleFactory
				.build(getFile("rule.evaluate.inner-function.js"));

		final HashMap<String, String> valueMap = Maps
				.<String, String> newHashMap();
		valueMap.put("foo", "true");
		valueMap.put("bar", "false");
		assertEquals(1, rule.build(valueMap).getValue());

		valueMap.put("foo", "false");
		valueMap.put("bar", "true");
		assertEquals(2, rule.build(valueMap).getValue());

		valueMap.put("foo", "false");
		valueMap.put("bar", "false");
		assertEquals(3, rule.build(valueMap).getValue());
	}

}
