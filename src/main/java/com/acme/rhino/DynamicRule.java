package com.acme.rhino;

import java.util.Map;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

import com.acme.rhino.util.ScriptableMap;

public class DynamicRule {

	private final Scriptable scope;
	private final Context context;

	protected DynamicRule(final Engine simpleEngine) {
		scope = simpleEngine.getScope();
		context = simpleEngine.getContext();
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

	public static DynamicRule parse(final Engine simpleEngine,
			final String script) {
		simpleEngine.evaluate(script);
		return new DynamicRule(simpleEngine);
	}
}
