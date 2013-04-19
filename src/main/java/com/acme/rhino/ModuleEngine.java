package com.acme.rhino;

import java.net.URI;
import java.util.List;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.commonjs.module.ModuleScriptProvider;
import org.mozilla.javascript.commonjs.module.RequireBuilder;
import org.mozilla.javascript.commonjs.module.provider.ModuleSourceProvider;
import org.mozilla.javascript.commonjs.module.provider.SoftCachingModuleScriptProvider;
import org.mozilla.javascript.commonjs.module.provider.UrlModuleSourceProvider;

public class ModuleEngine implements Engine {

	private final Context context;
	private final Scriptable topLevelScope;
	private final RequireBuilder builder;

	public ModuleEngine(final List<URI> modulePaths) {
		context = Context.enter();
		topLevelScope = context.initStandardObjects();

		final ModuleSourceProvider sourceProvider = new UrlModuleSourceProvider(
				modulePaths, null);
		final ModuleScriptProvider scriptProvider = new SoftCachingModuleScriptProvider(
				sourceProvider);
		builder = new RequireBuilder();
		builder.setModuleScriptProvider(scriptProvider);
	}

	@Override
	public void evaluate(final String script) {
		builder.createRequire(context, topLevelScope).install(topLevelScope);
		context.evaluateString(topLevelScope, script, "script", 1, null);
	}

	@Override
	public Context getContext() {
		return context;
	}

	@Override
	public Scriptable getScope() {
		return topLevelScope;
	}

	@Override
	public void exit() {
		Context.exit();
	}

}
