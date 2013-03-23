package com.acme.rhino;

import java.io.IOException;

import org.junit.Test;
import org.mozilla.javascript.EvaluatorException;

public class DynamicRuleFactoryTest extends AbstractTest {

	@Test(expected = NullPointerException.class)
	public void nonExistingJavaScriptFile() throws IOException {
		DynamicRuleFactory.build(getFile("non-existing.js"));
	}

	@Test(expected = ClassCastException.class)
	public void emptyJavaScriptFile() throws IOException {
		DynamicRuleFactory.build(getFile("rule.empty-file.js"))
				.getRequiredValues();
	}

	@Test(expected = EvaluatorException.class)
	public void malformedJavaScriptFile() throws IOException {
		DynamicRuleFactory.build(getFile("rule.malformed.js"));
	}

}
