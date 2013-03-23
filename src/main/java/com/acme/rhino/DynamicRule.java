package com.acme.rhino;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ScriptableObject;

import com.acme.rhino.util.ScriptableMap;

public class DynamicRule {

	private final ScriptableObject scope;
	private final Context context;

	protected DynamicRule(final Context context, final ScriptableObject scope) {
		this.context = context;
		this.scope = scope;
	}

	public String[] getRequiredValues() {
		final Function getRequiredValues = (Function) scope.get(
				"getRequiredValues", scope);

		return (String[]) Context.jsToJava(
				getRequiredValues.call(context, scope, scope, new Object[] {}),
				String[].class);

	}

	public Target build(final Map<String, String> values) {
		final Function buildFunction = (Function) scope.get("build", scope);
		return (Target) Context.jsToJava(buildFunction.call(context, scope,
				scope, new Object[] { new ScriptableMap<>(values) }),
				Target.class);
	}

	public static DynamicRule parse(final String script) {

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
