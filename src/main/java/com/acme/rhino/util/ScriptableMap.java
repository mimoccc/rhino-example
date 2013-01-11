package com.acme.rhino.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.mozilla.javascript.Scriptable;

public class ScriptableMap<V> implements Scriptable, Map<String, V> {
	public final Map<String, V> backingMap;

	public ScriptableMap(final Map<String, V> map) {
		this.backingMap = map;
	}

	@Override
	public void clear() {
		backingMap.clear();
	}

	@Override
	public boolean containsKey(final Object key) {
		return backingMap.containsKey(key);
	}

	@Override
	public boolean containsValue(final Object value) {
		return backingMap.containsValue(value);
	}

	@Override
	public boolean equals(final Object o) {
		return backingMap.equals(o);
	}

	@Override
	public int hashCode() {
		return backingMap.hashCode();
	}

	@Override
	public boolean isEmpty() {
		return backingMap.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return backingMap.keySet();
	}

	@Override
	public int size() {
		return backingMap.size();
	}

	@Override
	public Collection<V> values() {
		return backingMap.values();
	}

	@Override
	public void delete(final String name) {
		backingMap.remove(name);
	}

	@Override
	public void delete(final int index) {
		backingMap.remove(index);
	}

	@Override
	public Object get(final String name, final Scriptable start) {
		return backingMap.get(name);
	}

	@Override
	public Object get(final int index, final Scriptable start) {
		return backingMap.get(index);
	}

	@Override
	public String getClassName() {
		return backingMap.getClass().getName();
	}

	@Override
	public Object getDefaultValue(final Class<?> hint) {
		return toString();
	}

	@Override
	public Object[] getIds() {
		final Object[] res = new Object[backingMap.size()];
		int i = 0;
		for (final Object k : backingMap.keySet()) {
			res[i] = k;
			i++;
		}
		return res;
	}

	@Override
	public Scriptable getParentScope() {
		return null;
	}

	@Override
	public Scriptable getPrototype() {
		return null;
	}

	@Override
	public boolean has(final String name, final Scriptable start) {
		return backingMap.containsKey(name);
	}

	@Override
	public boolean has(final int index, final Scriptable start) {
		return backingMap.containsKey(index);
	}

	@Override
	public boolean hasInstance(final Scriptable instance) {
		return false;
	}

	@Override
	public void setParentScope(final Scriptable parent) {
	}

	@Override
	public void setPrototype(final Scriptable prototype) {
	}

	@Override
	public Set<java.util.Map.Entry<String, V>> entrySet() {
		return backingMap.entrySet();
	}

	@Override
	public V get(final Object key) {
		return backingMap.get(key);
	}

	@Override
	public V remove(final Object key) {
		return backingMap.remove(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void put(final String key, final Scriptable start, final Object value) {
		backingMap.put(key, (V) value);
	}

	@Override
	public void put(final int index, final Scriptable start, final Object value) {
		put(Integer.toString(index), start, value);
	}

	@Override
	public V put(final String key, final V value) {
		return backingMap.put(key, value);
	}

	@Override
	public void putAll(final Map<? extends String, ? extends V> m) {
		backingMap.putAll(m);
	}
}