package com.acme.rhino;

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
		return (Target) Context.jsToJava(buildFunction.call(context, scope, scope,
				new Object[] { new ScriptableMap<>(values) }), Target.class);
	}
}
