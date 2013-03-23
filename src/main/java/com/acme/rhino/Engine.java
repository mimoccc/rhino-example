package com.acme.rhino;

import java.lang.reflect.InvocationTargetException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

public class Engine {

	private final Context context;
	private final ScriptableObject scope;

	public Engine() {
		context = Context.enter();

		try {
			scope = context.initStandardObjects();
			ScriptableObject.defineClass(scope, Target.class, false, true);

		} catch (final IllegalAccessException e) {
			throw new IllegalArgumentException("Can't define scope.", e);
		} catch (final InstantiationException e) {
			throw new IllegalArgumentException("Can't define scope.", e);
		} catch (final InvocationTargetException e) {
			throw new IllegalArgumentException("Can't define scope.", e);
		}
	}

	public void evaluate(final String script) {
		context.evaluateString(scope, script, "script", 1, null);
	}

	public Context getContext() {
		return context;
	}

	public ScriptableObject getScope() {
		return scope;
	}

	public void exit() {
		Context.exit();
	}

}
