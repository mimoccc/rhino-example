package com.acme.rhino;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;

import com.google.common.collect.Maps;

public class DynamicRuleTest extends AbstractTest {

	private static final String NO_REQUIRED_VALUES = "/rule.values.no-required-values.js";
	private static final String WITH_REQUIRED_VALUES = "/rule.values.with-required-values.js";

	private static final String STATIC_RESULT = "/rule.evaluate.static-result.js";
	private static final String DYNAMIC_RESULT = "/rule.evaluate.dynamic-result.js";

	@Test
	public void noRequiredValues() throws IOException {
		final DynamicRule rule = DynamicRuleFactory
				.build(getFile(NO_REQUIRED_VALUES));
		final String[] requiredModels = rule.getRequiredValues();
		assertArrayEquals(requiredModels, new String[] {});
	}

	@Test
	public void withRequiredValues() throws IOException {
		final DynamicRule rule = DynamicRuleFactory
				.build(getFile(WITH_REQUIRED_VALUES));
		final String[] requiredModels = rule.getRequiredValues();
		assertArrayEquals(requiredModels, new String[] { "foo", "bar" });
	}

	@Test
	public void evaluateStaticResult() throws IOException {
		final DynamicRule rule = DynamicRuleFactory
				.build(getFile(STATIC_RESULT));
		final Result result = rule.evaluate(Maps.<String, String> newHashMap());
		assertEquals("static hello", result.getMessage());
		assertEquals(1, result.getValue());
	}

	@Test
	public void evaluateDynamicResult() throws IOException {
		final DynamicRule rule = DynamicRuleFactory
				.build(getFile(DYNAMIC_RESULT));

		final HashMap<String, String> valueMap = Maps.<String, String> newHashMap();
		valueMap.put("foo", "true");
		valueMap.put("bar", "false");
		assertEquals(1, rule.evaluate(valueMap).getValue());

		valueMap.put("foo", "false");
		valueMap.put("bar", "true");
		assertEquals(2, rule.evaluate(valueMap).getValue());

		valueMap.put("foo", "false");
		valueMap.put("bar", "false");
		assertEquals(0, rule.evaluate(valueMap).getValue());
	}

}
