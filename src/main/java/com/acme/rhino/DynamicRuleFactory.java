package com.acme.rhino;

import java.lang.reflect.InvocationTargetException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

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

}
