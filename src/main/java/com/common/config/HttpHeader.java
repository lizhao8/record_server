package com.common.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import lombok.Data;

@Data
public class HttpHeader {
	private String userAgent;
	private String accept;

	public List<Header> toList() {
		List<Header> list = new ArrayList<Header>();
		list.add(new BasicHeader("User-Agent", userAgent));
		list.add(new BasicHeader("Accept", accept));
		return list;
	}
}
