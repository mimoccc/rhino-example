package com.acme.rhino;

public class Modifier {

	private final String message;
	private final int value;

	public Modifier(final String message, final int value) {
		super();
		this.message = message;
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public int getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Modifier other = (Modifier) obj;
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		if (value != other.value) {
			return false;
		}
		return true;
	}

}
