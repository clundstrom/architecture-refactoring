package model;

public class MutableBoolean {

	private boolean value;

	public MutableBoolean() {}

	public boolean booleanValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
}
