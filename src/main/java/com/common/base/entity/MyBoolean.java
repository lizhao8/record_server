package com.common.base.entity;

public class MyBoolean implements java.io.Serializable,
		Comparable<MyBoolean> {
	public static final MyBoolean TRUE = new MyBoolean(true);

	public static final MyBoolean FALSE = new MyBoolean(false);

	public static final Class<MyBoolean> TYPE = MyBoolean.class;

	private boolean value;

	private static final long serialVersionUID = -3665804199014368530L;

	public MyBoolean(boolean value) {
		this.value = value;
	}

	public MyBoolean(String s) {
		this(toBoolean(s));
	}

	public static boolean parseBoolean(String s) {
		return toBoolean(s);
	}

	public boolean booleanValue() {
		return value;
	}
	public void setValue(boolean bool) {
		this.value=bool;
	}
	public static MyBoolean valueOf(boolean b) {
		return (b ? TRUE : FALSE);
	}

	public static MyBoolean valueOf(String s) {
		return toBoolean(s) ? TRUE : FALSE;
	}

	public static String toString(boolean b) {
		return b ? "true" : "false";
	}

	public String toString() {
		return value ? "true" : "false";
	}

	public int hashCode() {
		return value ? 1231 : 1237;
	}

	public boolean equals(Object obj) {
		if (obj instanceof MyBoolean) {
			return value == ((MyBoolean) obj).booleanValue();
		}
		return false;
	}

	public static boolean getBoolean(String name) {
		boolean result = false;
		try {
			result = toBoolean(System.getProperty(name));
		} catch (IllegalArgumentException e) {
		} catch (NullPointerException e) {
		}
		return result;
	}

	public int compareTo(MyBoolean b) {
		return compare(this.value, b.value);
	}

	public static int compare(boolean x, boolean y) {
		return (x == y) ? 0 : (x ? 1 : -1);
	}

	private static boolean toBoolean(String name) {
		return ((name != null) && name.equalsIgnoreCase("true"));
	}
}
