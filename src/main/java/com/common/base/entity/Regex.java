package com.common.base.entity;

public class Regex {
	private String base;
	private String regex;

	private Regex(String base, RegexEnum regexEnum) {
		this.setBase(base, regexEnum);
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base, RegexEnum regexEnum) {
		this.base = base;
		this.regex = regexEnum.getRegex(base);
	}

	public String getRegex() {
		return regex;
	}

	public static Regex getRegex(String base, RegexEnum regexEnum) {
		return new Regex(base, regexEnum);
	}

}
