package com.acme.rhino;

import java.io.File;
import java.io.IOException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class DynamicRuleFactory {

	public static DynamicRule build(final String script) {

		final Context context = Context.enter();
		try {
			final ScriptableObject scope = context.initStandardObjects();
			context.evaluateString(scope, script, "script", 1, null);
			return new DynamicRule(context, scope);

		} finally {
			Context.exit();
		}

	}

	public static DynamicRule build(final File file) throws IOException {
		return build(Files.toString(file, Charsets.UTF_8));
	}

}
