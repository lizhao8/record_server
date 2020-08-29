/**
  * Copyright 2020 bejson.com 
  */
package com.bilibili.entity.inner.roomPlay;

import java.util.List;

import lombok.Data;

@Data
public class Play_url {

	private int current_quality;
	private List<Integer> accept_quality;
	private int current_qn;
	private List<Quality_description> quality_description;
	private List<Durl> durl;
}