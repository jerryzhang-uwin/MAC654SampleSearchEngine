package com.uwin.engine.core;

import java.util.Map.Entry;

/**
 * Customized implementation of Entry interface.
 * 
 * @author 
 *
 * @param <K>
 * @param <V>
 */
public class KeyValuePair<K extends Comparable<? super K>, V> 
		implements Entry<K, V>, Comparable<KeyValuePair<K, V>> {
	
	private final K key;
	private V value = null;
	
	public KeyValuePair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return this.key;
	}

	@Override
	public V getValue() {
		return this.value;
	}

	@Override
	public V setValue(V value) {
		V oldValue = this.value;
		this.value = value;
		return oldValue;
	}

	@Override
	public int compareTo(KeyValuePair<K, V> o) {
		return this.key.compareTo(o.key);
	}
	
	@Override
	public String toString() {
		return this.key.toString() + "   " + this.value.toString();
	}
	

}
