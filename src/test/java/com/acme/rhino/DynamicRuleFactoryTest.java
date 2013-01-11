package com.acme.rhino;

import java.io.IOException;

import org.junit.Test;
import org.mozilla.javascript.EvaluatorException;

public class DynamicRuleFactoryTest extends AbstractTest {

	private static final String NON_EXISTING_FILE = "/non-existing.js";

	private static final String EMPTY_FILE = "/rule.empty-file.js";
	private static final String MALFORMED = "/rule.malformed.js";

	@Test(expected = NullPointerException.class)
	public void nonExistingJavaScriptFile() throws IOException {
		DynamicRuleFactory.build(getFile(NON_EXISTING_FILE));
	}

	@Test(expected = ClassCastException.class)
	public void emptyJavaScriptFile() throws IOException {
		DynamicRuleFactory.build(getFile(EMPTY_FILE)).getRequiredValues();
	}

	@Test(expected = EvaluatorException.class)
	public void malformedJavaScriptFile() throws IOException {
		DynamicRuleFactory.build(getFile(MALFORMED));
	}

}
