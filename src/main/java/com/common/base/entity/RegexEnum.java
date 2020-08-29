package com.common.base.entity;

public enum RegexEnum {
	// "^.*"+value+".*$"
	START("^", ".*$"), END("^.*", "$"), CONTAIN("^.*", ".*$");

	private String start;
	private String end;

	private RegexEnum(String start, String end) {
		this.start = start;
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public String getRegex(String base) {
		return start+base+end;
	}

}
