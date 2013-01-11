package com.acme.rhino;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSConstructor;
import org.mozilla.javascript.annotations.JSGetter;

public class Result extends ScriptableObject {

	private static final long serialVersionUID = 1L;

	private final String message;
	private final int value;

	@JSConstructor
	public Result(final String message, final int value) {
		super();
		this.message = message;
		this.value = value;
	}

	@JSGetter
	public String getMessage() {
		return message;
	}

	@JSGetter
	public int getValue() {
		return value;
	}

	@Override
	public String getClassName() {
		return "Result";
	}

}
