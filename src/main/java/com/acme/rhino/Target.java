package com.acme.rhino;

import java.util.LinkedList;
import java.util.List;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

public class Target extends ScriptableObject {

	private static final long serialVersionUID = 1L;

	public List<Modifier> modifiers = new LinkedList<>();

	public Target() {
	}

	public Target(final String message, final int value) {
		addModifier(message, value);
	}

	@JSFunction
	public void addModifier(final String message, final int value) {
		modifiers.add(new Modifier(message, value));
	}

	public List<Modifier> getModifiers() {
		return modifiers;
	}

	public int getValue() {
		int sum = 0;
		for (final Modifier modifier : modifiers) {
			sum += modifier.getValue();
		}
		return sum;
	}

	@Override
	public String getClassName() {
		return "Target";
	}

}
