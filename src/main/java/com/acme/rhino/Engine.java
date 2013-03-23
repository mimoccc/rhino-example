package com.acme.rhino;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public interface Engine {

	public void evaluate(String script);

	public Context getContext();

	public Scriptable getScope();

	public void exit();

}