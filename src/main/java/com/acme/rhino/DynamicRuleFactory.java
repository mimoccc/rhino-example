package com.acme.rhino;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class DynamicRuleFactory {

	public static DynamicRule build(final String script) {

		final Context context = Context.enter();
		try {
			final ScriptableObject scope = context.initStandardObjects();
			ScriptableObject.defineClass(scope, Target.class, false, true);
			context.evaluateString(scope, script, "script", 1, null);
			return new DynamicRule(context, scope);

		} catch (final IllegalAccessException e) {
			throw new IllegalArgumentException("Can't evaluate Script.", e);
		} catch (final InstantiationException e) {
			throw new IllegalArgumentException("Can't evaluate Script.", e);
		} catch (final InvocationTargetException e) {
			throw new IllegalArgumentException("Can't evaluate Script.", e);
		} finally {
			Context.exit();
		}

	}

	public static DynamicRule build(final File file) throws IOException {
		return build(Files.toString(file, Charsets.UTF_8));
	}

}
